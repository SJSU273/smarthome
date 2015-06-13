package smarthome;

import javax.validation.constraints.Null;

/**
 * Created by Scott on 6/12/15.
 */
public class InfoReport {
    private long id;
    private String msg;

    public InfoReport(long id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
