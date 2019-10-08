package com.company;

import javax.swing.*;
import java.util.Random;

public class Field extends JButton {
    //Feldtypen Char Legende N (Norden), O(Osten), W(westen), S(Sueden), B(Baum), H(Haus), F(Fels), G(Gras), D(Dynamisches Objekt)
    private final char type;
    private final boolean arable;
    private boolean treeCutted;
    // n = none, G = Gomphus
    private char defenderType;


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
        //this.defenderType = 'n';
        addActionListener(e -> clicked() );
        setSize(50, 50);
        setBorderPainted(false);
    }

    public char getType() {
        return type;
    }

    public boolean isArable() {
        return getType() == 'G' || isTreeCutted();
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
        return getType() == 'N' || getType() == 'O' || getType() == 'W' || getType() == 'S';
    }

    private void clicked() {
        System.out.println("");
        //Abfrage wenn Feld Baum ist und fällbar ist
        if (getType() == 'B' && Game.getCash() >= 200 && !isTreeCutted()) {
            int solution = JOptionPane.showConfirmDialog(Main.getMainframe(), "Soll der Baum gefällt werden? (kostet 200)");
            if (solution == JOptionPane.YES_OPTION) {
                cutTree();
                Game.setCash(Game.getCash() - 200);
                System.out.println(Game.getCash());
            }
        }
        else if (getType() == 'B' && Game.getCash() < 200 && !isTreeCutted()) System.out.println("Du hast nicht genügend Knete um den Baum zu fällen!");
        if (isArable()) System.out.println("Feld ist bebaubar");
        else System.out.println("Feld ist nicht bebaubar");
    }

    public char getDefenderType() {
        return defenderType;
    }

    public void setDefenderType(char defenderType) {
        this.defenderType = defenderType;
    }

    private void setBackgroundImage (String path) {
        setIcon(new ImageIcon(Class.class.getResource(path)));
    }

}
