package quake.codecops.in.quakelive;

/**
 * Created by sudhanshu on 12/3/17.
 */

public class QuakData {
    private String magnitude;
    private String location;
    private String date;

    public QuakData(String mag, String loc, String dte){
        magnitude =mag;
        location = loc;
        date = dte;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }
}
