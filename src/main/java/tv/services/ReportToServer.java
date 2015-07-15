package tv.services;

import org.springframework.web.client.RestTemplate;
import LWM2MServer.models.InfoReport;

import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 * Created by Scott on 6/26/15.
 */
public class ReportToServer {
    private RestTemplate restTemplate = new RestTemplate();
    private long l = 0;
    private final String uri = "http://localhost:8081//bootstrap/aircondition";
    private long id = 1002;

    public void sendOneReport() {
        InfoReport newData = new InfoReport(id, "This message is from TV 1002 sent at "+ LocalDateTime.now());
        LinkedList<InfoReport> results = restTemplate.postForObject( uri, newData, LinkedList.class);
        System.out.println("Sequence No. = " + l++);
        System.out.println("Client--->Server: " + newData.toString());
        System.out.println("Server--->Client: " + results);
    }

}
