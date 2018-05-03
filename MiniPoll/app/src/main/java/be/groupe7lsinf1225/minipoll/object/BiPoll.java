package be.groupe7lsinf1225.minipoll.object;

import java.util.ArrayList;

public class BiPoll extends Poll {

    private ArrayList<Choice> choices;
    private String question;
    private boolean isPicture;

    /**
     * Constructeur
     */
    public BiPoll(String question, boolean isPicture, String[] titles_choices) {
        this.question = question;
        ArrayList<Choice> c = new ArrayList<>();
        int i;
        for(i=0 ; i<2 ; i++){
            c.add(new Choice(isPicture,titles_choices[i]));
        }
        this.choices = c;
        this.isPicture = isPicture;
    }
}
