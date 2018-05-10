package be.groupe7lsinf1225.minipoll.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.MySQLiteHelper;
import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.activity.adapter.LeaderboardAdapter;
import be.groupe7lsinf1225.minipoll.activity.adapter.QuizAdapter;
import be.groupe7lsinf1225.minipoll.object.Quiz;
import be.groupe7lsinf1225.minipoll.object.User;

public class LeaderboardQuizActivity extends AppCompatActivity {

    String IDQuiz;
    String topicExtra;
    String stateExtra;
    String createdByExtra;
    private ArrayList<String> userlist;
    private LeaderboardAdapter myAdapter;
    String IDLastQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_quiz);
        Intent intent = getIntent();
        topicExtra = intent.getStringExtra("topic");
        stateExtra = intent.getStringExtra("state");
        createdByExtra = intent.getStringExtra("createdBy");
        IDQuiz = intent.getStringExtra("IDQUIZ");
        IDLastQuestion = intent.getStringExtra("IDLASTQUESTION");

        TextView topic = findViewById(R.id.Topic2);
        TextView state = findViewById(R.id.State2);
        TextView createdby = findViewById(R.id.CreatedBy2);
        
        topic.setText(topicExtra);
        state.setText(stateExtra);
        createdby.setText(createdByExtra);
        userlist = new ArrayList<>();
        sortUsersByScore();
        ListView myListView = findViewById(R.id.show_listView_leaders);
        myAdapter = new LeaderboardAdapter(this, userlist, IDQuiz);
        myListView.setAdapter(myAdapter);
    }
    /*
        A FAIRE :
        1. calculer ici la position de chaque utilisateur afin de ne pas refaire le calcul dans LeaderboardAdapter.java
        et donc passer cette liste en argument
        2. Faire une fonction qui calcule le nombre de points de chaque utilisateur (à utiliser dans 1 d'ailleurs)
     */
    public void sortUsersByScore(){
        SQLiteDatabase db;
        db = MySQLiteHelper.get().getReadableDatabase();

        String[] columns = {"LOGIN"};
        String[] valuesWhere = {IDLastQuestion};
        String selection = "IDQUESTION" + " = "+ IDLastQuestion;

        Cursor cursor = db.query(true,"ANSWER_QUIZ", columns, selection, null, null, null, null, null);
        if( cursor.moveToFirst() ) {
            int i;
            Log.e(null,"People who answered this quiz :" + cursor.getCount());
            for(i=0; i < cursor.getCount(); i++ ) {
                if(userlist.size()==0){
                    userlist.add(cursor.getString(0));
                }
                else{
                    for(int j = 0; j < userlist.size();j++){
                        if ((j == (userlist.size() - 1))) {
                            userlist.add(cursor.getString(0));
                            j = userlist.size();
                        } else if ((User.getQuizScore(IDQuiz, cursor.getString(0)) > User.getQuizScore(IDQuiz, String.valueOf(userlist.get(j))))) {
                            userlist.add(j,cursor.getString(0));
                            j = userlist.size();
                        }
                    }
                }
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        else {
            Log.e(null, "No one can see this quiz");
            cursor.close();
            db.close();
        }
    }
    public void onClick(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}
