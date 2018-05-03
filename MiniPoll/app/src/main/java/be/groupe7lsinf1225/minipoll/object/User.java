package be.groupe7lsinf1225.minipoll.object;

import java.util.ArrayList;

public class User {

    private static final String DB_COLUMN_ID = "u_id";
    private static final String DB_COLUMN_FIRST_NAME = "u_first_name";
    private static final String DB_COLUMN_LAST_NAME = "u_last_name";
    private static final String DB_COLUMN_LOGIN = "u_login";
    private static final String DB_COLUMN_PASSWORD = "u_password";
    private static final String DB_COLUMN_EMAIL = "u_email";
    private static final String DB_TABLE = "users";


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

    // === Get === //

    public static User getConnectedUser() {
        return User.connected_user;
    }

    public int getId() {
        return id;
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
