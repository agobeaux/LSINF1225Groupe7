package be.groupe7lsinf1225.minipoll.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.object.Quiz;

/**
 * Created by agobeaux on 5/10/18.
 */

public class LeaderboardAdapter extends BaseAdapter {

    /**
     * Permet d'instancier un fichier xml de layout dans une vue.
     */
    private final LayoutInflater mInflater;

    /**
     * Liste des éléments de collection à mettre dans la liste.
     */
    private ArrayList<String> userlist;

    /**
     * Constructeur.
     *
     * @param context Contexte de l'application.
     * @param userlist Liste des éléments de collection à placer dans la liste.
     */
    public LeaderboardAdapter(Context context, ArrayList<String> userlist) {
        mInflater = LayoutInflater.from(context);
        this.userlist = userlist;
    }

    @Override
    public int getCount() {
        return userlist.size();
    }

    @Override
    public String getItem(int i) {
        return userlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i; //idealement aller chercher id de quiz dans sql
    }


    public View getView(int i, View convertView, ViewGroup viewGroup) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.collected_poll_row, viewGroup, false); // A MODIFIER
        }
        TextView positionText = convertView.findViewById(R.id.show_row_position);
        TextView userText = convertView.findViewById(R.id.show_row_user);
        TextView scoreText = convertView.findViewById(R.id.show_row_score);

        String user = userlist.get(i);
        //positionText.setText(getPosition(user)); // fonction à faire OU PLUTOT : gérer dans L'activity et le passer en argument
        userText.setText(user);
        //scoreText.setText(getScore(user)); // fonction à faire OU PLUTOT : gérer dans L'activity et le passer en argument
        return convertView;
    }
    
}