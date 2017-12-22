package db.anint.testapp.Points;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;

import db.anint.testapp.Models.Point;

/**
 * Adapter used to populate listview with data.
 */
@EBean
public class PointsAdapter extends BaseAdapter {
    public ArrayList<Point> points = new ArrayList<>();

    @RootContext
    Context context;

    void update(ArrayList<Point> newPoints) {
        points.clear();
        points.addAll(newPoints);
        notifyDataSetChanged();
    }

    void delete(int pointId) {
        points.remove(pointId);
        notifyDataSetChanged();
    }
    void checkDone(int pointId){
        getItem(pointId).setDone(true);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return points.size();
    }

    @Override
    public Point getItem(int i) {
        return points.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        PointItemView pointItemView;

        if (view == null) {
            pointItemView = PointItemView_.build(context);
        } else {
            pointItemView = (PointItemView) view;

        }
        pointItemView.bind(getItem(i));
        return pointItemView;
    }
}
