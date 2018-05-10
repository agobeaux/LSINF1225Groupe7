package be.groupe7lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.activity.adapter.ViewBipollChoiceAdapter;
import be.groupe7lsinf1225.minipoll.activity.adapter.ViewFriendAdapter;
import be.groupe7lsinf1225.minipoll.object.BiPoll;
import be.groupe7lsinf1225.minipoll.object.User;

public class AnswerBipollActivity extends Activity {

    private ArrayList<String> choice1;
    private ViewBipollChoiceAdapter arrayAdapter1;
    private ArrayList<String> choice2;
    private ViewBipollChoiceAdapter arrayAdapter2;
    private boolean answered = false;

    private BiPoll bipoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bipoll_choice_adapter);

        Intent old_intent = getIntent();
        int idBipoll = old_intent.getIntExtra("idBipoll",0);

        //bipoll = BiPoll.find(idBipoll);

        TextView textViewauthor = findViewById(R.id.answer_bipoll_author);
        textViewauthor.setText("ici tu met l author");
        TextView textViewquestion = findViewById(R.id.answer_bipoll_question);
        textViewquestion.setText("ici tu met la question");

        //String[] choice = BiPoll.getChoice();

        final ArrayList<String> choice1 = new ArrayList<>();
        choice1.add("ici tu met le choice 1");
        choice1.add("ici tu met le choice 1");
        final ArrayList<String> choice2 = new ArrayList<>();
        choice2.add("ici tu met le choice 2");
        choice2.add("ici tu met le choice 2");

        arrayAdapter1 = new ViewBipollChoiceAdapter(this, choice1,R.layout.item_bipoll_choise1,R.id.item_bipoll_choice1);

        SwipeFlingAdapterView flingContainer1 = findViewById(R.id.view_choise1_frame);

        flingContainer1.setAdapter(arrayAdapter1);
        flingContainer1.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {

            @Override
            public void removeFirstObjectInAdapter() {
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                if(!answered) {
                makeToast(AnswerBipollActivity.this, "like!");
                //bipoll.answer(choice1.get(0));
                answered = true;
                }
            }

            @Override
            public void onRightCardExit(Object dataObject){
                if(!answered) {
                    makeToast(AnswerBipollActivity.this, "dislike!");
                    //bipoll.answer(choice1.get(1));
                    answered = true;
                }
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }
        });
        arrayAdapter2 = new ViewBipollChoiceAdapter(this, choice2,R.layout.item_bipoll_choise2,R.id.item_bipoll_choice2);

        SwipeFlingAdapterView flingContainer2 = findViewById(R.id.view_choise2_frame);

        flingContainer2.setAdapter(arrayAdapter2);
        flingContainer2.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {

            @Override
            public void removeFirstObjectInAdapter() {
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                if(!answered) {
                    makeToast(AnswerBipollActivity.this, "like!");
                    //bipoll.answer(choice1.get(1));
                    answered = true;
                }
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                if(!answered) {
                    makeToast(AnswerBipollActivity.this, "dislike!");
                    //bipoll.answer(choice1.get(0));
                    answered = true;
                }
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }
        });
    }

    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }
}