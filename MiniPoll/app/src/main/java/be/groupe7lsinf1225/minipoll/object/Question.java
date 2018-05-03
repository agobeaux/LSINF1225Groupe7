package be.groupe7lsinf1225.minipoll.object;

import java.util.ArrayList;

public class Question {

    private String title;
    private ArrayList<Choice> choices;
    // On stocke l'indice du choix qui correspond à la bonne réponse
    private int good_answer;

    /**
     * Constructeur
     */
    public Question(String title, boolean isPicture, String[] choices, int good_answer)
    {
        this.title = title;

        ArrayList<Choice> c = new ArrayList<>();
        int i;
        for(i=0 ; i<4 ; i++) {
            c.add(new Choice(isPicture,choices[i]));
        }

        this.choices = c;
        this.good_answer = good_answer;
    }

    // == get == //

    public int getGoodAnswer()
    {
        return good_answer;
    }

    public Choice getChoice(int ind)
    {
        return this.choices.get(ind);
    }

    public String getTitle()
    {
        return title;
    }
}
