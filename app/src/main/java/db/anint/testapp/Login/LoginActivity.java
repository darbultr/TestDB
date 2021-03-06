package db.anint.testapp.Login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import db.anint.testapp.Departments.DepartmentsListActivity_;
import db.anint.testapp.Models.User;
import db.anint.testapp.R;
import db.anint.testapp.Temp.TempUsers;
import db.anint.testapp.Utils.Validators;

/**
 * Activity used to validate user credentials inputs and give further access.
 */

@SuppressLint("Registered")
@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {
    private boolean usernameErrors, passwordErrors = true;

    TempUsers users = new TempUsers(); //Init temporary users

    @ViewById
    TextInputLayout txtLayoutUsername;

    @ViewById
    TextInputLayout txtLayoutPassword;

    @ViewById
    TextInputEditText txtInputUsername;

    @ViewById
    TextInputEditText txtInputPassword;

    @ViewById
    Button btnLogin;

    @AfterViews
    void init() {
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        users.initTempUsers(); //Init temporary users
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getTitle() + " - Please log in.");
        }
    }

    @AfterTextChange(R.id.txtInputUsername)
    void afterTextChangedOnUsername() {
        if (!Validators
                .validUser(txtInputUsername
                        .getText()
                        .toString())) {
            usernameError();
        } else {
            usernameErrors = false;
        }
    }

    @AfterTextChange(R.id.txtInputPassword)
    void afterTextChangedOnPassword() {
        if (!Validators
                .validPassword(txtInputPassword
                        .getText()
                        .toString())) {
            passwordError();
        } else {
            passwordErrors = false;
        }
    }

    @Click
    void btnLoginClicked() {
        if (!usernameErrors && !passwordErrors) {
            User user = new User(txtInputUsername.getText().toString(), txtInputPassword.getText().toString());
            if (users.accessGranted(user)) {
                Intent i = new Intent(this, DepartmentsListActivity_.class);
                i.putExtra("username", user.getUsername());
                i.putExtra("password", user.getPassword());
                startActivity(i);
            } else {
                Toast.makeText(this, getResources().getString(R.string.accessDenied), Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, getResources().getString(R.string.enterCredentials), Toast.LENGTH_SHORT).show();
        }
    }

    private void usernameError() {
        txtInputUsername.setError(getResources().getString(R.string.userEmpty));
    }

    private void passwordError() {
        txtInputPassword.setError(getResources().getString(R.string.passwordEmpty));
    }


}
