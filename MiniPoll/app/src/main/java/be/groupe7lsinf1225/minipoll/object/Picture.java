package be.groupe7lsinf1225.minipoll.object;

import be.groupe7lsinf1225.minipoll.R;

public class Picture {

    public static int get(String picture){
        int value;
        switch (picture) {
            case "1":
                value =  R.drawable.caterpie;
            break;
            case "2":
                value = R.drawable.charmander;
                break;
            case "3":
                value = R.drawable.eevee;
                break;
            case "4":
                value = R.drawable.gyarados;
                break;
            case "5":
                value = R.drawable.machop;
                break;
            case "6":
                value = R.drawable.pidgey;
                break;
            case "7":
                value = R.drawable.pikachu;
                break;
            case "8":
                value = R.drawable.rattata;
                break;
            case "9":
                value = R.drawable.squirtle;
                break;
            case "10":
                value = R.drawable.treecko;
                break;
            case "11":
                value = R.drawable.snorlax;
                break;
            case "12":
                value = R.drawable.zapdos;
                break;
            default:
                value = R.drawable.profile_placeholder;
                break;
        }
        return value;
    }
}
