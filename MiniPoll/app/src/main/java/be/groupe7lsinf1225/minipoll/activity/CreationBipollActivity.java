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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.AppMiniPoll;
import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.activity.adapter.BiPollAdapter;
import be.groupe7lsinf1225.minipoll.activity.adapter.SelectableFriendsAdapter;
import be.groupe7lsinf1225.minipoll.object.BiPoll;
import be.groupe7lsinf1225.minipoll.object.User;


public class CreationBipollActivity extends Activity implements TextView.OnEditorActionListener {

    private ArrayList<String> selectable_friends;
    private ArrayList<String> selected_friends = new ArrayList<>();
    private SelectableFriendsAdapter mySelectableFriendsAdapter;

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

        loadFriendsUsername();

        ListView myListView = findViewById(R.id.select_visible_friends);

        mySelectableFriendsAdapter = new SelectableFriendsAdapter(this, selectable_friends);
        myListView.setAdapter(mySelectableFriendsAdapter);

        makeToast(this,String.valueOf(BiPoll.getId())); //a retiré
    }

    private void loadFriendsUsername() {
        selectable_friends = User.loadFriendsUsername();
        if(selectable_friends.isEmpty()) {
            AppMiniPoll.notifyLong(R.string.no_friends_to_share);
            finish();
        }
    }

    public void oneFriendSelected(View view) {
        boolean checked = ((CheckBox)view).isChecked();
        String username = (String)(((CheckBox) view).getText());
        if(checked) {
            selected_friends.add(username);
        }
        else {
            selected_friends.remove(username);
        }
    }

    public void confirm(View view){
        EditText titleEditText = findViewById(R.id.creationbipoll_title);
        String title = titleEditText.getText().toString();
        EditText choice1EditText = findViewById(R.id.creationbipoll_choise1);
        String choice1 = choice1EditText.getText().toString();
        EditText choice2EditText = findViewById(R.id.creationbipoll_choise2);
        String choice2 =choice2EditText.getText().toString();

        String author = User.getConnectedUser().getLogin();
        if(title.equals("")||choice1.equals("")||choice2.equals("")){
            makeToast(this,"please fill all entry");
        }
        else if(selected_friends.isEmpty()) {
            AppMiniPoll.notifyShort(R.string.minimum_one_friend);
        }
        else {
            if ( BiPoll.addBiPoll(title, author, choice1, choice2,selected_friends) ){
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
