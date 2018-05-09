package be.groupe7lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.object.Picture;
import be.groupe7lsinf1225.minipoll.object.User;


public class GestionFriendActivity extends Activity {

    private User ConnectUser = User.getConnectedUser();
    ArrayList<User> list = ConnectUser.getAllFriendRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_friend);
        if(list.size()>0) {
            notif();
        }
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
        setContentView(R.layout.activity_gestion_friend);
        list.remove(0);
        if(list.size()>0) {
            notif();
        }
    }

    public void refuse (View view){
        ConnectUser.suppFriend(list.get(0).getLogin());
        setContentView(R.layout.activity_gestion_friend);
        list.remove(0);
        if(list.size()>0) {
            notif();
        }
    }

    public void viewfriend(View view){
        Intent intent = new Intent(this, ViewFriendActivity.class);
        startActivity(intent);
    }

    public void viewuser(View view){
        Intent intent = new Intent(this, ViewUserActivity.class);
        startActivity(intent);
    }

}
