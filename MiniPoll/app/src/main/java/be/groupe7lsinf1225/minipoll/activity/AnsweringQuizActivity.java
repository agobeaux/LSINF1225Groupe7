package be.groupe7lsinf1225.minipoll.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.AppMiniPoll;
import be.groupe7lsinf1225.minipoll.MySQLiteHelper;
import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.object.Question;
import be.groupe7lsinf1225.minipoll.object.Choice;
import be.groupe7lsinf1225.minipoll.object.Quiz;
import be.groupe7lsinf1225.minipoll.object.User;

public class AnsweringQuizActivity extends AppCompatActivity {

    String topicExtra;
    String stateExtra;
    String createdByExtra;
    Choice[] choices;
    private String IDQuiz;
    private ArrayList<Integer> IDQuestions;
    private String IDQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        IDQuiz = intent.getStringExtra("IDQUIZ");
        IDQuestions = Quiz.getIDQuestions(IDQuiz);
        IDQuestion = intent.getStringExtra("IDQUESTION");
        setContentView(R.layout.answering_quiz_text);
        //CHOICES
        RadioButton choice1 = findViewById(R.id.choice1);
        RadioButton choice2 = findViewById(R.id.choice2);
        RadioButton choice3 = findViewById(R.id.choice3);
        RadioButton choice4 = findViewById(R.id.choice4);
        choices = Question.getChoices(IDQuestion);
        choice1.setText(choices[0].getTitle());
        choice2.setText(choices[1].getTitle());
        choice3.setText(choices[2].getTitle());
        choice4.setText(choices[3].getTitle());
        //QUIZ INFO
        TextView topic = findViewById(R.id.Topic2);
        TextView state = findViewById(R.id.State2);
        TextView createdby = findViewById(R.id.CreatedBy2);
        Quiz CurrentQuiz = Quiz.getQuiz(IDQuiz);
        topicExtra = CurrentQuiz.getTitle();
        topic.setText(topicExtra);
        if(CurrentQuiz.getState()){
            stateExtra = "Closed";
        }
        else{
            stateExtra = "Open";
        }
        state.setText(stateExtra);
        createdByExtra = CurrentQuiz.getAuthor();
        createdby.setText(createdByExtra);

        TextView quest = findViewById(R.id.Question);
        Question question = Question.getQuestion(IDQuestion);
        quest.setText(question.getTitle());
    }
    public void submit(View view) {
        Choice selected_choice = null;
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.myRadioGroup);
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (checkedRadioButtonId == -1) {
            AppMiniPoll.notifyShort(R.string.select_one);
        }
        else{
            if (checkedRadioButtonId == R.id.choice1) {
                selected_choice = choices[0];
            }
            else if (checkedRadioButtonId == R.id.choice2) {
                selected_choice = choices[1];
            }
            else if (checkedRadioButtonId == R.id.choice3) {
                selected_choice = choices[2];
            }
            else {
                selected_choice = choices[3];
            }
        }
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();

        String Query = "INSERT INTO ANSWER_QUIZ (LOGIN, CHOICE, IDQUESTION) VALUES ("+"\'" + User.getConnectedUser().getLogin() + "\', " +selected_choice.getIDChoice() +", "+ Integer.parseInt(IDQuestion) + ");";
        Log.d(null, Query);
        db.beginTransaction();
        db.execSQL(Query);
        db.setTransactionSuccessful();
        db.endTransaction();
        Log.e(null, "INSERTED ");
        db.close();
        String nextQuestionID = null;
        for(int i = 0; i < IDQuestions.size()-1; i++){
            if(String.valueOf(IDQuestions.get(i)).equals(IDQuestion)){
                nextQuestionID = String.valueOf(IDQuestions.get(i+1));
                i = IDQuestions.size();
            }
        }
        if(nextQuestionID != null){
            Intent intent = new Intent(this, AnsweringQuizActivity.class);
            intent.putExtra("IDQUIZ", IDQuiz);
            intent.putExtra("IDQUESTION",nextQuestionID);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, LeaderboardQuizActivity.class);
            intent.putExtra("topic",topicExtra );
            intent.putExtra("state", stateExtra);
            intent.putExtra("createdBy", createdByExtra);
            intent.putExtra("IDQUIZ", IDQuiz);
            intent.putExtra("IDLASTQUESTION", IDQuestion);
            startActivity(intent);
        }

    }
}
