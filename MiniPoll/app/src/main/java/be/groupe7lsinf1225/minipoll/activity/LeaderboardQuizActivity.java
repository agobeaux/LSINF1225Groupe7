package be.groupe7lsinf1225.minipoll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import be.groupe7lsinf1225.minipoll.R;

public class LeaderboardQuizActivity extends AppCompatActivity {

    String topic;
    String state;
    String createdBy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_quiz);
        Bundle extras = getIntent().getExtras();
        topic = extras.getString("topic");
        state = extras.getString("state");
        createdBy = extras.getString("createdBy");
    }

    
}
