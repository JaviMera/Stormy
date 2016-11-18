package teamtreehouse.com.stormy.fragments.FragmentCurrent;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.fragments.FragmentCurrentBase;
import teamtreehouse.com.stormy.fragments.FragmentForecastBase;
import teamtreehouse.com.stormy.model.Current;
import teamtreehouse.com.stormy.model.Forecast;

public class FragmentCurrentPhone extends FragmentCurrentBase
{
    public static FragmentCurrentPhone newInstance(Current forecastCurrent, String timezone) {

        FragmentCurrentPhone fragment = new FragmentCurrentPhone();

        Bundle bundle = new Bundle();
        bundle.putParcelable(FORECAST_CURRENT, forecastCurrent);
        bundle.putString(Forecast.FORECAST_TIMEZONE, timezone);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_current_phone;
    }
}
