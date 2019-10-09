package com.company;

import javax.swing.*;

public class Map {
    private final char[][] map;
    private Field[][] field;
    private final int sizeX;
    private final int sizeY;

    public Map(char[][] map) {
        this.map = map;
        this.sizeX = map.length;
        this.sizeY = map[1].length;
        this.field = new Field[sizeX][sizeY];
    }

    void buildMap(JPanel panel) {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                field[i][j] = new Field(map[i][j]);
                int x = i + 1;
                int y = j + 1;
                panel.add(field[i][j]);
                field[i][j].setLocation(j * 50, i * 50);
            }
        }
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    //Eine Methode um alle verfügbaren Plätze zum Kauf eines Defenders anzuzeigen bzw. um die Felder wieder auf den Ursprungszustand zurück zu versetzen
     void makeMapActive(boolean isActive) {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                //Wenn Feld ein Grassfeld ist oder der Baum gefällt wurde
                if (field[i][j].getType() == 'G' || field[i][j].isTreeCutted()) {
                    //Mache es Aktiv um es für Kauf freizuschalten
                    field[i][j].setFieldActive(isActive);
                    //Wenn Feld none ist oder der Baum gefällt wurde und none ist und isActive true ist
                    if (isActive && field[i][j].getDefenderType() == 'n' ||
                            (field[i][j].isTreeCutted() && field[i][j].getDefenderType() == 'n' && isActive)
                    ) field[i][j].setBackgroundImage("/textures/grassActiveS.png");
                    else {
                        //Ansonsten breche den Kauf ab und mache es zum vorherigen Feld, wenn es Frei ist oder der Baum gefällt wurde
                        if (field[i][j].getDefenderType() == 'n' || (field[i][j].isTreeCutted()
                                && field[i][j].getDefenderType() == 'n' )
                        ) field[i][j].setBackgroundImage("/textures/grassS.png");
                    }
                }
            }
        }
    }
}
