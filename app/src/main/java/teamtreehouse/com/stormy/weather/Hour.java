package teamtreehouse.com.stormy.weather;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by benjakuben on 2/5/15.
 */
public class Hour implements Parcelable {

    @SerializedName("summary")
    private String mSummary;

    @SerializedName("data")
    private HourData[] mData;

    protected Hour(Parcel in) {
        mSummary = in.readString();
        mData = in.createTypedArray(HourData.CREATOR);
    }

    public static final Creator<Hour> CREATOR = new Creator<Hour>() {
        @Override
        public Hour createFromParcel(Parcel in) {
            return new Hour(in);
        }

        @Override
        public Hour[] newArray(int size) {
            return new Hour[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mSummary);
        dest.writeTypedArray(mData, flags);
    }

    public HourData[] getData() {

        return mData;
    }
}













