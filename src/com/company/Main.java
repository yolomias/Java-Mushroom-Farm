package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    //Variablen global und static damit aus anderen Klassen darauf zugegriffen werden kann
    private static JFrame mainframe = new JFrame("Java Mushroom Farm Defender");
    private static Map mapA;
    private static JLayeredPane gamepanel;
    private static char buyType;
    private static Game newGame;
    private static JLabel cashLabel;
    private static JButton buyGomphus;
    private final static char[][] a = new char[][]{
            {'D', 'D', 'D', 'B', 'D', 'D', 'r', 'D', 'D', 'B', 'D', 'B', 'D', 'D', 'D'},
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
            {'D', 'G', 'G', 'G', 'B', 'B', 'G', 'B', 'G', 'D', 'G', 'D', 'e', 'D', 'D'}
    };

    public static void main(String[] args) {
        final int x = 1280, y = 800;

        // Erstellung des Mainframes
        mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainframe.setSize(x, y);
        mainframe.setResizable(false);

        //Erstellung des Panels
        gamepanel = new JLayeredPane();
        gamepanel.setBounds(x, y, x, y);
        gamepanel.setLayout(null);
        mainframe.add(gamepanel);

        //MAP A
        mapA = new Map(a);
        mapA.buildMap(gamepanel);

        //Initialisiere GAME, unteranderem dass die statischen Variablen der Klasse benutzt werden können
        newGame = new Game();

        // Bedienung für Menü (rechts neben der Map auf dem Spielfeld)
        //Label was immer den Aktuellen Kontostand anzeigt
        cashLabel = new JLabel("Cash: " + Game.getCash() + " $");
        getCashLabel().setBounds(1000, 0, 150, 75);

        //Ein Button mit dem der Gomphus gekauft wird
        buyGomphus = new JButton();
        buyGomphus.setBounds(1000, 70, 75, 75);
        buyGomphus.setToolTipText("Buy Gomphus Defender");
        buyGomphus.setIcon(new ImageIcon(Class.class.getResource("/textures/buyGomphus.png")));
        buyGomphus.addActionListener(e -> buyDefender('G'));

        //Füge die Elemente dem gamepanel hinzu
        gamepanel.add(getCashLabel());
        gamepanel.add(buyGomphus);

        //Wenn alles fertig ist, mache den Frame sichtbar
        mainframe.setVisible(true);

        getNewGame().getRunningGame().start();
    }



    public static JFrame getMainframe() {
        return mainframe;
    }

    public static ImageIcon loadTexture(String path)/* throws IOException */{
        return new ImageIcon(Class.class.getResource(path));
       // return ImageIO.read(Main.class.getResource(path));
    }

    public static char getBuyType() {
        return buyType;
    }

    public static void setBuyType(char buyType) {
        Main.buyType = buyType;
    }

    public static Map getMapA() {
        return mapA;
    }

    public static void setMapA(Map mapA) {
        Main.mapA = mapA;
    }

    public static JLabel getCashLabel() {
        return cashLabel;
    }

    public static void setCashLabel(JLabel cashLabel) {
        Main.cashLabel = cashLabel;
    }

    public static Game getNewGame() {
        return newGame;
    }

    public static void setNewGame(Game newGame) {
        Main.newGame = newGame;
    }

    public static JButton getBuyGomphus() {
        return buyGomphus;
    }

    public static void setBuyGomphus(JButton buyGomphus) {
        Main.buyGomphus = buyGomphus;
    }

    //Wenn auf einen der Kauf Buttons geklickt wird
    public static void buyDefender(char type) {
        //Erstelle Preis
        int price = 0;
        //Setze BuyType damit in der Klasse Field darauf zugegriffen werden kann
        setBuyType(type);
        //Setze den Preis anhand des Defender Type
        switch (getBuyType()) {
            case 'G': // G wie Gomphus
                price = 100;
                break;
        }

        //Wenn Guthaben größer ist als der Preis, schalte mögliche Fields frei
        if (Game.getCash() >= price) {
            mapA.makeMapActive(true);
            System.out.println("Feld Aktiv");
        } else System.out.println("Du hast nicht genügend Knete!");
    }

    public static char[][] getA() {
        return a;
    }

    public static JLayeredPane getGamepanel() {
        return gamepanel;
    }
}
