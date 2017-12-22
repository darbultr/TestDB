package db.anint.testapp.Models;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;


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

    private boolean focus;

    public Department() {
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

    public boolean isFocus() {
        return focus;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }
}
