package tv.model;

import tv.repository.LWM2MServerObject;

/**
 * Created by Scott on 6/26/15.
 */
public class BootstrapResponse {
    // for LWM2MSecurityObject
    private String LWM2MServerURI;

    // for LWM2MServerObject
    private LWM2MServerObject serverObject;

    public BootstrapResponse() {
    }

    public BootstrapResponse(String LWM2MServerURI, LWM2MServerObject serverObject) {
        this.LWM2MServerURI = LWM2MServerURI;
        this.serverObject = serverObject;
    }

    public String getLWM2MServerURI() {
        return LWM2MServerURI;
    }

    public void setLWM2MServerURI(String LWM2MServerURI) {
        this.LWM2MServerURI = LWM2MServerURI;
    }

    public LWM2MServerObject getServerObject() {
        return serverObject;
    }

    public void setServerObject(LWM2MServerObject serverObject) {
        this.serverObject = serverObject;
    }

    @Override
    public String toString() {
        return "BootstrapResponse{" +
                "LWM2MServerURI='" + LWM2MServerURI + '\'' +
                ", serverObject=" + serverObject +
                '}';
    }
}
