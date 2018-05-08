package be.groupe7lsinf1225.minipoll.object;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.MySQLiteHelper;

public class User {

    private static final String DB_COLUMN_LOGIN = "LOGIN";
    private static final String DB_COLUMN_LASTNAME = "LASTNAME";
    private static final String DB_COLUMN_FIRSTNAME = "FIRSTNAME";
    private static final String DB_COLUMN_PASSWORD = "PASSWORD";
    private static final String DB_COLUMN_EMAIL = "EMAIL";
    private static final String DB_COLUMN_BEST_FRIEND = "BESTFRIEND";
    private static final String DB_COLUMN_PICTURE = "PICTURE";
    private static final String DB_COLUMN_BESTFRIEND = "BESTFRIEND";
    private static final String DB_TABLE = "USER";


    private String best_friend = null;

    /**
     * Contient le login du User connecté
     */
    private static User connected_user = null;

    /**
     * Contient tous les login des amis
     */
    private ArrayList<String> friends = null;

    /**
     * Contient tous les id des poll créés par ce User
     */
    private ArrayList<Integer> poll_created = null;

    private String first_name;
    private String last_name;
    private String login;
    private String password;
    private String email;
    private int picture;

    /**
     * Constructeur (accessible uniquement dans cette classe, instanciable en dehors via getUser)
     */
    private User(String login, String password){
        this.login = login;
        this.password = password;
    }
    public User(String login, String password, String first_name, String last_name, String email,String bestfriend,int picture) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.best_friend = bestfriend;
        this.picture = picture;
    }

    /**
     * Déconnecte le User
     */
    public static void logout() {
        User.connected_user = null;
        // + tout ce qu'il y aura à couper quand on se déconnecte (ex dans le music_player : couper
        // la musique)
    }

    /**
     * Connecte le User si le mot de passe entré est le bon
     */
    public boolean login(String passwordWritten) {
        if(this.goodPassword(passwordWritten)) {
            User.connected_user = this;
            return true;
        }
        return false;
    }

    public static ArrayList<User> getAllUser() {
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String[] columns = {DB_COLUMN_LOGIN,DB_COLUMN_PASSWORD,DB_COLUMN_FIRSTNAME,DB_COLUMN_LASTNAME,DB_COLUMN_EMAIL,DB_COLUMN_BEST_FRIEND,DB_COLUMN_PICTURE};

        Cursor cursor = db.query(DB_TABLE, columns, null, null, null, null, null);

        cursor.moveToFirst();

        String userRequest = User.getConnectedUser().getLogin();

        while (!cursor.isAfterLast()){
            if(!userRequest.equals(cursor.getString(0))) {
                users.add(new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), -1));
            }
            cursor.moveToNext();
        }

        cursor.close();
        db.close();
        return users;
    }

    public boolean goodPassword(String passwordWritten) {
        return this.password.equals(passwordWritten);
    }


    public static User getUser(String login) {
        // Récupération du  SQLiteHelper et de la base de données.
        // C'est ça le lien avec la base de données
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        // Colonnes à récupérer
        String[] columns = {DB_COLUMN_LOGIN,DB_COLUMN_PASSWORD};
        String[] valuesWhere = {login};
        String selection = DB_COLUMN_LOGIN + " = ?";

        // Requête de selection (SELECT)
        Cursor cursor = db.query(DB_TABLE, columns, selection, valuesWhere, null, null, null);

        if(cursor.getCount() <= 0){
            return null;
        }
        // Placement du curseur sur la première ligne.
        cursor.moveToFirst();

        String password = cursor.getString(1);


        User user = new User(login,password);

        // Fermeture du curseur et de la base de données.
        cursor.close();
        db.close();

        return user;
    }

    public static User getInfosConnectedUser(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String[] columns = {DB_COLUMN_LOGIN,DB_COLUMN_PASSWORD,DB_COLUMN_LASTNAME,DB_COLUMN_FIRSTNAME,DB_COLUMN_EMAIL};
        String[] valuesWhere = {User.getConnectedUser().getLogin()};
        String selection = DB_COLUMN_LOGIN + " = ?";

        Cursor cursor = db.query(DB_TABLE, columns, selection, valuesWhere, null, null, null);

        if(cursor.getCount() <= 0){
            return null;
        }

        // Placement du curseur sur la première ligne.
        cursor.moveToFirst();

        String username = cursor.getString(0);
        String password = cursor.getString(1);
        String first_name = cursor.getString(3);
        String last_name = cursor.getString(2);
        String email = cursor.getString(4);

        User user = new User(username,password,first_name,last_name,email,null,-1);

        cursor.close();
        db.close();

        return user;
    }

    public static boolean putUser(String username, String password, String first_name, String last_name, String email){
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DB_COLUMN_LOGIN, username);
        values.put(DB_COLUMN_PASSWORD, password);
        values.put(DB_COLUMN_LASTNAME, last_name);
        values.put(DB_COLUMN_FIRSTNAME, first_name);
        values.put(DB_COLUMN_EMAIL, email);

        int result = (int) db.insert(DB_TABLE,null,values);
        if(result == -1){
            // erreur dans l'écriture dans la base de données
            db.close();
            return false;
        }
        db.close();
        return true;
    }


    public static void updateUser(String old_username,String first_name,String last_name,String username,String mailaddress,String password) {

        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DB_COLUMN_LOGIN,username);
        values.put(DB_COLUMN_PASSWORD, password);
        values.put(DB_COLUMN_LASTNAME, last_name);
        values.put(DB_COLUMN_FIRSTNAME, first_name);
        values.put(DB_COLUMN_EMAIL, mailaddress);

        String selection = DB_COLUMN_LOGIN + " = ?";
        String[] valuesWhere = {old_username};

        db.update(DB_TABLE,values,selection,valuesWhere);

        db.close();

        User user = new User(username,password,first_name,last_name,null,mailaddress,-1);
        User.connected_user = user;
    }

    // === Set === //

    public void setbestfriend(String best_friend){ this.best_friend = best_friend; }

    // === Get === //

    public static User getConnectedUser() {
        return User.connected_user;
    }


    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getbestfriend() {
        return best_friend;
    }

    public int getPicture() {
        return picture;
    }
}
