package Refrigerator;

import org.springframework.web.client.RestTemplate;
import smarthome.InfoReport;
import java.time.LocalDateTime;

/**
 * Created by Scott on 6/13/15.
 */
public class RefrigeratorApplication {
    public static void main(String args[]) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        long l = 0;

        final String uri = "http://localhost:8080/report";

        while (true) {

            Thread.sleep(1000);

            InfoReport newData = new InfoReport(1001, "This message is from Refrigerator 1001 sent at "+ LocalDateTime.now());
            InfoReport result = restTemplate.postForObject( uri, newData, InfoReport.class);
            System.out.println("Sequence No. = " + l++);
            System.out.println("Client--->Server: " + newData.toString());
            System.out.println("Server--->Client: " + result.toString() + " receive at " + LocalDateTime.now());

        }


    }
}