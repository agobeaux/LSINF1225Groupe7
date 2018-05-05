package be.groupe7lsinf1225.minipoll.object;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
    private User(String ufirst_name, String ulast_name, String ulogin, String upassword, String uemail, String ubest_friend, String upicture) {
        this.first_name = ufirst_name;
        this.last_name = ulast_name;
        this.login = ulogin;
        this.password = upassword;
        this.email = uemail;
        this.best_friend = ubest_friend;
        this.picture = upicture;
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

    // Toute la partie en commentaire n'est pas fonctionnelle tant que la base de données n'est pas
    // liée (avec un db. et MySQLiteHelper)

    public static ArrayList<User> getUsers() {
        // Récupération du  SQLiteHelper et de la base de données.
        // C'est ça le lien avec la base de données
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        // Colonnes à récupérer
        String[] colonnes = {DB_COLUMN_LOGIN,DB_COLUMN_PASSWORD,DB_COLUMN_LASTNAME,DB_COLUMN_FIRSTNAME,DB_COLUMN_PICTURE,DB_COLUMN_EMAIL,DB_COLUMN_BEST_FRIEND};

        // Requête de selection (SELECT)
        Cursor cursor = db.query(DB_TABLE, colonnes, null, null, null, null, null);

        // Placement du curseur sur la première ligne.
        cursor.moveToFirst();

        // Initialisation la liste des utilisateurs.
        ArrayList<User> users = new ArrayList<>();

        // Tant qu'il y a des lignes.
        while (!cursor.isAfterLast()) {
            // Récupération des informations de l'utilisateur pour chaque ligne.
            String login = cursor.getString(0);
            String password = cursor.getString(1);
            String last_name = cursor.getString(2);
            String first_name = cursor.getString(3);
            String picture = cursor.getString(4);
            String email = cursor.getString(5);
            String best_friend = cursor.getString(6);


            User user = new User(first_name,last_name,login,password,email,best_friend,picture);

            // Ajout de l'utilisateur à la liste.
            users.add(user);

            // Passe à la ligne suivante.
            cursor.moveToNext();
        }

        // Fermeture du curseur et de la base de données.
        cursor.close();
        db.close();

        return users;
    }


    public static User FindUserWithString(String username){
        //to update
        // Récupération du  SQLiteHelper et de la base de données.
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        // Colonnes à récupérer
        String[] colonnes = {DB_COLUMN_LOGIN};

        // Requête de selection (SELECT)
        Cursor cursor = db.query(DB_TABLE, colonnes, null, null, null, null, null);

        // Placement du curseur sur la première ligne.
        cursor.moveToFirst();

        // Tant qu'il y a des lignes.
        while (!cursor.isAfterLast()) {
            // Récupération des informations de l'utilisateur pour chaque ligne.
            String login = cursor.getString(0);

            // Vérification pour savoir s'il y a déjà une instance de cet utilisateur.

            if (login.equals(username)) {
                // Si l'user existe
                String password = cursor.getString(1);
                User user = new User(null,null,login,password,null, null,null);
                cursor.close();
                db.close();
                return user;

            }

            // Passe à la ligne suivante.
            cursor.moveToNext();
        }

        // Fermeture du curseur et de la base de données.
        cursor.close();
        db.close();

        return null;


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
