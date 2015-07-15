package tv.services;

import org.springframework.web.client.RestTemplate;
import tv.model.BootstrapRequest;
import tv.model.BootstrapResponse;
import tv.repository.DeviceObject;
import tv.repository.LWM2MSecurityObject;
import tv.repository.LWM2MServerObject;

/**
 * Created by Scott on 6/26/15.
 */
public class BootstrapToServer {

    LWM2MSecurityObject securityObject;
    LWM2MServerObject serverObject;
    DeviceObject device;



    public BootstrapToServer(LWM2MSecurityObject securityObject, LWM2MServerObject serverObject, DeviceObject device) {
        this.securityObject = securityObject;
        this.serverObject = serverObject;
        this.device = device;
    }

    public void boot() {

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
        this.securityObject.setLWM2MServerURI(response.getLWM2MServerURI());
        this.securityObject.setBootstrapServer(false);

        // save server object data
        serverObject.setNotificationStoringWhenDisabledOrOffline(response.getServerObject().isNotificationStoringWhenDisabledOrOffline());
        serverObject.setShortServerID(response.getServerObject().getShortServerID());
        serverObject.setLifetime(response.getServerObject().getLifetime());
        serverObject.setDisableTimeout(response.getServerObject().getDisableTimeout());
        serverObject.setDefaultMinimumPeriod(response.getServerObject().getDefaultMinimumPeriod());
        serverObject.setDefaultMaximumPeriod(response.getServerObject().getDefaultMaximumPeriod());
        serverObject.setBindingPreference(response.getServerObject().getBindingPreference());


    }
}
