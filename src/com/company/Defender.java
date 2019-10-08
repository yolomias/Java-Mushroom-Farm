package com.company;

public abstract class Defender {

    int attack;
    int reload;
    int level;
    int range;
    int sellPrice;
    char affinity;
    int slots;

    public Defender() {

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
}
