package be.groupe7lsinf1225.minipoll.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.object.Picture;
import be.groupe7lsinf1225.minipoll.object.User;

public class MainMenuActivity extends AppCompatActivity {

    private User ConnectUser = User.getConnectedUser();
    ArrayList<User> list = ConnectUser.getAllFriendRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        if(list.size()>0) {
            notif();
        }
    }
    public void onBackPressed(){
        /*do nothing*/
         }

    public void friendlist(View view){
        Intent intent = new Intent(this, GestionFriendActivity.class);
        startActivity(intent);
    }

    public void logout(View view) {
        User.logout();
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the quiz image button */
    public void quiz(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the BiPoll image button */
    public void bipoll(View view) {
        Intent intent = new Intent(this, BiPollActivity.class);
        startActivity(intent);
    }


    public void consultProfile(View view) {
        Intent intent = new Intent(this, UpdateProfileActivity.class);
        startActivity(intent);
    }

    public void creatpoll(View view){
        Intent intent = new Intent(this, CreationActivity.class);
        startActivity(intent);
    }

    private void notif(){
        User user = list.get(0);
        setContentView(R.layout.notification_gestion_friend);
        TextView textViewusername = findViewById(R.id.add_username);
        textViewusername.setText(user.getLogin());
        TextView textViewfirstname = findViewById(R.id.add_firstname);
        textViewfirstname.setText(user.getFirstName());
        TextView textViewlastname = findViewById(R.id.add_lastname);
        textViewlastname.setText(user.getLastName());

        ImageView profileImage = findViewById(R.id.add_picture);
        String picture = String.valueOf(user.getPicture());
        profileImage.setImageResource(Picture.get(picture));
    }

    public void accept (View view){
        ConnectUser.addFriend(list.get(0).getLogin());
        setContentView(R.layout.activity_main_menu);
        list.remove(0);
        if(list.size()>0) {
            notif();
        }
    }

    public void refuse (View view){
        ConnectUser.suppFriend(list.get(0).getLogin());
        setContentView(R.layout.activity_main_menu);
        list.remove(0);
        if(list.size()>0) {
            notif();
        }
    }
}
