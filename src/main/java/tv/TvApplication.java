package tv;


import org.springframework.web.client.RestTemplate;
import smarthome.InfoReport;
import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 * Created by Scott on 6/13/15.
 */
public class TvApplication {
    public static void main(String args[]) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        long l = 0;

        final String uri = "http://localhost:8080/report";

        while (true) {

            Thread.sleep(5000);

            InfoReport newData = new InfoReport(1002, "This message is from TV 1002 sent at "+ LocalDateTime.now());
            LinkedList<InfoReport> results = restTemplate.postForObject( uri, newData, LinkedList.class);
            System.out.println("Sequence No. = " + l++);
            System.out.println("Client--->Server: " + newData.toString());
            System.out.println("Server--->Client: " + results);

        }


    }
}