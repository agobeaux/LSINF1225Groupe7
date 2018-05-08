package be.groupe7lsinf1225.minipoll.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import be.groupe7lsinf1225.minipoll.R;

public class ListFriendViewAdapter2 extends BaseAdapter {

    private final LayoutInflater mInflater;
    private ArrayList<String> friends;

    public ListFriendViewAdapter2(Context context, ArrayList<String> friend) {
        mInflater = LayoutInflater.from(context);
        this.friends = friend;
    }

    @Override
    public int getCount() { return friends.size(); }

    @Override
    public Object getItem(int position) { return friends.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.collected_friend_row, parent, false);
        }
        TextView nameTextView = convertView.findViewById(R.id.show_friend_name);
        nameTextView.setText(friends.get(position));
        return convertView;
    }

    public void setFriends(ArrayList<String> newfriend) {
        this.friends = newfriend;
        notifyDataSetChanged();
    }
}
