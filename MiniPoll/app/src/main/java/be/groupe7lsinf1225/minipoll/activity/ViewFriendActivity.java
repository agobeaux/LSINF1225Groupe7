package be.groupe7lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.activity.adapter.ViewUserAdapter;
import be.groupe7lsinf1225.minipoll.object.User;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

public class ViewFriendActivity extends Activity {

    private ArrayList<User> al;
    private ViewUserAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        //al = User.getAllFriend(User.getConnectedUser().getLogin());
        al = new ArrayList<>();

        arrayAdapter = new ViewUserAdapter(this, al );

        SwipeFlingAdapterView flingContainer = findViewById(R.id.view_user_frame);

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {

            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                al.add(al.get(0));
                makeToast(ViewFriendActivity.this, "See you soon!");
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                makeToast(ViewFriendActivity.this, "I'm Leaving!");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                al.add(new User("No More Friend","","","","","",-1));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                makeToast(ViewFriendActivity.this, "Clicked!");
            }
        });
    }

    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }


}