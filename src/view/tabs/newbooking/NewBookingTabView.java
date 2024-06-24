package view.tabs.newbooking;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;

import business.CarManager;
import entity.Car;
import entity.Model;
import view.tabs.car.CarUpdateView;
import view.tabs.tablehandlers.AvailableCarsTableHandler;

public class NewBookingTabView extends JPanel {
    private final AvailableCarsTableHandler availableCarsTableHandler;
    private JPanel panel_newbooking;
    private JPanel panel_filter_newbooking;
    private JTextField fld_newbooking_startdate;
    private JTextField fld_newbooking_enddate;
    private JComboBox<Model.TransmissionType> combo_newbooking_transmissiontype;
    private JComboBox<Model.FuelType> combo_newbooking_fueltype;
    private JComboBox<Model.VehicleType> combo_newbooking_vehicletype;
    private JButton button_newbooking_search;
    private JButton button_newbooking_clear;
    private JLabel lbl_newbooking_startdate;
    private JLabel lbl_newbooking_enddate;
    private JLabel lbl_newbooking_transmissiontype;
    private JLabel lbl_newbooking_fueltype;
    private JLabel lbl_newbooking_filter_vehicletype;
    private JScrollPane scroll_newbooking;
    private JTable table_newbooking;


    public NewBookingTabView() {
        this.add(panel_newbooking);

        availableCarsTableHandler = new AvailableCarsTableHandler(table_newbooking, new CarManager(), new CarUpdateView());
        availableCarsTableHandler.initializeTable();

        initAvailableCarsFilters();

        resetAvailableCarsFilters();

        filterAvailableCars();
    }

    // available cars filters
    private void initAvailableCarsFilters() {
        this.combo_newbooking_vehicletype.setModel(new DefaultComboBoxModel<>(Model.VehicleType.values()));
        this.combo_newbooking_fueltype.setModel(new DefaultComboBoxModel<>(Model.FuelType.values()));
        this.combo_newbooking_transmissiontype.setModel(new DefaultComboBoxModel<>(Model.TransmissionType.values()));

        String defaultStartDate = "2024-01-01";
        String defaultEndDate = "2024-12-31";

        this.fld_newbooking_startdate.setText(defaultStartDate);
        this.fld_newbooking_enddate.setText(defaultEndDate);

        button_newbooking_clear.addActionListener(e -> resetAvailableCarsFilters());
        button_newbooking_search.addActionListener(e -> filterAvailableCars());
    }

    private void filterAvailableCars() {
        Model.VehicleType selectedVehicleType = (Model.VehicleType) combo_newbooking_vehicletype.getSelectedItem();
        Model.FuelType selectedFuelType = (Model.FuelType) combo_newbooking_fueltype.getSelectedItem();
        Model.TransmissionType selectedTransmissionType = (Model.TransmissionType) combo_newbooking_transmissiontype.getSelectedItem();

        String startDateStr = fld_newbooking_startdate.getText();
        String endDateStr = fld_newbooking_enddate.getText();

        // Parse the text into Date objects
        Date startDate;
        Date endDate;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedStartDate = sdf.parse(startDateStr);
            java.util.Date parsedEndDate = sdf.parse(endDateStr);
            startDate = new Date(parsedStartDate.getTime());
            endDate = new Date(parsedEndDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        ArrayList<Car> filteredCars = new CarManager().filterCarsForBooking(selectedVehicleType, selectedFuelType, selectedTransmissionType, startDate, endDate);
        availableCarsTableHandler.loadTable(filteredCars);

    }

    private void resetAvailableCarsFilters() {
        this.combo_newbooking_vehicletype.setSelectedItem(null);
        this.combo_newbooking_fueltype.setSelectedItem(null);
        this.combo_newbooking_transmissiontype.setSelectedItem(null);
    }
}
