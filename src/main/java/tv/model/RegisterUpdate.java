package tv.model;

/**
 * Created by Scott on 6/27/15.
 */
public class RegisterUpdate {
    private String endpointClientName;
    private int lifetime;
    private String bindingMode;
    private String SMSNumber;
    private String objectsAndObjectInstances;

    public RegisterUpdate() {
    }

    public RegisterUpdate(String endpointClientName, int lifetime, String bindingMode, String SMSNumber, String objectsAndObjectInstances) {
        this.endpointClientName = endpointClientName;
        this.lifetime = lifetime;
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
        return "RegisterUpdate: {" +
                "endpointClientName='" + endpointClientName + '\'' +
                ", lifetime=" + lifetime +
                ", bindingMode='" + bindingMode + '\'' +
                ", SMSNumber='" + SMSNumber + '\'' +
                ", objectsAndObjectInstances='" + objectsAndObjectInstances + '\'' +
                '}';
    }
}
