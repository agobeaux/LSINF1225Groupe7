package be.groupe7lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import be.groupe7lsinf1225.minipoll.R;

public class AccountCreationActivity extends Activity implements TextView.OnEditorActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);

        EditText usernameEditText = findViewById(R.id.Username);
        usernameEditText.setOnEditorActionListener(this);
        EditText passwordEditText = findViewById(R.id.Password);
        passwordEditText.setOnEditorActionListener(this);
        EditText confirmpasswordEditText = findViewById(R.id.ConfirmPassword);
        confirmpasswordEditText.setOnEditorActionListener(this);
    }

    /** Called when the user taps the Send button */
    public void createAccount(View view) {
        EditText usernameEditText = findViewById(R.id.Username);
        String username = usernameEditText.getText().toString();
        EditText passwordEditText = findViewById(R.id.Password);
        String password = passwordEditText.getText().toString();
        EditText confirmpasswordEditText = findViewById(R.id.ConfirmPassword);
        String confirmpassword = confirmpasswordEditText.getText().toString();

        if(!password.equals(confirmpassword)){
            //notification : Wrong confirmPassword
        }
        else {
            Intent intent = new Intent(this, ProfileCreationActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("password",password);
            startActivity(intent);
        }
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            createAccount(v);
            return true;
        }
        return false;
    }
}