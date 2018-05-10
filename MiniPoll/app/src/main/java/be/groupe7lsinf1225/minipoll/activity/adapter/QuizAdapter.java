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

public class QuizAdapter extends BaseAdapter {

    /**
     * Permet d'instancier un fichier xml de layout dans une vue.
     */
    private final LayoutInflater mInflater;

    /**
     * Liste des éléments de collection à mettre dans la liste.
     */
    private ArrayList<Quiz> quizzes;

    /**
     * Constructeur.
     *
     * @param context        Contexte de l'application.
     * @param quizzes Liste des éléments de collection à placer dans la liste.
     */
    public QuizAdapter(Context context, ArrayList<Quiz> quizzes) {
        mInflater = LayoutInflater.from(context);
        this.quizzes = quizzes;
    }

    @Override
    public int getCount() {
        return quizzes.size();
    }

    @Override
    public Object getItem(int i) {
        return quizzes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i; //idealement aller chercher id de quiz dans sql
    }



    public View getView(int i, View convertView, ViewGroup viewGroup) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.collected_poll_row, viewGroup, false);
        }
        TextView topic = convertView.findViewById(R.id.show_row_topic);
        TextView state = convertView.findViewById(R.id.show_row_state);
        TextView createdby = convertView.findViewById(R.id.show_row_createdby);

        Quiz quiz = quizzes.get(i);
        topic.setText(quiz.getTitle());
        if(quiz.getState()){
            state.setText("Open");
        }
        else{
            state.setText("Closed");
        }
        createdby.setText(quiz.getAuthor());
        return convertView;
    }
    public void setQuizzes(ArrayList<Quiz> newQuizzes) {
        this.quizzes = newQuizzes;
    }
}
