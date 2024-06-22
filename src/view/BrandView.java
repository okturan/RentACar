package view;

import javax.swing.*;

import business.BrandManager;
import core.Helper;
import entity.Brand;

public class BrandView extends BaseView<Brand> {
    private final BrandManager brandManager = new BrandManager();
    private JPanel container;
    private JLabel lbl_brand;
    private JLabel lbl_brand_name;
    private JTextField fld_brand_name;
    private JButton btn_save;
    private JPanel pnl_action;
    private JButton btn_cancel;

    public BrandView() {
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
    protected void setFields(Brand brand) {
        brand.setName(fld_brand_name.getText());
    }

    @Override
    protected boolean saveEntity(Brand brand) {
        return currentEntity == null ? brandManager.save(brand) : brandManager.update(brand);
    }

    @Override
    protected Brand createNewEntityInstance() {
        return new Brand();
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
