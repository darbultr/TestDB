package db.anint.testapp.Routes;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;

import db.anint.testapp.Models.Route;
import db.anint.testapp.REST.RestClient;

/**
 * Function to get routes list from api
 */

@EBean
public class GetRoutes {
    @RootContext
    RoutesListActivity routesListActivity;

    @RestService
    RestClient restClient;

    @Background
    public void getRoutes(String username, String password, String id) {
        //TODO: Change after geting extra, so far u:dariuszb p:398kl*ALc5ffn9v
        try {
            restClient.setHttpBasicAuth("dariuszb", "398kl*ALc5ffn9v");
            ArrayList<Route> routes = restClient.getRoutes(id);
            publish(routes);
        } catch (Exception ex) {
            publishError(ex);
        }
    }

    @UiThread
    void publish(ArrayList<Route> routes){
        routesListActivity.showRoutes(routes);}

    @UiThread
    void publishError(Exception ex){
        routesListActivity.showErrors(ex);}
}
