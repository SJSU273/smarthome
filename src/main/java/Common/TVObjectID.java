package Common;

/**
 * Created by Scott on 7/16/15.
 */
public enum TVObjectID {
    TV_SECURITY_OBJECT_ID(0),
    TV_SERVER_OBJECT_ID(1),
    TV_ACCESS_CONTROL_OBJECT_ID(2),
    TV_DEVICE_OBJECT_ID(3),
    TV_CHANNEL_OBJECT_ID(10),
    TV_CONTROL_OBJECT_ID(11);

    private final int id;

    TVObjectID(int id) { this.id = id; }

    public int getValue() { return id; }

    public static TVObjectID fromInt(int x) {
        switch(x) {
            case 0:
                return TV_SECURITY_OBJECT_ID;
            case 1:
                return TV_SERVER_OBJECT_ID;
            case 2:
                return TV_ACCESS_CONTROL_OBJECT_ID;
            case 3:
                return TV_DEVICE_OBJECT_ID;
            case 10:
                return TV_CHANNEL_OBJECT_ID;
            case 11:
                return TV_CONTROL_OBJECT_ID;
        }
        return null;
    }

}
