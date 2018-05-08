package be.groupe7lsinf1225.minipoll.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.MySQLiteHelper;
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
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String[] columns1 = {"LOGIN","IDQUIZ"};
        String[] valuesWhere1 = {User.getConnectedUser().getLogin()};
        String selection1 = "LOGIN" + " = ?";

        Cursor cursor1 = db.query("VIEW_QUIZ", columns1, selection1, valuesWhere1, null, null, null);

        cursor1.moveToFirst();

        while (!cursor1.isAfterLast()) {
            String[] columns2 = {"IDQUIZ","TITLE", "AUTHOR", "CLOSED"};
            String[] valuesWhere2 = {String.valueOf(cursor1.getInt(1))};
            String selection2 = "IDQUIZ" + " = ?";

            Cursor cursor2 = db.query("QUIZ", columns2, selection2, valuesWhere2, null, null, null);
            Quiz locQuiz = new Quiz(cursor2.getString(1),cursor2.getString(3).equals("true"),cursor2.getString(2));
            cursor2.close();
            quizzes.add(locQuiz);

            cursor1.moveToNext();
        }
        cursor1.close();
        db.close();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
