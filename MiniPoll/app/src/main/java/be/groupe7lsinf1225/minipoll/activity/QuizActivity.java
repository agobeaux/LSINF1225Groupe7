package be.groupe7lsinf1225.minipoll.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.activity.adapter.QuizAdapter;
import be.groupe7lsinf1225.minipoll.object.Quiz;
import be.groupe7lsinf1225.minipoll.object.User;

public class QuizActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<Quiz> quizzes;
    private QuizAdapter myQuizAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        loadQuizzes();
        ListView myListView = findViewById(R.id.show_listView_Quizzes);
        myQuizAdapter = new QuizAdapter(this, quizzes);
        myListView.setAdapter(myQuizAdapter);
        myListView.setOnItemClickListener(this);
    }

    private void loadQuizzes(){

        quizzes = new ArrayList<>();
        ArrayList<String> Ids = User.getQuizzes();
        for(int i = 0; Ids != null && i < Ids.size(); i++){
            Log.e(null, "IDQUIZ :" + Integer.parseInt(Ids.get(i)));
            Quiz locQuiz = Quiz.getQuiz(Ids.get(i));
            if (locQuiz != null) {
                quizzes.add(locQuiz);
            }
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        // La liste des éléments est ici rechargées car en cas de modification d'un élément, l'ordre
        // a peut-être changé.

        loadQuizzes();

        myQuizAdapter.setQuizzes(quizzes);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        intent.putExtra("IDQUIZ", String.valueOf(myQuizAdapter.getItem(i).getID()));
        startActivity(intent);
    }
}
