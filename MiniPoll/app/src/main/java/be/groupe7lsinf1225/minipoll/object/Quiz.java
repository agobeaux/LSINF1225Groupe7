package be.groupe7lsinf1225.minipoll.object;

public class Quiz extends Poll {

    private String title;
    private String author;
    private boolean state;

    public Quiz(String title, boolean state, String author){
        this.title = title;
        this.state = state;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean getState() {
        return state;
    }
}
