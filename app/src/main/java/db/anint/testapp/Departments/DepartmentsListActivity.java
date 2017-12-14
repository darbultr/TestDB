package db.anint.testapp.Departments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
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
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import db.anint.testapp.Models.Department;
import db.anint.testapp.R;
import db.anint.testapp.Routes.RoutesListActivity_;
import db.anint.testapp.Utils.VoiceRecognizer;


@SuppressLint("Registered")
@EActivity(R.layout.activity_departments_list)
public class DepartmentsListActivity extends AppCompatActivity implements RecognitionListener {
    final String TAG = DepartmentsListActivity.class.getSimpleName();
    VoiceRecognizer vr = new VoiceRecognizer();

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

        getDepartments();

        vr.initListener(this, this);

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


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (vr.getListener() != null) {
            vr.getListener().destroy();
            Log.i(TAG, "Listener destroyed");
        }
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
        ArrayList<String> matches = bundle
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        //TODO: Check matches with commands array. if exists do specific action.
        if (matches != null) {
            Toast.makeText(this, matches.get(0), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPartialResults(Bundle bundle) {

    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }
}
