package Common;

/**
 * Created by Scott on 7/17/15.
 */
public class LWM2MAttribute {
    private int pmin; //minimum period 0
    private int pmax; //maximum period} 1
    private int gt; //{greater than} 2
    private int lt; //{less than} 3
    private int st; //{step} 4
    private String cancel; //cancel 5

    public LWM2MAttribute() {
    }

    public LWM2MAttribute(int pmin, int pmax, int gt, int lt, int st, String cancel) {
        this.pmin = pmin;
        this.pmax = pmax;
        this.gt = gt;
        this.lt = lt;
        this.st = st;
        this.cancel = cancel;
    }



    public int getPmin() {
        return pmin;
    }

    public void setPmin(int pmin) {
        this.pmin = pmin;
    }

    public int getPmax() {
        return pmax;
    }

    public void setPmax(int pmax) {
        this.pmax = pmax;
    }

    public int getGt() {
        return gt;
    }

    public void setGt(int gt) {
        this.gt = gt;
    }

    public int getLt() {
        return lt;
    }

    public void setLt(int lt) {
        this.lt = lt;
    }

    public int getSt() {
        return st;
    }

    public void setSt(int st) {
        this.st = st;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    @Override
    public String toString() {
        return "LWM2MAttribute{" +
                "pmin=" + pmin +
                ", pmax=" + pmax +
                ", gt=" + gt +
                ", lt=" + lt +
                ", st=" + st +
                ", cancel='" + cancel + '\'' +
                '}';
    }
}
