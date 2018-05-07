package be.groupe7lsinf1225.minipoll.object;

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
    private String picture;

    /**
     * Constructeur (accessible uniquement dans cette classe, instanciable en dehors via getUsers)
     */
    private User(String login, String password){
        this.login = login;
        this.password = password;
    }
    private User(String login, String password, String first_name, String last_name, String email, String best_friend, String picture) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.best_friend = best_friend;
        this.picture = picture;
    }

    /**
     * Déconnecte le User
     */
    public static void logout() {
        User.connected_user = null;
        // + tout ce qu'il y aura à couper quand on se déconnecte (ex dans le muusic_player : couper
        // la musique)
    }

    /**
     * Connecte le User si le mot de passe entré est le bon
     */
    public boolean login(String passwordWritten) {
        if(this.password.equals(passwordWritten)) {
            User.connected_user = this;
            return true;
        }
        return false;
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
}
