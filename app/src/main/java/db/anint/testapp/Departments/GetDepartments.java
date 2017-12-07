package db.anint.testapp.Departments;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.List;

import db.anint.testapp.Models.Department;
import db.anint.testapp.REST.RestClient;

/**
 * Created by darek on 06.12.17.
 */

@EBean
public class GetDepartments {
    @RootContext
    DepartmentsList departmentsList;

    @RestService
    RestClient restClient;

    @Background
    public void getDepartments(String username, String password) {
        //TODO: Change after geting extra, so far u:dariuszb p:398kl*ALc5ffn9v
        try {
            restClient.setHttpBasicAuth("dariuszb", "398kl*ALc5ffn9v");
            List<Department> departments = restClient.getDepartment();
            publish(departments);
        } catch (Exception ex) {
            publishE(ex);
        }
    }

    @UiThread
    void publish(List<Department> departments){departmentsList.showDepartments(departments);}

    @UiThread
    void publishE(Exception ex){departmentsList.showErrors(ex);}
}
