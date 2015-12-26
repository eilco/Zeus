package com.maporientation.maptoomi.model;

/**
 * Created by Ashraf on 24/11/2015.
 */
public class Etape {

    private String question,gauche,droite,value,image,choixGauche,choixDroite;
    private int id;
    private boolean RightSelected;
    private boolean LeftSelected;
    private int valueOfSeekBar;

    public Etape(int id, String value, String image, String question,boolean RightSelected,boolean LeftSelected){

        this.question = question;
        this.id = id;
        this.value = value;
        this.image = image;
        this.RightSelected = RightSelected;
        this.LeftSelected = LeftSelected;
    }


    public Etape(int id,String choixDroite, String choixGauche,int valueOfSeekBar){

        this.id = id;
        this.choixGauche = choixGauche;
        this.choixDroite = choixDroite;
        this.valueOfSeekBar = valueOfSeekBar;

    }


    public int getValueOfSeekBar() {
        return valueOfSeekBar;
    }

    public void setValueOfSeekBar(int valueOfSeekBar) {
        this.valueOfSeekBar = valueOfSeekBar;
    }

    public String getChoixGauche() {
        return choixGauche;
    }

    public void setChoixGauche(String choixGauche) {
        this.choixGauche = choixGauche;
    }

    public String getChoixDroite() {
        return choixDroite;
    }

    public void setChoixDroite(String choixDroite) {
        this.choixDroite = choixDroite;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setDroite(String droite) {
        this.droite = droite;
    }

    public boolean isRightSelected() {
        return RightSelected;
    }

    public void setRightSelected(boolean rightSelected) {
        RightSelected = rightSelected;
    }

    public boolean isLeftSelected() {
        return LeftSelected;
    }

    public void setLeftSelected(boolean leftSelected) {
        LeftSelected = leftSelected;
    }

}
