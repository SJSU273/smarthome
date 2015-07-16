package tv.repository;

/**
 * Created by Scott on 6/26/15.
 */
public class LWM2MServerObject {
    private String id;
    private int ShortServerID;
    private int Lifetime;
    private int DefaultMinimumPeriod;
    private int DefaultMaximumPeriod;
    private int DisableTimeout;
    private boolean NotificationStoringWhenDisabledOrOffline;
    private String BindingPreference;

    public LWM2MServerObject() {
    }

    public LWM2MServerObject(int shortServerID, int lifetime, int defaultMinimumPeriod, int defaultMaximumPeriod, int disableTimeout, boolean notificationStoringWhenDisabledOrOffline, String bindingPreference) {
        ShortServerID = shortServerID;
        Lifetime = lifetime;
        DefaultMinimumPeriod = defaultMinimumPeriod;
        DefaultMaximumPeriod = defaultMaximumPeriod;
        DisableTimeout = disableTimeout;
        NotificationStoringWhenDisabledOrOffline = notificationStoringWhenDisabledOrOffline;
        BindingPreference = bindingPreference;
    }

    public int getShortServerID() {
        return ShortServerID;
    }

    public void setShortServerID(int shortServerID) {
        ShortServerID = shortServerID;
    }

    public int getLifetime() {
        return Lifetime;
    }

    public void setLifetime(int lifetime) {
        Lifetime = lifetime;
    }

    public int getDefaultMinimumPeriod() {
        return DefaultMinimumPeriod;
    }

    public void setDefaultMinimumPeriod(int defaultMinimumPeriod) {
        DefaultMinimumPeriod = defaultMinimumPeriod;
    }

    public int getDefaultMaximumPeriod() {
        return DefaultMaximumPeriod;
    }

    public void setDefaultMaximumPeriod(int defaultMaximumPeriod) {
        DefaultMaximumPeriod = defaultMaximumPeriod;
    }

    public int getDisableTimeout() {
        return DisableTimeout;
    }

    public void setDisableTimeout(int disableTimeout) {
        DisableTimeout = disableTimeout;
    }

    public boolean isNotificationStoringWhenDisabledOrOffline() {
        return NotificationStoringWhenDisabledOrOffline;
    }

    public void setNotificationStoringWhenDisabledOrOffline(boolean notificationStoringWhenDisabledOrOffline) {
        NotificationStoringWhenDisabledOrOffline = notificationStoringWhenDisabledOrOffline;
    }

    public String getBindingPreference() {
        return BindingPreference;
    }

    public void setBindingPreference(String bindingPreference) {
        BindingPreference = bindingPreference;
    }

    @Override
    public String toString() {
        return "LWM2MServerObject: {" +
                "ShortServerID=" + ShortServerID +
                ", Lifetime=" + Lifetime +
                ", DefaultMinimumPeriod=" + DefaultMinimumPeriod +
                ", DefaultMaximumPeriod=" + DefaultMaximumPeriod +
                ", DisableTimeout=" + DisableTimeout +
                ", NotificationStoringWhenDisabledOrOffline=" + NotificationStoringWhenDisabledOrOffline +
                ", BindingPreference='" + BindingPreference + '\'' +
                '}';
    }
}
