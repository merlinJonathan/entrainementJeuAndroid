package com.example.formation.entrainementjeu;


import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by kurzen on 06/03/2018.
 */

public class Goku extends Personnage{
    /** Attributs **/

    /** Attributs animation 1 **/
    private BitmapDrawable image_attaque1_1, image_attaque1_2;
    private BitmapDrawable image_kamehameha_1, image_kamehameha_2, image_kamehameha_3;
    private int largeurKamehameha, hauteurKamehameha;
    private int xKamehameha, yKamehameha;

    /** Attributs animation 2 **/
    private BitmapDrawable image_attaque2_1, image_attaque2_2, image_attaque2_3, image_attaque2_4;

    /** Attributs animation 3 **/
    private BitmapDrawable image_attaque3_1, image_attaque3_2, image_attaque3_3, image_attaque3_4, image_attaque3_5, image_attaque3_6, image_attaque3_7, image_attaque3_8, image_attaque3_9;
    private BitmapDrawable image_perso_attaque3_1, image_perso_attaque3_2, image_perso_attaque3_3, image_perso_attaque3_4;
    private int largeurAttaque3;

    /** Attributs animation 4 **/
    private BitmapDrawable image_attaque4_1;
    private BitmapDrawable image_perso_attaque4_1, image_perso_attaque4_2, image_perso_attaque4_3, image_perso_attaque4_4;
    private int compteurGenkidama = 1;

