package com.example.formation.entrainementjeu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private CombatView combat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // suppression de la barre de notification, la barre des titres est supprime dans le manifest
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int ecranLargeur = metrics.widthPixels, ecranHauteur = metrics.heightPixels;
        combat = new CombatView(this, ecranLargeur, ecranHauteur);

        setContentView(combat);
    }
}