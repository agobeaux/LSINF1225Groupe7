package be.groupe7lsinf1225.minipoll.object;

import java.util.Date;

public class Quiz extends Poll {

    private String title;
    private String author;
    private Date date;

    public Quiz(String title, Date date, String author){
        this.title = title;
        this.date = date;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }
}
