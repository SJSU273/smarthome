package tv.repository;

import java.sql.Time;

/**
 * Created by Scott on 7/15/15.
 */
public class TVControlObject {
    private String id;
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
                ", powerSwitch=" + powerSwitch +
                ", volumeOfVoice=" + volumeOfVoice +
                '}';
    }
}
