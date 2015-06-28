package LWM2MServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import LWM2MServer.models.IoTClient;
import LWM2MServer.repository.IoTClientRepository;

/**
 * Created by Scott on 6/25/15.
 */
@SpringBootApplication
public class TestMongodbApp implements CommandLineRunner {
    @Autowired
    private IoTClientRepository clientRepository;

    public static void main(String[] args) {
        SpringApplication.run(TestMongodbApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        clientRepository.deleteAll();

        //clientRepository.save(new IoTClient("1001","This is #1"));
        //clientRepository.save(new IoTClient("1002","This is #2"));

        System.out.println("This is output of .findAll()");
        for (IoTClient client: clientRepository.findAll()) {
            System.out.println(client);
        }

        //System.out.println("This is output of .findByDescription");
        //IoTClient client= clientRepository.findByDescription("This is #2");
        //System.out.println(client);

    }
}