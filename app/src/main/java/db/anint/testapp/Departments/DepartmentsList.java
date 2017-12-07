package db.anint.testapp.Departments;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import db.anint.testapp.Models.Department;
import db.anint.testapp.R;

@EActivity(R.layout.activity_departments_list)
public class DepartmentsList extends AppCompatActivity {

    @ViewById
    ListView listDepartments;

    @Bean
    @NonConfigurationInstance
    GetDepartments getDepartments;
    ProgressDialog progressDialog;

    @AfterViews
    void init() {
        getSupportActionBar().setTitle(getTitle() + " - Departments list");
       progressDialog = new ProgressDialog(this);
       progressDialog.setMessage(getResources().getString(R.string.downloadingDepartmentsList));
       progressDialog.setIndeterminate(true);
       progressDialog.show();

       getDepartments.getDepartments("a","b");
    }

    public void showDepartments(List<Department> departments){
        progressDialog.dismiss();
        ArrayAdapter<Department> adapter = new ArrayAdapter<Department>
                (this, android.R.layout.simple_list_item_1,departments);
        listDepartments.setAdapter(adapter);
    }
    public void showErrors(Exception ex){
        progressDialog.dismiss();
        Log.e(DepartmentsList.class.getName(),ex.getMessage());
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }
}
