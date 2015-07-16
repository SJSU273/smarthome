package LWM2MBootstrapServer;

import LWM2MBootstrapServer.repository.LWM2MDevice;
import LWM2MBootstrapServer.repository.LWM2MDeviceRepository;
import LWM2MBootstrapServer.services.PrintMenu;
import LWM2MBootstrapServer.services.ShowBootstrapDevices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tv.repository.LWM2MServerObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Scott on 6/26/15.
 */
@SpringBootApplication
public class SmartHomeBootstrapApp implements CommandLineRunner {

    @Autowired
    LWM2MDeviceRepository deviceRepository;

    public static void main(String[] args) {
        SpringApplication.run(SmartHomeBootstrapApp.class, args);
    }

    @Override
    public void run(String... args) {

        String Manufacturer = "SONY";
        String ModelNumber = "UN65JS9500FXZA";
        String SerialNumber = "ZW153MRC300023V";
        String EPN = "urn:dev:ops:" + Manufacturer + "-" + ModelNumber + "-" + SerialNumber;
        String serverUri = "http://localhost:8081/register/tv";

        System.out.println("delete all db");
        deviceRepository.deleteAll();

        System.out.println("Initialize device db");
        LWM2MServerObject serverObject = new LWM2MServerObject(101, 86400, 300, 6000, 86400, true, "U");
        LWM2MDevice device = new LWM2MDevice(EPN, Manufacturer, ModelNumber, SerialNumber, serverUri,serverObject);
        deviceRepository.save(device);

        System.out.printf("find by id: %s\n", EPN);
        System.out.println(deviceRepository.findById(EPN));


        while (true) {

            PrintMenu.menu();

            try{
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                String s = bufferRead.readLine();


                switch (s) {

                    case "1":
                    case "1.1":
                        deviceRepository.deleteAll();
                        System.out.println("All the registered devices have been removed from database.");

                        break;

                    case "1.2":
                        ShowBootstrapDevices showBootstrapDevices = new ShowBootstrapDevices(deviceRepository);
                        showBootstrapDevices.display();
                        break;

                    default:
                        System.out.println("Please input number(s) 1.1 1.2\n\n");

                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}
