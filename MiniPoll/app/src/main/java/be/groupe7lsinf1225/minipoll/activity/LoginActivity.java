package be.groupe7lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import be.groupe7lsinf1225.minipoll.AppMiniPoll;
import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.object.User;


public class LoginActivity extends Activity implements TextView.OnEditorActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle(R.string.app_name);


        EditText usernameEditText = findViewById(R.id.Username);
        usernameEditText.setOnEditorActionListener(this);
        EditText passwordEditText = findViewById(R.id.Password);
        passwordEditText.setOnEditorActionListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // On efface le mot de passe qui était écrit quand on se déconnecte.
        EditText passwordEditText = findViewById(R.id.Password);
        passwordEditText.setText("");
    }

    /** Called when the user taps the Sign up button */
    public void signUp(View view) {
        Intent intent = new Intent(this, AccountCreationActivity.class);
        startActivity(intent);

    }

    public void login(View view) {
        EditText usernameEditText = findViewById(R.id.Username);
        String username = usernameEditText.getText().toString();
        EditText passwordEditText = findViewById(R.id.Password);
        String password = passwordEditText.getText().toString();

        User user = User.getUser(username);

        if(user != null) {
            if (user.login(password)) {
                Intent intent = new Intent(this, MainMenuActivity.class);
                startActivity(intent);
            }
            else{
                //notification : Wrong Password
                AppMiniPoll.notifyShort(R.string.wrong_password);
            }
        }
        else{
            //notification : Username doesn't exist
            AppMiniPoll.notifyShort(R.string.user_not_found);
        }
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            login(v);
            return true;
        }
        return false;
    }

}