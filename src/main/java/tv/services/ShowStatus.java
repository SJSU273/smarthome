package tv.services;


import tv.repository.*;

import java.util.List;

/**
 * Created by Scott on 6/26/15.
 */

public class ShowStatus {

    private AccessControlObjectRepository accessControlObjectRepository;
    private DeviceObjectRepository deviceObjectRepository;
    private LWM2MSecurityObjectRepository lwm2MSecurityObjectRepository;
    private LWM2MServerObjectRepository lwm2MServerObjectRepository;
    private TVChannelObjectRepository tvChannelObjectRepository;
    private TVControlObjectRepository tvControlObjectRepository;

    public ShowStatus(AccessControlObjectRepository accessControlObjectRepository, DeviceObjectRepository deviceObjectRepository, LWM2MSecurityObjectRepository lwm2MSecurityObjectRepository, LWM2MServerObjectRepository lwm2MServerObjectRepository, TVChannelObjectRepository tvChannelObjectRepository, TVControlObjectRepository tvControlObjectRepository) {
        this.accessControlObjectRepository = accessControlObjectRepository;
        this.deviceObjectRepository = deviceObjectRepository;
        this.lwm2MSecurityObjectRepository = lwm2MSecurityObjectRepository;
        this.lwm2MServerObjectRepository = lwm2MServerObjectRepository;
        this.tvChannelObjectRepository = tvChannelObjectRepository;
        this.tvControlObjectRepository = tvControlObjectRepository;
    }

    public void show() {
        String stars = new String(new char[50]).replace("\0", "-");
        String status = "The current status";

        System.out.printf("%-50s\n",status);
        System.out.println(stars);

        for(LWM2MSecurityObject securityObject: lwm2MSecurityObjectRepository.findAll()) {
            System.out.println(securityObject);
        }

        for(LWM2MServerObject serverObject: lwm2MServerObjectRepository.findAll()){
            System.out.println(serverObject);
        }

        for(AccessControlObject controlObject: accessControlObjectRepository.findAll()){
            System.out.println(controlObject);
        }

        for(DeviceObject device: deviceObjectRepository.findAll()){
            System.out.println(device);
        }

        for(TVControlObject tvControlObject: tvControlObjectRepository.findAll()){
            System.out.println(tvControlObject);
        }

        for(TVChannelObject tvChannelObject: tvChannelObjectRepository.findAll()){
            System.out.println(tvChannelObject);
        }
    }
}
