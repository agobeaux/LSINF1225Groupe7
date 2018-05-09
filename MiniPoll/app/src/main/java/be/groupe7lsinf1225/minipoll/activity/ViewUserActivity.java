package be.groupe7lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.activity.adapter.ViewUserAdapter;
import be.groupe7lsinf1225.minipoll.object.User;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

public class ViewUserActivity extends Activity {

    private ArrayList<User> al;
    private int arraysize;
    private ViewUserAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        al = User.getAllUser();
        arraysize = al.size();

        arrayAdapter = new ViewUserAdapter(this, al);

        SwipeFlingAdapterView flingContainer = findViewById(R.id.view_user_frame);

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {

            @Override
            public void removeFirstObjectInAdapter() {
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
                if(!arrayAdapter.isfinished()) {
                    arrayAdapter.tic();
                }
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                makeToast(ViewUserActivity.this, "Bye Bye!");
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                makeToast(ViewUserActivity.this, "I'm gone!");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                al.add(new User("No More User","","","","","",-1));
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                makeToast(ViewUserActivity.this, "Clicked!");
            }
        });
    }

    public void addfriend(View view){
        if(!arrayAdapter.isfinished()) {
            String friend = al.get(0).getLogin();

            if (User.getConnectedUser().FriendRequest(friend)) {
                makeToast(ViewUserActivity.this, "Friend request send to " + friend + " !");
                ImageButton addbutton = findViewById(R.id.view_user_add_button);
                addbutton.setImageResource(R.drawable.default_profile);
            } else {
                makeToast(ViewUserActivity.this, "inactif button");
            }
        }
    }

    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }


}
