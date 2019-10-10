package com.company;

public class Eggy extends Enemy {

    public Eggy(int health, int speed, int money, int damage, char affinity, String name, String path) {
        super(health, speed, money, damage, affinity, name, path = "/textures/enemyEggS.png");
    }
}
