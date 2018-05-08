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
    private ArrayList<User> user;

    public ViewUserAdapter(Context context, ArrayList<User> user){
        super(context,-1,user);
        mInflater = LayoutInflater.from(context);
        this.user = user;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        View newView = mInflater.inflate(R.layout.item_view_user, parent, false);
        TextView textViewusername = newView.findViewById(R.id.view_user_username);
          textViewusername.setText(user.get(position).getLogin());

          boolean friend=true;
          if(friend) {
              ImageButton addbutton = newView.findViewById(R.id.view_user_add_button);
              addbutton.setImageResource(R.drawable.ic_add_button);
          }
          else{

          }

        TextView textViewfirstname = newView.findViewById(R.id.view_user_first_name);
          textViewfirstname.setText(user.get(position).getFirstName());
        TextView textViewlastname = newView.findViewById(R.id.view_user_last_name);
          textViewlastname.setText(user.get(position).getLastName());
        TextView textViewemail = newView.findViewById(R.id.view_user_email_adresse);
          textViewemail.setText(user.get(position).getEmail());

        ImageView profileImage = newView.findViewById(R.id.view_user_profil_photo);
        int picture = user.get(position).getPicture();
        if(picture != -1){
            profileImage.setImageResource(picture);
        }
        else {
            profileImage.setImageResource(R.drawable.profile_placeholder);
        }

        return newView;
    }
}
