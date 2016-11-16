package teamtreehouse.com.stormy.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by benjakuben on 2/5/15.
 */
public class Day implements Parcelable {

    @SerializedName("summary")
    private String mSummary;

    @SerializedName("data")
    private DayData[] mData;


    protected Day(Parcel in) {
        mSummary = in.readString();
        mData = in.createTypedArray(DayData.CREATOR);
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
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

    public DayData[] getData() {

        return mData;
    }
}











