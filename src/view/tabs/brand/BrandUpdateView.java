package view.brand;

import javax.swing.*;

import business.BrandManager;
import core.Helper;
import entity.Brand;
import view.BaseView;

public class BrandUpdateView extends BaseView<Brand> {
    private JPanel container;
    private JLabel lbl_brand;
    private JLabel lbl_brand_name;
    private JTextField fld_brand_name;
    private JButton btn_save;
    private JPanel pnl_action;
    private JButton btn_cancel;

    public BrandUpdateView() {
        super(new BrandManager());
        this.add(container);
        this.setBtn_save(btn_save);
        this.setBtn_cancel(btn_cancel);
        initializeEventListeners();
    }

    @Override
    protected boolean validateFields() {
        return !Helper.isFieldEmpty(fld_brand_name);
    }

    @Override
    protected Brand setFields(Brand brand) {
        if (brand == null) {
            brand = new Brand();
        }
        brand.setName(fld_brand_name.getText());
        return brand;
    }

    @Override
    public void initializeUIComponents(Brand brand) {
        this.guiInitialize(500, 500);
        this.currentEntity = brand;

        fld_brand_name.setText(""); // TODO implement this for others

        if (brand != null) {
            fld_brand_name.setText(brand.getName());
        }
    }
}
