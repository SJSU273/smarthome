package tv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
    @RequestMapping(value="/value", method= RequestMethod.GET)
    private ResponseEntity<String> read(@RequestBody String s) {

        System.out.println("Received Message: " + s);

        System.out.println("Replied Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.OK);

    }

    //write by Xiaoxiao Li
    @RequestMapping(value="/value", method= RequestMethod.PUT)
    private ResponseEntity<String> write(@RequestBody String s) {

        System.out.println("Received Message: " + s);

        System.out.println("Replied Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.OK);

    }

    //discover by Wei Si
    @RequestMapping(value="/attribute", method= RequestMethod.GET)
    private ResponseEntity<String> discover(@RequestBody String s) {

        System.out.println("Received Message: " + s);

        System.out.println("Replied Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.OK);

    }

    //discover by Wei Si
    @RequestMapping(value="/attribute", method= RequestMethod.PUT)
    private ResponseEntity<String> writeAttributes(@RequestBody String s) {

        System.out.println("Received Message: " + s);

        System.out.println("Replied Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.OK);

    }

    //execute by Xin Huang
    @RequestMapping(value="/attribute", method= RequestMethod.POST)
    private ResponseEntity<String> execute(@RequestBody String s) {

        System.out.println("Received Message: " + s);

        System.out.println("Replied Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.OK);

    }

    //create by Xin Huang
    @RequestMapping(value="/object", method= RequestMethod.POST)
    private ResponseEntity<String> create(@RequestBody String s) {

        System.out.println("Received Message: " + s);

        System.out.println("Replied Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.OK);

    }

    //delete by Xin Huang
    @RequestMapping(value="/object", method= RequestMethod.DELETE)
    private ResponseEntity<String> delete(@RequestBody String s) {

        System.out.println("Received Message: " + s);

        System.out.println("Replied Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.OK);

    }

    //observe by Hongbo Tian
    @RequestMapping(value="/observe", method= RequestMethod.GET)
    private ResponseEntity<String> observe(@RequestBody String s) {

        System.out.println("Received Message: " + s);

        System.out.println("Replied Message: 200 (OK)");

        return new ResponseEntity(HttpStatus.OK);

    }


}
