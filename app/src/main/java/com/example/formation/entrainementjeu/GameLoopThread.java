package com.example.formation.entrainementjeu;


import android.graphics.Canvas;

/**
 * Created by kurzen on 05/03/2018.
 */

public class GameLoopThread extends Thread{
    private final static int FRAMES_PER_SECOND = 30;
    private final static int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;

    private CombatView combatView = null;

    private boolean running = false;
    private boolean combat = false;

    public GameLoopThread(CombatView view)
    {
        this.combatView = view;
        combat = true;
    }

    public void setRunning(boolean run)
    {
        this.running = run;
    }

    public void run()
    {
        long startTime, sleepTime;

        while(running)
        {
            startTime = System.currentTimeMillis();

            if(combat)
                runCombat();

            sleepTime = SKIP_TICKS - (System.currentTimeMillis() - startTime);
            try
            {
                if(sleepTime >= 0) {
                    sleep(sleepTime);
                }
            }
            catch(Exception e) {}
        }
    }

    private void runCombat()
    {
        Canvas c = null;
        try
        {
            c = combatView.getHolder().lockCanvas();
            synchronized (combatView.getHolder()) {combatView.doDraw(c);}
        }
        finally {
            if(c != null) {combatView.getHolder().unlockCanvasAndPost(c);}
        }
    }
}