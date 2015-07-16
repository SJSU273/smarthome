package tv.repository;

import java.sql.Time;

/**
 * Created by Scott on 6/26/15.
 */
public class DeviceObject {
    private String id;
    private String Manufacturer;
    private String ModelNumber;
    private String SerialNumber;
    private String FirmwareVersion;
    private int AvailablePowerSources;;
    private int PowerSourceVoltage;
    private int PowerSourceCurrent;
    private int BatteryLevel;
    private int MemoryFree;
    private int ErrorCode;
    private Time CurrentTime;
    private String UTCOffset;
    private String SupportedBindingAndModes;

    public DeviceObject() {
    }

    public DeviceObject(String manufacturer, String modelNumber, String serialNumber, String firmwareVersion, int availablePowerSources, int powerSourceVoltage, int powerSourceCurrent, int batteryLevel, int memoryFree, int errorCode, Time currentTime, String UTCOffset, String supportedBindingAndModes) {
        Manufacturer = manufacturer;
        ModelNumber = modelNumber;
        SerialNumber = serialNumber;
        FirmwareVersion = firmwareVersion;
        AvailablePowerSources = availablePowerSources;
        PowerSourceVoltage = powerSourceVoltage;
        PowerSourceCurrent = powerSourceCurrent;
        BatteryLevel = batteryLevel;
        MemoryFree = memoryFree;
        ErrorCode = errorCode;
        CurrentTime = currentTime;
        this.UTCOffset = UTCOffset;
        SupportedBindingAndModes = supportedBindingAndModes;
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

    public String getFirmwareVersion() {
        return FirmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        FirmwareVersion = firmwareVersion;
    }

    public int getAvailablePowerSources() {
        return AvailablePowerSources;
    }

    public void setAvailablePowerSources(int availablePowerSources) {
        AvailablePowerSources = availablePowerSources;
    }

    public int getPowerSourceVoltage() {
        return PowerSourceVoltage;
    }

    public void setPowerSourceVoltage(int powerSourceVoltage) {
        PowerSourceVoltage = powerSourceVoltage;
    }

    public int getPowerSourceCurrent() {
        return PowerSourceCurrent;
    }

    public void setPowerSourceCurrent(int powerSourceCurrent) {
        PowerSourceCurrent = powerSourceCurrent;
    }

    public int getBatteryLevel() {
        return BatteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        BatteryLevel = batteryLevel;
    }

    public int getMemoryFree() {
        return MemoryFree;
    }

    public void setMemoryFree(int memoryFree) {
        MemoryFree = memoryFree;
    }

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }

    public Time getCurrentTime() {
        return CurrentTime;
    }

    public void setCurrentTime(Time currentTime) {
        CurrentTime = currentTime;
    }

    public String getUTCOffset() {
        return UTCOffset;
    }

    public void setUTCOffset(String UTCOffset) {
        this.UTCOffset = UTCOffset;
    }

    public String getSupportedBindingAndModes() {
        return SupportedBindingAndModes;
    }

    public void setSupportedBindingAndModes(String supportedBindingAndModes) {
        SupportedBindingAndModes = supportedBindingAndModes;
    }

    @Override
    public String toString() {
        return "DeviceObject: {" +
                "Manufacturer='" + Manufacturer + '\'' +
                ", ModelNumber='" + ModelNumber + '\'' +
                ", SerialNumber='" + SerialNumber + '\'' +
                ", FirmwareVersion='" + FirmwareVersion + '\'' +
                ", AvailablePowerSources=" + AvailablePowerSources +
                ", PowerSourceVoltage=" + PowerSourceVoltage +
                ", PowerSourceCurrent=" + PowerSourceCurrent +
                ", BatteryLevel=" + BatteryLevel +
                ", MemoryFree=" + MemoryFree +
                ", ErrorCode=" + ErrorCode +
                ", CurrentTime=" + CurrentTime +
                ", UTCOffset='" + UTCOffset + '\'' +
                ", SupportedBindingAndModes='" + SupportedBindingAndModes + '\'' +
                '}';
    }
}

