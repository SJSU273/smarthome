package LWM2MServer.models;


import org.springframework.data.annotation.Id;

/**
 * Created by Scott on 6/25/15.
 */
public class IoTClient {
    @Id
    private String endpointClientName;
    private int lifetime;
    private String LWM2MVersion;
    private String bindingMode;
    private String SMSNumber;
    private String objectsAndObjectInstances;

    public IoTClient() {
    }

    public IoTClient(String endpointClientName, int lifetime, String LWM2MVersion, String bindingMode, String SMSNumber, String objectsAndObjectInstances) {
        this.endpointClientName = endpointClientName;
        this.lifetime = lifetime;
        this.LWM2MVersion = LWM2MVersion;
        this.bindingMode = bindingMode;
        this.SMSNumber = SMSNumber;
        this.objectsAndObjectInstances = objectsAndObjectInstances;
    }

    public String getEndpointClientName() {
        return endpointClientName;
    }

    public void setEndpointClientName(String endpointClientName) {
        this.endpointClientName = endpointClientName;
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public String getLWM2MVersion() {
        return LWM2MVersion;
    }

    public void setLWM2MVersion(String LWM2MVersion) {
        this.LWM2MVersion = LWM2MVersion;
    }

    public String getBindingMode() {
        return bindingMode;
    }

    public void setBindingMode(String bindingMode) {
        this.bindingMode = bindingMode;
    }

    public String getSMSNumber() {
        return SMSNumber;
    }

    public void setSMSNumber(String SMSNumber) {
        this.SMSNumber = SMSNumber;
    }

    public String getObjectsAndObjectInstances() {
        return objectsAndObjectInstances;
    }

    public void setObjectsAndObjectInstances(String objectsAndObjectInstances) {
        this.objectsAndObjectInstances = objectsAndObjectInstances;
    }

    @Override
    public String toString() {
        return "IoTClient: {" +
                "endpointClientName='" + endpointClientName + '\'' +
                ", lifetime=" + lifetime +
                ", LWM2MVersion='" + LWM2MVersion + '\'' +
                ", bindingMode='" + bindingMode + '\'' +
                ", SMSNumber='" + SMSNumber + '\'' +
                ", objectsAndObjectInstances='" + objectsAndObjectInstances + '\'' +
                '}';
    }
}
