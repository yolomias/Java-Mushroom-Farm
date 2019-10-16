package com.company;

import javax.swing.*;

public abstract class Defender {

    int attack;
    int reload;
    boolean isReloading;
    int level;
    int range;
    int sellPrice;
    char affinity;
    int slots;
    int posX;
    int posY;

    public Defender(int positionX, int positionY) {
        this.posX = positionX;
        this.posY = positionY;
        this.isReloading = false;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getReload() {
        return reload;
    }

    public void setReload(int reload) {
        this.reload = reload;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public char getAffinity() {
        return affinity;
    }

    public void setAffinity(char affinity) {
        this.affinity = affinity;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean isReloading() {
        return isReloading;
    }

    public void setReloading(boolean reloading) {
        isReloading = reloading;
    }



    public void levelUp(int price1, int price2, int attack1, int attack2, int reload1, int reload2, int range1,
                        int range2, int sell1, int sell2) {
        if (getLevel() == 1 && Game.getCash() >= price1) {
            setAttack(getAttack() + attack1);
            setReload(getReload() - reload1);
            setRange(getRange() + range1);
            setSellPrice(sell1);
            setLevel(2);
        }
        else if (getLevel() == 2 && Game.getCash() >= price2) {
            setAttack(getAttack() + attack2);
            setReload(getReload() - reload2);
            setRange(getRange() + range2);
            setSellPrice(sell2);
            setLevel(3);
        }
    }

    //Methode um einen Gegner zu attackieren wenn dieser in Reichweite ist
    public void attackEnemey(Enemy en) {
        if (!isReloading()) {
            final int enX = en.getX();
            final int enY = en.getY();
            final int sleepingTime = getReload() * 10;
            //L = Left, R = Right, U = Up, D = Down
            final int rangeXL = getPosX() - getRange();
            final int rangeXR = getPosX() + getRange();
            final int rangeYU = getPosY() - getRange();
            final int rangeYD = getPosY() + getRange();

            //Prüfe nach ob Gegner innerhalb der Reichweite des Defenders ist
            if (enX >= rangeXL && enX <= rangeXR && enY >= rangeYU && enY <= rangeYD) {
                //Füge neuen Shot hinzu
                Main.getNewGame().addShot(new Shot(getPosX(), getPosY(), en, this));
                //Lade nach
                setReloading(true);
                Thread threadReload = new Thread(() -> {
                    try {
                        Thread.sleep(sleepingTime);
                        //Wenn zeit abgelaufen ist nachgeladen
                        setReloading(false);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                threadReload.start();
            }
        }
    }
}
