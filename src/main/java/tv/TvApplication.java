package tv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tv.repository.*;
import tv.services.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by Scott on 6/13/15.
 */
@SpringBootApplication
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

    public static void main(String args[])  { SpringApplication.run(TvApplication.class, args); }

    @Override
    public void run(String... args) {

        // initialize security object[0] and populate Database
        LWM2MSecurityObject securityObject = new LWM2MSecurityObject();
        securityObject.setBootstrapServer(true);
        securityObject.setLWM2MServerURI("http://localhost:8080/bootstrap/tv");
        lwm2MSecurityObjectRepository.deleteAll();
        lwm2MSecurityObjectRepository.save(securityObject);

        // initialize server object[0] and populate database
        LWM2MServerObject serverObject = new LWM2MServerObject();
        lwm2MServerObjectRepository.deleteAll();
        lwm2MServerObjectRepository.save(serverObject);

        // initialize device object[0] and populate database
        DeviceObject device = new DeviceObject();
        device.setManufacturer("SONY");
        device.setModelNumber("UN65JS9500FXZA");
        device.setSerialNumber("ZW153MRC300023V");
        device.setFirmwareVersion("v1.10.8");
        deviceObjectRepository.deleteAll();
        deviceObjectRepository.save(device);

        // initialize accessControl object[0] and populate database
        AccessControlObject controlObject = new AccessControlObject();
        accessControlObjectRepository.deleteAll();
        accessControlObjectRepository.save(controlObject);


        while (true) {

            PrintMenu.menu();

            try{
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                String s = bufferRead.readLine();


                switch (s) {
                    case "1":
                        BootstrapToServer bootstrapToServer = new BootstrapToServer(securityObject, serverObject, device);
                        bootstrapToServer.boot();
                        break;
                    case "2":
                    case "2.1":
                        RegisterToServer registerRequest = new RegisterToServer(securityObject,serverObject,device);
                        registerRequest.register();
                        break;
                    case "2.2":
                        RegisterToServer updateRequest = new RegisterToServer(securityObject,serverObject,device);
                        updateRequest.update();
                        break;
                    case "2.3":
                        RegisterToServer deregisterRequest = new RegisterToServer(securityObject,serverObject,device);
                        deregisterRequest.delete();
                        break;

                    case "3":
                        System.out.println("T.B.D\n\n");
                        break;
                    case "4":
                        ReportToServer reportToServer = new ReportToServer();
                        reportToServer.sendOneReport();
                        break;

                    case "5":
                    case "5.1":
                        // change the TV Channel
                        break;

                    case "5.2":
                        ShowStatus showStatus = new ShowStatus(accessControlObjectRepository, deviceObjectRepository, lwm2MSecurityObjectRepository,lwm2MServerObjectRepository, tvChannelObjectRepository, tvControlObjectRepository);
                        showStatus.show();
                        break;

                    default:
                        System.out.println("Please input numbers 1 2.1 2.2 2.3 3 4 5\n\n");

                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }


    }
}