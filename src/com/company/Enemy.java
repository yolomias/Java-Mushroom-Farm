package com.company;

//Klasse für Gegner
public class Enemy {
    private int health;
    private final byte speed;
    private final int money;
    private final byte damage;
    private final char affinity;
    private final String name;

    public Enemy(int health, byte speed, int money, byte damage, char affinity, String name) {
        this.health = health;
        this.speed = speed;
        this.money = money;
        this.damage = damage;
        this.affinity = affinity;
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public byte getSpeed() {
        return speed;
    }

    public int getMoney() {
        return money;
    }

    public byte getDamage() {
        return damage;
    }

    public char getAffinity() {
        return affinity;
    }

    public String getName() {
        return name;
    }
}
