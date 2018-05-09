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
    private ArrayList<User> friends;
    private User RequestUser;
    private int countdown;

    public ViewFriendAdapter(Context context, ArrayList<User> friends){
        super(context,-1,friends);
        mInflater = LayoutInflater.from(context);
        this.friends = friends;
        this.RequestUser = User.getConnectedUser();
        this.countdown= friends.size();

    }
    public View getView(int position, View convertView, ViewGroup parent){
        View newView = mInflater.inflate(R.layout.item_view_friend, parent, false);
        User friend = friends.get(position);

        TextView textViewusername = newView.findViewById(R.id.view_friend_username);
          textViewusername.setText(friend.getLogin());

        ImageButton bestfriendbutton = newView.findViewById(R.id.view_friend_bestfriend_button);
        if(friend.getLogin().equals(RequestUser.getbestfriend())) {
            bestfriendbutton.setImageResource(R.drawable.default_profile);
        }
        else {
            bestfriendbutton.setImageResource(R.drawable.ic_add_button);
        }

        ImageButton suppbutton = newView.findViewById(R.id.view_friend_supp_button);
          suppbutton.setImageResource(R.drawable.ic_add_button);

        TextView textViewfirstname = newView.findViewById(R.id.view_friend_first_name);
          textViewfirstname.setText(friend.getFirstName());

        TextView textViewlastname = newView.findViewById(R.id.view_friend_last_name);
          textViewlastname.setText(friend.getLastName());

        TextView textViewemail = newView.findViewById(R.id.view_friend_email_adresse);
          textViewemail.setText(friend.getEmail());

        ImageView profileImage = newView.findViewById(R.id.view_friend_profil_photo);
        int picture = friend.getPicture();
        if(picture != -1){
            profileImage.setImageResource(picture);
        }
        else {
            profileImage.setImageResource(R.drawable.profile_placeholder);
        }

        return newView;
    }

    public boolean isfinished(){return (this.countdown<=0);}

    public void tic(){this.countdown--;}

    public void setbestfriend(View view){
    }
    public void suppfriend(View view){
    }

}
