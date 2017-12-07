package db.anint.testapp.REST;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.RequiresAuthentication;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;

import db.anint.testapp.Models.Department;
import db.anint.testapp.Models.Point;
import db.anint.testapp.Models.Route;
import db.anint.testapp.Utils.Constants;

/**
 * Implementation of REST Client
 */


@Rest(rootUrl = Constants.API_URL, converters =
        {MappingJackson2HttpMessageConverter.class,
                FormHttpMessageConverter.class,
                StringHttpMessageConverter.class})
public interface RestClient {


    @Get("oddzialy")
    @RequiresAuthentication
    ArrayList<Department> getDepartment();

    @Get("trasy&oddzial={id}")
    @RequiresAuthentication
    ArrayList<Route> getRoutes(@Path String id);

    @Get("miejsca&trasa={id}")
    @RequiresAuthentication
    ArrayList<Point> getPoints(@Path String id);

    void setHttpBasicAuth(String username, String password);
}
