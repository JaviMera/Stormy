package teamtreehouse.com.stormy.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

abstract class AdapterBase<T, H extends ViewHolderBase<T>> extends RecyclerView.Adapter<H> {

    protected abstract int getLayoutId();
    private H mViewHolderType;

    private T[] mData;

    AdapterBase(T[] data) {

        mData = data;
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

        // Here I'd like to return a ViewHolderBase implementation based on the type of H
        return null;
    }
}
