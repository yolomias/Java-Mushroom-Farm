package com.company;

import javax.swing.*;

public class MushroomGui {
    private JPanel panel1;
    private JPanel SpielfeldPanel;
    private JPanel HudPanel;

    static void gameStart() {
        /*
        MAP A

H, H, H, B, H, H, S, H, H, B, H, B, H, F, F
H, B, B, H, G, H, S, H, G, B, G, F, B, H, F
G, F, G, B, G, G, S, B, G, G, F, G, G, G, G
B, G, S, W, W, W, W, G, O, O, O, O, S, B, G
G, G, S, G, G, G, B, G, N, G, G, G, S, G, G
B, G, S, G, G, B, G, B, N, G, B, G, S, G, B
G, B, O, O, O, O, O, O, N, G, F, B, S, B, F
H, G, B, G, B, G, G, B, G, F, G, G, S, G, G
G, F, G, B, G, F, B, G, G, G, B, G, S, G, F
G, G, S, W, W, W, W, W, W, W, W, W, W, G, G
H, G, S, G, B, G, G, B, G, G, B, G, G, F, G
G, G, S, G, F, G, F, G, O, O, O, S, G, G, H
F, G, S, G, B, G, G, O, N, G, G, O, S, G, G
G, G, O, O, O, O, O, N, G, B, G, H, S, H, G
H, G, G, G, B, B, G, B, G, H, G, H, S, H, F
         */
        char[][] a = {{'H', 'H', 'H', 'B', 'H', 'H', 'S', 'H', 'H', 'B', 'H', 'B', 'H', 'F', 'F'},
                {'H', 'B', 'B', 'H', 'G', 'H', 'S', 'H', 'G', 'B', 'G', 'F', 'B', 'H', 'F'}};
        Map mapA = new Map(a);

    }
}
