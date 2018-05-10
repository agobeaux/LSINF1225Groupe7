package be.groupe7lsinf1225.minipoll.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.R;

public class SelectableFriendsAdapter extends BaseAdapter {
    /**
     * Permet d'instancier un fichier xml de layout dans une vue.
     */
    private final LayoutInflater mInflater;

    /**
     * Liste des éléments de collection à mettre dans la liste.
     */
    private ArrayList<String> selectable_friends;

    /**
     * Constructeur.
     *
     * @param context        Contexte de l'application.
     * @param selectable_friends Liste des éléments de collection à placer dans la liste.
     */
    public SelectableFriendsAdapter(Context context, ArrayList<String> selectable_friends) {
        mInflater = LayoutInflater.from(context);
        this.selectable_friends = selectable_friends;
    }

    @Override
    public int getCount() {
        if (selectable_friends == null) {
            return 0;
        }
        else {
            return selectable_friends.size();
        }
    }

    @Override
    public Object getItem(int i) {
        return selectable_friends.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i; //idealement aller chercher id de bipoll dans sql
    }


    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.collected_friend_row, parent, false);
        }

        CheckBox friend = convertView.findViewById(R.id.friend_checkBox);

        String friend_username = selectable_friends.get(i);
        friend.setText(friend_username);

        return convertView;
    }

    public void setBipolls(ArrayList<String> selectable_friends) {
        this.selectable_friends = selectable_friends;
        notifyDataSetChanged();
    }
}
