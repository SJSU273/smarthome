package tv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tv.repository.*;

/**
 * Created by Scott on 7/15/15.
 */
@RestController
public class TVController {
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

    //read by Xiaoxiao Li
    @RequestMapping(value="/value/{objectId}/{objectInstanceId}/{resourceId}", method= RequestMethod.GET)
    private ResponseEntity<String> read(@PathVariable("objectId") int objectId, @PathVariable("objectInstanceId") int objectInstanceId, @PathVariable("resourceId") int resourceId) {

        System.out.println("Receive Read Message: "
                + " objectId = "+ objectId
                + " objectInstanceId = " + objectInstanceId
                + " resourceId = " + resourceId);

        System.out.println("Send Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.OK);

    }

    //write by Xiaoxiao Li
    @RequestMapping(value="/value/{objectId}/{objectInstanceId}/{resourceId}", method= RequestMethod.POST)
    private ResponseEntity<String> write(@PathVariable("objectId") int objectId, @PathVariable("objectInstanceId") int objectInstanceId, @PathVariable("resourceId") int resourceId) {

        System.out.println("Receive Write Message: "
                + " objectId = "+ objectId
                + " objectInstanceId = " + objectInstanceId
                + " resourceId = " + resourceId);

        System.out.println("Send Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.OK);

    }

    //discover by Wei Si
    @RequestMapping(value="/attribute/{objectId}/{objectInstanceId}/{resourceId}", method= RequestMethod.GET)
    private ResponseEntity<String> discover(@PathVariable("objectId") int objectId, @PathVariable("objectInstanceId") int objectInstanceId, @PathVariable("resourceId") int resourceId) {

        System.out.println("Receive Discover Message: "
                + " objectId = "+ objectId
                + " objectInstanceId = " + objectInstanceId
                + " resourceId = " + resourceId);

        System.out.println("Send Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.OK);

    }

    //writeAttributes by Wei Si
    @RequestMapping(value="/attribute/{objectId}/{objectInstanceId}/{resourceId}", method= RequestMethod.PUT)
    private ResponseEntity<String> writeAttributes(@PathVariable("objectId") int objectId, @PathVariable("objectInstanceId") int objectInstanceId, @PathVariable("resourceId") int resourceId) {

        System.out.println("Receive WriteAttributes Message: "
                + " objectId = "+ objectId
                + " objectInstanceId = " + objectInstanceId
                + " resourceId = " + resourceId);

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

        System.out.println("Send Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.OK);

    }

    //create by Xin Huang
    @RequestMapping(value="/object/{objectId}/{objectInstanceId}", method= RequestMethod.POST)
    private ResponseEntity<String> create(@PathVariable("objectId") int objectId, @PathVariable("objectInstanceId") int objectInstanceId) {

        System.out.println("Receive Create Message: "
                + " objectId = "+ objectId
                + " objectInstanceId = " + objectInstanceId);

        System.out.println("Send Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.OK);

    }

    //delete by Xin Huang
    @RequestMapping(value="/object/{objectId}/{objectInstanceId}", method= RequestMethod.DELETE)
    private ResponseEntity<String> delete(@PathVariable("objectId") int objectId, @PathVariable("objectInstanceId") int objectInstanceId) {

        System.out.println("Receive Delete Message: "
                + " objectId = "+ objectId
                + " objectInstanceId = " + objectInstanceId);

        System.out.println("Send Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.OK);

    }

    //observe by Hongbo Tian
    @RequestMapping(value="/observe/{objectId}/{objectInstanceId}/{resourceId}", method= RequestMethod.GET)
    private ResponseEntity<String> observe(@PathVariable("objectId") int objectId, @PathVariable("objectInstanceId") int objectInstanceId, @PathVariable("resourceId") int resourceId) {

        System.out.println("Received Message 3: "
                + " objectId = "+ objectId
                + " objectInstanceId = " + objectInstanceId
                + " resourceId" + resourceId);

        System.out.println("Replied Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.OK);

    }

}
