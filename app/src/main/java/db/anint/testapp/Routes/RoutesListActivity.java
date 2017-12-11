package db.anint.testapp.Routes;

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
import db.anint.testapp.Departments.DepartmentsListActivity;
import db.anint.testapp.Models.Department;
import db.anint.testapp.Models.Route;
import db.anint.testapp.Points.PointsListActivity_;
import db.anint.testapp.R;

@SuppressLint("Registered")
@EActivity(R.layout.activity_routes_list)
public class RoutesListActivity extends AppCompatActivity {

    @Extra("username")
    String username;

    @Extra("password")
    String password;

    @Extra("route")
    String route;

    @ViewById
    ListView listRoutes;

    @ViewById
    ConstraintLayout routesLayout;

    @Bean
    @NonConfigurationInstance
    GetRoutes getRoutes;
    ProgressDialog progressDialog;

    @Bean
    RoutesAdapter routesAdapter;

    @AfterViews
    void init() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getTitle() + " - Routes list");
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.downloadingRoutesList));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
        tempPopulate();
        //TODO: Uncomment later;
      //  getRoutes();
    }

    public void getRoutes() {
        getRoutes.getRoutes(username, password, route);
    }

    public void showRoutes(ArrayList<Route> routes) {
        progressDialog.dismiss();
        routesAdapter.update(routes);
        listRoutes.setAdapter(routesAdapter);
    }

    @ItemClick
    void listRoutesItemClicked(Route route) {
        Intent i = new Intent(this, PointsListActivity_.class);
        i.putExtra("username", username);
        i.putExtra("password", password);
        i.putExtra("guid", route.getGuid());
        i.putExtra("symbol", route.getSymbol());
        startActivity(i);
    }

    public void showErrors(Exception ex) {
        progressDialog.dismiss();
        Log.e(RoutesListActivity.class.getName(), ex.getMessage());
        Snackbar errorBar = Snackbar.make(routesLayout, getResources().getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                .setAction(getResources().getString(R.string.retry), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        progressDialog.show();

                        getRoutes();
                    }
                });

        errorBar.show();
    }
    public void tempPopulate() {
        listRoutes.setAdapter(routesAdapter);
        progressDialog.dismiss();
    }
}
