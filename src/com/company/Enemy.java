package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

//Klasse für Gegner
public class Enemy extends JLabel {
    private int health;
    private final int speed;
    private final int money;
    private final int damage;
    private final char affinity;
    private final String name;
    private final Icon texture1;
    private int nextPosX;
    private int nextPosY;
    private boolean nextPosReached;
    private char direction;

    public Enemy(int health, int speed, int money, int damage, char affinity, String name, String path) {
        this.health = health;
        this.speed = speed;
        this.money = money;
        this.damage = damage;
        this.affinity = affinity;
        this.name = name;
        texture1 = Main.loadTexture(path);
        setIcon(texture1);
        setBounds(300, 0, 50, 50);
        this.nextPosX = 0;
        this.nextPosY = 0;
        this.nextPosReached = false;

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMoney() {
        return money;
    }

    public int getDamage() {
        return damage;
    }

    public char getAffinity() {
        return affinity;
    }

    public String getName() {
        return name;
    }

    public int getNextPosX() {
        return nextPosX;
    }

    public void setNextPosX(int nextPosX) {
        this.nextPosX = nextPosX;
    }

    public int getNextPosY() {
        return nextPosY;
    }

    public void setNextPosY(int nextPosY) {
        this.nextPosY = nextPosY;
    }

    public boolean isNextPosReached() {
        return nextPosReached;
    }

    public void setNextPosReached(boolean nextPosReached) {
        this.nextPosReached = nextPosReached;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public void move() {
        //Hohle Position nur wenn sie noch nicht gesetzt wurden
        if (getNextPosX() == 0 && getNextPosY() == 0) {
            final int[] position = Main.getMapA().findEnemyPosition(getX(), getY());
            //Wenn position gültig
            if (position[0] != 0 || position[1] != 0) {
                setDirection(Main.getA()[position[0]][position[1]]);
                switch (getDirection()) {
                    case 'N':
                        setNextPosX(getX());
                        setNextPosY(getY() - 50);
                        break;

                    case 'O':
                        setNextPosX(getX() + 50);
                        setNextPosY(getY());
                        break;

                    case 'W':
                        setNextPosX(getX() - 50);
                        setNextPosY(getY());
                        break;

                    case 'S':

                    case 'e':

                    case 'r':
                        setNextPosX(getX());
                        setNextPosY(getY() + 50);
                        break;
                }
                setNextPosReached(false);
            }
        }

        //Wenn nächste position noch nicht erreicht
        if (!isNextPosReached()) {
            //Finde heraus wo Enemy gerade steht
            //final int myPosX = getX();
            //final int myPosY = getY();

            switch (getDirection()) {
                case 'N':
                    setLocation(getX(), getY() - 1);
                    break;

                case 'O':
                    setLocation(getX() + 1, getY());
                    break;

                case 'W':
                    setLocation(getX() - 1, getY());
                    break;

                case 'S':

                case 'r':
                    setLocation(getX(), getY() + 1);
                    break;

                case 'e':
                    final int[] respawnPosition = Main.getMapA().getRespawnPosition();
                    if (respawnPosition[0] != 0 || respawnPosition[1] != 0) {
                        setLocation(respawnPosition[0], respawnPosition[1]);
                        setNextPosReached(true);
                        setNextPosX(0);
                        setNextPosY(0);
                    }
                    break;
            }

            Toolkit.getDefaultToolkit().sync();

            if (getNextPosX() == getX() && getNextPosY() == getY()) {
                setNextPosReached(true);
                setNextPosX(0);
                setNextPosY(0);
            }

        }
    }

}



