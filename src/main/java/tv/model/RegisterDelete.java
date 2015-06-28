package tv.model;

/**
 * Created by Scott on 6/27/15.
 */
public class RegisterDelete {
    private String endpointClientName;

    public RegisterDelete() {
    }

    public RegisterDelete(String endpointClientName) {
        this.endpointClientName = endpointClientName;
    }

    public String getEndpointClientName() {
        return endpointClientName;
    }

    public void setEndpointClientName(String endpointClientName) {
        this.endpointClientName = endpointClientName;
    }

    @Override
    public String toString() {
        return "RegisterDelete: {" +
                "endpointClientName='" + endpointClientName + '\'' +
                '}';
    }
}
