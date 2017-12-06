package db.anint.testapp.Models;

/**
 * Created by darek on 06.12.17.
 */

public class Department {

    String symbol;
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
