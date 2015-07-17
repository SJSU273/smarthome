package tv.repository;

import Common.TVObjectID;

import java.sql.Time;

/**
 * Created by Scott on 7/15/15.
 */
public class TVControlObject {
    private String id; //Used by MongoDB

    private final int thisObjectID = TVObjectID.TV_CONTROL_OBJECT_ID.getValue(); // device = 11
    private final int thisObjectInstanceID = 0; // Single = 0

    private int lock; // resource id = 0 Execute resource: Lock the TV (in this project locked status means no operation on console)
    private int volumeOfVoice; //[0...49] resource id = 1
    private int channelId; //[0...499] resource id = 2

    public TVControlObject() {
    }

    public TVControlObject(String id, int lock, int volumeOfVoice, int channelId) {
        this.id = id;
        this.lock = lock;
        this.volumeOfVoice = volumeOfVoice;
        this.channelId = channelId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getThisObjectID() {
        return thisObjectID;
    }

    public int getThisObjectInstanceID() {
        return thisObjectInstanceID;
    }

    public int getLock() {
        return lock;
    }

    public void setLock(int lock) {
        this.lock = lock;
    }

    public int getVolumeOfVoice() {
        return volumeOfVoice;
    }

    public void setVolumeOfVoice(int volumeOfVoice) {
        this.volumeOfVoice = volumeOfVoice;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        return "TVControlObject{" +
                "id='" + id + '\'' +
                ", thisObjectID=" + thisObjectID +
                ", thisObjectInstanceID=" + thisObjectInstanceID +
                ", lock=" + lock +
                ", volumeOfVoice=" + volumeOfVoice +
                ", channelId=" + channelId +
                '}';
    }
}
