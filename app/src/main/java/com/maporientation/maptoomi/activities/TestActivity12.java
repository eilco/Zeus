package com.maporientation.maptoomi.activities;

import android.content.Intent;
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
import android.widget.Button;

import com.maporientation.maptoomi.R;
import com.maporientation.maptoomi.adapters.TestAdapter12;
import com.maporientation.maptoomi.divider.DividerItemDecoration;
import com.maporientation.maptoomi.model.Etape;
import com.maporientation.maptoomi.parsexml.QuestionXmlParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestActivity12 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private Button next;
    private List<Etape> etapeList;
    private InputStream fOut = null;

    private static final String BUNDLE_RECYCLER_LAYOUT = "TestActivity12.recycler.layout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test11);
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

        //On récupére note Widget et le bouton
        mRecyclerView = (RecyclerView) findViewById(R.id.list_test1);
        next = (Button) findViewById(R.id.next);

        // On indique que notre Layout de notre RecyclerView va avoir une taille fixe
        mRecyclerView.setHasFixedSize(true);
        //On utilise un Linear Layout Manager
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        // mRecyclerView.setItemAnimator(new SlideInUpAnimator());

        parseXml();

        mAdapter = new TestAdapter12(this,etapeList,mRecyclerView);

        mRecyclerView.setAdapter(mAdapter);

        /*AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(mAdapter);
        alphaInAnimationAdapter.setDuration(1000);
        alphaInAnimationAdapter.setInterpolator(new OvershootInterpolator());
        alphaInAnimationAdapter.setFirstOnly(false);

        mRecyclerView.setAdapter(new SlideInRightAnimationAdapter(new ScaleInAnimationAdapter(alphaInAnimationAdapter)));*/


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity12.this,TestActivity22.class);
                startActivity(intent);
            }
        });

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
            etapeList = questionXmlParser.parseQuestionDocument();

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


    /*public void copyFileOrDir() throws IOException {
        Resources r = getResources();
        AssetManager assetManager = r.getAssets();
        //Créer le document si not exist
        File directory = new File(Environment.getExternalStorageDirectory()+File.separator+"Maptoomi/data/tricam/xml");
        if(!directory.exists())
            directory.mkdirs();

        //Créer le fichier tricam_question.xml
        File f = new File(directory, "tricam_question.xml");

        // Ouvrir le fichier xml dans le document Assets
        InputStream is = assetManager.open("tricam/tricam_question.xml");

        // Copier le contenu du fichier dans tricam_question.xml qui se trouve dans la carteSD
        OutputStream os = new FileOutputStream(f, true);

        final int buffer_size = 1024 * 1024;
        try {
            byte[] bytes = new byte[buffer_size];
            for (; ; ) {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                    break;
                os.write(bytes, 0, count);
            }
            is.close();
            os.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/
}
