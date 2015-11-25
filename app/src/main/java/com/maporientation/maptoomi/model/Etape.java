package com.maporientation.maptoomi.model;

/**
 * Created by Ashraf on 24/11/2015.
 */
public class Etape {

    String question,gauche,droite,value,image;
    String id;

    public Etape(String id, String value, String image, String question){

        this.question = question;
        this.id = id;
        this.value = value;
        this.image = image;
    }

    public Etape(String gauche, String droite){
        this.gauche = gauche;
        this.droite = droite;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getGauche() {
        return gauche;
    }

    public void setGauche(String gauche) {
        this.gauche = gauche;
    }

    public String getDroite() {
        return droite;
    }

    public void setDroite(String droit) {
        this.droite = droit;
    }
}
