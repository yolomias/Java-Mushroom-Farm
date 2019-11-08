package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    //private byte wave;
    private static int timer;
    private int enemiesCount;
    private long score;
    private int currentWave;
    private static long cash;
    private static int healthRemaining;
    private List<Wave> waves;
    private List<Enemy> enemies;
    private List<Defender> defenders;
    private List<Shot> shots;
    private final int frameDelay;
    private Thread runningGame;
    private Thread runningTimer;

    Game() {
        //this.wave = 0;
        timer = 60;
        this.score = 0;
        cash = 200;
        healthRemaining = 100;
        this.waves = new ArrayList<>();
        waves.add(new Wave(new Eggy(20, 5, 15, 5, 'f', "Ja!",""), 10, 'E', 0));
        waves.add(new Wave(new Eggy(30, 2, 20, 7, 'f', "Ja!2",""), 15, 'E', 1));
        this.currentWave = 0;
        this.enemiesCount = getWaves().get(currentWave).getEnemies().size();
        nextRound();
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
                List<Enemy> killedEnemies = new ArrayList<>();
                for (Wave wa: waves) {
                    if (wa.isActive()) {
                        for (Enemy en : wa.getEnemies()) {
                            if (en.isAddedToPanel()) {
                                en.move();

                                for (Defender def : defenders) {
                                    def.attackEnemey(en);
                                }

                                if (en.getHealth() <= 0) {
                                    killedEnemies.add(en);
                                }
                            } else {
                                spawnDifference = spawnBefore - System.currentTimeMillis();
                                if (spawnDifference <= -1250) {
                                    addEnemyToPanel(en);
                                    en.setAddedToPanel(true);
                                    spawnBefore = System.currentTimeMillis();
                                }
                            }
                        }
                    }
                }
                List<Shot> hittedShots = new ArrayList<>();
                for (Shot sh: getShots()) {
                    if (sh.hitEnemy()) hittedShots.add(sh);
                }

                for (Shot s: hittedShots) {
                    Main.getGamepanel().remove(s);
                    getShots().remove(s);
                }

                for (Enemy deadEn: killedEnemies) {
                    for (Wave wa: getWaves()) {
                        Main.getGamepanel().remove(deadEn);
                        wa.getEnemies().remove(deadEn);
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
                Main.getMainframe().repaint();

                before = System.currentTimeMillis();
            }
        });

        runningTimer = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setTimer(getTimer() - 1);
                if (getTimer() <= 1) {
                    nextRound();
                    setTimer(20);
                }
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

    public static int getTimer() {
        return timer;
    }

    public static void setTimer(int time) {
        timer = time;
        Main.getTimerLabel().setText("Timer: " + getTimer());
    }

    public int getEnemiesCount() {
        return enemiesCount;
    }

    public void setEnemiesCount(int enemiesCount) {
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

    public Thread getRunningTimer() {
        return runningTimer;
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
        //TODO Probleme mit Shot, hängt sich auf wenn Health unter 0 sinkt
        shots.add(s);
        //System.out.println(shots);
        Main.getGamepanel().add(s);
        Main.getGamepanel().setLayer(s, 2);
    }

    public int getCurrentWave() {
        return currentWave;
    }

    public void setCurrentWave(int currentWave) {
        this.currentWave = currentWave;
    }

    private void nextRound() {
        if (getEnemiesCount() <= 0){
            //TODO Biete über einen Button an, die nächste Welle jetzt schon zu starten
        }
        if (getWaves().size()  > currentWave) {
            getWaves().get(getCurrentWave()).setActive(true);
            setCurrentWave(getCurrentWave() + 1);
            setEnemiesCount(getEnemiesCount() + waves.get(currentWave).getEnemies().size());
            System.out.println("Welle Erhöht");
        }
        else System.out.println("Keine weiteren Wellen mehr vorhanden");
    }
}
