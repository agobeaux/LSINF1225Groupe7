package be.groupe7lsinf1225.minipoll.object;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.MySQLiteHelper;

public class Question {

    private String title;
    private ArrayList<Choice> choices;
    // On stocke l'indice du choix qui correspond à la bonne réponse
    private int good_answer;

    /**
     * Constructeur
     */
    public Question(String title, boolean isPicture, String[] choices, int good_answer)
    {
        this.title = title;

        ArrayList<Choice> c = new ArrayList<>();
        int i;
        for(i=0 ; i<4 ; i++) {
            c.add(new Choice(isPicture,choices[i]));
        }

        this.choices = c;
        this.good_answer = good_answer;
    }

    // == Picture == //

    public int getGoodAnswer()
    {
        return good_answer;
    }

    public Choice getChoice(int ind)
    {
        return this.choices.get(ind);
    }

    public String getTitle()
    {
        return title;
    }

    public static Choice[] getChoices(String IdQuestion) {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String[] columns = {"IDCHOICE","TITLE", "AUTHOR", "CLOSED"};
        int IdInt = Integer.parseInt(IdQuestion);
        String selection = "IDQUIZ = " + IdInt;
        Cursor cursor = db.query("CHOICE_QUIZ", columns, selection, null, null, null, null);
        if( cursor.moveToFirst() ) {
            Quiz locQuiz = new Quiz(cursor.getString(1), !cursor.getString(3).equals("false"), cursor.getString(2));
            cursor.close();
            db.close();
            return null;
        }
        Log.e(null, "Error : no quiz");
        db.close();
        return null;
    }
}
