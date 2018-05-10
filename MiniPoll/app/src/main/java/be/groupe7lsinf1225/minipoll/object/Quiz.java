package be.groupe7lsinf1225.minipoll.object;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import be.groupe7lsinf1225.minipoll.MySQLiteHelper;

public class Quiz extends Poll {

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

    public static Quiz getId(int Id) {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String[] columns = {"IDQUIZ","TITLE", "AUTHOR", "CLOSED"};
        String[] valuesWhere = {String.valueOf(Id)};
        Log.e(null, "IDQUIZFROMQUICLASS :" + valuesWhere[0]);
        String selection = "IDQUIZ" + " = ?";
        Cursor cursor = db.query("QUIZ", columns, selection, valuesWhere, null, null, null);
        Log.e(null, "NbOfRows: " + cursor.getCount());
        if( cursor.moveToFirst() ) {
            Log.e(null, "Title :" + cursor.getString(1));
            Quiz locQuiz = new Quiz(cursor.getString(1), !cursor.getString(3).equals("false"), cursor.getString(2));
            cursor.close();
            db.close();
            return locQuiz;
        }
        Log.e(null, "Pourquoi ça ne marche pas ?");
        db.close();
        return null;
    }
}
