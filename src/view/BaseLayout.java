package view;

import java.awt.*;
import javax.swing.*;
import core.Helper;

public abstract class BaseLayout extends JFrame {

    public BaseLayout() throws HeadlessException {
    }

    public void guiInitialize(int xSize, int ySize) {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(xSize, ySize);

        // Get centered location on either the second screen or primary screen
        Point location = Helper.getCenteredLocation(xSize, ySize);
        this.setLocation(location);
    }
}
