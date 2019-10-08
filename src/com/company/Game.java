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

    Game() {
        this.wave = 0;
        this.timer = 60;
        this.enemiesCount = 0;
        this.score = 0;
        cash = 200;
        this.healthRemaining = 100;
        this.enemies = new ArrayList<>();
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

    static void setCash(long money) {
        cash = money;
    }

    public byte getHealthRemaining() {
        return healthRemaining;
    }

    public void setHealthRemaining(byte healthRemaining) {
        this.healthRemaining = healthRemaining;
    }
}
