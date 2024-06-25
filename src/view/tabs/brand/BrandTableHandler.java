package view.tabs.brand;

import javax.swing.*;

import business.BrandManager;
import entity.Brand;
import view.tabs.BaseTableHandler;

public class BrandTableHandler extends BaseTableHandler<Brand, BrandManager, BrandUpdateView> {

    private static final String[] HEADERS = {
            "id",
            "Brand"
    };

    public BrandTableHandler(JTable table) {
        super(HEADERS, table, new BrandManager());
        initializeTable();
    }


    @Override
    protected BrandUpdateView createViewInstance() {
        return new BrandUpdateView();
    }
}
