package com.maporientation.maptoomi.parsexml;

import android.util.Xml;

import com.maporientation.maptoomi.model.Etape;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashraf on 24/11/2015.
 */
public class QuestionXmlParser {

    private String id;
    private String question;
    private String value;
    private String image;
    private String gauche;
    private String droite;

    private static final String ns = null;

    public List<Etape> parse(InputStream in) throws XmlPullParserException,IOException{
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readRoot(parser);
        }finally {
            in.close();
        }
    }

    private List<Etape> readRoot(XmlPullParser parser) throws XmlPullParserException,IOException{

       // List<Etape> roots = new ArrayList<Etape>();

        parser.require(XmlPullParser.START_TAG,ns,"root");
        while (parser.next() != XmlPullParser.END_TAG){
            if(parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }
            String name = parser.getName();
            if(name.equals("partie")){
                readPartie(parser);
            }else{
                skip(parser);
            }
        }
        return readPartie(parser);
    }
    private List<Etape> readPartie(XmlPullParser parser) throws XmlPullParserException,IOException{

        List<Etape> parties = new ArrayList<Etape>();
        parser.require(XmlPullParser.START_TAG,ns,"partie");

        while (parser.next() != XmlPullParser.END_TAG){
            if(parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }
            String name = parser.getName();
            if(name.equals("etape")){
                parties.add(readEtape(parser));
            }else{
                skip(parser);
            }
        }
        return parties;
    }

    private Etape readEtape(XmlPullParser parser) throws XmlPullParserException,IOException{

        List<Etape> etapes = new ArrayList<Etape>();
        parser.require(XmlPullParser.START_TAG,ns,"etape");

        while (parser.next() != XmlPullParser.END_TAG){
            if(parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }
            String name = parser.getName();
            if(name.equals("question")){
                question = readQuestion(parser);
            }else if (name.equals("choix")) {
                etapes.add(readChoix(parser));
            }else{
                skip(parser);
            }
        }
        return new Etape(id, value, image, question);
    }

    private Etape readChoix(XmlPullParser parser) throws XmlPullParserException,IOException{

        //List<Etape> choix = new ArrayList<Etape>();
        parser.require(XmlPullParser.START_TAG,ns,"choix");

        while (parser.next() != XmlPullParser.END_TAG){
            if(parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }
            String name = parser.getName();
            if(name.equals("gauche")){
                gauche = readGauche(parser);
            }else if (name.equals("droite")) {
                droite = readDroite(parser);
            }else{
                skip(parser);
            }
        }

        return new Etape(gauche,droite);
    }

    private String readQuestion(XmlPullParser parser) throws XmlPullParserException,IOException{

        image ="";
        value ="";
        question ="";

        parser.require(XmlPullParser.START_TAG,ns,"question");
        String question = readQuestionText(parser);
        parser.require(XmlPullParser.END_TAG, ns,"question");

        return question;
    }

    private String readGauche(XmlPullParser parser) throws XmlPullParserException,IOException{

        parser.require(XmlPullParser.START_TAG,ns,"gauche");
        String gauche = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns,"gauche");

        return gauche;
    }

    private String readDroite(XmlPullParser parser) throws XmlPullParserException,IOException{

        parser.require(XmlPullParser.START_TAG,ns,"droite");
        String droite = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns,"droite");

        return droite;
    }

    private String readQuestionText(XmlPullParser parser) throws XmlPullParserException,IOException{
        String result = "";
        if(parser.next() == XmlPullParser.TEXT){
            result = parser.getText();
            id = parser.getAttributeValue(null, "id");
            value = parser.getAttributeValue(null, "value");
            image = parser.getAttributeValue(null,"image");
            parser.nextTag();
        }
        return result;
    }

    private String readText(XmlPullParser parser) throws XmlPullParserException,IOException{
        String result = "";
        if(parser.next() == XmlPullParser.TEXT){
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException,IOException{

        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }

    }
}
