package be.groupe7lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import be.groupe7lsinf1225.minipoll.R;

public class ProfileCreationActivity extends Activity implements TextView.OnEditorActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);

        ImageButton profileImage = findViewById(R.id.imageButton4);
        profileImage.setImageResource(R.drawable.profile_placeholder);

        EditText firstnameEditText = findViewById(R.id.FirstName);
        firstnameEditText.setOnEditorActionListener(this);
        EditText lastnameEditText = findViewById(R.id.LastName);
        lastnameEditText.setOnEditorActionListener(this);
        EditText mailaddressEditText = findViewById(R.id.MailAddress);
        mailaddressEditText.setOnEditorActionListener(this);
    }

    public void createProfile(View view) {
        EditText firstnameEditText = findViewById(R.id.FirstName);
        String firstname = firstnameEditText.getText().toString();
        EditText lastnameEditText = findViewById(R.id.LastName);
        String lastname = lastnameEditText.getText().toString();
        EditText mailaddressEditText = findViewById(R.id.MailAddress);
        String mailaddress = mailaddressEditText.getText().toString();
        //missing photo

        if(firstname.equals("")||lastname.equals("")||mailaddress.equals("")){
            /*robustess to upgrade*/
            //notification : Missing entry
        }
        else {
            Intent intent = new Intent(this, MainMenuActivity.class);
            String username = intent.getStringExtra("username");
            String password = intent.getStringExtra("password");
            //creation account
            startActivity(intent);
        }
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            //creat account
            return true;
        }
        return false;
    }
}

