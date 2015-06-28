package tv.repository;

/**
 * Created by Scott on 6/26/15.
 */
public class AccessControlObject {
    private int ObjectID;
    private int ObjectInstanceID;
    private int ACL;
    private int AccessControlOwner;

    public AccessControlObject() {
    }

    public AccessControlObject(int objectID, int objectInstanceID, int ACL, int accessControlOwner) {
        ObjectID = objectID;
        ObjectInstanceID = objectInstanceID;
        this.ACL = ACL;
        AccessControlOwner = accessControlOwner;
    }

    public int getObjectID() {
        return ObjectID;
    }

    public void setObjectID(int objectID) {
        ObjectID = objectID;
    }

    public int getObjectInstanceID() {
        return ObjectInstanceID;
    }

    public void setObjectInstanceID(int objectInstanceID) {
        ObjectInstanceID = objectInstanceID;
    }

    public int getACL() {
        return ACL;
    }

    public void setACL(int ACL) {
        this.ACL = ACL;
    }

    public int getAccessControlOwner() {
        return AccessControlOwner;
    }

    public void setAccessControlOwner(int accessControlOwner) {
        AccessControlOwner = accessControlOwner;
    }

    @Override
    public String toString() {
        return "AccessControlObject: {" +
                "ObjectID=" + ObjectID +
                ", ObjectInstanceID=" + ObjectInstanceID +
                ", ACL=" + ACL +
                ", AccessControlOwner=" + AccessControlOwner +
                '}';
    }
}
