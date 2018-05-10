package be.groupe7lsinf1225.minipoll.object;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.MySQLiteHelper;

public class Question {

    private String title;
    // On stocke l'indice du choix qui correspond à la bonne réponse
    private int IDQuestion;


    /**
     * Constructeur
     */
    public Question(String title, int IDQuestion)
    {
        this.title = title;

        this.IDQuestion = IDQuestion;
    }

    // == Picture == //

    public int getIDQuestion()
    {
        return IDQuestion;
    }

    public String getTitle()
    {
        return title;
    }

    public static Choice[] getChoices(String IdQuestion) {
        Choice[] choices = new Choice[4];
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String[] columns = {"IDCHOICE", "IDQUESTION", "TITLE", "ISGOOD_ANSWER"};
        int IdInt = Integer.parseInt(IdQuestion);
        String selection = "IDQUESTION = " + IdInt;
        Cursor cursor = db.query("CHOICE_QUIZ", columns, selection, null, null, null, null);
        if( cursor.moveToFirst() ) {
            int i = 0;
            while (!cursor.isAfterLast()) {
                Choice choice = new Choice(cursor.getString(2),cursor.getInt(0), cursor.getString(3).equals("true"));
                choices[i] = choice;
                i++;
            }
            cursor.close();
            db.close();
            return choices;
        }
        Log.e(null, "Error : no quiz");
        db.close();
        return null;
    }
}
