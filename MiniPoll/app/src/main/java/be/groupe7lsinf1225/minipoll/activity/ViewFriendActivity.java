package be.groupe7lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.activity.adapter.ViewFriendAdapter;
import be.groupe7lsinf1225.minipoll.object.Picture;
import be.groupe7lsinf1225.minipoll.object.User;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

public class ViewFriendActivity extends Activity {

    private ArrayList<User> al;
    private ViewFriendAdapter arrayAdapter;
    private int  supp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        al = User.getConnectedUser().getAllFriend("1");

        arrayAdapter = new ViewFriendAdapter(this, al);

        SwipeFlingAdapterView flingContainer = findViewById(R.id.view_user_frame);

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {

            @Override
            public void removeFirstObjectInAdapter() {
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
                if(!arrayAdapter.isfinished()) {
                    arrayAdapter.tic();
                    supp = 0;
                    arrayAdapter.notifyDataSetChanged();
                }
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
            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }
        });


        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                //makeToast(ViewFriendActivity.this, "");
            }
        });
    }

    public void setbestfriend(View view){
        if(!arrayAdapter.isfinished()) {
            String newbestfriend = al.get(0).getLogin();
            if (!newbestfriend.equals(User.getConnectedUser().getbestfriend())) {
                User.getConnectedUser().setbestfriend(newbestfriend);
                ImageButton bestfriendbutton = view.findViewById(R.id.view_friend_bestfriend_button);
                bestfriendbutton.setImageResource(R.drawable.ic_best_friends);
                makeToast(ViewFriendActivity.this, "best friend set!");
            }
        }
    }

    public void suppfriend(View view){
        if(!arrayAdapter.isfinished()) {
            if(supp==2) {
                if(User.getConnectedUser().suppFriend(al.get(0).getLogin())) {
                    makeToast(ViewFriendActivity.this, "friend deleted!");
                    ImageView profileImage = view.findViewById(R.id.view_friend_profil_photo);
                    profileImage.setImageResource(R.drawable.ic_supp_friend);
                    arrayAdapter.notifyDataSetChanged();
                }
                else{
                    makeToast(ViewFriendActivity.this, "an error occurred");
                    supp = 1;
                }
            }
            else {
                if(supp==0){
                    makeToast(ViewFriendActivity.this, "Hit 3 time to delet him from your friend list");
                }
            }
            supp++;
        }
    }

    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }


}