package LWM2MServer.services;

import LWM2MServer.models.IoTClient;
import LWM2MServer.repository.IoTClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Scott on 6/27/15.
 */

public class ShowRegisteredDevices {
    IoTClientRepository ioTClientRepository;

    public ShowRegisteredDevices(IoTClientRepository ioTClientRepository) {
        this.ioTClientRepository = ioTClientRepository;
    }

    public void display() {
        String stars = new String(new char[50]).replace("\0", "-");
        String status = "The list of all registered devices:";

        System.out.printf("%-50s\n",status);
        System.out.println(stars);
        int i = 0;
        for (IoTClient client: ioTClientRepository.findAll()){

            i++;
            System.out.println(client);
        }
        System.out.println(stars);
        System.out.printf("Total %d device(s)\n",i);
        System.out.println(stars);


    }
  }
