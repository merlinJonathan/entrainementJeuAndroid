package com.example.formation.entrainementjeu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by kurzen on 05/03/2018.
 */

public class CombatView extends SurfaceView implements SurfaceHolder.Callback{
    /** Attributs utilitaire **/
    private GameLoopThread gameLoopThread;
    private MainActivity mContext;
    private int ecranLargeur, ecranHauteur;
    private boolean clicInterdit = false;

    /** Attributs pour le graphisme **/
    private BitmapDrawable imageFond;
    private Goku p1, p2, p3, p4, p5, p6;

    /** Constructeurs **/
    public CombatView(MainActivity c, int largeurEcran, int hauteurEcran)
    {
        super(c);
        getHolder().addCallback(this);

        this.ecranHauteur = hauteurEcran;
        this.ecranLargeur = largeurEcran;
        this.mContext = c;

        gameLoopThread = new GameLoopThread(this);
        p1 = new Goku(mContext, ecranLargeur, ecranHauteur, 1);
        p2 = new Goku(mContext, ecranLargeur, ecranHauteur, 2);
        p3 = new Goku(mContext, ecranLargeur, ecranHauteur, 3);
        p4 = new Goku(mContext, ecranLargeur, ecranHauteur, 4);
        p5 = new Goku(mContext, ecranLargeur, ecranHauteur, 5);
        p6 = new Goku(mContext, ecranLargeur, ecranHauteur, 6);
    }

    /** Methodes **/
    public void doDraw(Canvas c)
    {
        if(c == null) {return ;}

        c.drawBitmap(imageFond.getBitmap(), 0, 0, null);

        // pour eviter que les personnages se dessinne au dessus des attaques
        if(p1.animationEnCours())
        {
            p2.doDraw(c);
            p3.doDraw(c);
            p4.doDraw(c);
            p5.doDraw(c);
            p6.doDraw(c);
            p1.doDraw(c);
        }
        else if(p2.animationEnCours())
        {
            p1.doDraw(c);
            p3.doDraw(c);
            p4.doDraw(c);
            p5.doDraw(c);
            p6.doDraw(c);
            p2.doDraw(c);
        }
        else if(p3.animationEnCours())
        {
            p2.doDraw(c);
            p1.doDraw(c);
            p4.doDraw(c);
            p5.doDraw(c);
            p6.doDraw(c);
            p3.doDraw(c);
        }
        else if(p4.animationEnCours())
        {
            p2.doDraw(c);
            p3.doDraw(c);
            p1.doDraw(c);
            p5.doDraw(c);
            p6.doDraw(c);
            p4.doDraw(c);
        }
        else if(p5.animationEnCours())
        {
            p2.doDraw(c);
            p3.doDraw(c);
            p4.doDraw(c);
            p1.doDraw(c);
            p6.doDraw(c);
            p5.doDraw(c);
        }
        else
        {
            p2.doDraw(c);
            p3.doDraw(c);
            p4.doDraw(c);
            p5.doDraw(c);
            p1.doDraw(c);
            p6.doDraw(c);
        }
    }

    public BitmapDrawable setImage(final Context c, final int ressource, final int w, final int h)
    {
        Drawable dr = c.getResources().getDrawable(ressource);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        return new BitmapDrawable(c.getResources(), Bitmap.createScaledBitmap(bitmap, w, h, true));
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // création du processus GameLoopThread si cela n'est pas fait
        if (gameLoopThread.getState() == Thread.State.TERMINATED) {
            gameLoopThread = new GameLoopThread(this);
        }
        gameLoopThread.setRunning(true);
        gameLoopThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        gameLoopThread.setRunning(false);
        while (retry) {
            try {
                gameLoopThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int w, int h) {
        resize(w, h);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        clicInterdit = (p1.animationEnCours() || p2.animationEnCours() || p3.animationEnCours() || p4.animationEnCours() || p5.animationEnCours() || p6.animationEnCours()) ||
                (p1.isDeplacementEnCours() || p2.isDeplacementEnCours() || p3.isDeplacementEnCours() || p4.isDeplacementEnCours() || p5.isDeplacementEnCours() || p6.isDeplacementEnCours());
        if(!clicInterdit)
        {
            int currentX = (int)event.getX();
            int currentY = (int)event.getY();

            switch (event.getAction()) {

                // code exécuté lorsque le doigt touche l'écran.
                case MotionEvent.ACTION_DOWN:
                    // si on clic sur la premiere attaque
                    if(currentX >= p1.getcAtk1().getX() &&
                            currentX <= (p1.getcAtk1().getX() + p1.getcAtk1().getCadreLargeur()) &&
                            currentY >= p1.getcAtk1().getY() &&
                            currentY <= (p1.getcAtk1().getY() + p1.getcAtk1().getCadreLargeur()))
                    {
                        p2.attaque1(p5);
                    }

                    // si on clic sur la deuxieme attaque
                    if(currentX >= p1.getcAtk2().getX() &&
                            currentX <= (p1.getcAtk2().getX() + p1.getcAtk2().getCadreLargeur()) &&
                            currentY >= p1.getcAtk2().getY() &&
                            currentY <= (p1.getcAtk2().getY() + p1.getcAtk2().getCadreLargeur()))
                    {
                        p2.attaque2(p5);
                    }

                    // si on clic sur la troisieme attaque
                    if(currentX >= p1.getcAtk3().getX() &&
                            currentX <= (p1.getcAtk3().getX() + p1.getcAtk3().getCadreLargeur()) &&
                            currentY >= p1.getcAtk3().getY() &&
                            currentY <= (p1.getcAtk3().getY() + p1.getcAtk3().getCadreLargeur()))
                    {
                        p2.attaque3(p5);
                    }

                    // si on clic sur la quatrieme attaque
                    if(currentX >= p1.getcAtk4().getX() &&
                            currentX <= (p1.getcAtk4().getX() + p1.getcAtk4().getCadreLargeur()) &&
                            currentY >= p1.getcAtk4().getY() &&
                            currentY <= (p1.getcAtk4().getY() + p1.getcAtk4().getCadreLargeur()))
                    {
                        p2.attaque4(p5);
                    }
                    break;

                // code exécuté lorsque le doight glisse sur l'écran.
                case MotionEvent.ACTION_MOVE:

                    break;

                // lorsque le doigt quitte l'écran
                case MotionEvent.ACTION_UP:

            }
        }
        return true;  // On retourne "true" pour indiquer qu'on a géré l'évènement
    }

    public void resize(int wScreen, int hScreen)
    {
        ecranLargeur = wScreen;
        ecranHauteur = hScreen;

        imageFond = setImage(mContext, R.mipmap.image_fond_combat, ecranLargeur, ecranHauteur);
    }
}