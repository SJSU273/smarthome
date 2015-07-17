package tv.services;

import org.springframework.web.client.RestTemplate;
import tv.model.*;
import tv.repository.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Scott on 6/27/15.
 */
public class RegisterToServer {

    private LWM2MSecurityObjectRepository lwm2MSecurityObjectRepository;
    private LWM2MServerObjectRepository lwm2MServerObjectRepository;
    private DeviceObjectRepository deviceObjectRepository;
    private TVControlObjectRepository tvControlObjectRepository;

    public RegisterToServer() {
    }

    public RegisterToServer(LWM2MSecurityObjectRepository lwm2MSecurityObjectRepository, LWM2MServerObjectRepository lwm2MServerObjectRepository, DeviceObjectRepository deviceObjectRepository, TVControlObjectRepository tvControlObjectRepository) {
        this.lwm2MSecurityObjectRepository = lwm2MSecurityObjectRepository;
        this.lwm2MServerObjectRepository = lwm2MServerObjectRepository;
        this.deviceObjectRepository = deviceObjectRepository;
        this.tvControlObjectRepository = tvControlObjectRepository;
    }

    public void register() {

        LWM2MSecurityObject securityObject;
        LWM2MServerObject serverObject;
        DeviceObject deviceObject;

        securityObject = lwm2MSecurityObjectRepository.findAll().get(0);
        deviceObject = deviceObjectRepository.findAll().get(0);
        serverObject = lwm2MServerObjectRepository.findAll().get(0);


        if (securityObject.isBootstrapServer()) {
            System.out.println("Please bootstrap first.");
            return;
        }

        String uri = securityObject.getLWM2MServerURI();
        Map<String, String> paras = new HashMap<String, String>();

        String Manufacturer = deviceObject.getManufacturer();
        String ModelNumber = deviceObject.getModelNumber();
        String SerialNumber = deviceObject.getSerialNumber();
        String EPN = "urn:dev:ops:" + Manufacturer + "-" + ModelNumber + "-" + SerialNumber;

        RegisterRequest request = new RegisterRequest();
        request.setEndpointClientName(EPN);
        request.setSMSNumber("40812345678");
        request.setObjectsAndObjectInstances("</0/0>, </1>, </2/0>, </3/0>, </10>, </11>");
        request.setLWM2MVersion(deviceObject.getFirmwareVersion());
        request.setBindingMode(serverObject.getBindingPreference());
        request.setLifetime(serverObject.getLifetime());


        RestTemplate restTemplate = new RestTemplate();

        System.out.println("Sending the request: "+ request);

        restTemplate.put(uri, request, paras);

        System.out.println("Receiving the response: 200 (OK)");
        System.out.println("Register successfully.");

        //Create a TVControlObject after successful registration
        TVControlObject tvControlObject = new TVControlObject();
        tvControlObject.setChannelId(1);
        tvControlObject.setVolumeOfVoice(5);
        tvControlObjectRepository.save(tvControlObject);

        return;

    }

    public void update() {
        LWM2MSecurityObject securityObject;
        LWM2MServerObject serverObject;
        DeviceObject deviceObject;

        securityObject = lwm2MSecurityObjectRepository.findAll().get(0);
        deviceObject = deviceObjectRepository.findAll().get(0);
        serverObject = lwm2MServerObjectRepository.findAll().get(0);

        if (securityObject.isBootstrapServer()) {
            System.out.println("Please bootstrap first.");
            return;
        }

        String uri = securityObject.getLWM2MServerURI();
        Map<String, String> paras = new HashMap<String, String>();


        String Manufacturer = deviceObject.getManufacturer();
        String ModelNumber = deviceObject.getModelNumber();
        String SerialNumber = deviceObject.getSerialNumber();
        String EPN = "urn:dev:ops:" + Manufacturer + "-" + ModelNumber + "-" + SerialNumber;

        RegisterUpdate request = new RegisterUpdate();
        request.setEndpointClientName(EPN);
        request.setSMSNumber("40887654321");
        request.setObjectsAndObjectInstances("</1/102>, </2/0>, </3/0>, </4/0>, </5>");
        request.setBindingMode(serverObject.getBindingPreference());
        request.setLifetime(serverObject.getLifetime());


        RestTemplate restTemplate = new RestTemplate();

        System.out.println("Sending the request: "+ request);

        RegisterUpdate update = restTemplate.postForObject(uri, request, RegisterUpdate.class);

        System.out.println("Receiving the response: 200 (OK)");
        System.out.println("Update successfully.");

        return;
    }

    public void delete() {
        LWM2MSecurityObject securityObject;
        DeviceObject deviceObject;

        securityObject = lwm2MSecurityObjectRepository.findAll().get(0);
        deviceObject = deviceObjectRepository.findAll().get(0);


        if (securityObject.isBootstrapServer()) {
            System.out.println("Please bootstrap first.");
            return;
        }

        String uri = securityObject.getLWM2MServerURI()+"/{endpointClientName}";

        String Manufacturer = deviceObject.getManufacturer();
        String ModelNumber = deviceObject.getModelNumber();
        String SerialNumber = deviceObject.getSerialNumber();
        String EPN = "urn:dev:ops:" + Manufacturer + "-" + ModelNumber + "-" + SerialNumber;
        String ECN = Manufacturer + "-" + ModelNumber + "-" + SerialNumber;

        Map<String, String> paras = new HashMap<String, String>();
        //paras.put("endpointClientName", EPN);

        paras.put("endpointClientName",ECN);
        RestTemplate restTemplate = new RestTemplate();

        System.out.printf("Sending the request: De-Register Device %s\n", EPN);

        restTemplate.delete(uri, paras);

        System.out.println("Receiving the response: 200 (OK)");

        System.out.println("De-Register successfully.");

        //delete server object after de-register
        lwm2MServerObjectRepository.deleteAll();
        tvControlObjectRepository.deleteAll();

        return;

    }

}
