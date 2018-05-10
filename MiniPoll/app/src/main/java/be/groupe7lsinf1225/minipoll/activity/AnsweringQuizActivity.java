package be.groupe7lsinf1225.minipoll.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import be.groupe7lsinf1225.minipoll.R;

public class AnsweringQuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String IDQuiz = intent.getStringExtra("IDQUIZ");
        setContentView(R.layout.answering_quiz_text);

    }
}
