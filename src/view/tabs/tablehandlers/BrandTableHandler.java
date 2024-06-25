package view.tabs.tablehandlers;

import javax.swing.*;

import business.BrandManager;
import entity.Brand;
import view.tabs.brand.BrandUpdateView;

public class BrandTableHandler extends BaseTableHandler<Brand, BrandManager, BrandUpdateView> {

    private static final String[] HEADERS = {
            "id",
            "Brand"
    };

    public BrandTableHandler(JTable table) {
        super(HEADERS, table, new BrandManager(), new BrandUpdateView());
    }
}
