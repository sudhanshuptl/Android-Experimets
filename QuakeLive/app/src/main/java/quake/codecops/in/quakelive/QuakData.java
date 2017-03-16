package quake.codecops.in.quakelive;

/**
 * Created by sudhanshu on 12/3/17.
 */

public class QuakData {
    protected double mMagnitude;
    protected String mLocation;
    protected long mDate;
    protected String mUrl;

    public QuakData(double magnitude, String location, long date, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
        mUrl = url;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getDate() {
        return mDate;
    }

    public String getUrl() {
        return mUrl;
    }
}
