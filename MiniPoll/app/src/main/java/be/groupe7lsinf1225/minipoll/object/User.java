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
                users.add(new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6)));
            }
            cursor.moveToNext();
        }

        cursor.close();
        db.close();
        return users;
    }

    public boolean addFriend(String friend){
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("STATE",1);
        String[] valuesWhere = {friend,this.login,this.login,friend};
        String selection = "(LOGIN1 = ? AND LOGIN2 = ?) OR (LOGIN1 = ? AND LOGIN2 = ?)";

        int dia = db.update("FRIENDS",values,selection,valuesWhere);
        db.close();

        if(dia==-1){
            return false;
        }
        return true;
    }

    public boolean suppFriend(String friend){
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();

        String[] valuesWhere = {friend,this.login,this.login,friend};
        String selection = "(LOGIN1 = ? AND LOGIN2 = ?) OR (LOGIN1 = ? AND LOGIN2 = ?)";

         int dia = db.delete("FRIENDS",selection,valuesWhere);
         db.close();

         if(dia==-1){
             return false;
         }
         return true;
    }

    public boolean FriendRequest(String friend){
        int dia = this.inFriendList(friend);
        if(dia == -1){
             //   values.put("STATE",String.valueOf(1));
             //   String[] valuesWhere = {friend,this.login,this.login,friend};
             //   String selection = "(LOGIN1 = ? AND LOGIN2 = ?) OR (LOGIN1 = ? AND LOGIN2 = ?)";

             //   dia2 = db.update("FRIENDS",values,selection,valuesWhere);
            SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("LOGIN1",this.login);
            values.put("LOGIN2",friend);

            int dia2 = (int) db.insert("FRIENDS",null,values);
            db.close();
            if(dia2 == -1){
                return false;
            }
            return true;
        }
        return false;
    }

    //a corriger si on a le temps
    public int inFriendList(String friend){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String[] columns = {"LOGIN1","LOGIN2","STATE"};
        String[] valuesWhere = {friend,this.login,this.login,friend};
        String selection = "(LOGIN1 = ? AND LOGIN2 = ?) OR (LOGIN1 = ? AND LOGIN2 = ?)";

        Cursor cursor = db.query("FRIENDS", columns, selection,valuesWhere, null, null, null);

        int ret;
        if(cursor.getCount() <= 0){
            ret = -1;
        }
        else {
            //a corriger si on a le temps
            ret = 0;
            cursor = db.query("FRIENDS", columns, selection+" AND (STATE = 0)",valuesWhere, null, null, null);
            if(cursor.getCount() <= 0){
                ret = 1;
            }
        }

        db.close();
        cursor.close();
        return ret;
    }

    public ArrayList<User> getAllFriendRequest(){
        ArrayList<User> friends = new ArrayList<>();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String[] columns = {"LOGIN1","LOGIN2","STATE"};
        String[] valuesWhere = {this.login};
        String selection = "(STATE = 0) AND (LOGIN2 = ?)";

        Cursor cursor = db.query("FRIENDS", columns, selection,valuesWhere, null, null, null);

        cursor.moveToFirst();

        String userRequest = User.getConnectedUser().getLogin();
        while (!cursor.isAfterLast()){
            if(userRequest.equals(cursor.getString(0))){
                friends.add(User.getUser(cursor.getString(1)));
            }
            else if(userRequest.equals(cursor.getString(1))){
                friends.add(User.getUser(cursor.getString(0)));
            }
            cursor.moveToNext();
        }

        cursor.close();
        db.close();
        return friends;
    }

    public ArrayList<User> getAllFriend(String state){
        ArrayList<User> friends = new ArrayList<>();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String[] columns = {"LOGIN1","LOGIN2","STATE"};
        String[] valuesWhere = {this.login,this.login};
        String selection = "(STATE = " + state + ") AND (LOGIN1 = ? OR LOGIN2 = ?)";

        Cursor cursor = db.query("FRIENDS", columns, selection,valuesWhere, null, null, null);

        cursor.moveToFirst();

        String userRequest = User.getConnectedUser().getLogin();
        while (!cursor.isAfterLast()){
            if(userRequest.equals(cursor.getString(0))){
                friends.add(User.getUser(cursor.getString(1)));
            }
            else if(userRequest.equals(cursor.getString(1))){
                friends.add(User.getUser(cursor.getString(0)));
            }
            cursor.moveToNext();
        }

        cursor.close();
        db.close();
        return friends;
    }

    public boolean goodPassword(String passwordWritten) {
        return this.password.equals(passwordWritten);
    }


    public static User getUser(String login) {
        // Récupération du  SQLiteHelper et de la base de données.
        // C'est ça le lien avec la base de données
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        // Colonnes à récupérer
        String[] columns = {DB_COLUMN_LOGIN,DB_COLUMN_PASSWORD,DB_COLUMN_FIRSTNAME,DB_COLUMN_LASTNAME,DB_COLUMN_EMAIL,DB_COLUMN_BEST_FRIEND,DB_COLUMN_PICTURE};
        String[] valuesWhere = {login};
        String selection = DB_COLUMN_LOGIN + " = ?";

        // Requête de selection (SELECT)
        Cursor cursor = db.query(DB_TABLE, columns, selection, valuesWhere, null, null, null);

        if(cursor.getCount() <= 0){
            return null;
        }
        // Placement du curseur sur la première ligne.
        cursor.moveToFirst();


        User user = new User(login, cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6));

        // Fermeture du curseur et de la base de données.
        cursor.close();
        db.close();

        return user;
    }

    public static User getInfosConnectedUser(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String[] columns = {DB_COLUMN_LOGIN,DB_COLUMN_PASSWORD,DB_COLUMN_LASTNAME,DB_COLUMN_FIRSTNAME,DB_COLUMN_PICTURE,DB_COLUMN_EMAIL};
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
        String last_name = cursor.getString(2);
        String first_name = cursor.getString(3);
        int picture = cursor.getInt(4);
        String email = cursor.getString(5);

        User user = new User(username,password,first_name,last_name,email,null,picture);

        cursor.close();
        db.close();

        return user;
    }

    public static boolean putUser(String username, String password, String first_name, String last_name, String email, int picture){
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DB_COLUMN_LOGIN, username);
        values.put(DB_COLUMN_PASSWORD, password);
        values.put(DB_COLUMN_LASTNAME, last_name);
        values.put(DB_COLUMN_FIRSTNAME, first_name);
        values.put(DB_COLUMN_PICTURE, picture);
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


    public static void updateUser(String old_username,String first_name,String last_name,String username,String mailaddress,String password, int picture) {

        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DB_COLUMN_LOGIN,username);
        values.put(DB_COLUMN_PASSWORD, password);
        values.put(DB_COLUMN_LASTNAME, last_name);
        values.put(DB_COLUMN_FIRSTNAME, first_name);
        values.put(DB_COLUMN_EMAIL, mailaddress);
        values.put(DB_COLUMN_PICTURE, picture);

        String selection = DB_COLUMN_LOGIN + " = ?";
        String[] valuesWhere = {old_username};

        db.update(DB_TABLE,values,selection,valuesWhere);

        db.close();

        User user = new User(username,password,first_name,last_name,mailaddress,null,picture);
        User.connected_user = user;
    }

    // === Set === //

    public void setbestfriend(String best_friend){
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DB_COLUMN_BESTFRIEND,best_friend);

        String selection = DB_COLUMN_LOGIN + " = ?";
        String[] valuesWhere = {this.login};

        db.update(DB_TABLE,values,selection,valuesWhere);

        db.close();
        this.best_friend = best_friend;
    }


    public static void setConnected_user(User user) {
        User.connected_user = user;
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

    public String getbestfriend() {
        return best_friend;
    }

    public int getPicture() {
        return picture;
    }

    public static ArrayList<String> getQuizzes(){
        ArrayList<String> Ids = new ArrayList<>();
        SQLiteDatabase db;
        db = MySQLiteHelper.get().getReadableDatabase();

        String[] columns = {"LOGIN","IDQUIZ"};
        String[] valuesWhere = {connected_user.getLogin()};
        String selection = "LOGIN" + " = ?";

        Cursor cursor = db.query("VIEW_QUIZ", columns, selection, valuesWhere, null, null, null);
        Log.e(null, "NbOfRows: " + cursor.getCount());
        Log.e(null, "NBOfColumns" + cursor.getColumnCount());
        if( cursor.moveToFirst() ) {
            int i;
            for(i=0; i < cursor.getCount(); i++ ) {
                Ids.add(String.valueOf(cursor.getInt(1)));
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
            return Ids;
        }
        return Ids;
    }
}
