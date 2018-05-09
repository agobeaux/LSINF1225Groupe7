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

public class ViewUserAdapter extends ArrayAdapter {

    private final LayoutInflater mInflater;
    private ArrayList<User> users;
    private User RequestUser;
    private int countdown;

    public ViewUserAdapter(Context context, ArrayList<User> users){
        super(context,-1,users);
        mInflater = LayoutInflater.from(context);
        this.users = users;
        this.RequestUser = User.getConnectedUser();
        this.countdown = users.size();

    }
    public View getView(int position, View convertView, ViewGroup parent){
        View newView = mInflater.inflate(R.layout.item_view_user, parent, false);
        User user = users.get(position);

        TextView textViewusername = newView.findViewById(R.id.view_user_username);
          textViewusername.setText(user.getLogin());

        ImageButton addbutton = newView.findViewById(R.id.view_user_add_button);
        if(user.inFriendList(RequestUser.getLogin()) == -1){
            addbutton.setImageResource(R.drawable.ic_add_button);
        }
        else{
            addbutton.setImageResource(R.drawable.default_profile);

        }

        TextView textViewfirstname = newView.findViewById(R.id.view_user_first_name);
          textViewfirstname.setText(user.getFirstName());

        TextView textViewlastname = newView.findViewById(R.id.view_user_last_name);
          textViewlastname.setText(user.getLastName());

        TextView textViewemail = newView.findViewById(R.id.view_user_email_adresse);
          textViewemail.setText(user.getEmail());

        ImageView profileImage = newView.findViewById(R.id.view_user_profil_photo);
        int picture = user.getPicture();
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

    public void addfriend(View view){
    }

}
