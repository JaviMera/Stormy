package teamtreehouse.com.stormy.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import teamtreehouse.com.stormy.adapters.viewholders.DayViewHolderLandscape;
import teamtreehouse.com.stormy.adapters.viewholders.DayViewHolderPortrait;
import teamtreehouse.com.stormy.adapters.viewholders.HourViewHolderLandscape;
import teamtreehouse.com.stormy.adapters.viewholders.HourViewHolderPortrait;
import teamtreehouse.com.stormy.adapters.viewholders.ViewHolderBase;

// Abstract class that will be used to represent each the different layout in portrait or landscape
// T represents the item type class , such as DayData and HourData
// H represents the view holder type class.
// This class along with ViewHolderBase, will give the app flexibility to use a Portrait adapter
// and viewholder, to be used in landscape orientation without repeating code.
public abstract class AdapterBase<T, H extends ViewHolderBase<T>> extends RecyclerView.Adapter<H> {

    private final Class<H> mType;

    protected String mTimezone;
    protected abstract int getLayoutId();

    private T[] mData;

    protected AdapterBase(T[] data, Class<H> viewHolderType, String timezone) {

        mData = data;
        mType = viewHolderType;
        mTimezone = timezone;
    }

    @Override
    public int getItemCount() {

        return mData.length;
    }

    @Override
    public void onBindViewHolder(H holder, int position) {

        holder.bind(mData[position], position, mTimezone);
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);

        if(mType.equals(HourViewHolderPortrait.class)) {

            return (H)new HourViewHolderPortrait(view);
        }
        else if(mType.equals(DayViewHolderPortrait.class)) {

            return (H)new DayViewHolderPortrait(view);
        }
        else if(mType.equals(HourViewHolderLandscape.class)) {

            return (H) new HourViewHolderLandscape(view);
        }
        else if(mType.equals(DayViewHolderLandscape.class)) {

            return (H) new DayViewHolderLandscape(view);
        }

        return null;
    }
}
