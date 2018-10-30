package com.example.formation.entrainementjeu;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by kurzen on 06/03/2018.
 */

public class CadreAttaque {
    /** Attributs utilitaire **/
    private MainActivity mContext;
    private int x, y;
    private int cadreLargeur;
    private int ecranLargeur, ecranHauteur;

    /** Attributs graphique **/
    private BitmapDrawable image;

    /** Constructeurs **/
    public CadreAttaque(MainActivity c, int largeurEcran, int hauteurEcran, int numeroAttaque)
    {
        mContext = c;
        ecranHauteur = hauteurEcran;
        ecranLargeur = largeurEcran;

        int tmp = ecranHauteur/5;
        if(tmp > (ecranLargeur/11))
        {
            cadreLargeur = ecranLargeur/11;
            x = (ecranLargeur/11*2) + (ecranLargeur/11*2*(numeroAttaque-1));
        }
        else
        {
            cadreLargeur = tmp;
            x = ((ecranLargeur/2 - cadreLargeur/2) - (cadreLargeur*2) + (2*cadreLargeur*(numeroAttaque-1)));
        }

        y = ecranHauteur - cadreLargeur;
        image = setImage(mContext, R.mipmap.cadre_attaque, cadreLargeur, cadreLargeur);
    }

    public void draw(Canvas canvas)
    {
        if(image==null) {return;}
        canvas.drawBitmap(image.getBitmap(), x, y, null);
    }

    public BitmapDrawable setImage(final Context c, final int ressource, final int w, final int h)
    {
        Drawable dr = c.getResources().getDrawable(ressource);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        return new BitmapDrawable(c.getResources(), Bitmap.createScaledBitmap(bitmap, w, h, true));
    }

    public void resize(int wScreen, int hScreen) {
        ecranLargeur=wScreen;
        ecranHauteur=hScreen;

        cadreLargeur=hScreen/5;
        image = setImage(mContext, R.mipmap.cadre_attaque, cadreLargeur, cadreLargeur);
    }

    /** Getters **/
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCadreLargeur() {
        return cadreLargeur;
    }
}