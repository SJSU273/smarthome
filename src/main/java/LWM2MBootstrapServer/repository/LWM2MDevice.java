package LWM2MBootstrapServer.repository;


import org.springframework.data.annotation.Id;
import tv.repository.LWM2MServerObject;

/**
 * Created by Scott on 6/26/15.
 */
public class LWM2MDevice {
    @Id
    private String id; //urn:dev:ops:<OUI> - <ProductClass> - <SerialNumber>

    private String Manufacturer;
    private String ModelNumber;
    private String SerialNumber;

    private String registerSeverUri;

    private LWM2MServerObject serverObject;

    public LWM2MDevice() {
    }

    public LWM2MDevice(String id, String manufacturer, String modelNumber, String serialNumber, String registerSeverUri, LWM2MServerObject serverObject) {
        this.id = id;
        Manufacturer = manufacturer;
        ModelNumber = modelNumber;
        SerialNumber = serialNumber;
        this.registerSeverUri = registerSeverUri;
        this.serverObject = serverObject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getModelNumber() {
        return ModelNumber;
    }

    public void setModelNumber(String modelNumber) {
        ModelNumber = modelNumber;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getRegisterSeverUri() {
        return registerSeverUri;
    }

    public void setRegisterSeverUri(String registerSeverUri) {
        this.registerSeverUri = registerSeverUri;
    }

    public LWM2MServerObject getServerObject() {
        return serverObject;
    }

    public void setServerObject(LWM2MServerObject serverObject) {
        this.serverObject = serverObject;
    }

    @Override
    public String toString() {
        return "LWM2MDevice{" +
                "id='" + id + '\'' +
                ", Manufacturer='" + Manufacturer + '\'' +
                ", ModelNumber='" + ModelNumber + '\'' +
                ", SerialNumber='" + SerialNumber + '\'' +
                ", registerSeverUri='" + registerSeverUri + '\'' +
                ", serverObject=" + serverObject +
                '}';
    }
}

