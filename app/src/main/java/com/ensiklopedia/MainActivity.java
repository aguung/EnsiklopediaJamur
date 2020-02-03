package com.ensiklopedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView ascomycota = findViewById(R.id.ascomycota);
        ImageView basidiomycota = findViewById(R.id.basidiomycota);
        ImageView deuteromycota = findViewById(R.id.deuteromycota);
        ImageView zygomycota = findViewById(R.id.zygomycota);

        ascomycota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                intent.putExtra(ContentActivity.URL, "http://wongselo.com/jamur/divisi/jamur/ascomycota");
                intent.putExtra(ContentActivity.TITLE, "Ascomycota");
                startActivity(intent);
            }
        });

        basidiomycota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                intent.putExtra(ContentActivity.URL, "http://wongselo.com/jamur/divisi/jamur/basidiomycota");
                intent.putExtra(ContentActivity.TITLE, "Basidiomycota");
                startActivity(intent);
            }
        });

        deuteromycota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                intent.putExtra(ContentActivity.URL, "http://wongselo.com/jamur/divisi/jamur/deuteromycota");
                intent.putExtra(ContentActivity.TITLE, "Deuteromycota");
                startActivity(intent);
            }
        });

        zygomycota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                intent.putExtra(ContentActivity.URL, "http://wongselo.com/jamur/divisi/jamur/zygomycota");
                intent.putExtra(ContentActivity.TITLE, "Zygomycota");
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan Lagi Untuk Keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
