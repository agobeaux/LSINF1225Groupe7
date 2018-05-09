package be.groupe7lsinf1225.minipoll.object;

import java.util.ArrayList;

public class BiPoll extends Poll {

    private ArrayList<Choice> choices;
    private String question;
    private String author;
    private boolean state;

    /**
     * Constructeur
     */
    public BiPoll(String question, String author, String[] titles_choices, boolean state) {
        this.question = question;
        ArrayList<Choice> c = new ArrayList<>();
        int i;
        for(i=0 ; i<2 ; i++){
            c.add(new Choice(false, titles_choices[i]));
        }
        this.choices = c;
        this.author = author;
        this.state = state;
    }
}
