package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        MushroomGui.gameStart();
    }

    public Image loadTexture(String path) throws IOException {
        return ImageIO.read(getClass().getResource(path));
    }
}
