package be.groupe7lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.activity.adapter.ViewFriendAdapter;
import be.groupe7lsinf1225.minipoll.object.User;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

public class ViewFriendActivity extends Activity {

    private ArrayList<User> al;
    private ViewFriendAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        //al = User.getAllFriend(User.getConnectedUser().getLogin());
        al = new ArrayList<>();

        arrayAdapter = new ViewFriendAdapter(this, al);

        SwipeFlingAdapterView flingContainer = findViewById(R.id.view_user_frame);

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {

            @Override
            public void removeFirstObjectInAdapter() {
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                makeToast(ViewFriendActivity.this, "See you soon!");
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                makeToast(ViewFriendActivity.this, "I'm Leaving!");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                al.add(new User("No More Friend","","","","","",-1));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }
        });


        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                makeToast(ViewFriendActivity.this, "Clicked!");
            }
        });
    }

    public void setbestfriend(View view){
        String newbestfriend = al.get(0).getLogin();
        if(!newbestfriend.equals("No More Friend") && !newbestfriend.equals(User.getConnectedUser().getbestfriend())) {
            User.getConnectedUser().setbestfriend(newbestfriend);
            ImageButton bestfriendbutton = findViewById(R.id.view_friend_bestfriend_button);
            bestfriendbutton.setImageResource(R.drawable.default_profile);
            makeToast(ViewFriendActivity.this, "best friend set!");
        }
    }

    public void suppfriend(View view){
        makeToast(ViewFriendActivity.this, "friend deleted!");
    }

    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }


}