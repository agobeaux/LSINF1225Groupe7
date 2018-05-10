package be.groupe7lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import be.groupe7lsinf1225.minipoll.R;

public class AnswerBipollActivity extends Activity implements TextView.OnEditorActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_bipoll);

        Intent old_intent = getIntent();
        int idBipoll = old_intent.getIntExtra("idBipoll",0);
    }



    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            // confirm(v);
            return true;
        }
        return false;
    }
}
