package com.company;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private byte wave;
    private byte timer;
    private byte enemiesCount;
    private long score;
    private static long cash;
    private byte healthRemaining;
    private List<Enemy> enemies;
    private List<Defender> defenders;

    Game() {
        this.wave = 0;
        this.timer = 60;
        this.enemiesCount = 0;
        this.score = 0;
        cash = 2000;
        this.healthRemaining = 100;
        this.enemies = new ArrayList<>();
        this.defenders = new ArrayList<>();
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

    public byte getEnemies() {
        return enemiesCount;
    }

    public void setEnemies(byte enemiesCount) {
        this.enemiesCount = enemiesCount;
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

    public List<Defender> getDefenders() {
        return defenders;
    }

    public void setDefenders(List<Defender> defenders) {
        this.defenders = defenders;
    }
}
