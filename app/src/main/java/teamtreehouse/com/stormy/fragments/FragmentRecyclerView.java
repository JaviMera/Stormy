package teamtreehouse.com.stormy.fragments;

import android.support.v7.widget.RecyclerView;

import teamtreehouse.com.stormy.adapters.AdapterBase;

/**
 * Created by Javi on 11/16/2016.
 */
public interface FragmentRecyclerView {

    void setRecyclerAdapter(AdapterBase adapter);
    void setRecyclerManager(RecyclerView.LayoutManager manager);
    void setRecyclerSize(boolean isFixed);
}
