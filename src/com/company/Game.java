package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    //private byte wave;
    private byte timer;
    private byte enemiesCount;
    private long score;
    private static long cash;
    private static int healthRemaining;
    private List<Wave> waves;
    private List<Enemy> enemies;
    private List<Defender> defenders;
    private List<Shot> shots;
    private final int frameDelay;
    private Thread runningGame;

    Game() {
        //this.wave = 0;
        this.timer = 60;
        this.enemiesCount = 0;
        this.score = 0;
        cash = 2000;
        healthRemaining = 100;
        this.waves = new ArrayList<>();
        waves.add(new Wave(new Eggy(20, 5, 15, 5, 'f', "Ja!",""), 10, 'E', 0));
        this.enemies = new ArrayList<>();
        this.defenders = new ArrayList<>();
        this.shots = new ArrayList<>();
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
                        if (en.isAddedToPanel() ) {
                            en.move();

                            for (Defender def: defenders) {
                                def.attackEnemey(en);
                            }

                            if (en.getHealth() <= 0) {
                                Main.getGamepanel().remove(en);
                                wa.getEnemies().remove(en);
                            }
                        }
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

                for (Shot sh: shots) {
                    System.out.println(shots.size());
                    sh.hitEnemy();
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

  //  public byte getWave() {
  //      return wave;
  //  }
//
  //  public void setWave(byte wave) {
  //      this.wave = wave;
  //  }

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
        Main.getCashLabel().setText("Cash: " + getCash() + " $");
        //Wenn Kontostand zu gering um Gomphos zu kaufen, deaktiviere es oder reaktiviere bei genügend Geld
        if (cash < 100) Main.getBuyGomphus().setEnabled(false);
        else Main.getBuyGomphus().setEnabled(true);
    }

    public static int getHealthRemaining() {
        return healthRemaining;
    }

    public static void setHealthRemaining(int health) {
        healthRemaining = health;
        Main.getHealthLabel().setText("Health: " + getHealthRemaining());
        //Beende das Spiel, wenn keine Lebenspunkte mehr übrig sind
        if (getHealthRemaining() <= 0) {
            JOptionPane.showMessageDialog(Main.getMainframe(), "You lost, because you suck!");
            System.exit(0);
        }
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

    public List<Wave> getWaves() {
        return waves;
    }

    public List<Shot> getShots() {
        return shots;
    }

    void addEnemyToPanel(Enemy enemy) {
        Main.getGamepanel().add(enemy);
        Main.getGamepanel().setLayer(enemy, 1);
    }

    void addShot(Shot s) {
        //TODO Probleme mit Shot, hängt sich auf wenn Health unter 0 sinkt und die Grafik erscheint erst gar nicht
        shots.add(s);
        System.out.println(shots);
        Main.getGamepanel().add(s);
        Main.getGamepanel().setLayer(s, 2);
    }
}
