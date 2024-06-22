package view;

import java.util.List;

import javax.swing.*;

import business.BrandManager;
import business.ModelManager;
import core.Helper;
import entity.Brand;
import entity.Model;

public class ModelView extends BaseView<Model> {
    private final ModelManager modelManager = new ModelManager();
    private final BrandManager brandManager = new BrandManager();
    private JPanel container;
    private JLabel lbl_model;
    private JTextField fld_name;
    private JComboBox<Brand> combo_brand;
    private JTextField fld_year;
    private JComboBox<Model.VehicleType> combo_vehicletype;
    private JComboBox<Model.FuelType> combo_fuel;
    private JComboBox<Model.TransmissionType> combo_transmission;
    private JLabel lbl_brand;
    private JLabel lbl_name;
    private JLabel lbl_year;
    private JLabel lbl_transmission;
    private JLabel lbl_fuel;
    private JLabel lbl_vehicletype;
    private JButton btn_save;
    private JPanel pnl_action;
    private JButton btn_cancel;

    public ModelView() {
        this.add(container);
        this.setBtn_save(btn_save);
        this.setBtn_cancel(btn_cancel);
        loadBrands();
        loadEnumValues();
        initializeEventListeners();
    }

    private void loadBrands() {
        List<Brand> brands = brandManager.findAll();
        for (Brand brand : brands) {
            combo_brand.addItem(brand);
        }
    }

    private void loadEnumValues() {
        combo_vehicletype.setModel(new DefaultComboBoxModel<>(Model.VehicleType.values()));
        combo_fuel.setModel(new DefaultComboBoxModel<>(Model.FuelType.values()));
        combo_transmission.setModel(new DefaultComboBoxModel<>(Model.TransmissionType.values()));
    }

    @Override
    protected boolean validateFields() {
        return !Helper.isFieldListEmpty(fld_name, fld_year);
    }

    @Override
    protected void setFields(Model model) {
        model.setBrand((Brand) combo_brand.getSelectedItem());
        model.setName(fld_name.getText());
        model.setYear(fld_year.getText());
        model.setTransmissionType((Model.TransmissionType) combo_transmission.getSelectedItem());
        model.setFuelType((Model.FuelType) combo_fuel.getSelectedItem());
        model.setVehicleType((Model.VehicleType) combo_vehicletype.getSelectedItem());
    }

    @Override
    protected boolean saveEntity(Model model) {
        return currentEntity == null ? modelManager.save(model) : modelManager.update(model);
    }

    @Override
    protected Model createNewEntityInstance() {
        return new Model();
    }

    @Override
    public void initializeUIComponents(Model model) {
        this.guiInitialize(500, 500);
        this.currentEntity = model;

        fld_name.setText("");
        fld_year.setText("");

        if (model != null) {
            fld_name.setText(model.getName());
            combo_brand.setSelectedItem(model.getBrand());
            fld_year.setText(model.getYear());
            combo_transmission.setSelectedItem(model.getTransmissionType());
            combo_fuel.setSelectedItem(model.getFuelType());
            combo_vehicletype.setSelectedItem(model.getVehicleType());
        }
    }
}
