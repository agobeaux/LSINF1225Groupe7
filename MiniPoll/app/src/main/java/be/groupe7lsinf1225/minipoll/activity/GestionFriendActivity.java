package be.groupe7lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import be.groupe7lsinf1225.minipoll.R;


public class GestionFriendActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_friend);
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