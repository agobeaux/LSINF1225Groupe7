package be.groupe7lsinf1225.minipoll.object;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

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

    public static boolean addBiPoll(String title,String author,String choise1,String choise2){
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();

        int id = BiPoll.getId();
        int idc1 = 2*id - 1;
        int idc2 = 2*id;

        ContentValues values = new ContentValues();
        values.put("IDCHOICE",idc1);
        values.put("CONTENT",choise1);

        //le insert crash je sat pas pq
        if(-1 == db.insert("CHOICE_BIPOLL",null,values)){
            db.close();
            return false;
        }
        db.delete("CHOICE_BIPOLL", "IDCHOICE = "+ String.valueOf(idc1), null);
        if(false) {
            ContentValues values1 = new ContentValues();
            values1.put("IDCHOICE", idc2);
            values1.put("CONTENT", choise2);

            if (-1 == db.insert("CHOICE_BIPOLL", null, values1)) {
                db.delete("CHOICE_BIPOLL", "IDCHOICE = " + String.valueOf(idc1), null);
                db.close();
                return false;
            }

            ContentValues values2 = new ContentValues();
            values2.put("IDBIPOLL", id);
            values2.put("TITLE", title);
            values2.put("AUTHOR", author);
            values2.put("CHOICE1", idc1);
            values2.put("CHOICE2", idc2);

            if (-1 == (int) db.insert("BIPOLL", null, values2)) {
                db.delete("CHOICE_BIPOLL", "IDCHOICE = " + String.valueOf(idc1), null);
                db.delete("CHOICE_BIPOLL", "IDCHOICE = " + String.valueOf(idc2), null);
                db.close();
                return false;
            }
        }
        return true;
    }

    public static int getId(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String[] columns = {"IDBIPOLL"};
        Cursor cursor = null;
        for(int i = 1;i<1000;i++){
            cursor = db.query("BIPOLL", columns, "IDBIPOLL = " + String.valueOf(i),null, null, null, null);
            if(cursor.getCount() <= 0){
                cursor.close();
                db.close();
                return i;
            }
        }
        cursor.close();
        db.close();
        return -1;
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
