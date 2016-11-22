package teamtreehouse.com.stormy.ui;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

/**
 * Created by Javi on 11/16/2016.
 */

public interface ResultView {

    void startActivityForResult(String serviceName);
    void startActivityForResult(int requestCode);
}
