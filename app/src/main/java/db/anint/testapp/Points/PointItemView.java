package db.anint.testapp.Points;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import db.anint.testapp.Models.Point;
import db.anint.testapp.R;

/**
 * Binder for point item
 */
@EViewGroup(R.layout.point_list_item)
public class PointItemView extends RelativeLayout{
    @ViewById
    TextView tvPointAddress;
    @ViewById
    TextView tvPointClass;

    public PointItemView(Context context) {
        super(context);
    }

    public void bind(Point point) {
        tvPointAddress.setText(point.getAdres());
        tvPointClass.setText(point.getKlasa());
        if(point.isDone()){
           tvPointAddress.setTextColor(getResources().getColor(R.color.primary_dark));
        }
    }

}
