package Refrigerator;

import org.springframework.web.client.RestTemplate;
import LWM2MServer.models.InfoReport;
import tv.repository.AccessControlObject;
import tv.repository.DeviceObject;
import tv.repository.LWM2MSecurityObject;
import tv.repository.LWM2MServerObject;
import tv.service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 * Created by Scott on 6/13/15.
 */
public class RefrigeratorApplication {
    public static void main(String args[]) throws InterruptedException {

        // initialize security object[0]
        LWM2MSecurityObject securityObject = new LWM2MSecurityObject();
        securityObject.setBootstrapServer(true);
        securityObject.setLWM2MServerURI("http://localhost:8081/bootstrap/refrigerator");

        // initialize server object[0]
        LWM2MServerObject serverObject = new LWM2MServerObject();

        // initialize device object[0]
        DeviceObject device = new DeviceObject();
        device.setManufacturer("LG");
        device.setModelNumber("CYE2000AZW");
        device.setSerialNumber("11493143CJ");
        device.setFirmwareVersion("v3.9.6");

        // initialize accessControl object[0]
        AccessControlObject controlObject = new AccessControlObject();


        while (true) {

            PrintMenu.menu();

            try{
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                String s = bufferRead.readLine();


                switch (s) {
                    case "1":
                        BootstrapToServer bootstrapToServer = new BootstrapToServer(securityObject, serverObject, device);
                        bootstrapToServer.boot();
                        break;
                    case "2":
                    case "2.1":
                        RegisterToServer request = new RegisterToServer(securityObject,serverObject,device);
                        request.register();
                        break;
                    case "2.2":
                        RegisterToServer request2 = new RegisterToServer(securityObject,serverObject,device);
                        request2.update();
                        break;
                    case "2.3":
                        RegisterToServer request3 = new RegisterToServer(securityObject,serverObject,device);
                        request3.delete();
                        break;

                    case "3":
                        System.out.println("T.B.D\n\n");
                        break;
                    case "4":
                        ReportToServer reportToServer = new ReportToServer();
                        reportToServer.sendOneReport();
                        break;
                    case "5":
                        ShowStatus showStatus = new ShowStatus(securityObject, serverObject, device, controlObject);
                        showStatus.show();
                        break;

                    default:
                        System.out.println("Please input numbers 1, 2, 3, 4, or 5\n\n");

                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }


    }
}