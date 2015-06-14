package smarthome;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Scott on 6/13/15.
 */
public class DataHouse {
    private HashMap<Long, String> dataHouse = new HashMap<Long,String>();

    public boolean storeData(InfoReport r) {
        if (r == null) return false;
        //return true if no <id, string> existing before, otherwise return false;
        dataHouse.put(r.getId(), r.getMsg());
        return true;
    }

    public InfoReport retrieveData(long id) {
        return new InfoReport(id, dataHouse.get(id));
    }



}