    /** Constructeurs **/
    public Goku(MainActivity c, int largeurEcran, int hauteurECran, int numero)
    {
        int placement = 0;

        this.mContext = c;
        this.ecranHauteur = hauteurECran;
        this.ecranLargeur = largeurEcran;
        this.numero = numero;
        this.persoHauteur = (ecranHauteur - ecranHauteur/5)/4;
        this.persoLargeur = ecranLargeur/11;
        this.largeurKamehameha = persoLargeur/2;
        this.hauteurKamehameha = largeurKamehameha*59/64;
        this.largeurAttaque3 = largeurKamehameha;

        //Attributs de barre de pv
        initialisationPersonnage();

        // personnages regardant a gauche
        if(numero > 3)
        {
            if(numero == 4)
            {
                xPerso = ecranLargeur/8*7;
                placement = 0;
            }
            else if(numero == 5)
            {
                xPerso = ecranLargeur/4*3;
                placement = 1;
            }
            else
            {
                xPerso = ecranLargeur/8*7;
                placement = 2;
            }

            // image du personnage
            this.imageRepos1 = setImage(mContext, R.mipmap.goku_pose_gauche_1, persoLargeur, persoHauteur);
            this.imageRepos2 = setImage(mContext, R.mipmap.goku_pose_gauche_2, persoLargeur, persoHauteur);
            this.imageRepos3 = setImage(mContext, R.mipmap.goku_pose_gauche_3, persoLargeur, persoHauteur);

            // image animation attaque 1
            this.image_attaque1_1 = setImage(mContext, R.mipmap.goku_kamehameha_gauche_1, persoLargeur, persoHauteur);
            this.image_attaque1_2 = setImage(mContext, R.mipmap.goku_kamehameha_gauche_2, persoLargeur, persoHauteur);

            this.image_kamehameha_1 = setImage(mContext, R.mipmap.kamehameha_gauche_1, largeurKamehameha, hauteurKamehameha);
            this.image_kamehameha_3 = setImage(mContext, R.mipmap.kamehameha_gauche_3, largeurKamehameha, hauteurKamehameha);

            // image animation attaque 2
            this.image_attaque2_1 = setImage(mContext, R.mipmap.goku_coup_pied_gauche_1, persoLargeur, persoHauteur);
            this.image_attaque2_2 = setImage(mContext, R.mipmap.goku_coup_pied_gauche_2, persoHauteur, persoHauteur);
            this.image_attaque2_3 = setImage(mContext, R.mipmap.goku_coup_pied_gauche_3, persoLargeur, persoHauteur);
            this.image_attaque2_4 = setImage(mContext, R.mipmap.goku_coup_pied_gauche_4, persoHauteur, persoHauteur);

            // image animation attaque 3
            this.image_perso_attaque3_3 = setImage(mContext, R.mipmap.goku_lance_energie_gauche_3, persoLargeur, persoHauteur);
            this.image_perso_attaque3_4 = setImage(mContext, R.mipmap.goku_lance_energie_gauche_4, persoLargeur, persoHauteur);
            this.image_attaque3_2 = setImage(mContext, R.mipmap.goku_attaque_energie_gauche_2, largeurAttaque3, largeurAttaque3);
            this.image_attaque3_3 = setImage(mContext, R.mipmap.goku_attaque_energie_gauche_3, largeurAttaque3, largeurAttaque3);

            // images animation 4
            this.image_perso_attaque4_1 = setImage(mContext, R.mipmap.goku_perso_genkidama_gauche_1, persoLargeur, persoHauteur);
            this.image_perso_attaque4_2 = setImage(mContext, R.mipmap.goku_perso_genkidama_gauche_2, persoLargeur, persoHauteur);
            this.image_perso_attaque4_3 = setImage(mContext, R.mipmap.goku_perso_genkidama_gauche_3, persoLargeur, persoHauteur);
            this.image_perso_attaque4_4 = setImage(mContext, R.mipmap.goku_perso_genkidama_gauche_4, persoLargeur, persoHauteur);
        }
        else // persos regardant a droite
        {
            if(numero == 1)
            {
                xPerso = ecranLargeur/8;
                placement = 0;
            }
            else if(numero == 2)
            {
                xPerso = ecranLargeur/4;
                placement = 1;
            }
            else
            {
                xPerso = ecranLargeur/8;
                placement = 2;
            }

            // image du personnage
            this.imageRepos1 = setImage(mContext, R.mipmap.goku_pose_droite_1, persoLargeur, persoHauteur);
            this.imageRepos2 = setImage(mContext, R.mipmap.goku_pose_droite_2, persoLargeur, persoHauteur);
            this.imageRepos3 = setImage(mContext, R.mipmap.goku_pose_droite_3, persoLargeur, persoHauteur);

            // image animation attaque 1
            this.image_attaque1_1 = setImage(mContext, R.mipmap.goku_kamehameha_droite_1, persoLargeur, persoHauteur);
            this.image_attaque1_2 = setImage(mContext, R.mipmap.goku_kamehameha_droite_2, persoLargeur, persoHauteur);

            this.image_kamehameha_1 = setImage(mContext, R.mipmap.kamehameha_droite_1, largeurKamehameha, hauteurKamehameha);
            this.image_kamehameha_3 = setImage(mContext, R.mipmap.kamehameha_droite_3, largeurKamehameha, hauteurKamehameha);

            // image animation attaque 2
            this.image_attaque2_1 = setImage(mContext, R.mipmap.goku_coup_pied_droite_1, persoLargeur, persoHauteur);
            this.image_attaque2_2 = setImage(mContext, R.mipmap.goku_coup_pied_droite_2, persoHauteur, persoHauteur);
            this.image_attaque2_3 = setImage(mContext, R.mipmap.goku_coup_pied_droite_3, persoLargeur, persoHauteur);
            this.image_attaque2_4 = setImage(mContext, R.mipmap.goku_coup_pied_droite_4, persoHauteur, persoHauteur);

            // image animation attaque 3
            this.image_perso_attaque3_3 = setImage(mContext, R.mipmap.goku_lance_energie_droite_3, persoLargeur, persoHauteur);
            this.image_perso_attaque3_4 = setImage(mContext, R.mipmap.goku_lance_energie_droite_4, persoLargeur, persoHauteur);
            this.image_attaque3_2 = setImage(mContext, R.mipmap.goku_attaque_energie_droite_2, largeurAttaque3, largeurAttaque3);
            this.image_attaque3_3 = setImage(mContext, R.mipmap.goku_attaque_energie_droite_3, largeurAttaque3, largeurAttaque3);

            // images animation 4
            this.image_perso_attaque4_1 = setImage(mContext, R.mipmap.goku_perso_genkidama_droite_1, persoLargeur, persoHauteur);
            this.image_perso_attaque4_2 = setImage(mContext, R.mipmap.goku_perso_genkidama_droite_2, persoLargeur, persoHauteur);
            this.image_perso_attaque4_3 = setImage(mContext, R.mipmap.goku_perso_genkidama_droite_3, persoLargeur, persoHauteur);
            this.image_perso_attaque4_4 = setImage(mContext, R.mipmap.goku_perso_genkidama_droite_4, persoLargeur, persoHauteur);
        }

        // image communes
        this.image_kamehameha_2 = setImage(mContext, R.mipmap.kamehameha_2, largeurKamehameha, hauteurKamehameha);
        this.image_attaque3_1 = setImage(mContext, R.mipmap.goku_attaque_energie_1, largeurAttaque3, largeurAttaque3);
        this.image_attaque3_4 = setImage(mContext, R.mipmap.goku_attaque_energie_4, largeurAttaque3, largeurAttaque3);
        this.iconeAttaque1 = setImage(mContext, R.mipmap.goku_cadre_attaque_1, cAtk1.getCadreLargeur(), cAtk1.getCadreLargeur());
        this.iconeAttaque2 = setImage(mContext, R.mipmap.goku_cadre_attaque_2, cAtk1.getCadreLargeur(), cAtk1.getCadreLargeur());
        this.iconeAttaque3 = setImage(mContext, R.mipmap.goku_cadre_attaque_3, cAtk1.getCadreLargeur(), cAtk1.getCadreLargeur());
        this.iconeAttaque4 = setImage(mContext, R.mipmap.goku_cadre_attaque_4, cAtk1.getCadreLargeur(), cAtk1.getCadreLargeur());


        yPerso = persoHauteur/2 + persoHauteur*placement;
    }

