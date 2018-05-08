package be.groupe7lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import be.groupe7lsinf1225.minipoll.AppMiniPoll;
import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.object.User;

public class UpdateProfileActivity extends Activity implements TextView.OnEditorActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        ImageButton profileImage = findViewById(R.id.picture_update);
        profileImage.setImageResource(R.drawable.profile_placeholder);

        EditText firstnameEditText = findViewById(R.id.first_name_update);
        firstnameEditText.setOnEditorActionListener(this);
        EditText lastnameEditText = findViewById(R.id.last_name_update);
        lastnameEditText.setOnEditorActionListener(this);
        EditText mailaddressEditText = findViewById(R.id.mail_address_update);
        mailaddressEditText.setOnEditorActionListener(this);
        EditText usernameEditText = findViewById(R.id.username_update);
        usernameEditText.setOnEditorActionListener(this);
        EditText oldPasswordEditText = findViewById(R.id.old_password_update);
        oldPasswordEditText.setOnEditorActionListener(this);
        EditText passwordEditText = findViewById(R.id.password_update);
        passwordEditText.setOnEditorActionListener(this);

        User user = User.getInfosConnectedUser();

        firstnameEditText.setText(user.getFirstName());
        lastnameEditText.setText(user.getLastName());
        mailaddressEditText.setText(user.getEmail());
        usernameEditText.setText(user.getLogin());

    }
    public void profileUpdate(View v){
        EditText firstnameEditText = findViewById(R.id.first_name_update);
        String first_name = firstnameEditText.getText().toString();
        EditText lastnameEditText = findViewById(R.id.last_name_update);
        String last_name = lastnameEditText.getText().toString();
        EditText mailaddressEditText = findViewById(R.id.mail_address_update);
        String mailaddress = mailaddressEditText.getText().toString();
        EditText usernameEditText = findViewById(R.id.username_update);
        String username = usernameEditText.getText().toString();
        EditText oldPasswordEditText = findViewById(R.id.old_password_update);
        String old_password = oldPasswordEditText.getText().toString();
        EditText passwordEditText = findViewById(R.id.password_update);
        String password = passwordEditText.getText().toString();

        User user = User.getConnectedUser();

        String old_username = user.getLogin();

        if(first_name.equals("") || last_name.equals("") || mailaddress.equals("") || username.equals("") || password.equals("")){
            AppMiniPoll.notifyShort(R.string.not_completed_required);
        }
        else if(!mailaddress.contains("@") || !mailaddress.contains(".")) {
            AppMiniPoll.notifyShort(R.string.wrong_email);
        }
        else if(username.length() < 5 || username.length() > 14) {
            AppMiniPoll.notifyLong(R.string.username_wrong_length);
        }
        else if(password.length() < 5 || password.length() > 14) {
            AppMiniPoll.notifyShort(R.string.password_wrong_length);
        }
        else if(!old_username.equals(username) && User.getUser(username) != null){
            AppMiniPoll.notifyShort(R.string.username_already_used);
        }
        else if(!old_password.equals("") && (old_password.length() < 5 || old_password.length() > 14)) {
            AppMiniPoll.notifyShort(R.string.password_wrong_length);
        }
        else if(!old_password.equals("") && !user.goodPassword(old_password)) {
            AppMiniPoll.notifyShort(R.string.wrong_old_password);
        }
        else if(old_password.equals("") && !user.goodPassword(password)) {
            AppMiniPoll.notifyShort(R.string.wrong_password);
        }
        else {
            User.updateUser(old_username,first_name,last_name,username,mailaddress,password);

            Intent intent = new Intent(this,MainMenuActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            profileUpdate(v);
            return true;
        }
        return false;
    }
}
