package view.tabs.newbooking;

import java.util.Date;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import core.Helper;
import dto.FilterCriteria;
import entity.Model;

public class NewBookingTabView extends JPanel {

    static final Date DEFAULT_START_DATE = Helper.parseDate("2023-01-01");
    static final Date DEFAULT_END_DATE = Helper.parseDate("2024-12-31");
    private final AvailableCarsTableHandler availableCarsTableHandler;
    private final FilterCriteria filterCriteria;

    private JPanel panel_newbooking;
    private JPanel panel_filter_newbooking;
    private JLabel lbl_newbooking_startdate;
    private JTextField fld_newbooking_startdate;
    private JLabel lbl_newbooking_enddate;
    private JTextField fld_newbooking_enddate;
    private JLabel lbl_newbooking_transmissiontype;
    private JComboBox<Model.TransmissionType> combo_newbooking_transmissiontype;
    private JLabel lbl_newbooking_fueltype;
    private JComboBox<Model.FuelType> combo_newbooking_fueltype;
    private JLabel lbl_newbooking_filter_vehicletype;
    private JComboBox<Model.VehicleType> combo_newbooking_vehicletype;
    private JButton button_newbooking_clear;
    private JButton button_newbooking_search;

    private JScrollPane scroll_newbooking;
    private JTable table_newbooking;

    public NewBookingTabView() {
        this.add(panel_newbooking);

        this.filterCriteria = new FilterCriteria();
        initializeFilters();

        availableCarsTableHandler = new AvailableCarsTableHandler(table_newbooking, filterCriteria);
    }

    private void initializeFilters() {
        addActionListeners();
        initializeComboBoxes();
        initializeDateFields();
    }

    private void initializeComboBoxes() {
        combo_newbooking_vehicletype.setModel(new DefaultComboBoxModel<>(Model.VehicleType.values()));
        combo_newbooking_fueltype.setModel(new DefaultComboBoxModel<>(Model.FuelType.values()));
        combo_newbooking_transmissiontype.setModel(new DefaultComboBoxModel<>(Model.TransmissionType.values()));
        resetComboBoxes();
    }

    private void addActionListeners() {
        button_newbooking_clear.addActionListener(e -> clearSearch());
        button_newbooking_search.addActionListener(e -> searchAvailableCars());

        combo_newbooking_vehicletype.addActionListener(
                e -> filterCriteria.setVehicleType((Model.VehicleType) combo_newbooking_vehicletype.getSelectedItem()));
        combo_newbooking_fueltype.addActionListener(
                e -> filterCriteria.setFuelType((Model.FuelType) combo_newbooking_fueltype.getSelectedItem()));
        combo_newbooking_transmissiontype.addActionListener(
                e -> filterCriteria.setTransmissionType((Model.TransmissionType) combo_newbooking_transmissiontype.getSelectedItem()));

        fld_newbooking_startdate.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {filterCriteria.setStartDate(Helper.parseDate(fld_newbooking_startdate.getText()));}
            public void removeUpdate(DocumentEvent e) {}
            public void changedUpdate(DocumentEvent e) {}
        });

        fld_newbooking_enddate.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {filterCriteria.setEndDate(Helper.parseDate(fld_newbooking_enddate.getText()));}
            public void removeUpdate(DocumentEvent e) {}
            public void changedUpdate(DocumentEvent e) {}
        });

    }

    private void initializeDateFields() {
        fld_newbooking_startdate.setText(Helper.formatDate(DEFAULT_START_DATE));
        fld_newbooking_enddate.setText(Helper.formatDate(DEFAULT_END_DATE));
    }

    private void searchAvailableCars() {
        availableCarsTableHandler.getEntities();
    }

    private void clearSearch() {
        resetComboBoxes();
        searchAvailableCars();
    }

    private void resetComboBoxes() {
        combo_newbooking_vehicletype.setSelectedItem(null);
        combo_newbooking_fueltype.setSelectedItem(null);
        combo_newbooking_transmissiontype.setSelectedItem(null);
    }
}
