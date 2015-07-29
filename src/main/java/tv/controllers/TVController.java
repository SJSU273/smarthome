package tv.controllers;

import Common.LWM2MAttribute;
import Common.TVChannelId;
import Common.TVObjectID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tv.repository.*;
import tv.services.ReportToServer;


import java.util.Date;
import java.util.List;


/**
 * Created by Scott on 7/15/15.
 */
@RestController
public class TVController {

    private static boolean observed = false; //true: observed, false: not observed

    @Autowired
    private AccessControlObjectRepository accessControlObjectRepository;

    @Autowired
    private LWM2MServerObjectRepository lwm2MServerObjectRepository;

    @Autowired
    private LWM2MSecurityObjectRepository lwm2MSecurityObjectRepository;

    @Autowired
    private DeviceObjectRepository deviceObjectRepository;

    @Autowired
    private TVControlObjectRepository tvControlObjectRepository;

    @Autowired
    private TVChannelObjectRepository tvChannelObjectRepository;

    @Autowired
    private TVAttributeObjectRepository tvAttributeObjectRepository;

    @Autowired
    private ClientTvWatchRecordRepository clientTvWatchRecordRepository;

    @Autowired
    private ReportToServer report;

    public static boolean isObserved() {
        return observed;
    }

    public static void setObserved(boolean observed) {
        TVController.observed = observed;
    }

    //read by Xiaoxiao Li
    @RequestMapping(value="/value/{objectId}/{objectInstanceId}/{resourceId}", method= RequestMethod.GET)
    private TVChannelId read(@PathVariable("objectId") int objectId, @PathVariable("objectInstanceId") int objectInstanceId, @PathVariable("resourceId") int resourceId) {

        TVChannelId channelId = null;

        System.out.println("Receive Read Message: "
                + " objectId = "+ objectId
                + " objectInstanceId = " + objectInstanceId
                + " resourceId = " + resourceId);

        for (TVControlObject object: tvControlObjectRepository.findAll()) {
            if (object.getThisObjectID() == objectId && object.getThisObjectInstanceID() == objectInstanceId) {
                channelId = new TVChannelId(object.getChannelId());
                System.out.println("Find the record in database");
                break;
            }
        }
        System.out.println("Send Message: 200 (OK)");

        return channelId;

    }

