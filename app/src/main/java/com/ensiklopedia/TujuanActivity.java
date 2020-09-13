package com.ensiklopedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TujuanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tujuan);

        Button next = findViewById(R.id.btnNext);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.button);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                startActivity(new Intent(TujuanActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}