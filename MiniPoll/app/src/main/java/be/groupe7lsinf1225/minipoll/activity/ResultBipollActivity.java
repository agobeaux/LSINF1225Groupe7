package be.groupe7lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import be.groupe7lsinf1225.minipoll.AppMiniPoll;
import be.groupe7lsinf1225.minipoll.MySQLiteHelper;
import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.object.BiPoll;
import be.groupe7lsinf1225.minipoll.object.User;

public class ResultBipollActivity extends Activity implements TextView.OnEditorActionListener {

    private BiPoll bipoll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_bipoll);

        Intent old_intent = getIntent();
        int idBipoll = old_intent.getIntExtra("idBipoll",0);

        TextView questionTextView = findViewById(R.id.result_question);
        questionTextView.setOnEditorActionListener(this);
        TextView choiceTextView = findViewById(R.id.result_liked_choice);
        choiceTextView.setOnEditorActionListener(this);
        TextView preferTextView = findViewById(R.id.prefer);
        preferTextView.setOnEditorActionListener(this);
        TextView selectedFriendTextView = findViewById(R.id.result_selected_friend);
        selectedFriendTextView.setOnEditorActionListener(this);

        ImageButton option = findViewById(R.id.Bipoll_option_button);
        option.setImageResource(R.drawable.ic_options);


        bipoll = BiPoll.find(idBipoll);
        String question = bipoll.getQuestion();
        String[] answer = bipoll.getAnswer();  // selectedFriend,likedChoice

        if(answer.length == 0) {
            AppMiniPoll.notifyShort(R.string.not_answered_yet);
            finish();
        }
        else {
            questionTextView.setText(question);
            choiceTextView.setText(answer[1]);
            selectedFriendTextView.setText(answer[0]);
        }
    }

    public void option(View view){
        if(bipoll.getAuthor().equals(User.getConnectedUser().getLogin())){
            //ouvre les option
        }
    }

    public void close(View view) {
        Intent intent = new Intent(this,BiPollActivity.class);
        startActivity(intent);
       // Intent old_intent = getIntent();
        //int idBipoll = old_intent.getIntExtra("idBipoll",0);
        //BiPoll.close(idBipoll);
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            close(v);
            return true;
        }
        return false;
    }
}
