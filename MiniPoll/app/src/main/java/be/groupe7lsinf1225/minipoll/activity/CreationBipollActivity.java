package be.groupe7lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.object.BiPoll;
import be.groupe7lsinf1225.minipoll.object.User;


public class CreationBipollActivity extends Activity implements TextView.OnEditorActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_bipoll);

        EditText titleEditText = findViewById(R.id.creationbipoll_title);
        titleEditText.setOnEditorActionListener(this);
        EditText choise1EditText = findViewById(R.id.creationbipoll_choise1);
        choise1EditText.setOnEditorActionListener(this);
        EditText choise2EditText = findViewById(R.id.creationbipoll_choise2);
        choise2EditText.setOnEditorActionListener(this);

        makeToast(this,String.valueOf(BiPoll.getId())); //a retiré
    }

    public void confirm(View view){
        EditText titleEditText = findViewById(R.id.creationbipoll_title);
        String title = titleEditText.getText().toString();
        EditText choise1EditText = findViewById(R.id.creationbipoll_choise1);
        String choise1 = choise1EditText.getText().toString();
        EditText choise2EditText = findViewById(R.id.creationbipoll_choise2);
        String choise2 =choise2EditText.getText().toString();

        String author = User.getConnectedUser().getLogin();
        if(title.equals("")||choise1.equals("")||choise2.equals("")){
            makeToast(this,"please fill all entry");
        }
        else {
            if ( BiPoll.addBiPoll(title, author, choise1, choise2) ){
                makeToast(this,"Bipoll created!");
                Intent intent = new Intent(this, MainMenuActivity.class);
                startActivity(intent);
            }
            else{
                makeToast(this,"error occurred");
            }
        }

    }

    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            confirm(v);
            return true;
        }
        return false;
    }
}
