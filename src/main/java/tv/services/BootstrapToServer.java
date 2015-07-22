package tv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tv.model.BootstrapRequest;
import tv.model.BootstrapResponse;
import tv.repository.*;

/**
 * Created by Scott on 6/26/15.
 */

@Component
public class BootstrapToServer {
    @Autowired
    private LWM2MSecurityObjectRepository lwm2MSecurityObjectRepository;
    @Autowired
    private LWM2MServerObjectRepository lwm2MServerObjectRepository;
    @Autowired
    private DeviceObjectRepository deviceObjectRepository;

    public void boot() {

        LWM2MSecurityObject securityObject = lwm2MSecurityObjectRepository.findAll().get(0);
        DeviceObject device = deviceObjectRepository.findAll().get(0);

        String uri = securityObject.getLWM2MServerURI();

        String Manufacturer = device.getManufacturer();
        String ModelNumber = device.getModelNumber();
        String SerialNumber = device.getSerialNumber();
        String EPN = "urn:dev:ops:" + Manufacturer + "-" + ModelNumber + "-" + SerialNumber;


        RestTemplate restTemplate = new RestTemplate();

        BootstrapRequest request = new BootstrapRequest(EPN,Manufacturer,ModelNumber,SerialNumber);

        System.out.println("Sending the request: "+ request);

        BootstrapResponse response = restTemplate.postForObject(uri, request, BootstrapResponse.class);

        System.out.println("Receiving the response: " + response);

        if (response == null) {
            System.out.println("Bootstrap failed.");
            return;
        }

        // save register server info
        securityObject.setLWM2MServerURI(response.getLWM2MServerURI());
        securityObject.setBootstrapServer(false);

        lwm2MSecurityObjectRepository.save(securityObject);

        // save server object data
        LWM2MServerObject serverObject = new LWM2MServerObject();
        serverObject.setNotificationStoringWhenDisabledOrOffline(response.getServerObject().isNotificationStoringWhenDisabledOrOffline());
        serverObject.setShortServerID(response.getServerObject().getShortServerID());
        serverObject.setLifetime(response.getServerObject().getLifetime());
        serverObject.setDisableTimeout(response.getServerObject().getDisableTimeout());
        serverObject.setDefaultMinimumPeriod(response.getServerObject().getDefaultMinimumPeriod());
        serverObject.setDefaultMaximumPeriod(response.getServerObject().getDefaultMaximumPeriod());
        serverObject.setBindingPreference(response.getServerObject().getBindingPreference());

        lwm2MServerObjectRepository.save(serverObject);


    }
}
