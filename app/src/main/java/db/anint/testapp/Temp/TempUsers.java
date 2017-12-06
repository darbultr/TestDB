package db.anint.testapp.Temp;

import java.util.HashMap;
import java.util.Map;

import db.anint.testapp.Models.User;

/**
 * Temporary users for login tests.
 */

public class TempUsers {

    private Map<String, String> users = new HashMap<>();

    public TempUsers() {

    }

    public void initTempUsers() {
        users.put("Jan", "Kowalski");
        users.put("Piotr", "Fronczewski");
        users.put("Robert", "Lewandowski");
    }

    public boolean accessGranted(User user) {
        return (users.get(user.getUsername()) != null && users.get(user.getUsername()).equals(user.getPassword()));
    }
}
