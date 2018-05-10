package be.groupe7lsinf1225.minipoll.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.activity.adapter.LeaderboardAdapter;
import be.groupe7lsinf1225.minipoll.activity.adapter.QuizAdapter;
import be.groupe7lsinf1225.minipoll.object.Quiz;
import be.groupe7lsinf1225.minipoll.object.User;

public class LeaderboardQuizActivity extends AppCompatActivity {

    String topicExtra;
    String stateExtra;
    String createdByExtra;
    private ArrayList<String> userlist;
    private LeaderboardAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_quiz);
        Bundle extras = getIntent().getExtras(); // /!\ il faut faire des intent setExtras dans QuizActivity et dans AnsweringQuizActivity
        topicExtra = extras.getString("topic");
        stateExtra = extras.getString("state");
        createdByExtra = extras.getString("createdBy");

        TextView topic = findViewById(R.id.Topic2);
        TextView state = findViewById(R.id.State2);
        TextView createdby = findViewById(R.id.CreatedBy2);
        
        topic.setText(topicExtra);
        state.setText(stateExtra);
        createdby.setText(createdByExtra);

        // REMPLIR LES CASES DE LA LISTVIEW !!!
        
    }
    /*
        A FAIRE :
        1. calculer ici la position de chaque utilisateur afin de ne pas refaire le calcul dans LeaderboardAdapter.java
        et donc passer cette liste en argument
        2. Faire une fonction qui calcule le nombre de points de chaque utilisateur (à utiliser dans 1 d'ailleurs)
     */
    public void onClick() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}
