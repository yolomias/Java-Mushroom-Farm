package com.company;

public class Gomphus extends Defender {

    public Gomphus(int posX, int posY) {
        super(posX,posY);
        this.attack = 10;
        this.reload = 35;
        this.level = 1;
        this.range = 100;
        this.sellPrice = 50;
        this.slots = 1;
    }

    public void levelUp() {
        super.levelUp(200,300,22, 35, 2,2,10,20, 150, 300);
    }
}
