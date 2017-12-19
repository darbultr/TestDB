package db.anint.testapp.Points;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
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

import db.anint.testapp.Login.LoginActivity_;
import db.anint.testapp.Models.Point;
import db.anint.testapp.R;
import db.anint.testapp.Utils.VoiceRecognizer;


@SuppressLint("Registered")
@EActivity(R.layout.activity_points_list)
public class PointsListActivity extends AppCompatActivity implements RecognitionListener {
    final String TAG = PointsListActivity.class.getSimpleName();
    VoiceRecognizer vr = new VoiceRecognizer();
    Snackbar confirm;
    int possition = 0;

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
        initUtils();
        getPoints();
    }

    public void initUtils() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.downloadingPointsList));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        confirm = Snackbar
                .make(pointsLayout, getResources().getString(R.string.exitApp), Snackbar.LENGTH_INDEFINITE);

        vr.initListener(this, this);
    }

    public void getPoints() {
        getPoints.getPoints(username, password, guid);
    }

    public void showPoints(ArrayList<Point> points) {
        progressDialog.dismiss();
        pointsAdapter.update(points);
        listPoints.setAdapter(pointsAdapter);
        setFocus();
        vr.startListener();
    }

    public void showErrors(Exception ex) {
        vr.getListener().stopListening();
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

    public void setFocus() {
        pointsAdapter.getItem(possition).setFocus(true);
        pointsAdapter.notifyDataSetChanged();
    }

    public void clearFocus() {
        pointsAdapter.getItem(possition).setFocus(false);
        pointsAdapter.notifyDataSetChanged();
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
                    //TODO: CREATE FUNCTIONS
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

    @Override
    public void onResume() {
        super.onResume();
        if (vr != null) {
            vr.startListener();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (vr.getListener() != null) {
            vr.getListener().destroy();
            Log.i(TAG, "Listener destroyed");
        }
    }

    //TODO: Method to save data from made operations to json, { point guid, operation, location(?where was made?), time, photo name
    public void saveOperationData() {

    }

     /*
        RecognitionListener methods
     */

    @Override
    public void onReadyForSpeech(Bundle bundle) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float v) {

    }

    @Override
    public void onBufferReceived(byte[] bytes) {

    }

    @Override
    public void onEndOfSpeech() {
        vr.startListener();
    }

    @Override
    public void onError(int i) {
        vr.startListener();
    }

    @Override
    public void onResults(Bundle bundle) {
        ArrayList<String> matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

        if (matches != null) {
            String command = vr.
                    executeCommands(
                            matches,
                            listPoints,
                            possition,
                            this,
                            pointsAdapter,
                            confirm,
                            true);

            switch (command) {
                case "down":
                    clearFocus();
                    possition++;
                    setFocus();
                    break;
                case "up":
                    clearFocus();
                    possition--;
                    setFocus();
                    break;
                case "yes":
                    Intent intent = new Intent(getApplicationContext(), LoginActivity_.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("EXIT", true);
                    startActivity(intent);
                    break;
                case "navigate":

                    break;
                case "delete":
                    break;
                case "completed":
                    break;
                case "photo":
                    break;


            }
        }
    }

    @Override
    public void onPartialResults(Bundle bundle) {

    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }
}
