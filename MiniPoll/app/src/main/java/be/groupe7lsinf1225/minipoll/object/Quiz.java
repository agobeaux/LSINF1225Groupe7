package be.groupe7lsinf1225.minipoll.object;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.MySQLiteHelper;

public class Quiz extends Poll {

    private String title;
    private String author;
    private boolean state;
    private int IDQuiz;

    public Quiz(String title, boolean state, String author, int IDQuiz){
        this.title = title;
        this.state = state;
        this.author = author;
        this.IDQuiz = IDQuiz;
    }

    public int getID() { return IDQuiz; }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean getState() {
        return state;
    }

    public static Quiz getQuiz(String Id) {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String[] columns = {"IDQUIZ","TITLE", "AUTHOR", "CLOSED"};
        int IdInt = Integer.parseInt(Id);
        String selection = "IDQUIZ = " + IdInt;
        Cursor cursor = db.query("QUIZ", columns, selection, null, null, null, null);
        if( cursor.moveToFirst() ) {
            Quiz locQuiz = new Quiz(cursor.getString(1), !cursor.getString(3).equals("false"), cursor.getString(2), cursor.getInt(0));
            cursor.close();
            db.close();
            return locQuiz;
        }
        Log.e(null, "Error : no quiz");
        db.close();
        return null;
    }
    public static ArrayList<Integer> getIDQuestions(String IdQuiz){
        ArrayList<Integer> IDQuestions = new ArrayList<Integer>();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String[] columns = {"IDQUESTION","IDQUIZ","POSITION","TITLE"};
        int IdInt = Integer.parseInt(IdQuiz);
        String selection = "IDQUIZ = " + IdInt;
        Cursor cursor = db.query("QUESTION_QUIZ", columns, selection, null, null, null, "POSITION");
        if( cursor.moveToFirst() ) {
            while(!cursor.isAfterLast()){
                IDQuestions.add(cursor.getInt(0));
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
            return IDQuestions;
        }
        Log.e(null, "Error : no questions in quiz");
        db.close();
        return null;
    }
    public static ArrayList<String> getLogins(String IDQuiz){
        ArrayList<String> Ids = new ArrayList<>();
        SQLiteDatabase db;
        db = MySQLiteHelper.get().getReadableDatabase();

        String[] columns = {"LOGIN","IDQUIZ"};
        String[] valuesWhere = {IDQuiz};
        String selection = "IDQUIZ" + " = ?";

        Cursor cursor = db.query("VIEW_QUIZ", columns, selection, valuesWhere, null, null, null);
        if( cursor.moveToFirst() ) {
            int i;
            for(i=0; i < cursor.getCount(); i++ ) {
                Ids.add(String.valueOf(cursor.getInt(0)));
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
            return Ids;
        }
        cursor.close();
        db.close();
        return Ids;
    }
    public static boolean isInQuiz(String Login, String IdQuiz){
        ArrayList<Integer> IDQuestions = Quiz.getIDQuestions(IdQuiz);
        SQLiteDatabase db1;
        db1 = MySQLiteHelper.get().getReadableDatabase();
        String[] columns = {"LOGIN"};
        String[] valuesWhere = {Login};
        String selection = "LOGIN = \"" + Login + "\"" + " AND IDQUESTION = "+ String.valueOf(IDQuestions.get(0));
        Log.e(null,selection );
        Cursor cursor = db1.query("ANSWER_QUIZ", columns, selection, null, null, null, null );
        if( cursor.moveToFirst() ) {
            cursor.close();
            db1.close();
            return true;
        }
        db1.close();
        return false;
    }


}
