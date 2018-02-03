package guide.athletes.thai.muay.project_mtag_rachelsoderberg;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity {
    EditText editTextUserName;
    EditText editTextEmail;
    EditText editTextPassword;
    TextInputLayout textInputLayoutUserName;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;
    Button buttonRegister;
    MTAGDatabaseHelper mtagDatabaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mtagDatabaseHelper = new MTAGDatabaseHelper(this);
        initTextViewLogin();
        initViews();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String UserName = editTextUserName.getText().toString();
                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();

                    // Any users associated with this email?
                    if (!mtagDatabaseHelper.isEmailExists(Email)) {
                        // Does not exist, add to database
                        mtagDatabaseHelper.addAccount(new Account(null, UserName, Email, Password));
                        Snackbar.make(buttonRegister, "User created successfully! Please log in ", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Snackbar.LENGTH_LONG);
                    } else {
                        // Email already exists, show error
                        Snackbar.make(buttonRegister, "Email already in use ", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    // Set Login TextView click event
    private void initTextViewLogin() {
        TextView textViewLogin = (TextView) findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    // Connect XML views to its Objects
    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutUserName = (TextInputLayout) findViewById(R.id.textInputLayoutUserName);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
    }
    // Validate user input
    public boolean validate() {
        boolean valid = false;

        // Get values from EditText fields
        String UserName = editTextUserName.getText().toString();
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        // Handle UserName field validation
        if (UserName.isEmpty()) {
            valid = false;
            textInputLayoutUserName.setError("Please enter a valid username");
        } else {
            if (UserName.length() > 4 && UserName.length() <= 15) {
                valid = true;
                textInputLayoutUserName.setError(null);
            } else {
                valid = false;
                textInputLayoutEmail.setError("Username must be greater than 4 characters and less than or equal to 15.");
            }
        }
        // Handle Email field validation
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            textInputLayoutEmail.setError("Please enter a valid email!");
        } else {
            valid = true;
            textInputLayoutEmail.setError(null);
        }

        // Handle Password field validation
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please enter a valid password");
        } else {
            if (Password.length() > 5 && Password.length() <= 20) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password must be greater than 5 characters and less than or equal to 20");
            }
        }
        return valid;
    }
}
