package be.groupe7lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.activity.adapter.ViewUserAdapter;
import be.groupe7lsinf1225.minipoll.object.User;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

public class ViewUserActivity extends Activity {

    private ArrayList<User> al;
    private ViewUserAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        al = new ArrayList<>();
        al.add(new User("LoL1","","first1","last1","coucou1@com","",-1));
        al.add(new User("LoL2","","first2","last2","coucou2@com","",-1));
        al.add(new User("LoL3","","first3","last3","coucou3@com","",-1));
        al.add(new User("LoL4","","first4","last4","coucou4@com","",-1));
        al.add(new User("LoL5","","first5","last5","coucou5@com","",-1));
        al.add(new User("LoL6","","first6","last6","coucou6@com","",-1));
        al.add(new User("LoL7","","first7","last7","coucou7@com","",-1));
        al.add(new User("LoL8","","first8","last8","coucou8@com","",-1));

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
                makeToast(ViewUserActivity.this, "Left!");
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                makeToast(ViewUserActivity.this, "Right!");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                al.add(new User("LoLX","","firstX","lastX","coucou@com","",-1));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                //View view = flingContainer.getSelectedView();
                //view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                //view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
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

    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }


}
