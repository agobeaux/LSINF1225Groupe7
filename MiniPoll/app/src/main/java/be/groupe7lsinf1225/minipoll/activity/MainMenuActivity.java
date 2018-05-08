package be.groupe7lsinf1225.minipoll.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.object.User;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
git
    public void friendlist(View view){
        Intent intent = new Intent(this, GestionFriendActivity.class);
        startActivity(intent);
    }

    public void logout(View view) {
        User.logout();
        finish();
    }

    public void consultProfile(View view) {
        //Intent intent = new Intent(this, ProfileConsultationActivity.class);
        //startActivity(intent);
    }
}
