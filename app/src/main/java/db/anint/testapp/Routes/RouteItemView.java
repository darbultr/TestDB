package db.anint.testapp.Routes;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import db.anint.testapp.Models.Route;
import db.anint.testapp.R;

/**
 * Binder for route item
 */
@EViewGroup(R.layout.routes_list_item)
public class RouteItemView extends ConstraintLayout {

    @ViewById
    TextView tvRouteName;

    @ViewById
    ConstraintLayout routesLayout;

    public RouteItemView(Context context) {
        super(context);
    }

    public void bind(Route route) {
        tvRouteName.setText(route.getSymbol());
        if (route.isFocus()) {
            routesLayout.setBackgroundResource(R.color.primary_light);
        } else {
            routesLayout.setBackgroundResource(android.R.color.transparent);
        }
    }
}
