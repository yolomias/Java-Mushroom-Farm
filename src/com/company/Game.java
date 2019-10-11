package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private byte wave;
    private byte timer;
    private byte enemiesCount;
    private long score;
    private static long cash;
    private byte healthRemaining;
    private List<Wave> waves;
    private List<Enemy> enemies;
    private List<Defender> defenders;
    private final int frameDelay;
    private Thread runningGame;

    Game() {
        this.wave = 0;
        this.timer = 60;
        this.enemiesCount = 0;
        this.score = 0;
        cash = 2000;
        this.healthRemaining = 100;
        this.waves = new ArrayList<>();
        waves.add(new Wave(new Eggy(20, 2, 15, 5, 'f', "Ja!",""), 10, 'E'));
        this.enemies = new ArrayList<>();
        this.defenders = new ArrayList<>();
        this.frameDelay = 33;

        this.runningGame = new Thread(() -> {
            long before = System.currentTimeMillis();;
            long difference;
            long sleepingTime;
            long spawnDifference;
            long spawnBefore = System.currentTimeMillis();

            while (true) {
                for (Wave wa: waves) {
                    for (Enemy en: wa.getEnemies()) {
                        if (en.isAddedToPanel() ) en.move();
                        else {
                            spawnDifference = spawnBefore - System.currentTimeMillis();
                            if (spawnDifference <= -1250) {
                                addEnemyToPanel(en);
                                en.setAddedToPanel(true);
                                spawnBefore = System.currentTimeMillis();
                            }
                        }
                    }
                }

                difference = System.currentTimeMillis() - before;
                sleepingTime = frameDelay - difference;

                if (sleepingTime < 0) sleepingTime = 2;

                try {
                    Thread.sleep(sleepingTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                before = System.currentTimeMillis();
            }
        });
    }

    public byte getWave() {
        return wave;
    }

    public void setWave(byte wave) {
        this.wave = wave;
    }

    public byte getTimer() {
        return timer;
    }

    public void setTimer(byte timer) {
        this.timer = timer;
    }

    public byte getEnemiesCount() {
        return enemiesCount;
    }

    public void setEnemiesCount(byte enemiesCount) {
        this.enemiesCount = enemiesCount;    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    static long getCash() {
        return cash;
    }

    //Methode um den Kontostand zu ändern, alle Sachen die dadurch beeinflusst werden, sollen ebenso geändert werden
    static void setCash(long money) {
        cash = money;
        //Anpassung des Labels welche den Kontostand anzeigt
        Main.getCashLabel().setText("Cash: " + Game.getCash() + " $");
        //Wenn Kontostand zu gering um Gomphos zu kaufen, deaktiviere es oder reaktiviere bei genügend Geld
        if (cash < 100) Main.getBuyGomphus().setEnabled(false);
        else Main.getBuyGomphus().setEnabled(true);
    }

    public byte getHealthRemaining() {
        return healthRemaining;
    }

    public void setHealthRemaining(byte healthRemaining) {
        this.healthRemaining = healthRemaining;
    }

    List<Defender> getDefenders() {
        return defenders;
    }

    public void setDefenders(List<Defender> defenders) {
        this.defenders = defenders;
    }

    public int getFrameDelay() {
        return frameDelay;
    }

    public Thread getRunningGame() {
        return runningGame;
    }

    public List getWaves() {
        return waves;
    }

    void addEnemyToPanel(Enemy enemy) {
        Main.getGamepanel().add(enemy);
        Main.getGamepanel().setLayer(enemy, 1);
    }
}
