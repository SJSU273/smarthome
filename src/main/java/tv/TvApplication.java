package tv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tv.repository.*;
import tv.services.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;


/**
 * Created by Scott on 6/13/15.
 */
@SpringBootApplication
@EnableScheduling
public class TvApplication implements CommandLineRunner{
    @Autowired
    private AccessControlObjectRepository accessControlObjectRepository;

    @Autowired
    private DeviceObjectRepository deviceObjectRepository;
    @Autowired
    private LWM2MSecurityObjectRepository lwm2MSecurityObjectRepository;
    @Autowired
    private LWM2MServerObjectRepository lwm2MServerObjectRepository;
    @Autowired
    private TVChannelObjectRepository tvChannelObjectRepository;
    @Autowired
    private TVControlObjectRepository tvControlObjectRepository;
    @Autowired
    private TVAttributeObjectRepository tvAttributeObjectRepository;
    @Autowired
    private ClientTvWatchRecordRepository clientTvWatchRecordRepository;

    @Autowired
    private ShowData showStatus;

    @Autowired
    private BootstrapToServer boot;

    @Autowired
    private RegisterToServer register;

    @Autowired
    private ReportToServer report;

    @Autowired
    private AutoChangeChannel autoChangeChannel;

    public static void main(String args[])  { SpringApplication.run(TvApplication.class, args); }

    @Override
    public void run(String... args) {

        // clean up the database
        lwm2MServerObjectRepository.deleteAll();
        accessControlObjectRepository.deleteAll();
        tvChannelObjectRepository.deleteAll();
        tvControlObjectRepository.deleteAll();
        tvAttributeObjectRepository.deleteAll();

        // initialize security object[0] and populate Database
        lwm2MSecurityObjectRepository.deleteAll();

        LWM2MSecurityObject securityObject = new LWM2MSecurityObject();
        securityObject.setBootstrapServer(true);
        securityObject.setLWM2MServerURI("http://localhost:8080/bootstrap/tv");
        lwm2MSecurityObjectRepository.save(securityObject);


        // initialize device object[0] and populate database
        deviceObjectRepository.deleteAll();

        DeviceObject device = new DeviceObject();
        device.setManufacturer("SONY");
        device.setModelNumber("UN65JS9500FXZA");
        device.setSerialNumber("ZW153MRC300023V");
        device.setFirmwareVersion("v1.10.8");
        deviceObjectRepository.save(device);

        while (true) {

            int lock = 0; //0: unlocked, 1: locked

            for(TVControlObject o: tvControlObjectRepository.findAll()) {
                lock = o.getLock();
                break;
            }

            PrintMenu.menu();


            try{
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                String s = bufferRead.readLine();

                if(lock == 1 && !"5.3".equals(s)) {
                    System.out.println("TV is locked. Please use '5.3' command to unlock.");
                    System.out.println(s);
                    continue;
                }

                switch (s) {
                    case "1":
                        boot.boot();
                        break;
                    case "2":
                    case "2.1":
                        register.register();
                        break;
                    case "2.2":
                        register.update();
                        break;
                    case "2.3":
                        register.delete();
                        break;

                    case "3":
                        System.out.println("T.B.D\n\n");
                        break;
                    case "4":
                    case "4.1":
                        report.notifyTvChannelObject("http://localhost:8081/notify/tv/channel");
                        break;

                    case "5":

                    case "5.1":
                        // change the TV Channel up
                        List<TVControlObject> l = tvControlObjectRepository.findAll();
                        if (!l.isEmpty()) {
                            TVControlObject tvControlObject = l.get(0);
                            tvControlObject.setChannelId((tvControlObject.getChannelId()+1) % 500);
                            tvControlObjectRepository.save(tvControlObject);

                            report.notifyTvChannelObject("http://localhost:8081/notify/tv/channel");

                        } else {
                            System.out.println("Error: Can't find TV Control Object. Please check if TV has registered already.");
                        }
                        break;

                    case "5.2":
                        // change the TV Channel down
                        l = tvControlObjectRepository.findAll();
                        if (!l.isEmpty()) {
                            TVControlObject tvControlObject = l.get(0);
                            tvControlObject.setChannelId((tvControlObject.getChannelId() + 500 - 1) % 500);
                            tvControlObjectRepository.save(tvControlObject);


                            report.notifyTvChannelObject("http://localhost:8081/notify/tv/channel");

                        } else {
                            System.out.println("Error: Can't find TV Control Object. Please check if TV has registered already.");
                        }
                        break;

                    case "5.3":
                        // unlock TV
                        for (TVControlObject o: tvControlObjectRepository.findAll()) {
                            o.setLock(0);
                            tvControlObjectRepository.save(o);
                        }
                        System.out.println("TV is unlocked.");
                        break;

                    case "5.4":
                        showStatus.show();
                        break;

                    case "5.5":
                        // Show Data
                        for (ClientTvWatchRecord record: clientTvWatchRecordRepository.findAll()) {
                            System.out.println(record);
                        }
                        break;

                    case "5.6":
                        //start change channel automatically
                        System.out.println("Start to change channel automatically in every 5 seconds.");
                        autoChangeChannel.setStop(false);
                        break;

                    case "5.7":
                        //stop change channel automatically
                        System.out.println("Stop to change channel automatically.");
                        autoChangeChannel.setStop(true);
                        break;

                    default:
                        System.out.println("Please input command: \n\n");

                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }


    }
}