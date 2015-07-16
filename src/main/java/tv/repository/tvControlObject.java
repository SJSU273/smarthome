package tv.repository;

import java.sql.Time;

/**
 * Created by Scott on 7/15/15.
 */
public class TVControlObject {
    private String id; //Used by MongoDB

    private final int thisObjectID = 11; // device = 11
    private final int thisObjectInstanceID = 0; // Single = 0

    private Boolean powerSwitch; //True: ON, False: OFF
    private int volumeOfVoice; //0: OFF

    public TVControlObject() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getPowerSwitch() {
        return powerSwitch;
    }

    public void setPowerSwitch(Boolean powerSwitch) {
        this.powerSwitch = powerSwitch;
    }

    public int getVolumeOfVoice() {
        return volumeOfVoice;
    }

    public void setVolumeOfVoice(int volumeOfVoice) {
        this.volumeOfVoice = volumeOfVoice;
    }

    @Override
    public String toString() {
        return "TVControlObject{" +
                "id='" + id + '\'' +
                ", thisObjectID=" + thisObjectID +
                ", thisObjectInstanceID=" + thisObjectInstanceID +
                ", powerSwitch=" + powerSwitch +
                ", volumeOfVoice=" + volumeOfVoice +
                '}';
    }
}
