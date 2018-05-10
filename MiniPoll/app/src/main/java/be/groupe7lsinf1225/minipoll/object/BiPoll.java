package be.groupe7lsinf1225.minipoll.object;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.AppMiniPoll;
import be.groupe7lsinf1225.minipoll.MySQLiteHelper;

public class BiPoll extends Poll {

    private ArrayList<Choice> choices;
    private String question;
    private String author;
    private boolean state;

    /**
     * Constructeur
     */
    public BiPoll(String question, String author, String[] titles_choices) {
        this.question = question;
        ArrayList<Choice> c = new ArrayList<>();
        int i;
        for(i=0 ; i<2 ; i++){
            c.add(new Choice(false, titles_choices[i]));
        }
        this.choices = c;
        this.author = author;
        this.state = false;
    }

    public static boolean addBiPoll(String title,String author,String choice1,String choice2,ArrayList<String> selected_friends){

        int id = BiPoll.getId();
        //int idc1 = 2*id - 1;
        int idc1 = id;
        int idc2 = id+1;

        ContentValues values = new ContentValues();
        values.put("IDCHOICE",idc1);
        values.put("CONTENT",choice1);

        ContentValues values2 = new ContentValues();
        values2.put("IDCHOICE",idc2);
        values2.put("CONTENT",choice2);

        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();


        //int dia1 = 1;
        if(-1 == (int) db.insert("CHOICE_BIPOLL",null,values)){
            db.close();
            return false;
        }
        if(-1 == (int) db.insert("CHOICE_BIPOLL",null,values2)){
            String selection = "IDCHOICE = CAST(? as INTEGER)";
            String[] valuesWhere = {String.valueOf(idc1)};
            db.delete("CHOICE_BIPOLL",selection,valuesWhere);
            db.close();
            return false;
        }


        // db.delete("CHOICE_BIPOLL", "IDCHOICE = "+ String.valueOf(idc1), null);

        /*
        String[] selectionTest = {"CONTENT"};
        String where = "IDCHOICE = CAST(? as INTEGER)";
        String[] valuesWhere = {String.valueOf(idc1)};
        Cursor c1 = db.query("CHOICE_BIPOLL",selectionTest,where,valuesWhere,null,null,null);

        if(c1.getCount() <= 0)
            Log.e("TEST","C1 <= 0");
        else if(c1.getCount() == 1)
            Log.e("TEST","C1 = 1");
        */

        int idBipoll = idc2/2;
        ContentValues values3 = new ContentValues();
        values3.put("IDBIPOLL", idBipoll);
        values3.put("TITLE", title);
        values3.put("AUTHOR", author);
        values3.put("CHOICE1", idc1);
        values3.put("CHOICE2", idc2);

        if (-1 == (int) db.insert("BIPOLL", null, values3)) {
            db.delete("CHOICE_BIPOLL", "IDCHOICE = " + String.valueOf(idc1), null);
            db.delete("CHOICE_BIPOLL", "IDCHOICE = " + String.valueOf(idc2), null);
            db.close();
            return false;
        }
        int i;
        for(i=0 ; i<selected_friends.size(); i++) {
            ContentValues values4 = new ContentValues();
            values4.put("LOGIN",selected_friends.get(i));
            values4.put("IDBIPOLL", idBipoll);

            if(-1 == (int) db.insert("VIEW_BIPOLL",null,values4)){
                db.delete("VIEW_BIPOLL","IDBIPOLL = " + String.valueOf(idBipoll),null);
                db.delete("CHOICE_BIPOLL", "IDCHOICE = " + String.valueOf(idc1), null);
                db.delete("CHOICE_BIPOLL", "IDCHOICE = " + String.valueOf(idc2), null);
                db.delete("BIPOLL","IDBIPOLL = " + String.valueOf(idBipoll),null);
                db.close();
                return false;
            }

        }
        ContentValues values5 = new ContentValues();
        values5.put("LOGIN",User.getConnectedUser().getLogin());
        values5.put("IDBIPOLL",idBipoll);

        if(-1 == (int) db.insert("VIEW_BIPOLL",null,values5)) {
            db.delete("VIEW_BIPOLL","IDBIPOLL = " + String.valueOf(idBipoll),null);
            db.delete("CHOICE_BIPOLL", "IDCHOICE = " + String.valueOf(idc1), null);
            db.delete("CHOICE_BIPOLL", "IDCHOICE = " + String.valueOf(idc2), null);
            db.delete("BIPOLL","IDBIPOLL = " + String.valueOf(idBipoll),null);
            db.close();
            return false;
        }

        db.close();


        return true;
    }

    public static int getId(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String[] columns = {"IDCHOICE"};

        Cursor cursor = db.query("CHOICE_BIPOLL",columns,null,null,null,null,"IDCHOICE");

        cursor.moveToLast();

        int id = cursor.getInt(0);

        Log.e("getID","id du dernier: "+id);

        cursor.close();
        db.close();
        return id+1;
    }

    public static ArrayList<BiPoll> getBiPolls() {
        ArrayList<BiPoll> biPolls = new ArrayList<>();

        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String[] columns1 = {"LOGIN","IDBIPOLL"};
        String[] valuesWhere1 = {User.getConnectedUser().getLogin()};
        String selection1 = "LOGIN" + " = ?";

        Cursor cursor1 = db.query("VIEW_BIPOLL", columns1, selection1, valuesWhere1, null, null, null);

        cursor1.moveToFirst();

        String question;
        String author;

        while (!cursor1.isAfterLast()) {
            String[] columns2 = {"IDBIPOLL","TITLE", "AUTHOR", "CHOICE1", "CHOICE2", "CLOSED"};
            String[] valuesWhere2 = {String.valueOf(cursor1.getInt(1))};
            String selection2 = "IDBIPOLL = CAST(? as INTEGER)";

            Cursor cursor2 = db.query("BIPOLL", columns2, selection2, valuesWhere2, null, null, null);
            cursor2.moveToFirst();

            String[] columns3 = {"CONTENT"};
            String selection3 = "IDCHOICE = CAST(? as INTEGER)";
            String[] valuesWhere3_1 = {String.valueOf(cursor2.getInt(3))};
            String[] valuesWhere3_2 = {String.valueOf(cursor2.getInt(4))};

            Cursor cursor3_1 = db.query("CHOICE_BIPOLL",columns3,selection3,valuesWhere3_1,null,null,null);
            Cursor cursor3_2 = db.query("CHOICE_BIPOLL",columns3,selection3,valuesWhere3_2,null,null,null);

            cursor3_1.moveToFirst();
            cursor3_2.moveToFirst();

            String titles[] = {cursor3_1.getString(0),cursor3_2.getString(0)};
            question = cursor2.getString(1);
            author = cursor2.getString(2);

            BiPoll locBipoll = new BiPoll(question,author,titles);
            cursor2.close();

            biPolls.add(locBipoll);

            cursor2.close();
            cursor3_1.close();
            cursor3_2.close();

            cursor1.moveToNext();
        }
        cursor1.close();
        db.close();
        return biPolls;
    }

    public String getQuestion() {
        return question;
    }

    public String getAuthor() {
        return author;
    }

    public boolean getState() {
        return state;
    }

    public ArrayList<Choice> getChoices() {return choices;}
}
