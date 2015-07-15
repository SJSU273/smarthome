package LWM2MServer;

import LWM2MServer.repository.IoTClientRepository;
import LWM2MServer.services.PrintMenu;
import LWM2MServer.services.ShowRegisteredDevices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class SmartHomeApp implements CommandLineRunner {

    @Autowired
    IoTClientRepository ioTClientRepository;

    public static void main(String[] args) {
        SpringApplication.run(SmartHomeApp.class, args);
    }

    @Override
    public void run(String...args) {

        while (true) {

            PrintMenu.menu();

            try{
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                String s = bufferRead.readLine();


                switch (s) {

                    case "1.1":
                        ioTClientRepository.deleteAll();
                        System.out.println("All the registered devices have been removed from database.");

                        break;

                    case "1.2":
                        ShowRegisteredDevices showRegisteredDevices = new ShowRegisteredDevices(ioTClientRepository);
                        showRegisteredDevices.display();
                        break;

                    case "2":
                    case "2.1":
                        System.out.println("Read: T.B.D\n\n");
                        break;

                    case "2.2":
                        System.out.println("Discover: T.B.D\n\n");
                        break;

                    case "2.3":
                        System.out.println("Write: T.B.D\n\n");
                        break;

                    case "2.4":
                        System.out.println("Write Attributes: T.B.D\n\n");
                        break;

                    case "2.5":
                        System.out.println("Execute: T.B.D\n\n");
                        break;

                    case "2.6":
                        System.out.println("Create: T.B.D\n\n");
                        break;

                    case "2.7":
                        System.out.println("Delete: T.B.D\n\n");
                        break;

                    case "3":
                    case "3.1":
                        System.out.println("Observe: T.B.D\n\n");
                        break;

                    case "3.2":
                        System.out.println("Notify: T.B.D\n\n");
                        break;

                    case "3.3":
                        System.out.println("Cancel Observation: T.B.D\n\n");
                        break;


                    default:
                        System.out.println("Please input number(s) 1.1 1.2 2.1 2.2 2.3 2.4 2.5 2.6 2.7 3.1 3.2 3.3\n\n");

                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
