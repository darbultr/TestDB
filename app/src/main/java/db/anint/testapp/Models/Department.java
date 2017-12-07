package db.anint.testapp.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Department class to download into listview
 {
 "symbol": "CA",
 "opis": "CZĘSTOCHOWA (oddziałowy)"
 }
 */

public class Department implements Serializable{
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("symbol")
    String symbol;
    @JsonProperty("opis")
    String opis;

    public Department(String symbol, String opis) {
        this.symbol = symbol;
        this.opis = opis;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getOpis() {
        return opis;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
