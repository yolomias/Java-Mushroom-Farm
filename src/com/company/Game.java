package com.company;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private byte wave;
    private byte timer;
    private byte enemiesCount;
    private long score;
    private long cash;
    private byte healthRemaining;
    private List<Enemy> enemies;

    public Game() {
        this.wave = 0;
        this.timer = 60;
        this.enemiesCount = 0;
        this.score = 0;
        this.cash = 200;
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

    public long getCash() {
        return cash;
    }

    public void setCash(long cash) {
        this.cash = cash;
    }

    public byte getHealthRemaining() {
        return healthRemaining;
    }

    public void setHealthRemaining(byte healthRemaining) {
        this.healthRemaining = healthRemaining;
    }
}
