package LWM2MBootstrapServer.controllers;

import LWM2MBootstrapServer.repository.LWM2MDevice;
import LWM2MBootstrapServer.repository.LWM2MDeviceRepository;
import LWM2MServer.models.InfoReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tv.model.BootstrapRequest;
import tv.model.BootstrapResponse;
import tv.repository.LWM2MServerObject;

import java.util.LinkedList;

/**
 * Created by Scott on 6/26/15.
 */
@RestController
public class BootstrapController {
    @Autowired
    LWM2MDeviceRepository deviceRepository;

    @RequestMapping("/bootstrap/tv")
    public BootstrapResponse bootstrapTv(@RequestBody BootstrapRequest request) {
        System.out.println("Received Message: " + request);

        LWM2MDevice device = deviceRepository.findById(request.getEPN());
        if (device != null) {
            BootstrapResponse response = new BootstrapResponse();
            response.setLWM2MServerURI(device.getRegisterSeverUri());
            LWM2MServerObject serverObject = new LWM2MServerObject();
            serverObject.setShortServerID(device.getServerObject().getShortServerID());
            serverObject.setBindingPreference(device.getServerObject().getBindingPreference());
            serverObject.setDefaultMaximumPeriod(device.getServerObject().getDefaultMaximumPeriod());
            serverObject.setDefaultMinimumPeriod(device.getServerObject().getDefaultMinimumPeriod());
            serverObject.setDisableTimeout(device.getServerObject().getDisableTimeout());
            serverObject.setLifetime(device.getServerObject().getLifetime());
            serverObject.setNotificationStoringWhenDisabledOrOffline(device.getServerObject().isNotificationStoringWhenDisabledOrOffline());
            //response.setServerObject(device.getServerObject());
            response.setServerObject(serverObject);

            System.out.println("Replied Message: " + response);
            return response;

        } else {

            System.out.println("Can't find this device in Bootstrap server database");
            System.out.println("Replied Message: null");
            return null;
        }
    }

    @RequestMapping("/bootstrap/aircondition")

    public LinkedList<InfoReport> bootstrapAircondition(@RequestBody InfoReport r) {

        //   if (r == null) return new LinkedList<InfoReport>(new InfoReport(0, "There is no any data in your request!"));

        //save to database
        return new LinkedList<>();

    }

    @RequestMapping("/bootstrap/refrigerator")
    public BootstrapResponse bootstrapRefrigerator(@RequestBody BootstrapRequest request) {
        System.out.println("Received Message: " + request);

        LWM2MDevice device = deviceRepository.findById(request.getEPN());
        if (device != null) {
            BootstrapResponse response = new BootstrapResponse();
            response.setLWM2MServerURI(device.getRegisterSeverUri());
            LWM2MServerObject serverObject = new LWM2MServerObject();
            serverObject.setShortServerID(device.getServerObject().getShortServerID());
            serverObject.setBindingPreference(device.getServerObject().getBindingPreference());
            serverObject.setDefaultMaximumPeriod(device.getServerObject().getDefaultMaximumPeriod());
            serverObject.setDefaultMinimumPeriod(device.getServerObject().getDefaultMinimumPeriod());
            serverObject.setDisableTimeout(device.getServerObject().getDisableTimeout());
            serverObject.setLifetime(device.getServerObject().getLifetime());
            serverObject.setNotificationStoringWhenDisabledOrOffline(device.getServerObject().isNotificationStoringWhenDisabledOrOffline());
            //response.setServerObject(device.getServerObject());
            response.setServerObject(serverObject);

            System.out.println("Replied Message: " + response);
            return response;

        } else {

            System.out.println("Can't find this device in Bootstrap server database");
            System.out.println("Replied Message: null");
            return null;
        }
    }
}
