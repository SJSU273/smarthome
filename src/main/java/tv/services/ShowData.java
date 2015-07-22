package tv.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tv.repository.*;

import java.util.List;

/**
 * Created by Scott on 6/26/15.
 */
@Component
public class ShowData {

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

        for(TVAttributeObject tvAttributeObject: tvAttributeObjectRepository.findAll()) {
            System.out.println(tvAttributeObject);
        }
    }
}
