package LWM2MServer.services;

import Common.LWM2MAttribute;
import Common.TVChannelId;
import Common.TVObjectID;
import org.springframework.web.client.RestTemplate;
import tv.repository.TVAttributeObject;

import java.util.Random;

/**
 * Created by Scott on 7/16/15.
 */
public class ValueOperation {
    String url;
    int objectId;
    int objectInstanceId;
    int resourceId;

    public ValueOperation() {
    }

    public ValueOperation(String url, TVObjectID objectId, int objectInstanceId, int resourceId) {
        this.url = url;
        this.objectId = objectId.getValue();
        this.objectInstanceId = objectInstanceId;
        this.resourceId = resourceId;
    }

    public void write() {
        RestTemplate r = new RestTemplate();


        Random random = new Random();
        int i = random.nextInt(500);
        TVChannelId channelId = new TVChannelId(i);

        url += "/" + objectId + "/" + objectInstanceId + "/" + resourceId;
        System.out.println("Sending the request (Set Channel = " + i + ") to " + url);
        String s = r.postForObject(url, channelId, String.class );
        System.out.println("Receive response message from " + url );

    }

    public void read() {
        RestTemplate r = new RestTemplate();

        url += "/" + objectId + "/" + objectInstanceId + "/" + resourceId;
        System.out.println("Send read message to " + url);
        TVChannelId channelId = r.getForObject(url, TVChannelId.class);
        System.out.println("Receive response message from " + url );
        System.out.println(channelId);


    }
}
