package be.groupe7lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.AppMiniPoll;
import be.groupe7lsinf1225.minipoll.MySQLiteHelper;
import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.activity.adapter.BiPollAdapter;
import be.groupe7lsinf1225.minipoll.object.BiPoll;
import be.groupe7lsinf1225.minipoll.object.User;

//@Override
public class BiPollActivity extends Activity implements AdapterView.OnItemClickListener {


        private ArrayList<BiPoll> bipolls;
        private BiPollAdapter myBiPollAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_bipoll);

            loadBiPolls();

            ListView myListView = findViewById(R.id.show_listView_bipolls);

            myBiPollAdapter = new BiPollAdapter(this, bipolls);
            myListView.setAdapter(myBiPollAdapter);

            myListView.setOnItemClickListener(this);
        }


        private void loadBiPolls() {
            bipolls = BiPoll.getBiPolls();
            if(bipolls.isEmpty()) {
                AppMiniPoll.notifyLong(R.string.no_visible_bipoll);
                finish();
            }
        }

        @Override
        public void onResume() {
            super.onResume();

            loadBiPolls();

            myBiPollAdapter.setBipolls(bipolls);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(this.bipolls.get(position).equals(User.getConnectedUser().getLogin())){
                //full gestion & resultat
            }
            else{
                //pour repondre / resultat
                // ( implementez bipoll.haveAnswered() )
            }
        }

}