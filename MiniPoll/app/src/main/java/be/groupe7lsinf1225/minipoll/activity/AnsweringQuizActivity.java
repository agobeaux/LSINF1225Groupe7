package be.groupe7lsinf1225.minipoll.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.object.Question;
import be.groupe7lsinf1225.minipoll.object.Choice;
import be.groupe7lsinf1225.minipoll.object.Quiz;

public class AnsweringQuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String IDQuiz = intent.getStringExtra("IDQUIZ");
        String IDQuestion = intent.getStringExtra("IDQUESTION");
        setContentView(R.layout.answering_quiz_text);
        //CHOICES
        RadioButton choice1 = findViewById(R.id.choice1);
        RadioButton choice2 = findViewById(R.id.choice2);
        RadioButton choice3 = findViewById(R.id.choice3);
        RadioButton choice4 = findViewById(R.id.choice4);
        Choice[] choices = Question.getChoices(IDQuestion);
        choice1.setText(choices[0].getTitle());
        choice2.setText(choices[1].getTitle());
        choice3.setText(choices[2].getTitle());
        choice4.setText(choices[3].getTitle());
        //QUIZ INFO
        TextView topic = findViewById(R.id.Topic2);
        TextView state = findViewById(R.id.State2);
        TextView createdby = findViewById(R.id.CreatedBy2);
        Quiz CurrentQuiz = Quiz.getQuiz(IDQuiz);
        topic.setText(CurrentQuiz.getTitle());
        if(CurrentQuiz.getState()){
            state.setText("Closed");
        }
        else{
            state.setText("Open");
        }
        createdby.setText(CurrentQuiz.getAuthor());

        TextView quest = findViewById(R.id.Question);
        Question question = Question.getQuestion(IDQuestion);
        quest.setText(question.getTitle());
    }
}
