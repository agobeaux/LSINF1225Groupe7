package be.groupe7lsinf1225.minipoll.object;

public class Choice {
    private boolean isPicture;
    private String title;

    public Choice(boolean isPicture,String title) {
        this.isPicture = isPicture;
        this.title = title;
    }

    // == get == //

    public boolean getIsPicture(){
        return isPicture;
    }

    public String getTitle(){
        return title;
    }
}
