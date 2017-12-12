package db.anint.testapp.Departments;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;


import java.util.ArrayList;

import db.anint.testapp.Models.Department;
import db.anint.testapp.REST.RestClient;

/**
 * Function to get routes list from api
 */

@EBean
public class GetDepartments {

    @RootContext
    DepartmentsListActivity departmentsListActivity;

    @RestService
    RestClient restClient;

    @Background
    public void getDepartments(String username, String password) {
        //TODO: Change after geting extra, so far u:dariuszb p:398kl*ALc5ffn9v
        try {
            restClient.setHttpBasicAuth("dariuszb", "398kl*ALc5ffn9v");
            publish(restClient.getDepartment());
        } catch (Exception ex) {
            publishError(ex);
        }
    }

    @UiThread
    void publish(ArrayList<Department> departmentsArray) {
        departmentsListActivity.showDepartments(departmentsArray);
    }

    @UiThread
    void publishError(Exception ex) {
        departmentsListActivity.showErrors(ex);
    }
}
