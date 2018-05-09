package be.groupe7lsinf1225.minipoll.object;

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
