package com.maporientation.maptoomi.activities;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.maporientation.maptoomi.fragments.facebookfragment;
import com.maporientation.maptoomi.fragments.mapsfragment;
import com.maporientation.maptoomi.fragments.twitterfragment;
import com.maporientation.maptoomi.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Override;import java.lang.SuppressWarnings;
import java.util.ArrayList;

public class ActuOrientationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    public int id=1;
    protected String jsonResult;
    protected String urlServer;
    public static ArrayList<page> listpages= new ArrayList<page>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actu_orientation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame,new facebookfragment()).commit();
        urlServer = "http://data.maporientation.com/files/json/pages_emploi.txt";
        accessWebService();
    }


        @Override
        public void onBackPressed()
        {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu)
        {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_home, menu);

                        return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item)
        {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                Intent intention = new Intent(ActuOrientationActivity.this,mapsfragment.class);
                startActivity(intention);
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item)
        {

            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            int id = item.getItemId();

            if (id == R.id.nav_facebook) {

                fm.beginTransaction().replace(R.id.content_frame,new facebookfragment()).commit();

            } else if (id == R.id.nav_twitter) {

                fm.beginTransaction().replace(R.id.content_frame,new twitterfragment()).commit();

            }
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);

            return true;

        }



    //
    //parser fichier JSON
    //



    public void accessWebService() {
        try {
            //Log.d("hello","helloooooooo");
            JsonReadTask task = new JsonReadTask();
            task.execute(new String[]{urlServer});
            Log.d("url", "url : " + urlServer);
        } catch (Exception e) {
            Log.d("Errorn", "ERROR in accessWebService");
        }
        Log.d("looog ","acces webserivce  !!!");

    }

    private StringBuilder inputStreamToString(InputStream is) {
        String rLine = "";
        StringBuilder answer = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try {
            while ((rLine = br.readLine()) != null) {
                answer.append(rLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
    private class JsonReadTask extends AsyncTask<String, Void, String> {


        protected String doInBackground(String... params) {
            Log.d("looog dzaaab", "acces jsonreadTask doinbackground !!!");
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(params[0]);
            try {
                HttpResponse response = httpClient.execute(httpPost);
                jsonResult = inputStreamToString(response.getEntity().getContent()).toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private StringBuilder inputStreamToString(InputStream is) {
            Log.d("looog dzaaab", "acces inputStream !!!");
            String rLine = "";
            StringBuilder answer = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            try {
                while ((rLine = br.readLine()) != null) {
                    answer.append(rLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return answer;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                log("onPostExecute");
                ParseJson();
                Log.d("looog dzaaab", "accés parseJson reussit !! !!!");
            } catch (Exception e) {
                log("ERROR in onPostExecute");
                Log.d("looog dzaaab", "je ne dois pas entrer ici XD !!!");

            }
        }

    }
    public void ParseJson() {


        try {
            JSONObject jsonReponse = new JSONObject(jsonResult);
            log(jsonResult);
            JSONArray emplois = jsonReponse.optJSONArray("Pages Emplois");

            for(int i = 0;i<emplois.length();i++) {
                JSONObject obj = new JSONObject(emplois.getString(i));
                //Log.d("xd", "xd" + obj);
                page p = new page();
                //String id=obj.getString("id");
                p.setId(obj.getString("id"));
                //String ville=obj.getString("ville");
                p.setVille(obj.getString("ville"));
                //String url=obj.getString("url");
                p.setUrl(obj.getString("url"));
                String coords=obj.getString("coords");
                // Log.d("xd", "coords" + coords);
                if(coords.length()>3) {
                    String[] parts = coords.split("\\(");
                    String[] parts2 = parts[1].split("\\)");
                    String[] coordonne = parts2[0].split("\\,");
                    Float latitude =Float.parseFloat(coordonne[0]);
                    p.setLatitude(Float.parseFloat(coordonne[0]));
                    Float longitude=Float.parseFloat(coordonne[1]);
                    p.setLongitude(Float.parseFloat(coordonne[1]));

                }
                else{
                    float latitude =0;
                    float longitude=0;
                    p.setLatitude(latitude);
                    p.setLongitude(longitude);
                }
                listpages.add(p);
            }
            Log.d("size","size"+listpages.size());
        } catch (JSONException e) {e.getMessage();}

    }

    public void log(String string) {
        String TAG = "DEBUG";
        Log.v(TAG, string);
    }

    ////
    ///
    ///

}


