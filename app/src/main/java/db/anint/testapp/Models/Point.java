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

@JsonIgnoreProperties(ignoreUnknown = true)
public class Point {
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

    private boolean done = false;

    public Point(){
        super();
    }

    public Point(double lat, double lon, String adres, String klasa, String trasa, boolean done) {
        this.lat = lat;
        this.lon = lon;
        this.adres = adres;
        this.klasa = klasa;
        this.trasa = trasa;
        this.done = done;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getAdres() {
        return adres;
    }

    public String getKlasa() {
        return klasa;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
