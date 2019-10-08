package com.company;

import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main {

    private static JFrame mainframe = new JFrame("Java Mushroom Farm Defender");

    public static void main(String[] args) {
        final int x = 1280, y = 800;
        // Erstellung des Mainframes

        mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainframe.setSize(x, y);
        mainframe.setResizable(false);

        //Erstellung des Panels
        JPanel gamepanel = new JPanel();
        gamepanel.setBounds(x, y, x, y);
        gamepanel.setLayout(null);
        mainframe.add(gamepanel);

        char[][] a = {{'D', 'D', 'D', 'B', 'D', 'D', 'S', 'D', 'D', 'B', 'D', 'B', 'D', 'D', 'D'},
                {'D', 'B', 'B', 'D', 'G', 'D', 'S', 'D', 'G', 'B', 'G', 'D', 'B', 'D', 'D'},
                {'G', 'D', 'G', 'B', 'G', 'G', 'S', 'B', 'G', 'G', 'D', 'G', 'G', 'G', 'G'},
                {'B', 'G', 'S', 'W', 'W', 'W', 'W', 'G', 'O', 'O', 'O', 'O', 'S', 'B', 'G'},
                {'G', 'G', 'S', 'G', 'G', 'G', 'B', 'G', 'N', 'G', 'G', 'G', 'S', 'G', 'G'},
                {'B', 'G', 'S', 'G', 'G', 'B', 'G', 'B', 'N', 'G', 'B', 'G', 'S', 'G', 'B'},
                {'G', 'B', 'O', 'O', 'O', 'O', 'O', 'O', 'N', 'G', 'D', 'B', 'S', 'B', 'D'},
                {'D', 'G', 'B', 'G', 'B', 'G', 'G', 'B', 'G', 'D', 'G', 'G', 'S', 'G', 'G'},
                {'G', 'D', 'G', 'B', 'G', 'D', 'B', 'G', 'G', 'G', 'B', 'G', 'S', 'G', 'D'},
                {'G', 'G', 'S', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'G', 'G'},
                {'D', 'G', 'S', 'G', 'B', 'G', 'G', 'B', 'G', 'G', 'B', 'G', 'G', 'D', 'G'},
                {'G', 'G', 'S', 'G', 'D', 'G', 'D', 'G', 'O', 'O', 'O', 'S', 'G', 'G', 'D'},
                {'D', 'G', 'S', 'G', 'B', 'G', 'G', 'O', 'N', 'G', 'G', 'O', 'S', 'G', 'G'},
                {'G', 'G', 'O', 'O', 'O', 'O', 'O', 'N', 'G', 'B', 'G', 'D', 'S', 'D', 'G'},
                {'D', 'G', 'G', 'G', 'B', 'B', 'G', 'B', 'G', 'D', 'G', 'D', 'S', 'D', 'D'}
        };
        Map mapA = new Map(a);
        mapA.buildMap(gamepanel);



        Game neuesSpiel = new Game();

        // Bedienung für Menü
        JButton buyGomphus = new JButton("Kaufe Gomphus");
        buyGomphus.setBounds(1000, 50, 100, 75);
        buyGomphus.addActionListener(e -> buyDefender('G'));
        gamepanel.add(buyGomphus);

        mainframe.setVisible(true);
    }



    public static JFrame getMainframe() {
        return mainframe;
    }

    public Image loadTexture(String path) throws IOException {
        return ImageIO.read(getClass().getResource(path));
    }

    public static void buyDefender(char type) {
        int price = 0;
        switch (type) {
            case 'G':
                price = 100;
                if (Game.getCash() >= price) {
                    //TODO Kauf einbauen
                } else System.out.println("Du hast nicht genügend Knete!");
                break;

        }
    }


}
