package be.groupe7lsinf1225.minipoll.object;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import be.groupe7lsinf1225.minipoll.MySQLiteHelper;

public class Choice {

    private String title;
    private int IDChoice;
    private boolean good_answer;

    /**
     * Constructeur
     */
    public Choice(String title, int IDChoice, boolean good_answer) {
        this.title = title;
        this.IDChoice = IDChoice;
        this.good_answer = good_answer;
    }

    // == get == //

    public static boolean isGoodQuizAnswer(String Id) {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String[] columns = {"IDCHOICE", "ISGOOD_ANSWER"};
        int id = Integer.parseInt(Id);
        String selection = "IDCHOICE = " + id;
        Cursor cursor = db.query("CHOICE_QUIZ", columns, selection, null, null, null, null);
        if(cursor.moveToFirst()){
            cursor.close();
            db.close();
            return cursor.getString(1).equals("true");
        }
        Log.e(null, "Not an IDChoice");
        cursor.close();
        db.close();
        return false;
    }

    public int getIDChoice() { return IDChoice;}
    public boolean isGoodAnswer() { return good_answer;}
    public String getTitle(){
        return title;
    }
}