    //write by Xiaoxiao Li
    @RequestMapping(value="/value/{objectId}/{objectInstanceId}/{resourceId}", method= RequestMethod.POST)
    private ResponseEntity<String> write(@PathVariable("objectId") int objectId, @PathVariable("objectInstanceId") int objectInstanceId, @PathVariable("resourceId") int resourceId, @RequestBody TVChannelId channelId) {

        System.out.println("Receive Write Message: "
                + " objectId = "+ objectId
                + " objectInstanceId = " + objectInstanceId
                + " resourceId = " + resourceId);

        for (TVControlObject object: tvControlObjectRepository.findAll()) {
            if (object.getThisObjectID() == objectId && object.getThisObjectInstanceID() == objectInstanceId) {
                if (object.getChannelId() != channelId.getChannelId()) {
                    // If different channel id is received, it needs to save to local database and notify server.

                    // save to local database
                    object.setChannelId(channelId.getChannelId());
                    System.out.println("Find the record in database");
                    tvControlObjectRepository.save(object);

                    // Notify server
                    report.notifyTvChannelObject("http://localhost:8081/notify/tv/channel");

                }
                     break;
            }
        }

        System.out.println("Send Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.OK);

    }

    //discover by Wei Si
    @RequestMapping(value="/attribute/{objectId}/{objectInstanceId}/{resourceId}", method= RequestMethod.GET)
    private LWM2MAttribute discover(@PathVariable("objectId") int objectId, @PathVariable("objectInstanceId") int objectInstanceId, @PathVariable("resourceId") int resourceId) {

        LWM2MAttribute attribute = null;
        System.out.println("Receive Discover Message: "
                + " objectId = "+ objectId
                + " objectInstanceId = " + objectInstanceId
                + " resourceId = " + resourceId);

        for (TVAttributeObject object: tvAttributeObjectRepository.findAll()) {
            if (object.getObjectId() == objectId && object.getObjectInstanceId() == objectInstanceId && object.getResourceId() == resourceId) {
                attribute = new LWM2MAttribute();
                attribute.setSt(object.getAttribute().getSt());
                System.out.println("find the record in database");
                break;
            }
        }


        System.out.println("Send Message: 200 (OK)");

        return attribute;

    }

    //writeAttributes by Wei Si
    @RequestMapping(value="/attribute/{objectId}/{objectInstanceId}/{resourceId}", method= RequestMethod.PUT)
    private ResponseEntity<String> writeAttributes(@PathVariable("objectId") int objectId, @PathVariable("objectInstanceId") int objectInstanceId, @PathVariable("resourceId") int resourceId, @RequestBody LWM2MAttribute attribute) {

        TVAttributeObject attributeObject = null;

        System.out.println("Receive WriteAttributes Message: "
                + " objectId = "+ objectId
                + " objectInstanceId = " + objectInstanceId
                + " resourceId = " + resourceId);

        System.out.println(attribute);

        for (TVAttributeObject object: tvAttributeObjectRepository.findAll()) {
            if (object.getObjectId() == objectId && object.getObjectInstanceId() == objectInstanceId && object.getResourceId() == resourceId) {
                attributeObject = object;
                //System.out.println("find the record in database");
                break;
            }
        }
        if (attributeObject == null) {
            attributeObject = new TVAttributeObject();
            attributeObject.setAttribute(new LWM2MAttribute());
        }

        attributeObject.setObjectId(objectId);
        attributeObject.setObjectInstanceId(objectInstanceId);
        attributeObject.setResourceId(resourceId);

        attributeObject.getAttribute().setSt(attribute.getSt());


        // save to database
        tvAttributeObjectRepository.save(attributeObject);

        // set observation flag in TVController
        if("stop".equals(attribute.getCancel())){
            TVController.setObserved(false);
        }

        System.out.println("Send Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.OK);

    }

    //execute by Xin Huang
    @RequestMapping(value="/attribute/{objectId}/{objectInstanceId}/{resourceId}", method= RequestMethod.POST)
    private ResponseEntity<String> execute(@PathVariable("objectId") int objectId, @PathVariable("objectInstanceId") int objectInstanceId, @PathVariable("resourceId") int resourceId) {

        System.out.println("Receive Execute Message: "
                + " objectId = "+ objectId
                + " objectInstanceId = " + objectInstanceId
                + " resourceId = " + resourceId);

        for (TVControlObject object: tvControlObjectRepository.findAll()) {
            if (object.getThisObjectID() == objectId && object.getThisObjectInstanceID() == objectInstanceId) {
                object.setLock(resourceId);
                System.out.println("Find the record in database");
                tvControlObjectRepository.save(object);
                break;
            }
        }
        System.out.println("Send Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.OK);

    }

    //create by Xin Huang
    @RequestMapping(value="/object/{objectId}/{objectInstanceId}", method= RequestMethod.POST)
    private ResponseEntity<String> create(@PathVariable("objectId") int objectId, @PathVariable("objectInstanceId") int objectInstanceId) {

        System.out.println("Receive Create Message: "
                + " objectId = "+ objectId
                + " objectInstanceId = " + objectInstanceId);

        switch (TVObjectID.fromInt(objectId)) {
            case TV_CHANNEL_OBJECT_ID:

                TVChannelObject tvChannelObject = new TVChannelObject();
                //default value
                tvChannelObject.setChannelID(1);
                tvChannelObject.setChannelName("BBC-"+tvChannelObject.getChannelID());
                tvChannelObject.setStartTime(new Date());
                tvChannelObject.setEndTime(null);


                //try to get current channel id from TV Control Object
                for (TVControlObject controlObject: tvControlObjectRepository.findAll()){
                    tvChannelObject.setChannelID(controlObject.getChannelId());
                    tvChannelObject.setChannelName("BBC-"+tvChannelObject.getChannelID());
                    break;
                }


                tvChannelObjectRepository.save(tvChannelObject);

                break;

            default:
                System.out.println("Send Message: 20X (NOT FOUND)");

                return new ResponseEntity(HttpStatus.NOT_FOUND);

        }

        System.out.println("Send Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.OK);

    }

    //delete by Xin Huang
    @RequestMapping(value="/object/{objectId}/{objectInstanceId}", method= RequestMethod.DELETE)
    private ResponseEntity<String> delete(@PathVariable("objectId") int objectId, @PathVariable("objectInstanceId") int objectInstanceId) {

        System.out.println("Receive Delete Message: "
                + " objectId = "+ objectId
                + " objectInstanceId = " + objectInstanceId);

        switch (TVObjectID.fromInt(objectId)) {
            case TV_CHANNEL_OBJECT_ID:
                for (TVChannelObject object: tvChannelObjectRepository.findAll()) {
                    if (object.getThisObjectInstanceID() == objectInstanceId) {
                        tvChannelObjectRepository.delete(object);
                    }
                }
                break;
            default:
                System.out.println("Send Message: 20X (NOT FOUND)");

                return new ResponseEntity(HttpStatus.NOT_FOUND);

        }

        System.out.println("Send Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.OK);
    }

    //observe by Hongbo Tian
    @RequestMapping(value="/observe/{objectId}/{objectInstanceId}/{resourceId}", method= RequestMethod.GET)
    private ResponseEntity<String> observe(@PathVariable("objectId") int objectId, @PathVariable("objectInstanceId") int objectInstanceId, @PathVariable("resourceId") int resourceId) {

        System.out.println("Received Message: "
                + " objectId = "+ objectId
                + " objectInstanceId = " + objectInstanceId
                + " resourceId" + resourceId);

        System.out.println("Replied Message: 200 (OK)");

        System.out.println("Object is set to be observed: /" + objectId + "/" + objectInstanceId + "/" + resourceId );

        this.observed = true;

        return new ResponseEntity(HttpStatus.OK);

    }

    //get by Hongbo Tian
    @RequestMapping(value="/tv/{id}/records/current", method= RequestMethod.GET)
    private TVChannelObject getCurrentChannel(@PathVariable("id") String id) {

        System.out.println("Received Message : "
                + " id = "+ id);

        System.out.println("Replied Message: 200 (OK)");

        for (TVChannelObject o : tvChannelObjectRepository.findAll()) {
            return o;
        }

        return new TVChannelObject();

    }

}
