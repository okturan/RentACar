package view;

import java.awt.*;

import javax.swing.*;

import core.Helper;

public abstract class Layout extends JFrame {

    public Layout() throws HeadlessException {
    }

    public void guiInitialize(int xSize, int ySize) {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(xSize, ySize);
        this.setLocation(Helper.getLocation("x", xSize), Helper.getLocation("y", ySize));
    }
}
