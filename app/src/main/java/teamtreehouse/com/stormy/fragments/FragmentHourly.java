package teamtreehouse.com.stormy.fragments;

/**
 * Created by Javi on 11/14/2016.
 */
public class FragmentHourly extends ForecastFragmentBase{


    @Override
    protected String getTitle() {

        return "Hourly";
    }

    public static FragmentHourly newInstance() {

        FragmentHourly fragment = new FragmentHourly();
        return fragment;
    }
}
