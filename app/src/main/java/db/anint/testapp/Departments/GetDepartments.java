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
            ArrayList<Department> departments = restClient.getDepartment();
            publish(departments);
        } catch (Exception ex) {
            publishError(ex);
        }
    }

    @UiThread
    void publish(ArrayList<Department> departments){
        departmentsListActivity.showDepartments(departments);}

    @UiThread
    void publishError(Exception ex){
        departmentsListActivity.showErrors(ex);}
}
