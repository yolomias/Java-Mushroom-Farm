package com.company;

import java.util.ArrayList;
import java.util.List;

public class Wave {
    private byte currentWave;
    private List<Enemy> enemies;
    private final int enemyCount;
    private char defenderName;
    private int index;

    public Wave(/*byte currentWave, */Enemy en, int enemyCount, char defenderName, int index) {
        //this.currentWave = currentWave;
        this.enemies = new ArrayList<>();
        this.enemyCount = enemyCount;
        this.defenderName = defenderName;
        this.index = index;
        addEnemies(en);
    }

    //Füge Enemies auf die Liste hinzu, erstelle immer ein neues Objekt damit nicht auf das selbe zugegriffen wird
    private void addEnemies(Enemy enemy) {
        for (int i = 0; i < getEnemyCount(); i++) {
            Enemy ene = new Enemy(0,0,0,0,'f', "", "/textures/enemyEggS.png");
            switch (getDefenderName()) {
                case 'E':
                     ene = new Eggy(enemy.getHealth(), enemy.getSpeed(), enemy.getMoney(), enemy.getDamage(),
                             enemy.getAffinity(), enemy.getName(), "");
                    break;
            }
            enemies.add(ene);
        }
    }

    List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public char getDefenderName() {
        return defenderName;
    }

    public int getIndex() {
        return index;
    }
}
