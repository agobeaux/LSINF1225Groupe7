package be.groupe7lsinf1225.minipoll.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

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
    }

    private void loadQuizzes(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String[] columns = {"LOGIN","IDQUIZ"};
        String[] valuesWhere = {User.getConnectedUser().getLogin()};
        String selection = "LOGIN" + " = ?";

        Cursor cursor = db.query("VIEW_QUIZ", columns, selection, valuesWhere, null, null, null);

        cursor.moveToFirst();

        ArrayList<Quiz> quizzes = new ArrayList<>();

        while (!cursor.isAfterLast()) {


            cursor.moveToNext();
        }



    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
