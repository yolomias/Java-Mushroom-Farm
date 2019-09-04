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

    void buildMap() {

    }
}
