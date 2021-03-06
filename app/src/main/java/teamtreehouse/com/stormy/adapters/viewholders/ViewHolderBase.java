package teamtreehouse.com.stormy.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Locale;
import java.util.Objects;

/**
 * Created by Javi on 11/15/2016.
 */

// Abstract class to represent a viewholder object for Day and Hour items, depending on the orientation
// T represents the item type class, such as DayData or HourData
public abstract class ViewHolderBase<T> extends RecyclerView.ViewHolder {

    public abstract void bind(T data, int position, String timezone);
    protected abstract void setViews();

    public ViewHolderBase(View itemView) {
        super(itemView);

        setViews();
    }

    String valueFormat(String format, Object value) {

        return String.format(Locale.ENGLISH, format, value);
    }
}