    /** Methodes **/

    public  void attaque1(Personnage cible){
        if(cible != null)
        {
            animationEnCoursAttaque1 = true;
            deplacementVersCible(cible, largeurKamehameha*4 + persoLargeur);
            compteurAnimation = 0;
        }
    }

    public  void attaque2(Personnage cible)
    {
        if(cible != null)
        {
            animationEnCoursAttaque2 = true;
            deplacementVersCible(cible, persoLargeur);
            compteurAnimation = 0;
        }
    }

    public  void attaque3(Personnage cible)
    {
        if(cible != null)
        {
            animationEnCoursAttaque3 = true;
            deplacementVersCible(cible, largeurKamehameha*6 + persoLargeur);
            compteurAnimation = 0;
            this.cible = cible;
        }
    }

    public  void attaque4(Personnage cible)
    {
        if(cible != null)
        {
            animationEnCoursAttaque4 = true;
            deplacementVersCible(cible, largeurKamehameha*6 + persoLargeur);
            compteurAnimation = 0;
            this.cible = cible;
        }
    }

    protected void animationAttaque1(Canvas c)
    {
        // placement du kamehameha
        if(numero > 3)
        {
            xKamehameha = xPerso - largeurKamehameha;
            yKamehameha = yPerso + persoHauteur*25/100;
        }
        else
        {
            xKamehameha = xPerso + persoLargeur;
            yKamehameha = yPerso + persoHauteur*25/100;
        }

        if(compteurAnimation > 15)
        {
            c.drawBitmap(image_kamehameha_1.getBitmap(), xKamehameha, yKamehameha, null);
            c.drawBitmap(image_attaque1_2.getBitmap(), xPerso, yPerso, null);

            for(int i = 0 ; i < 2; i++)
            {
                if(numero > 3)
                {
                    c.drawBitmap(image_kamehameha_2.getBitmap(), xKamehameha - largeurKamehameha*(i+1), yKamehameha, null);
                }
                else
                {
                    c.drawBitmap(image_kamehameha_2.getBitmap(), xKamehameha + largeurKamehameha*(i+1), yKamehameha, null);
                }
            }

            if(numero > 3)
            {
                c.drawBitmap(image_kamehameha_3.getBitmap(), xKamehameha - largeurKamehameha*3, yKamehameha, null);
            }
            else
            {
                c.drawBitmap(image_kamehameha_3.getBitmap(), xKamehameha + largeurKamehameha*3, yKamehameha, null);
            }
        }
        else
        {
            c.drawBitmap(image_attaque1_1.getBitmap(), xPerso, yPerso, null);
        }
        if(compteurAnimation > 30)
        {
            animationEnCoursAttaque1 = false;
            deplacementVersCible(null, 0);
        }
    }

    protected void animationAttaque2(Canvas c)
    {
        int ecartAnimation = 0 ;//difference de pixel pour garder le personnage au bon endroit
        if(numero > 3)
        {
            ecartAnimation = 50*persoLargeur/100;
        }

        if(compteurAnimation < 12)
            c.drawBitmap(image_attaque2_1.getBitmap(), xPerso, yPerso, null);
        else if (compteurAnimation < 24)
        {
            c.drawBitmap(image_attaque2_2.getBitmap(), xPerso - ecartAnimation, yPerso, null);
        }
        else if (compteurAnimation < 36)
        {
            c.drawBitmap(image_attaque2_3.getBitmap(), xPerso, yPerso, null);
        }
        else if (compteurAnimation < 48)
        {
            c.drawBitmap(image_attaque2_4.getBitmap(), xPerso - ecartAnimation, yPerso, null);
        }
        else if (compteurAnimation < 60)
        {
            c.drawBitmap(image_attaque2_3.getBitmap(), xPerso, yPerso, null);
        }
        else
        {
            animationEnCoursAttaque2 = false;
            deplacementVersCible(null, 0);
        }
    }

