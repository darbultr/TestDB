package db.anint.testapp.Departments;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import java.util.ArrayList;


import db.anint.testapp.Models.Department;


/**
 * Adapter used to populate listview with data.
 */
@EBean
public class DepartmentsAdapter extends BaseAdapter {

    private ArrayList<Department> departments = new ArrayList<>();
    @RootContext
    Context context;

    void update(ArrayList<Department> newDepartments) {
        departments.clear();
        departments.addAll(newDepartments);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return departments.size();
    }

    @Override
    public Department getItem(int i) {
        return departments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        DepartmentItemView departmentItemView;

        if (view == null) {
            departmentItemView = DepartmentItemView_.build(context);
        } else {
            departmentItemView = (DepartmentItemView) view;

        }
        departmentItemView.bind(getItem(i));
        return departmentItemView;
    }
}
