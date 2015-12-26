package com.maporientation.maptoomi.parsexml;

import android.util.Log;

import com.maporientation.maptoomi.model.Etape;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Ashraf on 24/11/2015.
 */
public class QuestionXmlParser {

    private int idQuestion,idChoix;
    private String questionText;
    private String value;
    private String image;
    private String choixGauche;
    private String choixDroite;
    private List<Etape> etapeQuestionList=null;
    private List<Etape> etapeChoixList=null;
    private Document dom;
    private static final String ns = null;
    private NodeList partie;
    private NodeList etapeQuestionNodeList;
    private NodeList etapeChoixNodeList;
    private NodeList etapeChoixGaucheDroiteNodeList;

    public QuestionXmlParser(){
        etapeQuestionList = new ArrayList<Etape>();
        etapeChoixList = new ArrayList<Etape>();
    }

    public void parseXmlFile(InputStream fOut){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse(fOut);
            parseQuestionDocument();
            parseChoixDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Etape> parseQuestionDocument(){
        Log.v("TAG","ParceDocument()");
        Element documentElement = dom.getDocumentElement();

        //obtenir l'id de la partie
        // pour chaque element <etape> on obtient le text ou la valeur de
        // si partie id = 1 => on obtient le contenu des informations concernant la question id, type, value, image et le texte
        // si partie id = 2 => on obtient le contenu des informations concernant le choix id et contenu des elements <gauche> et <droite>

        partie = documentElement.getElementsByTagName("partie");
        for (int i=0; i<partie.getLength();i++){
            Log.v("TAG","partie.getLength(): "+partie.getLength()+ " i == "+i);
            Log.v("getIdPartie","id partie == "+partie.item(i).getAttributes().getNamedItem("id").getNodeValue());

            if(Integer.parseInt(partie.item(i).getAttributes().getNamedItem("id").getNodeValue()) == 1){

                etapeQuestionNodeList = documentElement.getElementsByTagName("question");
                Log.v("TAG", "etapeQuestionNodeList.getLength(): "+etapeQuestionNodeList.getLength()+" i == "+i);
                if(etapeQuestionNodeList != null && etapeQuestionNodeList.getLength()>0){
                    for(int j=0; j<etapeQuestionNodeList.getLength();j++){
                        // obtenir le nouveau Element etape
                        Element etapeElement = (Element) etapeQuestionNodeList.item(j);
                        // obtenir l'objet etape
                        //Etape etape = getQuestion(etapeElement);
                        // ajouter l'etape à la list
                        questionText = etapeElement.getTextContent()+" ?";
                        idQuestion = Integer.parseInt(etapeElement.getAttribute("id"));
                        value = etapeElement.getAttribute("value");
                        image = etapeElement.getAttribute("image");
                        Etape question = new Etape(idQuestion,value,image,questionText,false,false);
                        Log.v("getQuestion","idQuestion: "+idQuestion+" value: "+value
                                +" image: "+image+" questionText: "+questionText);
                        etapeQuestionList.add(j,question);
                    }
                }
                return  etapeQuestionList;
            }
        }
        return null;
    }

    public List<Etape> parseChoixDocument(){
        Log.v("TAG","ParceDocument()");
        Element documentElement = dom.getDocumentElement();

        //obtenir l'id de la partie
        // pour chaque element <etape> on obtient le text ou la valeur de
        // si partie id = 1 => on obtient le contenu des informations concernant la question id, type, value, image et le texte
        // si partie id = 2 => on obtient le contenu des informations concernant le choix id et contenu des elements <gauche> et <droite>

        partie = documentElement.getElementsByTagName("partie");
        for (int i=0; i<partie.getLength();i++){
            Log.v("TAG","partie.getLength(): "+partie.getLength()+ " i == "+i);
            Log.v("getIdPartie","id partie == "+partie.item(i).getAttributes().getNamedItem("id").getNodeValue());

            if(Integer.parseInt(partie.item(i).getAttributes().getNamedItem("id").getNodeValue()) == 2){

                etapeChoixNodeList = documentElement.getElementsByTagName("choix");
                if(etapeChoixNodeList != null && etapeChoixNodeList.getLength()>0) {
                    for (int j = 0; j < etapeChoixNodeList.getLength(); j++) {
                        // obtenir le nouveau Element etape
                        //Element etapeElement = (Element) etapeChoixNodeList.item(j);
                        // obtenir l'id du choix
                        //getIdChoix(i,etapeElement);
                        Element choixElement = (Element) etapeChoixNodeList.item(j);
                        idChoix=Integer.parseInt(choixElement.getAttribute("id"));
                        Element droite = (Element) choixElement.getElementsByTagName("droite").item(0);
                        choixDroite = droite.getTextContent();
                        Element gauche = (Element) choixElement.getElementsByTagName("gauche").item(0);
                        choixGauche = gauche.getTextContent();
                        Log.v("getChoix","idChoix: "+idChoix+" ChoixGauche: "+choixGauche
                                +" ChoixDroite: "+choixDroite);
                        Etape choix = new Etape(idChoix,choixDroite,choixGauche,3);
                        etapeChoixList.add(j,choix);

                    }
                }
                return  etapeChoixList;
            }
        }
        return null;
    }

}
