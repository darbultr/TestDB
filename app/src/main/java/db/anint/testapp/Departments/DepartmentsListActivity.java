package db.anint.testapp.Departments;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
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
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import db.anint.testapp.Login.LoginActivity_;
import db.anint.testapp.Models.Department;
import db.anint.testapp.R;
import db.anint.testapp.Routes.RoutesListActivity_;
import db.anint.testapp.Utils.VoiceRecognizer;


@SuppressLint("Registered")
@EActivity(R.layout.activity_departments_list)
public class DepartmentsListActivity extends AppCompatActivity implements RecognitionListener {
    final String TAG = DepartmentsListActivity.class.getSimpleName();
    VoiceRecognizer vr = new VoiceRecognizer();
    Snackbar confirm;
    int possition = 0;

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
        initUtils();
        getDepartments();

    }

    //TODO: Set within Utils
    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {
        String permissions = "android.permission.RECORD_AUDIO";
        int requestCode = 200;
        requestPermissions(new String[]{permissions}, requestCode);
    }

    public void initUtils() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.downloadingDepartmentsList));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        confirm = Snackbar
                .make(departmentsLayout, getResources().getString(R.string.exitApp), Snackbar.LENGTH_INDEFINITE);

        vr.initListener(this, this);
    }

    public void getDepartments() {
        getDepartments.getDepartments(username, password);
    }

    public void showDepartments(ArrayList<Department> departments) {
        progressDialog.dismiss();
        departmentsAdapter.update(departments);
        listDepartments.setAdapter(departmentsAdapter);
        setFocus();
        if (shouldAskPermissions()) {
            askPermissions();
        }
        vr.startListener();
    }

    public void showErrors(Exception ex) {
        vr.getListener().stopListening();
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

    public void setFocus() {
        departmentsAdapter.getItem(possition).setFocus(true);
        departmentsAdapter.notifyDataSetChanged();
    }

    public void clearFocus() {
        departmentsAdapter.getItem(possition).setFocus(false);
        departmentsAdapter.notifyDataSetChanged();
    }

    @ItemClick
    void listDepartmentsItemClicked(final int pos) {
        clearFocus();
        possition = pos;
        Intent i = new Intent(this, RoutesListActivity_.class);
        i.putExtra("username", username);
        i.putExtra("password", password);
        i.putExtra("route", departmentsAdapter.getItem(pos).getSymbol());
        startActivity(i);
    }

    @ItemLongClick
    void listDepartmentsItemLongClicked(final int pos) {
        clearFocus();
        possition = pos;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (vr == null) {
            vr = new VoiceRecognizer();
            vr.initListener(this, this);
            vr.startListener();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (vr.getListener() != null) {
            vr.getListener().stopListening();
            vr.getListener().cancel();
            vr.getListener().destroy();
        }
        vr = null;
        Log.i(TAG, "Listener stoped");

    }

    /*
        RecognitionListener methods
     */

    @Override
    public void onReadyForSpeech(Bundle bundle) {
        Log.e(TAG, "ready for speech");
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
        String message;
        switch (i) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Not recognised";
                break;
        }
        Log.e(TAG, message);
        vr.startListener();
    }

    @Override
    public void onResults(Bundle bundle) {
        ArrayList<String> matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

        if (matches != null) {
            String command = vr.
                    executeCommands(
                            matches,
                            listDepartments,
                            possition,
                            this,
                            departmentsAdapter,
                            confirm,
                            false);

            switch (command) {
                case "down":
                    clearFocus();
                    listDepartments.clearChoices();
                    departmentsAdapter.notifyDataSetChanged();
                    possition++;
                    setFocus();
                    break;
                case "up":
                    clearFocus();
                    listDepartments.clearChoices();
                    departmentsAdapter.notifyDataSetChanged();
                    possition--;
                    setFocus();
                    break;
                case "yes":
                    Intent intent = new Intent(getApplicationContext(), LoginActivity_.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("EXIT", true);
                    startActivity(intent);
                    break;
            }
        }
    }

    @Override
    public void onPartialResults(Bundle bundle) {
    }

    @Override
    public void onEvent(int i, Bundle bundle) {
        Log.e(TAG, "event" + i + bundle.toString());
    }
}
