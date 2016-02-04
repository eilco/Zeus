package com.maporientation.maptoomi.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maporientation.maptoomi.R;

import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthListener;
import android.util.Log;
import android.view.View.OnClickListener;

import java.io.File;


public class TroisCentSoixanteActivity extends AppCompatActivity {
    // SocialAuth Component
    SocialAuthAdapter adapter;
    boolean status;

    // Android Components
    Button update;
    EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trois_cent_soixante);

        TextView textview = (TextView) findViewById(R.id.text);
        textview.setText("dans cette rubrique vous pouvez partager votre expérience avec votre entourage via les reseaux social");

        LinearLayout bar = (LinearLayout) findViewById(R.id.linearbar);
        bar.setBackgroundResource(R.drawable.bar_gradient);

        // Add Bar to library
        adapter = new SocialAuthAdapter(new ResponseListener());

        // Please note : Update status functionality is only supported by
        // Facebook, Twitter, Linkedin, MySpace, Yahoo and Yammer.

        // Add providers
        adapter.addProvider(SocialAuthAdapter.Provider.FACEBOOK, R.drawable.facebook);
        adapter.addProvider(SocialAuthAdapter.Provider.TWITTER, R.drawable.twitter);
        adapter.addProvider(SocialAuthAdapter.Provider.LINKEDIN, R.drawable.linkedin);

        // Add email and mms providers
        adapter.addProvider(SocialAuthAdapter.Provider.EMAIL, R.drawable.email);
        adapter.addProvider(SocialAuthAdapter.Provider.MMS, R.drawable.mms);

        // For twitter use add callback method. Put your own callback url here.
        adapter.addCallBack(SocialAuthAdapter.Provider.TWITTER, "http://socialauth.in/socialauthdemo/socialAuthSuccessAction.do");

        // Add and Secrets keys
        try {
            adapter.addConfig(SocialAuthAdapter.Provider.FACEBOOK, "297841130364674", "dc9c59d0c72d4f2533580e80ba4c2a59", null);
            adapter.addConfig(SocialAuthAdapter.Provider.TWITTER, "5jwyYJia583EEczmdAmlOA", "j0rQkJjTjwVdv7HFiE4zz2qKJKzqjksR2aviVU8fSc",
                    null);
            adapter.addConfig(SocialAuthAdapter.Provider.LINKEDIN, "bh82t52rdos6", "zQ1LLrGbhDZ36fH8", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapter.enable(bar);
    }

    /**
     * Listens Response from Library
     *
     */

    private final class ResponseListener implements DialogListener {
        @Override
        public void onComplete(Bundle values) {

            // Variable to receive message status
            Log.d("Share-Bar", "Authentication Successful");

            // Get name of provider after authentication
            final String providerName = values.getString(SocialAuthAdapter.PROVIDER);
            Log.d("Share-Bar", "Provider Name = " + providerName);
            Toast.makeText(TroisCentSoixanteActivity.this, providerName + " connected", Toast.LENGTH_SHORT).show();

            update = (Button) findViewById(R.id.update);
            edit = (EditText) findViewById(R.id.editTxt);

            // Please avoid sending duplicate message. Social Media Providers
            // block duplicate messages.

            update.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // Call updateStatus to share message via oAuth providers
                    // adapter.updateStatus(edit.getText().toString(), new
                    // MessageListener(), false);

                    // call to update on all connected providers at once
                    Toast.makeText(TroisCentSoixanteActivity.this," onclick", Toast.LENGTH_SHORT).show();
                    adapter.updateStatus(edit.getText().toString(), new MessageListener(), true);
                }
            });

            // Share via Email Intent
            if (providerName.equalsIgnoreCase("share_mail")) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",
                        "maporientation@gmail.com", null));
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "signaler un bug");
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                        "image5964402.png");
                Uri uri = Uri.fromFile(file);
                emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(emailIntent, "Test"));
            }

            // Share via mms intent
            if (providerName.equalsIgnoreCase("share_mms")) {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                        "image5964402.png");
                Uri uri = Uri.fromFile(file);

                Intent mmsIntent = new Intent(Intent.ACTION_SEND, uri);
                mmsIntent.putExtra("sms_body", "Test");
                mmsIntent.putExtra(Intent.EXTRA_STREAM, uri);
                mmsIntent.setType("image/png");
                startActivity(mmsIntent);
            }
        }

        @Override
        public void onError(SocialAuthError error) {
            error.printStackTrace();
            Log.d("Share-Bar", error.getMessage());
        }

        @Override
        public void onCancel() {
            Log.d("Share-Bar", "Authentication Cancelled");
        }

        @Override
        public void onBack() {
            Log.d("Share-Bar", "Dialog Closed by pressing Back Key");

        }
    }

    // To get status of message after authentication
    private final class MessageListener implements SocialAuthListener<Integer> {
        @Override
        public void onExecute(String provider, Integer t) {
            Integer status = t;
            if (status.intValue() == 200 || status.intValue() == 201 || status.intValue() == 204)
                Toast.makeText(TroisCentSoixanteActivity.this, "Message posted on " + provider, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(TroisCentSoixanteActivity.this, "Message not posted on" + provider, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(SocialAuthError e) {

        }
    }
/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        inviter = (Button) findViewById(R.id.inviterunami);
        inviter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "email@email.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });*/

}
