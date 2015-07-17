package LWM2MServer;

import Common.TVObjectID;
import LWM2MServer.controllers.LWM2MController;
import LWM2MServer.models.ServerTvWatchRecord;
import LWM2MServer.repository.IoTClientRepository;
import LWM2MServer.repository.ServerTvWatchRecordRepository;
import LWM2MServer.services.*;
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
    private IoTClientRepository ioTClientRepository;

    @Autowired
    private ServerTvWatchRecordRepository serverTvWatchRecordRepository;

    public static void main(String[] args) {
        SpringApplication.run(SmartHomeApp.class, args);
    }

    @Override
    public void run(String...args) {


        ioTClientRepository.deleteAll();

        while (true) {

            PrintMenu.menu();

            try{
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                String s = bufferRead.readLine();


                switch (s) {

                    case "1":
                        System.out.println("Nothing to do.");

                        break;


                    case "2":
                    case "2.1":
                        ValueOperation req21 = new ValueOperation("http://localhost:8082/value", TVObjectID.TV_CONTROL_OBJECT_ID , 0, 2);
                        req21.read();
                        break;

                    case "2.2":
                        ValueOperation req23 = new ValueOperation("http://localhost:8082/value", TVObjectID.TV_CONTROL_OBJECT_ID , 0, 2);
                        req23.write();
                        break;

                    case "2.3":
                        AttributeOperation req1 = new AttributeOperation("http://localhost:8082/attribute", TVObjectID.TV_CHANNEL_OBJECT_ID , 0, 4);
                        req1.discover();
                        break;

                    case "2.4":
                        AttributeOperation req2 = new AttributeOperation("http://localhost:8082/attribute", TVObjectID.TV_CHANNEL_OBJECT_ID , 0, 4);
                        req2.writeAttribute(1);
                        break;

                    case "2.5":
                        AttributeOperation req25 = new AttributeOperation("http://localhost:8082/attribute", TVObjectID.TV_CONTROL_OBJECT_ID , 0, 1);
                        req25.execute();
                        break;

                    case "2.6":
                        // To simplify with using the static Uri for a TV
                        ObjectOperation objectOperation1 = new ObjectOperation("http://localhost:8082/object", TVObjectID.TV_CHANNEL_OBJECT_ID , 0);
                        objectOperation1.create();
                        break;

                    case "2.7":
                        // To simplify with using the static Uri for a TV
                        ObjectOperation objectOperation2 = new ObjectOperation("http://localhost:8082/object", TVObjectID.TV_CHANNEL_OBJECT_ID, 0);
                        objectOperation2.delete();
                        break;

                    case "3":
                    case "3.1":
                        ObserveOperation observeRequest = new ObserveOperation();
                        observeRequest.observe();
                        LWM2MController.setCancelObserve(false);
                        System.out.println("Observation is set, and client is going to notify its data.");
                        break;

                    case "3.2":
                        LWM2MController.setCancelObserve(true);
                        System.out.println("Observation is canceled, and will inform client in the coming notify message.");
                        break;

                    case "3.3":
                        AttributeOperation req33 = new AttributeOperation("http://localhost:8082/attribute", TVObjectID.TV_CHANNEL_OBJECT_ID , 0, 4);
                        req33.writeAttribute("stop");

                        break;

                    case "4":
                    case "4.1":
                        ioTClientRepository.deleteAll();
                        System.out.println("All the registered devices have been removed from database.");

                        break;

                    case "4.2":
                        ShowRegisteredDevices showRegisteredDevices = new ShowRegisteredDevices(ioTClientRepository);
                        showRegisteredDevices.display();
                        break;

                    case "4.3":
                        for (ServerTvWatchRecord record: serverTvWatchRecordRepository.findAll()){
                            System.out.println(record);
                        }
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
