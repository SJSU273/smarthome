package LWM2MServer.services;

import LWM2MServer.models.InfoReport;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Scott on 6/18/15.
 */
public class DataHouse2 {
    private HashMap<Long, LinkedList<InfoReport>> dataHouse = new HashMap<Long,LinkedList<InfoReport>>();

    public boolean storeData(InfoReport r) {
        if (r == null) return false;
        //return true if no <id, string> existing before, otherwise return false;
        if (dataHouse.containsKey(r.getId())) {
            LinkedList<InfoReport> l = dataHouse.get(r.getId());
            l.add(r);
        } else {
            LinkedList<InfoReport> l = new LinkedList<InfoReport>();
            dataHouse.put(r.getId(), l);
            l.add(r);
        }

        return true;
    }

    public LinkedList<InfoReport> retrieveData(long id) {
        return dataHouse.get(id);
    }
}
