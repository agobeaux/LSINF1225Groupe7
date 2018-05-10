package be.groupe7lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import be.groupe7lsinf1225.minipoll.R;

public class CreationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);
        ImageView Imagebipoll = findViewById(R.id.creation_bipoll_frame);
        Imagebipoll.setImageResource(R.drawable.menu_helpafriend);
        ImageView Imageagreement = findViewById(R.id.creation_agreement_frame);
        Imageagreement.setImageResource(R.drawable.menu_agreements);
        ImageView Imagequiz = findViewById(R.id.creation_quiz_frame);
        Imagequiz.setImageResource(R.drawable.menu_quiz);

    }

    void bipoll(View view){
        Intent intent = new Intent(this, CreationBipollActivity.class);
        startActivity(intent);
    }

    void agreement(View view){
        Intent intent = new Intent(this, CreationBipollActivity.class);
        startActivity(intent);
    }

    void quiz(View view){
        Intent intent = new Intent(this, CreationBipollActivity.class);
        startActivity(intent);
    }
}
