package view.tabs.tablehandlers;

import javax.swing.*;

import business.Manager;
import entity.Brand;
import view.tabs.brand.BrandUpdateView;

public class BrandTableHandler extends TableHandler<Brand> {

    private static final String[] HEADERS = {
            "id",
            "Brand"
    };

    public BrandTableHandler(JTable table, Manager<Brand> manager, BrandUpdateView view) {
        super(HEADERS, table, manager, view);
    }

}
