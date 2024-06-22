package view;

import javax.swing.*;

import business.Manager;
import entity.Brand;

public class BrandTableHandler extends TableHandler<Brand> {

    private static final String[] HEADERS = {
            "id",
            "Brand"
    };

    public BrandTableHandler(JTable table, Manager<Brand> manager, BrandView view) {
        super(HEADERS, table, manager, view);
    }

}
