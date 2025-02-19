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

        String picture = getIntent().getStringExtra("picture");

        User user = User.getInfosConnectedUser();

        if(picture == null)
        {
            picture = String.valueOf(user.getPicture());
        }
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
        EditText passwordConfirmEditText = findViewById(R.id.password_confirm_update);
        passwordConfirmEditText.setOnEditorActionListener(this);

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
        EditText passwordConfirmEditText = findViewById(R.id.password_confirm_update);
        String password_confirm = passwordConfirmEditText.getText().toString();


        ImageButton profileImage = findViewById(R.id.picture_update);
        CharSequence picture = profileImage.getContentDescription();

        int numPicture = 0;
        if(picture != null) {
            numPicture = Integer.parseInt((String)picture);
        }

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
            AppMiniPoll.notifyLong(R.string.password_wrong_length);
        }
        else if(!old_password.equals("") && !user.goodPassword(old_password)) {
            AppMiniPoll.notifyShort(R.string.wrong_old_password);
        }
        else if(old_password.equals("") && !user.goodPassword(password)) {
            AppMiniPoll.notifyShort(R.string.wrong_password);
        }
        else if(!old_password.equals("") && !(password.equals(password_confirm))) {
            AppMiniPoll.notifyShort(R.string.confirm_failed_update);
        }
        else {
            User.updateUser(old_username,first_name,last_name,username,mailaddress,password,numPicture);

            Intent intent = new Intent(this,MainMenuActivity.class);
            startActivity(intent);
        }

    }
    public void pictureUpdateLayout(View v) {
        setContentView(R.layout.activity_update_profile_picture);
    }
    public void pictureUpdate(View v){
        Intent intent = new Intent(this,UpdateProfileActivity.class);
        intent.putExtra("picture",v.getContentDescription());
        startActivity(intent);
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
