package LWM2MServer.services;

import Common.LWM2MAttribute;
import Common.TVObjectID;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Scott on 7/16/15.
 */
public class AttributeOperation {
    String url;
    int objectId;
    int objectInstanceId;
    int resourceId;

    public AttributeOperation() {
    }

    public AttributeOperation(String url, TVObjectID objectId, int objectInstanceId, int resourceId) {
        this.url = url;
        this.objectId = objectId.getValue();
        this.objectInstanceId = objectInstanceId;
        this.resourceId = resourceId;
    }

    //set step
    public void writeAttribute(int step) {
        RestTemplate r = new RestTemplate();

        LWM2MAttribute attribute = new LWM2MAttribute();
        attribute.setSt(step);

        url += "/" + objectId + "/" + objectInstanceId + "/" + resourceId;
        System.out.println("Sending the request: " + url);
        r.put(url, attribute);

    }

    //Cancel observation
    public void writeAttribute(String s) {
        RestTemplate r = new RestTemplate();

        LWM2MAttribute attribute = new LWM2MAttribute();
        attribute.setCancel(s);

        url += "/" + objectId + "/" + objectInstanceId + "/" + resourceId;
        System.out.println("Sending the request: " + url);
        r.put(url, attribute);

    }
    public void discover() {
        RestTemplate r = new RestTemplate();

        url += "/" + objectId + "/" + objectInstanceId + "/" + resourceId;
        System.out.println("Send discover message to " + url);
        LWM2MAttribute attribute = r.getForObject(url, LWM2MAttribute.class);
        System.out.println("Receive response message from " + url );
        System.out.println(attribute);


    }

    public void execute() {


        RestTemplate r = new RestTemplate();

        url += "/" + objectId + "/" + objectInstanceId + "/" + resourceId;
        System.out.println("Send execute message to " + url);
        String result = r.postForObject(url,null, String.class);
        System.out.println("Receive response message from " + url );
        System.out.println(result);


    }
}
