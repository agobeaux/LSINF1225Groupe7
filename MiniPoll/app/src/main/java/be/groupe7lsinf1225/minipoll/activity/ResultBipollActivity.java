package be.groupe7lsinf1225.minipoll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import be.groupe7lsinf1225.minipoll.R;

public class ResultBipollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_bipoll);
        //Select CHOICE_BIPOLL.CONTENT ,count(*) as Score
        //From CHOICE_BIPOLL,ANSWER_BIPOLL
        //WHERE ANSWER_BIPOLL.IDBIPOLL='id poll'
        //and ANSWER_BIPOLL.CHOICE==CHOICE_BIPOLL.IDCHOICE
        //GROUP BY  CHOICE_BIPOLL.CONTENT
    }
}
