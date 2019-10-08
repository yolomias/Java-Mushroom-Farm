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
}
