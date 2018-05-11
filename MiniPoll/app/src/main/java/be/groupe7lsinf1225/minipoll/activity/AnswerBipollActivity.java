package be.groupe7lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.AppMiniPoll;
import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.activity.adapter.ViewBipollChoiceAdapter;
import be.groupe7lsinf1225.minipoll.activity.adapter.ViewFriendAdapter;
import be.groupe7lsinf1225.minipoll.object.BiPoll;
import be.groupe7lsinf1225.minipoll.object.Picture;
import be.groupe7lsinf1225.minipoll.object.User;

public class AnswerBipollActivity extends Activity {

    private ArrayList<String> choice1;
    private ViewBipollChoiceAdapter arrayAdapter1;
    private ArrayList<String> choice2;
    private ViewBipollChoiceAdapter arrayAdapter2;

    private String select = null;
    private BiPoll bipoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bipoll_choice_adapter);

        Intent old_intent = getIntent();
        int idBipoll = old_intent.getIntExtra("idBipoll",0);

        bipoll = BiPoll.find(idBipoll);

        Log.e("TEST","state : "+bipoll.getState());

        if(bipoll.getState()) {
            if (bipoll == null) {
                ArrayList<String> nul = new ArrayList<>();
                nul.add("Unknownen");
                nul.add("Unknownen");
                bipoll = new BiPoll("Unknownen", "Unknownen", -1, nul);
            }
            ImageView image = findViewById(R.id.answer_bipoll_haveanswer);
            if (bipoll.haveanswer()) {
                image.setImageResource(R.drawable.ic_ok_friend);
            }
            else{
                image.setImageResource(R.drawable.ic_supp_friend);
            }

            TextView textViewauthor = findViewById(R.id.answer_bipoll_author);
            textViewauthor.setText(bipoll.getAuthor());
            TextView textViewquestion = findViewById(R.id.answer_bipoll_question);
            textViewquestion.setText(bipoll.getQuestion());
            ImageView likeImageView = findViewById(R.id.like);
            likeImageView.setImageResource(R.drawable.like);
            ImageView dislikeImageView = findViewById(R.id.dislike);
            dislikeImageView.setImageResource(R.drawable.dislike);

            final ArrayList<String> choice1 = new ArrayList<>();
            choice1.add(bipoll.getChoice1());
            choice1.add(bipoll.getChoice1());
            final ArrayList<String> choice2 = new ArrayList<>();
            choice2.add(bipoll.getChoice2());
            choice2.add(bipoll.getChoice2());

            arrayAdapter1 = new ViewBipollChoiceAdapter(this, choice1, R.layout.item_bipoll_choice1, R.id.item_bipoll_choice1);

            SwipeFlingAdapterView flingContainer1 = findViewById(R.id.view_choise1_frame);

            flingContainer1.setAdapter(arrayAdapter1);
            flingContainer1.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {

                @Override
                public void removeFirstObjectInAdapter() {
                    arrayAdapter1.notifyDataSetChanged();
                }

                @Override
                public void onLeftCardExit(Object dataObject) {
                    select = choice1.get(0);
                    makeToast(AnswerBipollActivity.this, "like!");

                }

                @Override
                public void onRightCardExit(Object dataObject) {
                    select = choice2.get(0);
                    makeToast(AnswerBipollActivity.this, "dislike!");
                }

                @Override
                public void onAdapterAboutToEmpty(int itemsInAdapter) {
                }

                @Override
                public void onScroll(float scrollProgressPercent) {

                }
            });
            arrayAdapter2 = new ViewBipollChoiceAdapter(this, choice2, R.layout.item_bipoll_choice2, R.id.item_bipoll_choice2);

            SwipeFlingAdapterView flingContainer2 = findViewById(R.id.view_choise2_frame);

            flingContainer2.setAdapter(arrayAdapter2);
            flingContainer2.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {

                @Override
                public void removeFirstObjectInAdapter() {
                    arrayAdapter2.notifyDataSetChanged();
                }

                @Override
                public void onLeftCardExit(Object dataObject) {
                    select = choice2.get(0);
                    makeToast(AnswerBipollActivity.this, "like!");

                }

                @Override
                public void onRightCardExit(Object dataObject) {
                    select = choice1.get(0);
                    makeToast(AnswerBipollActivity.this, "dislike!");

                }

                @Override
                public void onAdapterAboutToEmpty(int itemsInAdapter) {
                }

                @Override
                public void onScroll(float scrollProgressPercent) {

                }
            });
        }
        else {
            AppMiniPoll.notifyLong(R.string.bipoll_closed);
            finish();
        }
    }

    public void confirm(View view){
        if(select!=null){
            boolean err;
            err = bipoll.updateanswer(select);
            if(err) {
                ImageView image = findViewById(R.id.answer_bipoll_haveanswer);
                image.setImageResource(R.drawable.ic_ok_friend);
                makeToast(AnswerBipollActivity.this, "Your answer have been saved!");
            }
        }
        else{
            makeToast(AnswerBipollActivity.this, "Please make a choice!");
        }
    }

    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }
}
