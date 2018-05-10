package be.groupe7lsinf1225.minipoll.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.object.Picture;
import be.groupe7lsinf1225.minipoll.object.User;
import be.groupe7lsinf1225.minipoll.R;

public class ViewBipollChoiceAdapter extends ArrayAdapter {

    private final LayoutInflater mInflater;
    private ArrayList<String> choice;
    private int layout;
    private int textview;
    private User RequestUser;

    public ViewBipollChoiceAdapter(Context context, ArrayList<String> choice,int layout,int textview){
        super(context,-1,choice);
        mInflater = LayoutInflater.from(context);
        this.choice = choice;
        this.layout = layout;
        this.textview = textview;
        this.RequestUser = User.getConnectedUser();

    }
    public View getView(int position, View convertView, ViewGroup parent){
        View newView = mInflater.inflate(this.layout, parent, false);

        TextView textViewusername = newView.findViewById(textview);
        textViewusername.setText(this.choice.get(0));

        return newView;
    }

}