    protected void animationAttaque3(Canvas c)
    {
        int distanceEntreAttaque1Et3;
        int yBoule = yPerso + persoHauteur/10*2;
        int ecartPerso;

        if(numero > 3)
        {
            distanceEntreAttaque1Et3 = -1*((xPerso) - (cible.xPerso + persoLargeur / 2));
            ecartPerso = 0;
        }
        else
        {
            distanceEntreAttaque1Et3 = (cible.xPerso + persoLargeur/2) - (xPerso + largeurAttaque3 + persoLargeur);
            ecartPerso = persoLargeur;
        }

        if((compteurAnimation % 8) < 2) {
            c.drawBitmap(image_perso_attaque3_3.getBitmap(), xPerso, yPerso, null);
            c.drawBitmap(image_attaque3_1.getBitmap(), xPerso + ecartPerso , yBoule, null);
        }
        else if((compteurAnimation % 8)  < 4)
        {
            c.drawBitmap(image_perso_attaque3_3.getBitmap(), xPerso, yPerso, null);
            c.drawBitmap(image_attaque3_2.getBitmap(), xPerso  + ecartPerso + distanceEntreAttaque1Et3 / 3 * 1, yBoule, null);
        }
        else if((compteurAnimation % 8)  < 6)
        {
            c.drawBitmap(image_perso_attaque3_4.getBitmap(), xPerso, yPerso, null);
            c.drawBitmap(image_attaque3_3.getBitmap(), xPerso + ecartPerso  + distanceEntreAttaque1Et3 / 3 * 2, yBoule, null);
        }
        else if((compteurAnimation % 8)  < 8)
        {
            c.drawBitmap(image_perso_attaque3_4.getBitmap(), xPerso, yPerso, null);
            c.drawBitmap(image_attaque3_4.getBitmap(), xPerso + ecartPerso  + distanceEntreAttaque1Et3, yBoule, null);
        }

        //quand c'est finis
        if(compteurAnimation == 60)
        {
            animationEnCoursAttaque3 = false;
            deplacementVersCible(null, 0);
        }
    }

    protected void animationAttaque4(Canvas c)
    {
        int largeurGenkidama = (ecranHauteur)/16;
        int distanceGenkidama;
        int genkidamaAvanceX, genkidamaAvanceY;
        int xGenkidama = (xPerso + persoLargeur / 2) - (largeurGenkidama*compteurGenkidama)/2;

        if(numero > 3)
        {
            distanceGenkidama = ((xGenkidama) - ((ecranLargeur/8) - (largeurGenkidama*compteurGenkidama)/2))* -1;
            genkidamaAvanceX = distanceGenkidama / 36 ;
        }
        else
        {
            distanceGenkidama = ((ecranLargeur/8*7) - (largeurGenkidama*compteurGenkidama)/2) - xGenkidama;
            genkidamaAvanceX = distanceGenkidama / 36 ;
        }
        distanceGenkidama = -1 * ((yPerso - largeurGenkidama*compteurGenkidama) - (ecranHauteur/2 - largeurGenkidama*compteurGenkidama/2));
        genkidamaAvanceY = distanceGenkidama / 36;

        if(compteurAnimation < 8)
        {
            c.drawBitmap(image_perso_attaque4_1.getBitmap(), xPerso, yPerso, null);
        }
        else if(compteurAnimation < 16)
        {
            c.drawBitmap(image_perso_attaque4_2.getBitmap(), xPerso, yPerso, null);
            this.image_attaque4_1 = setImage(mContext, R.mipmap.goku_attaque_genkidama_1, largeurGenkidama*compteurGenkidama, largeurGenkidama*compteurGenkidama);
            xGenkidama = (xPerso + persoLargeur / 2) - (largeurGenkidama*compteurGenkidama)/2;
            c.drawBitmap(image_attaque4_1.getBitmap(), xGenkidama, yPerso - (largeurGenkidama*compteurGenkidama), null);
            compteurGenkidama++;
        }
        else if(compteurAnimation < 24)
        {
            c.drawBitmap(image_perso_attaque4_3.getBitmap(), xPerso, yPerso, null);
            this.image_attaque4_1 = setImage(mContext, R.mipmap.goku_attaque_genkidama_1, largeurGenkidama*compteurGenkidama, largeurGenkidama*compteurGenkidama);
            xGenkidama = (xPerso + persoLargeur / 2) - (largeurGenkidama*compteurGenkidama)/2;
            c.drawBitmap(image_attaque4_1.getBitmap(), xGenkidama, yPerso - (largeurGenkidama*compteurGenkidama), null);
            compteurGenkidama++;
        }
        else
        {
            c.drawBitmap(image_perso_attaque4_4.getBitmap(), xPerso, yPerso, null);
            c.drawBitmap(image_attaque4_1.getBitmap(), xGenkidama + genkidamaAvanceX * (compteurAnimation - 24), yPerso - (largeurGenkidama*compteurGenkidama) + genkidamaAvanceY * (compteurAnimation - 24), null);
        }

        //quand c'est finis
        if(compteurAnimation == 60)
        {
            compteurGenkidama = 1;
            animationEnCoursAttaque4 = false;
            deplacementVersCible(null, 0);
        }
    }
}
