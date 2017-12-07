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

    public Point(double lat, double lon, String adres, String klasa, String trasa) {
        this.lat = lat;
        this.lon = lon;
        this.adres = adres;
        this.klasa = klasa;
        this.trasa = trasa;
    }

    //TODO: Remove not used methods

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getKlasa() {
        return klasa;
    }

    public void setKlasa(String klasa) {
        this.klasa = klasa;
    }

    public String getTrasa() {
        return trasa;
    }

    public void setTrasa(String trasa) {
        this.trasa = trasa;
    }
}