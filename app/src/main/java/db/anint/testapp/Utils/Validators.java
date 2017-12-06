package db.anint.testapp.Utils;

import android.text.TextUtils;

import db.anint.testapp.Temp.TempUsers;

/**
 * Validators for login textBoxes
 */

public class Validators {
    public static boolean validUser(String user) {
        return !TextUtils.isEmpty(user) && user.length() >= Constants.MIN_USERNAME_LEN;

    }

    public static boolean validPassword(String password) {
        return !TextUtils.isEmpty(password) && password.length() >= Constants.MIN_PASS_LEN;
    }
}
