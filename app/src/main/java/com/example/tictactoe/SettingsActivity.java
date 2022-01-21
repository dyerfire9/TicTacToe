package com.example.tictactoe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button button = findViewById(R.id.button30);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Tic Tac Toe");
                    String shareMessage = "https://google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    intent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(intent, "Share By"));
                }
                catch (Exception e){
                    Toast.makeText(SettingsActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void bounceAni(View view){
        ImageView logo = (ImageView) findViewById(R.id.imageView3);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        logo.startAnimation(animation);
    }

    public void menuLaunch(View v) {
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }

    public void tcLaunch(View v){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.termsfeed.com/live/8e0d029e-e635-4ab2-bb60-5b0465b188e7"));
        startActivity(browserIntent);
    }
    public void ppLaunch(View v){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.termsfeed.com/live/8cd851d3-73aa-4a65-9e13-d2047b98c1a5"));
        startActivity(browserIntent);
    }
}