package db.anint.testapp.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Route class to download into listview
 {
 "guid": "676e61664b8baed66547fa8d56ac463e",
 "symbol": "GDANSK PORT"
 }
 */

public class Route {
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("guid")
    private String guid;

    @JsonProperty("symbol")
    private String symbol;

    public Route(String guid, String symbol) {
        this.guid = guid;
        this.symbol = symbol;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
