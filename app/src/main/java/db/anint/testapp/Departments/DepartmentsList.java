package db.anint.testapp.Departments;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

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
    }
}
