package com.maporientation.maptoomi.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.maporientation.maptoomi.R;
import com.maporientation.maptoomi.parsexml.QuestionXmlParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

public class TestActivity extends AppCompatActivity {

    private ListView listView;
    private InputStream in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        listView = (ListView) findViewById(R.id.list_item);

        QuestionXmlParser questionXmlParser = new QuestionXmlParser();

        try {
            in =  openFileInput("C:\\Users\\Ashraf\\AndroidStudioProjects\\Zeus\\app\\src\\main\\res\\tricam\\tricam_question.xml");;

            questionXmlParser.parse(in);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
