package tv.repository;

import java.sql.Time;

/**
 * Created by Scott on 7/15/15.
 */
public class TVChannelObject {
    private String id; //Used by MongoDB

    private final int thisObjectID = 10; // device = 10
    private int thisObjectInstanceID; // Multiple = 0...n


    private int channelID;
    private String channelName;
    private Time startTime;
    private Time endTime;

    public TVChannelObject() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "TVChannelObject{" +
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
