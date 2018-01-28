package guide.athletes.thai.muay.project_mtag_rachelsoderberg;

import android.content.Intent;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Rachel on 1/27/2018.
 */

public class LoginActivity extends Activity{
    EditText editTextUsername;
    EditText editTextPassword;
    TextInputLayout textInputLayoutUsername;
    TextInputLayout textInputLayoutPassword;
    Button buttonLogin;
    MTAGDatabaseHelper mtagDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mtagDatabaseHelper = new MTAGDatabaseHelper(this);
        initCreateAccountTextView();
        initViews();

        // Login button click event
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validate user input
                if (validate()) {
                    // Get values from EditText fields
                    String Username = editTextUsername.getText().toString();
                    String Password = editTextPassword.getText().toString();

                    // Authenticate account
                    Account currentAccount = mtagDatabaseHelper.Authenticate(new Account(null, null, Username, Password));

                    // Was authentication successful?
                    if (currentAccount != null) {
                        Snackbar.make(buttonLogin, "Successfully logged in", Snackbar.LENGTH_LONG).show();
                        // Successfully logged in, launch activity
                        /* Intent intent = new Intent(LoginActivity.this, HomeScreenActivity.class);
                        startActivity(intent);
                        finish();*/
                    } else {
                        // Login failed
                        Snackbar.make(buttonLogin, "Login failed", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    // Set Create Account TextView text and click events
    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(fromHtml("<font color='#ffffff'> I don't have account yet. </font><font color='#0c0099'>create one</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    // Connect XML views to its objects
    private void initViews() {
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textInputLayoutUsername = (TextInputLayout) findViewById(R.id.textInputLayoutUsername);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
    }

    // Handle fromHtml deprecation
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    // Validate user input
    public boolean validate() {
        boolean valid = false;

        String Username = editTextUsername.getText().toString();
        String Password = editTextPassword.getText().toString();

        // Handle Username field validation
        if (Username.isEmpty()) {
            valid = false;
            textInputLayoutUsername.setError("Please enter a valid username");
        } else {
            if (Username.length() > 4 && Username.length() < 12) {
                valid = true;
                textInputLayoutUsername.setError(null);
            } else {
                valid = false;
                textInputLayoutUsername.setError("Username must be greater than 4 characters and less than 12.");
            }
        }
        return valid;
    }
}
