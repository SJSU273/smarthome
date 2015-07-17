package Common;

/**
 * Created by Scott on 7/17/15.
 */
public class TVChannelId {
    int channelId;

    public TVChannelId() {
    }

    public TVChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        return "TVChannelId{" +
                "channelId=" + channelId +
                '}';
    }
}
