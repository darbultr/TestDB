package db.anint.testapp.REST;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Part;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.RequiresAuthentication;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

import db.anint.testapp.Departments.GetDepartments;
import db.anint.testapp.Models.Department;
import db.anint.testapp.Points.GetPoints;
import db.anint.testapp.Routes.GetRoutes;
import db.anint.testapp.Utils.Constants;

/**
 * Implementation of REST Client
 */
@Rest(rootUrl = Constants.API_URL, converters = {MappingJackson2HttpMessageConverter.class})
public interface RestClient {

    @Get("oddzialy")
    @RequiresAuthentication
    List<Department> getDepartment();


    @Get("trasy&oddzial={id}")
    @RequiresAuthentication
    GetRoutes getRoutes(@Path String id);

    @Get("miejsca&trasa={id}")
    @RequiresAuthentication
    GetPoints getPoints(@Path String id);

    void setHttpBasicAuth(String username, String password);
}
