package be.groupe7lsinf1225.minipoll.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.MySQLiteHelper;
import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.activity.adapter.BiPollAdapter;
import be.groupe7lsinf1225.minipoll.object.BiPoll;
import be.groupe7lsinf1225.minipoll.object.User;

//@Override
public class BiPollActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


        private ArrayList<BiPoll> bipolls;
        private BiPollAdapter myBiPollAdapter;


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
                SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

                String[] columns1 = {"LOGIN","IDBIPOLL"};
                String[] valuesWhere1 = {User.getConnectedUser().getLogin()};
                String selection1 = "LOGIN" + " = ?";

                Cursor cursor1 = db.query("VIEW_BIPOLL", columns1, selection1, valuesWhere1, null, null, null);

                cursor1.moveToFirst();

                while (!cursor1.isAfterLast()) {
                        String[] columns2 = {"IDBIPOLL","TITLE", "AUTHOR", "CHOICE1", "CHOICE2", "CLOSED"};
                        String[] valuesWhere2 = {String.valueOf(cursor1.getInt(1))};
                        String selection2 = "IDBIPOLL" + " = ?";

                        Cursor cursor2 = db.query("BIPOLL", columns2, selection2, valuesWhere2, null, null, null);
                        String loctitle[] = {"reponse1","reponse2"};
                        loctitle[1] = cursor2.getString(3);
                        loctitle[2] = cursor2.getString(4);

                        BiPoll locbipoll = new BiPoll(cursor2.getString(1),cursor2.getString(2) ,loctitle, cursor2.getString(5).equals(true));
                        cursor2.close();
                        bipolls.add(locbipoll);

                        cursor1.moveToNext();
                }
                cursor1.close();
                db.close();
        }



        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }



}
