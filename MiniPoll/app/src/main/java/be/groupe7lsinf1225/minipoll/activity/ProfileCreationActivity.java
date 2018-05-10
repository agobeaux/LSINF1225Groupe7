package be.groupe7lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
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

public class ProfileCreationActivity extends Activity implements TextView.OnEditorActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);

        ImageButton profileImage = findViewById(R.id.picture_creation);

        String picture = getIntent().getStringExtra("picture");

        if(picture == null) {
            profileImage.setImageResource(R.drawable.profile_placeholder);
        }
        else {
            switch (picture) {
                case "1":
                    profileImage.setImageResource(R.drawable.caterpie);
                    break;
                case "2":
                    profileImage.setImageResource(R.drawable.charmander);
                    break;
                case "3":
                    profileImage.setImageResource(R.drawable.eevee);
                    break;
                case "4":
                    profileImage.setImageResource(R.drawable.gyarados);
                    break;
                case "5":
                    profileImage.setImageResource(R.drawable.machop);
                    break;
                case "6":
                    profileImage.setImageResource(R.drawable.pidgey);
                    break;
                case "7":
                    profileImage.setImageResource(R.drawable.pikachu);
                    break;
                case "8":
                    profileImage.setImageResource(R.drawable.rattata);
                    break;
                case "9":
                    profileImage.setImageResource(R.drawable.squirtle);
                    break;
                case "10":
                    profileImage.setImageResource(R.drawable.treecko);
                    break;
                case "11":
                    profileImage.setImageResource(R.drawable.snorlax);
                    break;
                case "12":
                    profileImage.setImageResource(R.drawable.zapdos);
                    break;
                default:
                    profileImage.setImageResource(R.drawable.profile_placeholder);
                    break;
            }
        }
        profileImage.setContentDescription(picture);

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

        Log.e("Je passe","oui oui je passe");

        ImageButton profileImage = findViewById(R.id.picture_creation);
        CharSequence picture = profileImage.getContentDescription();

        int numPicture = 0;
        if(picture != null) {
            numPicture = Integer.parseInt((String)picture);
        }

        Log.e("Mais enfin","je suis passe :o");


        if (firstname.equals("") || lastname.equals("") || mailaddress.equals("")) {
            AppMiniPoll.notifyShort(R.string.not_completed);
        }
        else if (!mailaddress.contains("@") || !mailaddress.contains(".")) {
            AppMiniPoll.notifyShort(R.string.wrong_email);
        }
        else {
            Intent intent = new Intent(this, MainMenuActivity.class);
            Intent oldIntent = getIntent();
            String username = oldIntent.getStringExtra("username");
            String password = oldIntent.getStringExtra("password");

            if(!User.putUser(username,password,firstname,lastname,mailaddress,numPicture)){
                AppMiniPoll.notifyLong(R.string.error_sign_up);
            }
            else{
                AppMiniPoll.notifyShort(R.string.sign_up);
            }
            User user = User.getUser(username);
            User.setConnected_user(user);

            startActivity(intent);
        }
    }

    public void pictureUpdateLayout(View v) {
        setContentView(R.layout.activity_update_profile_picture);
    }

    public void pictureUpdate(View v) {
        Intent oldIntent = getIntent();
        String username = oldIntent.getStringExtra("username");
        String password = oldIntent.getStringExtra("password");
        Intent intent = new Intent(this,ProfileCreationActivity.class);
        intent.putExtra("picture",v.getContentDescription());
        intent.putExtra("username",username);
        intent.putExtra("password",password);
        startActivity(intent);
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            createProfile(v);
            return true;
        }
        return false;
    }
}

