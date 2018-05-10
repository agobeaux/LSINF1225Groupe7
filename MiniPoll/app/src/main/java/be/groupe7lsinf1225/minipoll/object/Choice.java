package be.groupe7lsinf1225.minipoll.object;

public class Choice {

    private String title;
    private int IDChoice;
    private boolean good_answer;

    /**
     * Constructeur
     */
    public Choice(String title, int IDChoice, boolean good_answer) {
        this.title = title;
        this.IDChoice = IDChoice;
        this.good_answer = good_answer;
    }

    // == get == //

    public int getIDChoice() { return IDChoice;}
    public boolean getGoodAnserState() { return good_answer;}
    public String getTitle(){
        return title;
    }
}
