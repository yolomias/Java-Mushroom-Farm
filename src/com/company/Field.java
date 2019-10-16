package com.company;

import javax.swing.*;
import java.util.Random;

public class Field extends JButton {
    //Feldtypen Char Legende N (Norden), O(Osten), W(westen), S(Sueden), B(Baum), H(Haus), F(Fels), G(Gras), D(Dynamisches Objekt), r(respawn), e(ende)
    private final char type;
    private boolean arable;
    private boolean treeCutted;
    // n = none, G = Gomphus
    private char defenderType;
    private boolean fieldActive;


    Field (char type){
        this.type = type;
        this.arable = getType() == 'G' || getType() == 'B';
        if (arable) this.treeCutted = false;
        if (isDirection() ) setBackgroundImage("/textures/fieldS.png");
        else if (getType() == 'B') setBackgroundImage("/textures/treeS.png");
        else if (getType() == 'G') setBackgroundImage("/textures/grassS.png");
        else if (getType() == 'D') {
            Random rand = new Random();
            int ranndom = rand.nextInt(3);
            switch (ranndom) {
                case 0: setBackgroundImage("/textures/rockS.png");
                break;
                case 1: setBackgroundImage("/textures/houseS.png");
                break;
                case 2: setBackgroundImage("/textures/house2S.png");
                break;
            }
        }
        this.defenderType = 'n';
        addActionListener(e -> clicked() );
        setSize(50, 50);
        setBorderPainted(false);
    }

    public char getType() {
        return type;
    }

    //Wenn Feld Grass ist oder der Baum gefällt wurde und das Feld noch frei ist
    public boolean isArable() {
        return (getType() == 'G' || isTreeCutted()) && getDefenderType() == 'n';
    }

    public void setArable(boolean arable) {
        this.arable = arable;
    }

    public boolean isTreeCutted() {
        return treeCutted;
    }

    public void setTreeCutted(boolean treeCutted) {
        this.treeCutted = treeCutted;
    }

    //Fällt den Baum, wenn das Feld ein Baum ist und er noch nicht gefällt wurde
    public void cutTree() {
        //Wenn Type Baum ist
        if (getType() == 'B') {
            //Wenn Baum noch nicht gefällt wurde und ausreichend Geld vorhanden ist, dann fälle Baum
            if (!isTreeCutted() && Game.getCash() >= 200) {
                setTreeCutted(true);
                System.out.println("Baum wurde gefällt");
                this.setBackgroundImage("/textures/grassS.png");
            }
        }
    }

    //Prüft nach ob das Feld ein Bewegungsfeld ist
    public boolean isDirection() {
        return getType() == 'N' || getType() == 'O' || getType() == 'W' || getType() == 'S' || getType() == 'r' || getType() == 'e';
    }

    private void clicked() {
        System.out.println();
        System.out.println("X: " + getX() + ", Y: " + getY());
        //Abfrage wenn Feld Baum ist und Baum nicht gefällt wurde
        if (getType() == 'B' && Game.getCash() >= 200 && !isTreeCutted()) {
            //Breche vorherigen kauf eines Defenders ab
            Main.getMapA().makeMapActive(false);
            //Frage nach ob der Baum gefällt werden soll
            int solution = JOptionPane.showConfirmDialog(Main.getMainframe(), "Will you cut down this fucking tree? (costs 200 $)");
            if (solution == JOptionPane.YES_OPTION) {
                cutTree();
                Game.setCash(Game.getCash() - 200);
                System.out.println(Game.getCash());
            }
        }
        else if (getType() == 'B' && Game.getCash() < 200 && !isTreeCutted()) System.out.println("Du hast nicht genügend Knete um den Baum zu fällen!");

        //Wenn Feld bebaubar ist
        if (isArable()) {
            System.out.println("Feld ist bebaubar");
            //Wenn das Feld zum kauf freigegeben wurde
            if (isFieldActive()) {
                //Setze DefenderType indem der Typ vom aktuellen Kauf geholt wird
                setDefenderType(Main.getBuyType());
                // Feld soll nicht mehr bebaubar sein, da es jetzt bebaut wurde
                this.setArable(false);
                //Bebaue Feld mit dem aktuellen Kauf
                switch (Main.getBuyType()) {
                    case 'G':
                        setBackgroundImage("/textures/gomphusS.png");
                        Game.setCash(Game.getCash() - 100);
                        Main.getNewGame().getDefenders().add(new Gomphus(getX(), getY()));
                        //Schließe Kauf ab damit nicht weitere Felder bebaut werden können
                        Main.getMapA().makeMapActive(false);
                        System.out.println("Gomphus gekauft");
                        break;
                }
                //Setze BuyType wieder auf none
                Main.setBuyType('n');
            }
        }
        else System.out.println("Feld ist nicht bebaubar");
    }

    public char getDefenderType() {
        return defenderType;
    }

    public void setDefenderType(char defenderType) {
        this.defenderType = defenderType;
    }

    public boolean isFieldActive() {
        return fieldActive;
    }

    public void setFieldActive(boolean fieldActive) {
        this.fieldActive = fieldActive;
    }

    void setBackgroundImage (String path) {
        setIcon(new ImageIcon(Class.class.getResource(path)));
    }

}
