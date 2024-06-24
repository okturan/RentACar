package view.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import business.BrandManager;
import business.ModelManager;
import entity.Brand;
import entity.Model;
import view.tablehandlers.ModelTableHandler;

public class ModelTabView extends JPanel {
    private final ModelTableHandler modelTableHandler;
    private JPanel panel_models;

    private JPanel panel_filter_models;
    private JLabel lbl_models_brand;
    private JComboBox<Brand> combo_models_brand;
    private JLabel lbl_models_fueltype;
    private JComboBox<Model.FuelType> combo_models_fueltype;
    private JLabel lbl_models_transmissiontype;
    private JComboBox<Model.TransmissionType> combo_models_transmissiontype;
    private JLabel lbl_models_vehicletype;
    private JComboBox<Model.VehicleType> combo_models_vehicletype;

    private JButton button_models_search;
    private JButton button_models_clear;

    private JScrollPane scroll_models;
    private JTable table_models;

    public ModelTabView() {
        this.add(panel_models);

        modelTableHandler = new ModelTableHandler(table_models, new ModelManager(), new ModelUpdateView());
        modelTableHandler.initializeTable();

        initModelFilters();
        resetModelFilters();
        filterModels();
    }

    // model filters
    private void initModelFilters() {
        this.combo_models_vehicletype.setModel(new DefaultComboBoxModel<>(Model.VehicleType.values()));
        this.combo_models_fueltype.setModel(new DefaultComboBoxModel<>(Model.FuelType.values()));
        this.combo_models_transmissiontype.setModel(new DefaultComboBoxModel<>(Model.TransmissionType.values()));

        List<Brand> brands = new BrandManager().findAll();
        for (Brand brand : brands) {
            this.combo_models_brand.addItem(brand);
        }

        button_models_clear.addActionListener(e -> resetModelFilters());
        button_models_search.addActionListener(e -> filterModels());
    }

    private void filterModels() {
        Brand selectedBrand = (Brand) combo_models_brand.getSelectedItem();
        Model.VehicleType selectedVehicleType = (Model.VehicleType) combo_models_vehicletype.getSelectedItem();
        Model.FuelType selectedFuel = (Model.FuelType) combo_models_fueltype.getSelectedItem();
        Model.TransmissionType selectedTransmission = (Model.TransmissionType) combo_models_transmissiontype.getSelectedItem();

        ArrayList<Model> filteredModels = new ModelManager().filterModels(selectedBrand, selectedVehicleType, selectedFuel, selectedTransmission);
        modelTableHandler.loadTable(filteredModels);
    }

    private void resetModelFilters() {
        this.combo_models_vehicletype.setSelectedItem(null);
        this.combo_models_fueltype.setSelectedItem(null);
        this.combo_models_transmissiontype.setSelectedItem(null);
        this.combo_models_brand.setSelectedItem(null);
    }
}
