package be.groupe7lsinf1225.minipoll.object;

import java.util.ArrayList;

abstract class Poll {
    private String title;
    private String author;
    private ArrayList<String> visible;
    private boolean state;

    public void close() {
        this.state = true;
    }
}
