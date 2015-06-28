package tv.model;

/**
 * Created by Scott on 6/26/15.
 */
public class BootstrapRequest {

    //end point name format: 'urn:dev:ops:<OUI> - <ProductClass> - <SerialNumber>'
    private String EPN;
    private String Manufacturer;
    private String ModelNumber;
    private String SerialNumber;

    public BootstrapRequest() {
    }

    public BootstrapRequest(String EPN, String manufacturer, String modelNumber, String serialNumber) {
        this.EPN = EPN;
        Manufacturer = manufacturer;
        ModelNumber = modelNumber;
        SerialNumber = serialNumber;
    }

    public String getEPN() {
        return EPN;
    }

    public void setEPN(String EPN) {
        this.EPN = EPN;
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

    @Override
    public String toString() {
        return "BootstrapRequest{" +
                "EPN='" + EPN + '\'' +
                ", Manufacturer='" + Manufacturer + '\'' +
                ", ModelNumber='" + ModelNumber + '\'' +
                ", SerialNumber='" + SerialNumber + '\'' +
                '}';
    }
}