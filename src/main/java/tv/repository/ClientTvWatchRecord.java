package tv.repository;

import java.util.Date;

/**
 * Created by Scott on 7/17/15.
 */
public class ClientTvWatchRecord {
    private String id; //Used by MongoDB

    //The below is same as TVChannelObject
    private int thisObjectID; //
    private int thisObjectInstanceID; // Multiple = 0...n


    private int channelID;
    private String channelName;
    private Date startTime;
    private Date endTime;

    public ClientTvWatchRecord() {
    }

    public ClientTvWatchRecord(String id, int thisObjectID, int thisObjectInstanceID, int channelID, String channelName, Date startTime, Date endTime) {
        this.id = id;
        this.thisObjectID = thisObjectID;
        this.thisObjectInstanceID = thisObjectInstanceID;
        this.channelID = channelID;
        this.channelName = channelName;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public void setThisObjectID(int thisObjectID) {
        this.thisObjectID = thisObjectID;
    }

    public int getThisObjectInstanceID() {
        return thisObjectInstanceID;
    }

    public void setThisObjectInstanceID(int thisObjectInstanceID) {
        this.thisObjectInstanceID = thisObjectInstanceID;
    }

    public int getChannelID() {
        return channelID;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "ClientTvWatchRecord{" +
                "id='" + id + '\'' +
                ", thisObjectID=" + thisObjectID +
                ", thisObjectInstanceID=" + thisObjectInstanceID +
                ", channelID=" + channelID +
                ", channelName='" + channelName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
