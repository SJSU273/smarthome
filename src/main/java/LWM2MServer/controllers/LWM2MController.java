package LWM2MServer.controllers;

import LWM2MServer.models.IoTClient;
import LWM2MServer.repository.IoTClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import LWM2MServer.models.InfoReport;
import tv.model.RegisterRequest;
import tv.model.RegisterUpdate;

import java.util.LinkedList;

/**
 * Created by Scott on 6/12/15.
 */
@RestController
public class LWM2MController {

    @Autowired
    IoTClientRepository ioTClientRepository;

    @RequestMapping(value="/register/tv", method= RequestMethod.PUT)
    private void registerTV(@RequestBody RegisterRequest request) {

        System.out.println("Received Message: " + request);

        IoTClient ioTClient = ioTClientRepository.findByEndpointClientName(request.getEndpointClientName());

        if (ioTClient != null) {
            ioTClientRepository.delete(request.getEndpointClientName());
        } else {
            ioTClient = new IoTClient();
        }

        ioTClient.setEndpointClientName(request.getEndpointClientName());
        ioTClient.setLifetime(request.getLifetime());
        ioTClient.setBindingMode((request.getBindingMode()));
        ioTClient.setLWM2MVersion(request.getLWM2MVersion());
        ioTClient.setObjectsAndObjectInstances(request.getObjectsAndObjectInstances());
        ioTClient.setSMSNumber(request.getSMSNumber());

        ioTClientRepository.save(ioTClient);
        System.out.println("Replied Message: 200 (OK)");

        return;

    }

    @RequestMapping(value="/register/tv", method= RequestMethod.POST)
    private ResponseEntity<String> registerTV(@RequestBody RegisterUpdate update) {

        System.out.println("Received Message: " + update);

        IoTClient ioTClient = ioTClientRepository.findByEndpointClientName(update.getEndpointClientName());

        if (ioTClient == null) {
            System.out.println("Can't find the registered device in database.");
            return new ResponseEntity(HttpStatus.OK);

        }

        // delete the previouse data
        ioTClientRepository.delete(update.getEndpointClientName());

        //Update the data
        ioTClient.setLifetime(update.getLifetime());
        ioTClient.setBindingMode((update.getBindingMode()));
        ioTClient.setObjectsAndObjectInstances(update.getObjectsAndObjectInstances());
        ioTClient.setSMSNumber(update.getSMSNumber());

        //save data into database
        ioTClientRepository.save(ioTClient);
        System.out.println("Replied Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.CREATED);

    }

    @RequestMapping(value="/register/tv/{endpointClientName}", method= RequestMethod.DELETE)
    private ResponseEntity<String>  deleteTV(@PathVariable("endpointClientName") String endpointClientName) {

        String EPN = "urn:dev:ops:" + endpointClientName;

        System.out.printf("Received Message: De-Register Device: %s\n", EPN);

        ioTClientRepository.delete(EPN);


        System.out.println("Replied Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping("/report")
    public LinkedList<InfoReport> report(@RequestBody InfoReport r) {

     //   if (r == null) return new LinkedList<InfoReport>(new InfoReport(0, "There is no any data in your request!"));

        //save to database
        return new LinkedList<InfoReport>();

     }
}
