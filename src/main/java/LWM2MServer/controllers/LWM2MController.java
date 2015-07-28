package LWM2MServer.controllers;

import Common.TVObjectID;
import LWM2MServer.models.IoTClient;
import LWM2MServer.models.ServerTvWatchRecord;
import LWM2MServer.repository.IoTClientRepository;
import LWM2MServer.repository.ServerTvWatchRecordRepository;
import LWM2MServer.services.AttributeOperation;
import LWM2MServer.services.ObserveOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import LWM2MServer.models.InfoReport;
import tv.model.RegisterRequest;
import tv.model.RegisterUpdate;
import tv.repository.TVChannelObject;

import java.util.LinkedList;

/**
 * Created by Scott on 6/12/15.
 */
@RestController
public class LWM2MController {

    private static boolean cancelObserve = false; //true: cancel, false: not cancel

    @Autowired
    private IoTClientRepository ioTClientRepository;

    @Autowired
    private ServerTvWatchRecordRepository serverTvWatchRecordRepository;

    public static boolean isCancelObserve() {
        return cancelObserve;
    }

    public static void setCancelObserve(boolean cancelObserve) {
        LWM2MController.cancelObserve = cancelObserve;
    }

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

    @RequestMapping(value="/notify/tv/channel/{endpointClientName}", method= RequestMethod.POST)
    public String report(@PathVariable("endpointClientName") String endpointClientName, @RequestBody TVChannelObject tvChannelObject) {

        //save to database
        String EPN = "urn:dev:ops:" + endpointClientName;

        ServerTvWatchRecord record = new ServerTvWatchRecord();
        record.setEndTime(tvChannelObject.getEndTime());
        record.setChannelName(tvChannelObject.getChannelName());
        record.setEPN(EPN);
        record.setChannelID(tvChannelObject.getChannelID());
        record.setStartTime(tvChannelObject.getStartTime());
        record.setThisObjectInstanceID(tvChannelObject.getThisObjectInstanceID());
        record.setThisObjectID(tvChannelObject.getThisObjectID());

        System.out.println(record);

        serverTvWatchRecordRepository.save(record);

        if(this.cancelObserve) {
            //sent "stop" to cancel observation
            return "stop";
        }

        return null;

     }

    //For application
    @RequestMapping(value="/tv/{id}/observe", method = RequestMethod.POST)
    public void TVObserve(@PathVariable("id") int id) {

        System.out.println("TV was set to be observed, id = " + id);

        ObserveOperation observeRequest = new ObserveOperation();
        observeRequest.observe();
        LWM2MController.setCancelObserve(false);
        System.out.println("Observation is set, and client is going to notify its data.");

    }

    @RequestMapping(value="/tv/{id}/observe", method = RequestMethod.DELETE)
    public void TVCancelObservation(@PathVariable("id") int id) {
        System.out.println("TV was set to stop observe, id = " + id);

        AttributeOperation req = new AttributeOperation("http://localhost:8082/attribute", TVObjectID.TV_CHANNEL_OBJECT_ID , 0, 4);
        req.writeAttribute("stop");

    }

    @RequestMapping(value = "/tv/{id}/lock", method = RequestMethod.POST)
    public void TVLock(@PathVariable("id") int id) {

        System.out.println("TV was set to be locked, id = " + id);
        AttributeOperation req = new AttributeOperation("http://localhost:8082/attribute", TVObjectID.TV_CONTROL_OBJECT_ID , 0, 1);
        req.execute();

    }

    @RequestMapping(value = "/tv/{id}/lock", method = RequestMethod.DELETE)
    public void TVUnlock(@PathVariable("id") int id) {
        System.out.println("TV was set to be unlocked, id = " + id);

        AttributeOperation req = new AttributeOperation("http://localhost:8082/attribute", TVObjectID.TV_CONTROL_OBJECT_ID , 0, 0);
        req.execute();

    }
}
