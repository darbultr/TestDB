package db.anint.testapp.Departments;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import db.anint.testapp.Models.Department;
import db.anint.testapp.R;

/**
 * Binder for depertment item
 */
@EViewGroup(R.layout.department_list_item)
public class DepartmentItemView extends ConstraintLayout {

    @ViewById
    TextView tvDepartmentName;

    @ViewById
    ConstraintLayout departmentsLayout;

    public DepartmentItemView(Context context) {
        super(context);
    }

    public void bind(Department department) {
        tvDepartmentName.setText(department.getOpis());
        if (department.isFocus()) {
            departmentsLayout.setBackgroundResource(R.color.primary_light);
        } else {
            departmentsLayout.setBackgroundResource(android.R.color.transparent);
        }
    }
}
