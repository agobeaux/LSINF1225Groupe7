package be.groupe7lsinf1225.minipoll.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.R;
import be.groupe7lsinf1225.minipoll.object.Quiz;
import be.groupe7lsinf1225.minipoll.object.BiPoll;

public class BiPollAdapter extends BaseAdapter {

    /**
     * Permet d'instancier un fichier xml de layout dans une vue.
     */
    private final LayoutInflater mInflater;

    /**
     * Liste des éléments de collection à mettre dans la liste.
     */
    private ArrayList<BiPoll> bipolls;

    /**
     * Constructeur.
     *
     * @param context        Contexte de l'application.
     * @param bipolls Liste des éléments de collection à placer dans la liste.
     */
    public BiPollAdapter(Context context, ArrayList<BiPoll> bipolls) {
        mInflater = LayoutInflater.from(context);
        this.bipolls = bipolls;
    }

    @Override
    public int getCount() {
        return bipolls.size();
    }

    @Override
    public Object getItem(int i) {
        return bipolls.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i; //idealement aller chercher id de bipoll dans sql
    }



    public View getView(int i, View convertView, ViewGroup viewGroup) {

        if (convertView == null) {
            //convertView = mInflater.inflate(R.layout.collected_friend_row, viewGroup, false);
        }
        TextView topic = convertView.findViewById(R.id.show_row_topic);
        TextView state = convertView.findViewById(R.id.show_row_state);
        TextView createdby = convertView.findViewById(R.id.show_row_createdby);

        BiPoll bipoll = bipolls.get(i);
        topic.setText(bipoll.getQuestion());
        if(bipoll.getState()){
            state.setText("Open");
        }
        else{
            state.setText("Closed");
        }
        createdby.setText(bipoll.getAuthor());
        return convertView;
    }
}
