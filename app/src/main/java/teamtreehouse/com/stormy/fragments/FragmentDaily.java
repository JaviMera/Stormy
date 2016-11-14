package teamtreehouse.com.stormy.fragments;

/**
 * Created by Javi on 11/14/2016.
 */
public class FragmentDaily extends FragmentBase {

    @Override
    public String getTitle() {

        return "Daily";
    }


    public static FragmentDaily newInstance() {

        FragmentDaily fragment = new FragmentDaily();
        return fragment;
    }
}
