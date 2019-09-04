package com.company;

import javax.swing.*;

public abstract class Field extends JButton {

    //Feldtypen Char Legende N (Norden), O(Osten), W(westen), S(Sueden), B(Baum), H(Haus), F(Fels), G(Gras)
    private final char type;
    private final boolean arable;
    private boolean treeCutted;


    Field (char type){
        this.type = type;
        this.arable = getType() == 'G' || getType() == 'B';
        if (arable) this.treeCutted = false;
        if (isDirection() ) this.setIcon(new ImageIcon(Class.class.getResource("/textures/field.png")));
        else if (getType() == 'B') setIcon(new ImageIcon(Class.class.getResource("/textures/tree.png")));
        else if (getType() == 'G') setIcon(new ImageIcon(Class.class.getResource("/textures/grass.png")));
        else if (getType() == 'F') setIcon(new ImageIcon(Class.class.getResource("/textures/rock.png")));
        else if (getType() == 'H') setIcon(new ImageIcon(Class.class.getResource("/textures/house.png")));
        setSize(75, 75);

    }

    public char getType() {
        return type;
    }

    public boolean isArable() {
        return arable;
    }

    public boolean isTreeCutted() {
        return treeCutted;
    }

    public void setTreeCutted(boolean treeCutted) {
        this.treeCutted = treeCutted;
    }

    //Fällt den Baum, wenn das Feld ein Baum ist und er noch nicht gefällt wurde
    public void cutTree() {
        if (getType() == 'B') {
            if (!isTreeCutted()) setTreeCutted(true);
        }
    }

    //Prüft nach ob das Feld ein Bewegungsfeld ist
    public boolean isDirection() {
        return getType() == 'N' || getType() == 'O' || getType() == 'W' || getType() == 'S';
    }


}
