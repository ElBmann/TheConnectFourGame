package com.androiddev.brianrecuero.theconnectfourgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PlayerName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);
    }
    public void Play(View v){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

}
