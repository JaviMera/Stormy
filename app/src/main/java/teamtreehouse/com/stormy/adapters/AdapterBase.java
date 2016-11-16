package teamtreehouse.com.stormy.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

abstract class AdapterBase<T, H extends ViewHolderBase<T>> extends RecyclerView.Adapter<H> {

    private final Class<H> mType;
    private String mTimezone;

    protected abstract int getLayoutId();

    private T[] mData;

    AdapterBase(T[] data, Class<H> viewHolderType, String timezone) {

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

        if(mType.equals(HourViewHolder.class)) {

            return (H)new HourViewHolder(view);
        }
        else if(mType.equals(DayViewHolder.class)) {

            return (H)new DayViewHolder(view);
        }

        return null;
    }
}
