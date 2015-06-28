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

                    case "0":
                        ioTClientRepository.deleteAll();
                        System.out.println("All the registered devices have been removed from database.");

                        break;

                    case "1":
                        ShowRegisteredDevices showRegisteredDevices = new ShowRegisteredDevices(ioTClientRepository);
                        showRegisteredDevices.display();
                        break;

                    default:
                        System.out.println("Please input number(s) 1\n\n");

                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
