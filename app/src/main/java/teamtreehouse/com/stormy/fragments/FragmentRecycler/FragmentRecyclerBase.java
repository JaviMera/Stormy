package teamtreehouse.com.stormy.fragments.FragmentRecycler;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.adapters.AdapterBase;
import teamtreehouse.com.stormy.fragments.FragmentForecastBase;

/**
 * Created by Javi on 11/16/2016.
 */

public abstract class FragmentRecyclerBase extends FragmentForecastBase
        implements FragmentRecyclerView {

    private FragmentActivity mFragmentActivity;
    private FragmentRecyclerPresenter mPresenter;

    protected abstract int getLayoutId();
    protected abstract AdapterBase getAdapter();

    @BindView(R.id.forecastRecycler)
    RecyclerView mRecycler;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mFragmentActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutId(), container, false);

        ButterKnife.bind(this, view);

        mPresenter = new FragmentRecyclerPresenter(this);

        AdapterBase adapter = getAdapter();
        mPresenter.setRecyclerAdapter(adapter);

        int orientation = getOrientation();
        RecyclerView.LayoutManager layoutManager = createLayoutManager(orientation);
        mPresenter.setRecyclerManager(layoutManager);

        mPresenter.setRecyclerSize(true);

        return view;
    }

    @Override
    public void setRecyclerAdapter(AdapterBase adapter) {

        mRecycler.setAdapter(adapter);
    }


    @Override
    public void setRecyclerManager(RecyclerView.LayoutManager manager) {

        mRecycler.setLayoutManager(manager);
    }

    @Override
    public void setRecyclerSize(boolean isFixed) {

        mRecycler.setHasFixedSize(isFixed);
    }

    private int getOrientation() {

        return getResources()
                .getConfiguration()
                .orientation;
    }

    private RecyclerView.LayoutManager createLayoutManager(int orientation) {

        switch(orientation) {

            case Configuration.ORIENTATION_PORTRAIT:
                return new LinearLayoutManager(getActivity());

            case Configuration.ORIENTATION_LANDSCAPE:
                return new GridLayoutManager(mFragmentActivity,
                    1,
                    LinearLayoutManager.HORIZONTAL,
                    false);
        }

        return null;
    }
}