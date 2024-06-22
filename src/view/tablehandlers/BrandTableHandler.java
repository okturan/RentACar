package view.tablehandlers;

import javax.swing.*;

import business.Manager;
import entity.Brand;
import view.BrandView;

public class BrandTableHandler extends TableHandler<Brand> {

    private static final String[] HEADERS = {
            "id",
            "Brand"
    };

    public BrandTableHandler(JTable table, Manager<Brand> manager, BrandView view) {
        super(HEADERS, table, manager, view);
    }

}
