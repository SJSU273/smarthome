package tv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import LWM2MServer.models.InfoReport;
import tv.controllers.TVController;
import tv.repository.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Scott on 6/26/15.
 */
@Component
public class ReportToServer {

    @Autowired
    private TVAttributeObjectRepository tvAttributeObjectRepository;
    @Autowired
    private TVChannelObjectRepository tvChannelObjectRepository;
    @Autowired
    private TVControlObjectRepository tvControlObjectRepository;
    @Autowired
    private DeviceObjectRepository deviceObjectRepository;
    @Autowired
    private ClientTvWatchRecordRepository clientTvWatchRecordRepository;

    public ReportToServer() {
    }

    public void notifyTvChannelObject(String uri) {
        TVChannelObject tvChannelObject = null;
        String Manufacturer = null;
        String ModelNumber = null;
        String SerialNumber = null;

        if (!TVController.isObserved()) {
            return;
        }

        RestTemplate restTemplate = new RestTemplate();
        for (TVChannelObject object: tvChannelObjectRepository.findAll()) {
            //find the anyone
            tvChannelObject = object;
            System.out.println("find the channel object: " + tvChannelObject);
            break;
        }
        if (tvChannelObject != null) {

            uri += "/{endpointClientName}";

            for (DeviceObject deviceObject: deviceObjectRepository.findAll()) {
                Manufacturer = deviceObject.getManufacturer();
                ModelNumber = deviceObject.getModelNumber();
                SerialNumber = deviceObject.getSerialNumber();
            }

            String EPN = "urn:dev:ops:" + Manufacturer + "-" + ModelNumber + "-" + SerialNumber;
            String ECN = Manufacturer + "-" + ModelNumber + "-" + SerialNumber;

            Map<String, String> paras = new HashMap<String, String>();

            paras.put("endpointClientName",ECN);

            tvChannelObject.setEndTime(new Date());

            System.out.println("Sent notify message(TV Channel Object) to " + uri);

            String result = restTemplate.postForObject(uri, tvChannelObject, String.class, paras);

            System.out.println("Receive response message with result: " + result);

            //Check whether server wants to cancel observation
            if ("stop".equals(result)) {
                TVController.setObserved(false);
                System.out.println("Observation is canceled by server, and no more data will be sent to server.");
            }

            //Save to local database
            ClientTvWatchRecord record = new ClientTvWatchRecord();
            record.setStartTime(tvChannelObject.getStartTime());
            record.setThisObjectInstanceID(tvChannelObject.getThisObjectInstanceID());
            record.setThisObjectID(tvChannelObject.getThisObjectID());
            record.setChannelName(tvChannelObject.getChannelName());
            record.setEndTime(tvChannelObject.getEndTime());
            clientTvWatchRecordRepository.save(record);

            //update TV Channel object
            for (TVControlObject controlObject: tvControlObjectRepository.findAll()) {
                tvChannelObject.setChannelID(controlObject.getChannelId());
                tvChannelObject.setChannelName("BBC-" + tvChannelObject.getChannelID());
            }
            tvChannelObject.setStartTime(tvChannelObject.getEndTime());
            tvChannelObject.setEndTime(null);
            tvChannelObjectRepository.save(tvChannelObject);

        }
    }

}
