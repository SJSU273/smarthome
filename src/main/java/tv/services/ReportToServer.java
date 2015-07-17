package tv.services;

import org.springframework.web.client.RestTemplate;
import LWM2MServer.models.InfoReport;
import tv.repository.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Scott on 6/26/15.
 */
public class ReportToServer {

    private TVAttributeObjectRepository tvAttributeObjectRepository;
    private TVChannelObjectRepository tvChannelObjectRepository;
    private TVControlObjectRepository tvControlObjectRepository;
    private DeviceObjectRepository deviceObjectRepository;

    public ReportToServer() {
    }

    public ReportToServer(TVAttributeObjectRepository tvAttributeObjectRepository, TVChannelObjectRepository tvChannelObjectRepository, TVControlObjectRepository tvControlObjectRepository, DeviceObjectRepository deviceObjectRepository) {
        this.tvAttributeObjectRepository = tvAttributeObjectRepository;
        this.tvChannelObjectRepository = tvChannelObjectRepository;
        this.tvControlObjectRepository = tvControlObjectRepository;
        this.deviceObjectRepository = deviceObjectRepository;
    }

    public void notifyTvChannelObject(String uri) {
        TVChannelObject tvChannelObject = null;
        String Manufacturer = null;
        String ModelNumber = null;
        String SerialNumber = null;

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

        }
    }

}
