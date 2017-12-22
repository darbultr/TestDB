package db.anint.testapp.Points;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.UUID;

import db.anint.testapp.Models.Point;
import db.anint.testapp.REST.RestClient;


/**
 * Function to get points list from api
 */
@EBean
public class GetPoints {
    @RootContext
    PointsListActivity pointsListActivity;

    @RestService
    RestClient restClient;

    @Background
    public void getPoints(String username, String password, String guid) {
        //TODO: Change after geting extra, so far u:dariuszb p:398kl*ALc5ffn9v
        try {
            restClient.setHttpBasicAuth("dariuszb", "398kl*ALc5ffn9v");
            ArrayList<Point> points = restClient.getPoints(guid);
            for (Point point : points
                    ) {
                point.setUuid(UUID.randomUUID());
            }
            publish(points);
        } catch (Exception ex) {
            publishError(ex);
        }
    }

    @UiThread
    void publish(ArrayList<Point> points) {
        pointsListActivity.showPoints(points);
    }

    @UiThread
    void publishError(Exception ex) {
        pointsListActivity.showErrors(ex);
    }
}
