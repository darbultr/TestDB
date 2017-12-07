package db.anint.testapp.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Point class to download into listview
 {
 "lat": "53.911135",
 "lon": "18.217692",
 "adres": "ul.Osiedlowa 5, 83-260 Frank, poczta Kaliska (Kaliska, POMORSKIE)",
 "klasa": "WŁASNE_ZUŻYCIE",
 "trasa": "SO-3"
 }
 */

public class Point {
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("lat")
    private double lat;

    @JsonProperty("lon")
    private double lon;

    @JsonProperty("adres")
    private String adres;

    @JsonProperty("klasa")
    private String klasa;

    @JsonProperty("trasa")
    private String trasa;



}
