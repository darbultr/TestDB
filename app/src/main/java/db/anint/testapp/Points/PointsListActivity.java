package db.anint.testapp.Points;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import db.anint.testapp.Models.Point;
import db.anint.testapp.R;


@SuppressLint("Registered")
@EActivity(R.layout.activity_points_list)
public class PointsListActivity extends AppCompatActivity {


    @Extra("username")
    String username;

    @Extra("password")
    String password;

    @Extra("guid")
    String guid;

    @Extra("symbol")
    String symbol;

    @ViewById
    ListView listPoints;

    @ViewById
    ConstraintLayout pointsLayout;

    @Bean
    @NonConfigurationInstance
    GetPoints getPoints;
    ProgressDialog progressDialog;

    @Bean
    PointsAdapter pointsAdapter;

    @AfterViews
    void init() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getTitle() + " - " + symbol);
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.downloadingPointsList));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        getPoints();
    }

    public void getPoints() {
        getPoints.getPoints(username, password, guid);
    }

    public void showPoints(ArrayList<Point> points) {
        progressDialog.dismiss();
        pointsAdapter.update(points);
        listPoints.setAdapter(pointsAdapter);
    }

    @ItemLongClick
    void listPointsItemLongClicked(final int pos) {
        String options[] = new String[]
                {this.getResources().getString(R.string.navigate),
                        this.getResources().getString(R.string.delete),
                        this.getResources().getString(R.string.makePhoto),
                        this.getResources().getString(R.string.done)};

        AlertDialog.Builder optionsDialog = new AlertDialog.Builder(this);
        optionsDialog.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        Intent navigate = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://maps.google.com/maps?daddr="
                                        + pointsAdapter.getItem(pos).getLat() + ","
                                        + pointsAdapter.getItem(pos).getLon()));
                        navigate.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                        startActivity(navigate);
                        break;
                    case 1:
                        final Snackbar confirm = Snackbar.make(pointsLayout, getResources().getString(R.string.confirmDelete), Snackbar.LENGTH_LONG)
                                .setAction(getResources().getString(R.string.yes), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        pointsAdapter.delete(pos);
                                    }
                                });
                        confirm.show();

                        break;
                    case 2:
                        //TODO: Make photo and save name to json
                        Toast.makeText(PointsListActivity.this, "Zdjęcie", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        pointsAdapter.checkDone(pos);
                        break;
                }
            }
        });
        optionsDialog.show();
    }

    public void showErrors(Exception ex) {
        progressDialog.dismiss();
        Log.e(PointsListActivity.class.getName(), ex.getMessage());
        Snackbar errorBar = Snackbar.make(pointsLayout, getResources().getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                .setAction(getResources().getString(R.string.retry), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        progressDialog.show();
                        getPoints();
                    }
                });
        errorBar.show();
    }

    //TODO: Method to save data from made operations to json, { point guid, operation, location(?where was made?), time, photo name
    public void saveOperationData() {

    }
}
