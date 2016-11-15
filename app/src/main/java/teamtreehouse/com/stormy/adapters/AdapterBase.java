package teamtreehouse.com.stormy.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

abstract class AdapterBase<T, H extends ViewHolderBase<T>> extends RecyclerView.Adapter<H> {

    private final Class<H> mType;
    protected abstract int getLayoutId();

    private T[] mData;

    AdapterBase(T[] data, Class<H> viewHolderType) {

        mData = data;
        mType = viewHolderType;
    }

    @Override
    public int getItemCount() {

        return mData.length;
    }

    @Override
    public void onBindViewHolder(H holder, int position) {

        holder.bind(mData[position]);
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);

        if(mType.getClass().equals(HourViewHolder.class)) {

            return (H)new HourViewHolder(view);
        }

        return null;
    }
}
