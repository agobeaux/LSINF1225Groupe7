package be.groupe7lsinf1225.minipoll.object;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.MySQLiteHelper;

public class Quiz extends Poll {

    private ArrayList<Question> Questions;
    private String title;
    private String author;
    private boolean state;

    public Quiz(String title, boolean state, String author){
        this.title = title;
        this.state = state;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean getState() {
        return state;
    }

    public static Quiz getId(String Id) {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String[] columns = {"IDQUIZ","TITLE", "AUTHOR", "CLOSED"};
        int IdInt = Integer.parseInt(Id);
        String selection = "IDQUIZ = " + IdInt;
        Cursor cursor = db.query("QUIZ", columns, selection, null, null, null, null);
        if( cursor.moveToFirst() ) {
            Quiz locQuiz = new Quiz(cursor.getString(1), !cursor.getString(3).equals("false"), cursor.getString(2));
            cursor.close();
            db.close();
            return locQuiz;
        }
        Log.e(null, "Error : no quiz");
        db.close();
        return null;
    }
    public static ArrayList<Integer> getQuestions(String IdQuiz){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String[] columns = {"IDQUESTION","IDQUIZ","POSITION","TITLE"};
        int IdInt = Integer.parseInt(IdQuiz);
        String selection = "IDQUIZ = " + IdInt;
        Cursor cursor = db.query("QUESTION_QUIZ", columns, selection, null, null, null, "POSITION");
        if( cursor.moveToFirst() ) {
            while(!cursor.isAfterLast()){
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        Log.e(null, "Error : no questions in quiz");
        db.close();
        return null;
    }
}
