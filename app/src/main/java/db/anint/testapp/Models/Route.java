package db.anint.testapp.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Route class to download into listview
 * {
 * "guid": "676e61664b8baed66547fa8d56ac463e",
 * "symbol": "GDANSK PORT"
 * }
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {
    @JsonProperty("guid")
    private String guid;

    @JsonProperty("symbol")
    private String symbol;

    private boolean focus;

    public Route() {
        super();
    }

    public Route(String guid, String symbol) {
        this.guid = guid;
        this.symbol = symbol;
    }

    public String getGuid() {
        return guid;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isFocus() {
        return focus;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }
}
