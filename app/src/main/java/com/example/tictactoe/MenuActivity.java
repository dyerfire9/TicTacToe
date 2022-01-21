package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    public void GameLaunch(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void AboutLaunch(View view){
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }

    public void bounceAni(View view){
        ImageView img = (ImageView) findViewById(R.id.imageView2);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        img.startAnimation(animation);
    }
}