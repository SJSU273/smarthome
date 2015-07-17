package LWM2MServer.services;

import Common.TVObjectID;
import org.springframework.web.client.RestTemplate;
import tv.model.BootstrapResponse;

/**
 * Created by Scott on 7/16/15.
 */
public class ObserveOperation {
    String url = "http://localhost:8082/observe";

    int objectId = TVObjectID.TV_CHANNEL_OBJECT_ID.getValue();
    int objectInstanceId = 0;
    int resourceId = 3;




    public void observe() {
        RestTemplate r = new RestTemplate();
        url += "/"+objectId+"/"+objectInstanceId+"/"+resourceId;
        System.out.println("Sending the request: "+ url);

        String response = r.getForObject(url, String.class);

        System.out.println("Receiving the response: " + response);

        return;
    }
}
