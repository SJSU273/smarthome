package LWM2MBootstrapServer.services;

import LWM2MBootstrapServer.repository.LWM2MDevice;
import LWM2MBootstrapServer.repository.LWM2MDeviceRepository;

/**
 * Created by Scott on 6/27/15.
 */
public class ShowBootstrapDevices {
    LWM2MDeviceRepository deviceRepository;

    public ShowBootstrapDevices(LWM2MDeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public void display() {
        String stars = new String(new char[50]).replace("\0", "-");
        String status = "The list of all bootstrap devices:";

        System.out.printf("%-50s\n",status);
        System.out.println(stars);
        int i = 0;
        for (LWM2MDevice client: deviceRepository.findAll()){

            i++;
            System.out.println(client);
        }
        System.out.println(stars);
        System.out.printf("Total %d device(s)\n",i);
        System.out.println(stars);


    }
}
