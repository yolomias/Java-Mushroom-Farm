package com.company;

import javax.swing.*;

public class Shot extends JLabel {
    private Enemy target;
    private Defender attacker;

    public Shot(int posX, int posY, Enemy target, Defender attacker) {
        setBounds(posX, posY, 8,8);
        //this.setLocation(posX, posY);
        this.target = target;
        this.attacker = attacker;
        setIcon(Main.loadTexture("/textures/shotS.png"));
      //  System.out.println("ja!");
    }

    boolean hitEnemy() {
        //Bewege Shot in Richtung target
        if (getX() > target.getX()) setLocation(getX() - 1, getY());
        else if (getX() < target.getX()) setLocation(getX() + 1, getY());
        if (getY() > target.getY()) setLocation(getX(), getY() - 1);
        else if (getY() < target.getY()) setLocation(getX(), getY() + 1);

        //Wenn Positionen übereinstimmen, dann getroffen, shot soll verschwinden
        if(getX() == target.getX() && getY() == target.getY()) {
            target.setHealth(target.getHealth() - attacker.getAttack());
            System.out.println("Health: " + target.getHealth());
            System.out.println("schaden gemacht!");
            if (target.getHealth() <= 0) {
                Main.getGamepanel().remove(this);
                Main.getNewGame().getShots().remove(this);
            }
            return true;
        }
        else return false;
    }
}
