package LWM2MServer.services;

import Common.TVObjectID;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Scott on 7/16/15.
 */
public class ObjectOperation {

    String url;
    int objectId;
    int objectInstanceId;

    public ObjectOperation(String url, TVObjectID objectId, int objectInstanceId) {
        this.url = url;
        this.objectId = objectId.getValue();
        this.objectInstanceId = objectInstanceId;
    }

    public void create() {

        String s = "hello";

        RestTemplate r = new RestTemplate();
        url += "/" + objectId + "/" + objectInstanceId;
        System.out.println("Sending the request: " + url);

        String response = r.postForObject(url, s, String.class);

        System.out.println("Receiving the response: " + response);

    }

    public void delete() {
        RestTemplate r = new RestTemplate();
        url += "/" + objectId + "/" + objectInstanceId;
        System.out.println("Sending the request: " + url);

        r.delete(url);

        System.out.println("Receiving the response: ");


    }

}
