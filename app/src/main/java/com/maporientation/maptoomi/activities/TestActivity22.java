package com.maporientation.maptoomi.activities;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.maporientation.maptoomi.R;
import com.maporientation.maptoomi.adapters.TestAdapter22;
import com.maporientation.maptoomi.divider.DividerItemDecoration;
import com.maporientation.maptoomi.model.Etape;
import com.maporientation.maptoomi.parsexml.QuestionXmlParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestActivity22 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private List<Etape> etapeList;
    private InputStream fOut = null;

    private static final String BUNDLE_RECYCLER_LAYOUT = "TestActivity22.recycler.layout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test22);
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

        //On récupére note Widget
        mRecyclerView = (RecyclerView) findViewById(R.id.list_test2);
        // On indique que notre Layout de notre RecyclerView va avoir une taille fixe
        mRecyclerView.setHasFixedSize(true);
        //On utilise un Linear Layout Manager
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        // mRecyclerView.setItemAnimator(new SlideInUpAnimator());

        parseXml();

        mAdapter = new TestAdapter22(this,etapeList,mRecyclerView);

        mRecyclerView.setAdapter(mAdapter);
        /*AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(mAdapter);
        alphaInAnimationAdapter.setDuration(1000);
        alphaInAnimationAdapter.setInterpolator(new OvershootInterpolator());
        alphaInAnimationAdapter.setFirstOnly(false);

        mRecyclerView.setAdapter(new SlideInRightAnimationAdapter(new ScaleInAnimationAdapter(alphaInAnimationAdapter)));*/
        /*try {
            copyFileOrDir();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //FileInputStream fOut = getBaseContext().openFileInput(Environment.getExternalStorageDirectory()+File.separator+"Maptoomi/data/tricam/xml/tricam_question.xml");
        //File fXmlFile = getResources().getXml(getAssets().open("tricam/tricam_question.xml"));
        //File toPath = Environment.getExternalStoragePublicDirectory("Maptoomi/data/tricam/xml");
        //File fOut = new File(toPath, "tricam_question.xml");

        //questionXmlParser.parse(fOut);
        Log.v("taille List", etapeList.size() + "");
        for(int i = 0; i<etapeList.size()/2; i++) {
            Log.v("etapeListActivity","question "+i+" : " +etapeList.get(i).getQuestion()+" isRightSelected :"+etapeList.get(i).isRightSelected()+" isLeftSelected : "+etapeList.get(i).isLeftSelected());
        }
    }

    private void parseXml() {
        try {
            fOut = this.getAssets().open("tricam/tricam_question.xml");
            QuestionXmlParser questionXmlParser = new QuestionXmlParser();
            questionXmlParser.parseXmlFile(fOut);
            etapeList = questionXmlParser.parseChoixDocument();

            Log.v("taille List", etapeList.size() + "");
            for(int i = 0; i<etapeList.size()/2; i++) {
                Log.v("etapeList","question "+i+" : " +etapeList.get(i).getQuestion()+" isRightSelected :"+etapeList.get(i).isRightSelected()+" isLeftSelected : "+etapeList.get(i).isLeftSelected());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null)
        {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            mRecyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, mRecyclerView.getLayoutManager().onSaveInstanceState());
    }

}
