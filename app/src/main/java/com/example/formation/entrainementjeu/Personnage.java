package com.example.formation.entrainementjeu;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by kurzen on 06/03/2018.
 */

public abstract class Personnage {
    /** Attributs utilitaire **/
    protected MainActivity mContext;
    protected int xPerso, yPerso;
    protected int persoLargeur, persoHauteur;
    protected int ecranLargeur, ecranHauteur;
    protected int numero;
    protected Personnage cible;

    // Attributs pour les cadres d'attaques
    protected CadreAttaque cAtk1, cAtk2, cAtk3, cAtk4;
    protected BitmapDrawable iconeAttaque1, iconeAttaque2, iconeAttaque3, iconeAttaque4;

    // Attributs d'animation
    protected boolean animationEnCoursAttaque1 = false, animationEnCoursAttaque2 = false, animationEnCoursAttaque3 = false, animationEnCoursAttaque4 = false;
    protected int compteurAnimation = 0;
    protected int xPersoApresDeplacement, yPersoApresDeplacement;
    protected int avancerPersoX, avancerPersoY;
    protected boolean deplacementEnCours = false;
    protected int compteurDeplacement = 0;
    private int xPersoInitial, yPersoInitial;

    // Attributs de la phase de repos
    protected BitmapDrawable imageRepos1, imageRepos2, imageRepos3;
    protected int compteurRepos;
    protected boolean croissanceRepos;

    // Attributs barre de pv
    protected int largeurCadrePV, hauteurCadrePV;
    protected int largeurBarrePVPleine, largeurBarrePVVide;
    protected BitmapDrawable cadre_pv;
    protected BitmapDrawable barre_pv_pleine;
    protected BitmapDrawable barre_pv_vide;
    protected int pourcentageCadreSupprime;
    protected int hauteurBarrePV;

    // Attributs de stats perso
    protected int pourcentageVie;

    /** Attributs graphique **/
    protected BitmapDrawable personnage;

    /** Methodes **/
    public void initialisationPersonnage()
    {
        // Attributs personnage
        pourcentageVie = 50;

            // Attributs de la barre de pv
            this.largeurCadrePV = persoLargeur;
        this.hauteurCadrePV = persoHauteur/5;
        pourcentageCadreSupprime = (20*100/largeurCadrePV);
        this.largeurBarrePVPleine = (largeurCadrePV - pourcentageCadreSupprime*2) * pourcentageVie/100;
        this.largeurBarrePVVide = largeurCadrePV - pourcentageCadreSupprime*2 - largeurBarrePVPleine;
        this.hauteurBarrePV = (hauteurCadrePV * 55/100);
        this.barre_pv_pleine = setImage(mContext, R.mipmap.barre_pv_pleine, largeurBarrePVPleine, hauteurBarrePV);
        this.barre_pv_vide = setImage(mContext, R.mipmap.barre_pv_vide, largeurBarrePVVide, hauteurBarrePV);
        this.cadre_pv = setImage(mContext, R.mipmap.cadre_pv, largeurCadrePV, hauteurCadrePV);
        this.compteurRepos = 1;
        this.croissanceRepos = true;

        // Cadres images
        cAtk1 = new CadreAttaque(mContext, ecranLargeur, ecranHauteur, 1);
        cAtk2 = new CadreAttaque(mContext, ecranLargeur, ecranHauteur, 2);
        cAtk3 = new CadreAttaque(mContext, ecranLargeur, ecranHauteur, 3);
        cAtk4 = new CadreAttaque(mContext, ecranLargeur, ecranHauteur, 4);
    }

