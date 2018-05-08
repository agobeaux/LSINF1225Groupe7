package be.groupe7lsinf1225.minipoll.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import be.groupe7lsinf1225.minipoll.R;

public class ListFriendViewAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] friends;

    public ListFriendViewAdapter(Context context, String[] friends){
        super(context,-1,friends);
        this.context=context;
        this.friends=friends;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.collected_friend_row,parent,false);
        TextView textView = rowView.findViewById(R.id.show_friend_row);
        textView.setText(friends[position]);

        return rowView;
    }
}
