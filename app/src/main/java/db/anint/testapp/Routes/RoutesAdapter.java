package db.anint.testapp.Routes;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;

import db.anint.testapp.Models.Route;

/**
 * Adapter used to populate listview with data.
 */
@EBean
public class RoutesAdapter extends BaseAdapter{
    private ArrayList<Route> routes = new ArrayList<>();

    @RootContext
    Context context;

    void update(ArrayList<Route> newRoutes){
        routes.clear();
        routes.addAll(newRoutes);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return routes.size();
    }

    @Override
    public Route getItem(int i) {
        return routes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RouteItemView routeItemView;

        if (view == null) {
            routeItemView = RouteItemView_.build(context);
        } else {
            routeItemView = (RouteItemView) view;

        }
        routeItemView.bind(getItem(i));
        return routeItemView;
    }
}
