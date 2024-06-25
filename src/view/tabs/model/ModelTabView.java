package view.tabs.model;

import java.util.List;

import javax.swing.*;

import business.BrandManager;
import dto.FilterCriteria;
import entity.Brand;
import entity.Model;

public class ModelTabView extends JPanel {

    private final ModelTableHandler modelTableHandler;
    private final FilterCriteria filterCriteria;

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

        this.filterCriteria = new FilterCriteria();
        initializeFilters();

        modelTableHandler = new ModelTableHandler(table_models, filterCriteria);
    }

    private void initializeFilters() {
        addActionListeners();
        initializeComboBoxes();
    }

    private void initializeComboBoxes() {
        combo_models_vehicletype.setModel(new DefaultComboBoxModel<>(Model.VehicleType.values()));
        combo_models_fueltype.setModel(new DefaultComboBoxModel<>(Model.FuelType.values()));
        combo_models_transmissiontype.setModel(new DefaultComboBoxModel<>(Model.TransmissionType.values()));
        new BrandManager().findAll().forEach(combo_models_brand::addItem);
        resetComboBoxes();
    }

    private void addActionListeners() {
        button_models_clear.addActionListener(e -> clearSearch());
        button_models_search.addActionListener(e -> searchModels());

        combo_models_vehicletype.addActionListener(
                e -> filterCriteria.setVehicleType((Model.VehicleType) combo_models_vehicletype.getSelectedItem()));
        combo_models_fueltype.addActionListener(
                e -> filterCriteria.setFuelType((Model.FuelType) combo_models_fueltype.getSelectedItem()));
        combo_models_transmissiontype.addActionListener(
                e -> filterCriteria.setTransmissionType((Model.TransmissionType) combo_models_transmissiontype.getSelectedItem()));
        combo_models_brand.addActionListener(
                e -> filterCriteria.setBrand((Brand) combo_models_brand.getSelectedItem()));
    }

    private void searchModels() {
        modelTableHandler.getEntities();
    }

    private void clearSearch() {
        resetComboBoxes();
        searchModels();
    }

    private void resetComboBoxes() {
        combo_models_vehicletype.setSelectedItem(null);
        combo_models_fueltype.setSelectedItem(null);
        combo_models_transmissiontype.setSelectedItem(null);
        combo_models_brand.setSelectedItem(null);
    }
}
