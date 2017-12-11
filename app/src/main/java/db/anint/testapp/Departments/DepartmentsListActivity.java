package db.anint.testapp.Departments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import db.anint.testapp.Models.Department;
import db.anint.testapp.R;
import db.anint.testapp.Routes.RoutesListActivity_;

@SuppressLint("Registered")
@EActivity(R.layout.activity_departments_list)
public class DepartmentsListActivity extends AppCompatActivity {

    @Extra("username")
    String username;

    @Extra("password")
    String password;

    @ViewById
    ListView listDepartments;

    @ViewById
    ConstraintLayout departmentsLayout;

    @Bean
    @NonConfigurationInstance
    GetDepartments getDepartments;
    ProgressDialog progressDialog;

    @Bean
    DepartmentsAdapter departmentsAdapter;

    @AfterViews
    void init() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getTitle() + " - Departments list");
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.downloadingDepartmentsList));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
        tempPopulate();
        //TODO: Uncomment later send extras
      // getDepartments();
    }

    public void getDepartments() {
        getDepartments.getDepartments(username, password);
    }


    public void showDepartments(ArrayList<Department> departments) {
        progressDialog.dismiss();
        departmentsAdapter.update(departments);
        listDepartments.setAdapter(departmentsAdapter);
    }

    @ItemClick
    void listDepartmentsItemClicked(Department department) {
        Intent i = new Intent(this, RoutesListActivity_.class);
        i.putExtra("username", username);
        i.putExtra("password", password);
        i.putExtra("route", department.getSymbol());
        startActivity(i);
    }

    public void showErrors(Exception ex) {
        progressDialog.dismiss();
        Log.e(DepartmentsListActivity.class.getName(), ex.getMessage());
        Snackbar errorBar = Snackbar.make(departmentsLayout, getResources().getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                .setAction(getResources().getString(R.string.retry), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        progressDialog.show();

                        getDepartments();
                    }
                });
        errorBar.show();
    }


    //Temp method to populate listview
    public void tempPopulate() {
        listDepartments.setAdapter(departmentsAdapter);
        progressDialog.dismiss();
    }
}