    public void doDraw(Canvas c)
    {
        if(c == null) {return ;}

        if(!animationEnCoursAttaque1)
        {
            int ecart = 5; // sert a regler la vitesse d'animation
            if(compteurRepos < ecart)
            {
                personnage = imageRepos1;
                if(croissanceRepos)
                    compteurRepos++;
                else
                    compteurRepos--;
                if(compteurRepos == 0)
                    croissanceRepos = true;
            }else if(compteurRepos < ecart*3)
            {
                personnage = imageRepos2;
                if(croissanceRepos)
                    compteurRepos++;
                else
                    compteurRepos--;
            }else
            {
                personnage = imageRepos3;
                if(croissanceRepos)
                    compteurRepos++;
                else
                    compteurRepos--;
                if(compteurRepos == ecart*4)
                    croissanceRepos = false;
            }
        }

        // on deplace le perso
        if(deplacementEnCours)
        {
            xPerso += avancerPersoX;
            yPerso += avancerPersoY;
            compteurDeplacement++;
        }

        // quand le perso a finis de ce deplacer
        if(compteurDeplacement == 30)
        {
            compteurDeplacement = 0;
            deplacementEnCours = false;
        }

        // lancement de l'animation des attaques
        if(animationEnCoursAttaque1 && deplacementEnCours == false)
        {
            compteurAnimation++;
            animationAttaque1(c);
        }
        else if(animationEnCoursAttaque2 && deplacementEnCours == false)
        {
            compteurAnimation++;
            animationAttaque2(c);
        }
        else if(animationEnCoursAttaque3 && deplacementEnCours == false)
        {
            compteurAnimation++;
            animationAttaque3(c);
        }
        else if(animationEnCoursAttaque4 && deplacementEnCours == false)
        {
            compteurAnimation++;
            animationAttaque4(c);
        }
        else
        {
            c.drawBitmap(personnage.getBitmap(), xPerso, yPerso, null);
        }

        int xCadre = xPerso;
        int yCadre = yPerso + persoHauteur;
        c.drawBitmap(barre_pv_pleine.getBitmap(), xCadre + pourcentageCadreSupprime, yCadre+ persoHauteur/20, null);
        c.drawBitmap(barre_pv_vide.getBitmap(), xCadre + pourcentageCadreSupprime + largeurBarrePVPleine, yCadre+ persoHauteur/20, null);
        c.drawBitmap(cadre_pv.getBitmap(), xCadre, yCadre, null);

        // dessine les cadres
        c.drawBitmap(iconeAttaque1.getBitmap(), cAtk1.getX(), cAtk1.getY(), null);
        c.drawBitmap(iconeAttaque2.getBitmap(), cAtk2.getX(), cAtk2.getY(), null);
        c.drawBitmap(iconeAttaque3.getBitmap(), cAtk3.getX(), cAtk3.getY(), null);
        c.drawBitmap(iconeAttaque4.getBitmap(), cAtk4.getX(), cAtk4.getY(), null);

        cAtk1.draw(c);
        cAtk2.draw(c);
        cAtk3.draw(c);
        cAtk4.draw(c);

    }

    public BitmapDrawable setImage(final Context c, final int ressource, final int w, final int h)
    {
        Drawable dr = c.getResources().getDrawable(ressource);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        return new BitmapDrawable(c.getResources(), Bitmap.createScaledBitmap(bitmap, w, h, true));
    }

    public void resize(int wScreen, int hScreen)
    {
        ecranLargeur = wScreen;
        ecranHauteur = hScreen;
    }

    public abstract void attaque1(Personnage cible);
    public abstract void attaque2(Personnage cible);
    public abstract void attaque3(Personnage cible);
    public abstract void attaque4(Personnage cible);

    protected abstract void animationAttaque1(Canvas c);
    protected abstract void animationAttaque2(Canvas c);
    protected abstract void animationAttaque3(Canvas c);
    protected abstract void animationAttaque4(Canvas c);

    public void deplacementVersCible(Personnage cible, int distanceDeLaCible)
    {
        deplacementEnCours = true;
        if(cible != null)
        {
            xPersoInitial = xPerso;
            yPersoInitial = yPerso;
            if(cible.numero <= 3)
                xPersoApresDeplacement = cible.xPerso + distanceDeLaCible ;
            else
                xPersoApresDeplacement = cible.xPerso - distanceDeLaCible;

            yPersoApresDeplacement = cible.yPerso;
            avancerPersoX = (xPersoApresDeplacement - this.xPerso)/30;
            avancerPersoY = (yPersoApresDeplacement - this.yPerso)/30;
        }
        else
        {
            xPersoApresDeplacement = xPersoInitial;
            yPersoApresDeplacement = yPersoInitial;

            avancerPersoX = (xPersoApresDeplacement - this.xPerso)/30;
            avancerPersoY = (yPersoApresDeplacement - this.yPerso)/30;
        }
    }

    /** Getters **/
    public int getxPerso() {
        return xPerso;
    }

    public int getyPerso() {
        return yPerso;
    }

    public boolean animationEnCours()
    {
        return (animationEnCoursAttaque1 || animationEnCoursAttaque2 || animationEnCoursAttaque3 || animationEnCoursAttaque4) ;
    }

    public boolean isDeplacementEnCours() {
        return deplacementEnCours;
    }

    public CadreAttaque getcAtk1() {
        return cAtk1;
    }

    public CadreAttaque getcAtk2() {
        return cAtk2;
    }

    public CadreAttaque getcAtk3() {
        return cAtk3;
    }

    public CadreAttaque getcAtk4() {
        return cAtk4;
    }
}