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

import be.groupe7lsinf1225.minipoll.object.User;
import be.groupe7lsinf1225.minipoll.R;

public class ViewFriendAdapter extends ArrayAdapter {

    private final LayoutInflater mInflater;
    private ArrayList<User> friend;

    public ViewFriendAdapter(Context context, ArrayList<User> friend){
        super(context,-1,friend);
        mInflater = LayoutInflater.from(context);
        this.friend = friend;

    }
    public View getView(int position, View convertView, ViewGroup parent){
        View newView = mInflater.inflate(R.layout.item_view_friend, parent, false);

        TextView textViewusername = newView.findViewById(R.id.view_friend_username);
          textViewusername.setText(friend.get(position).getLogin());

        ImageButton bestfriendbutton = newView.findViewById(R.id.view_friend_bestfriend_button);
        if(User.getConnectedUser().getbestfriend().equals(friend.get(position).getLogin())) {
            bestfriendbutton.setImageResource(R.drawable.default_profile);
        }
        else {
            bestfriendbutton.setImageResource(R.drawable.ic_add_button);
        }

        ImageButton suppbutton = newView.findViewById(R.id.view_friend_supp_button);
          suppbutton.setImageResource(R.drawable.ic_add_button);

        TextView textViewfirstname = newView.findViewById(R.id.view_friend_first_name);
          textViewfirstname.setText(friend.get(position).getFirstName());

        TextView textViewlastname = newView.findViewById(R.id.view_friend_last_name);
          textViewlastname.setText(friend.get(position).getLastName());

        TextView textViewemail = newView.findViewById(R.id.view_friend_email_adresse);
          textViewemail.setText(friend.get(position).getEmail());

        ImageView profileImage = newView.findViewById(R.id.view_friend_profil_photo);
        int picture = friend.get(position).getPicture();
        if(picture != -1){
            profileImage.setImageResource(picture);
        }
        else {
            profileImage.setImageResource(R.drawable.profile_placeholder);
        }

        return newView;
    }

    public void setbestfriend(View view){
    }
    public void suppfriend(View view){
    }

}
