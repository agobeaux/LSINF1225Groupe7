package be.groupe7lsinf1225.minipoll.object;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

import java.util.ArrayList;

public class User {

    private static final String DB_COLUMN_FIRST_NAME = "FIRSTNAME";
    private static final String DB_COLUMN_LAST_NAME = "LASTNAME";
    private static final String DB_COLUMN_LOGIN = "LOGIN";
    private static final String DB_COLUMN_PASSWORD = "PASSWORD";
    private static final String DB_COLUMN_EMAIL = "EMAIL";
    private static final String DB_TABLE = "USER";

    private static SparseArray<User> userSparseArray = new SparseArray<>();

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

    private final int id;
    private String first_name;
    private String last_name;
    private String login;
    private String password;
    private String email;
    private String picture;

    /**
     * Constructeur (accessible uniquement dans cette classe, instanciable en dehors via getUsers)
     */
    private User(int uid, String ufirst_name, String ulast_name, String ulogin, String upassword, String uemail, String ubest_friend) {
        this.id = uid;
        this.first_name = ufirst_name;
        this.last_name = ulast_name;
        this.login = ulogin;
        this.password = upassword;
        this.email = uemail;
        this.best_friend = ubest_friend;
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
 /*
    public static ArrayList<User> getUtilisateurs() {
        // Récupération du  SQLiteHelper et de la base de données.
        // C'est ça le lien avec la base de données
        // SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        // Colonnes à récupérer
        String[] colonnes = {DB_COLUMN_LOGIN, DB_COLUMN_FIRST_NAME, DB_COLUMN_PASSWORD};

        // Requête de selection (SELECT)
        Cursor cursor = db.query(DB_TABLE, colonnes, null, null, null, null, null);

        // Placement du curseur sur la première ligne.
        cursor.moveToFirst();

        // Initialisation la liste des utilisateurs.
        ArrayList<User> users = new ArrayList<>();

        // Tant qu'il y a des lignes.
        while (!cursor.isAfterLast()) {
            // Récupération des informations de l'utilisateur pour chaque ligne.
            int uId = cursor.getInt(0);
            String uNom = cursor.getString(1);
            String uPassword = cursor.getString(2);

            // Vérification pour savoir s'il y a déjà une instance de cet utilisateur.
            User user = User.userSparseArray.get(uId);
            if (user == null) {
                // Si pas encore d'instance, création d'une nouvelle instance.
                user = new User(uId, uNom, uPassword);
            }

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
    */

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
