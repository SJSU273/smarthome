package tv.service;

import LWM2MBootstrapServer.repository.LWM2MDevice;
import org.springframework.beans.factory.annotation.Autowired;
import tv.repository.AccessControlObject;
import tv.repository.DeviceObject;
import tv.repository.LWM2MSecurityObject;
import tv.repository.LWM2MServerObject;

/**
 * Created by Scott on 6/26/15.
 */
public class ShowStatus {

    LWM2MSecurityObject securityObject;
    LWM2MServerObject serverObject;
    DeviceObject device;
    AccessControlObject controlObject;

    public ShowStatus(LWM2MSecurityObject securityObject, LWM2MServerObject serverObject, DeviceObject device, AccessControlObject controlObject) {
        this.securityObject = securityObject;
        this.serverObject = serverObject;
        this.device = device;
        this.controlObject = controlObject;
    }

    public void show() {
        String stars = new String(new char[50]).replace("\0", "-");
        String status = "The current status";

        System.out.printf("%-50s\n",status);
        System.out.println(stars);

        System.out.println(securityObject);
        System.out.println(serverObject);
        System.out.println(device);
        System.out.println(controlObject);
    }
}
