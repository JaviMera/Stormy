package teamtreehouse.com.stormy.fragments.FragmentRecycler;

import android.support.v7.widget.RecyclerView;

import teamtreehouse.com.stormy.adapters.AdapterBase;

/**
 * Created by Javi on 11/16/2016.
 */
public class FragmentRecyclerPresenter {

    private FragmentRecyclerView mView;

    public FragmentRecyclerPresenter(FragmentRecyclerView view) {

        mView = view;
    }

    public void setRecyclerAdapter(AdapterBase adapter) {

        mView.setRecyclerAdapter(adapter);
    }

    public void setRecyclerManager(RecyclerView.LayoutManager manager) {

        mView.setRecyclerManager(manager);
    }

    public void setRecyclerSize(boolean isFixed) {

        mView.setRecyclerSize(isFixed);
    }
}
