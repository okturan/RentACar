package view.tabs.brand;

import javax.swing.*;

import business.BrandManager;
import view.tabs.tablehandlers.BrandTableHandler;

public class BrandTabView extends JPanel {
    private final BrandTableHandler brandTableHandler;
    private JPanel panel_brands;
    private JScrollPane scroll_brands;
    private JTable table_brands;

    public BrandTabView() {
        this.add(panel_brands);

        brandTableHandler = new BrandTableHandler(table_brands, new BrandManager(), new BrandUpdateView());
        brandTableHandler.initializeTable();
    }
}
