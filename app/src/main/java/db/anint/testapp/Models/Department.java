package db.anint.testapp.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Department class to download into listview
 * {
 * "symbol": "CA",
 * "opis": "CZĘSTOCHOWA (oddziałowy)"
 * }
 */

public class Department implements Serializable {
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("opis")
    private String opis;

    public Department(){
        super();
    }

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

}
