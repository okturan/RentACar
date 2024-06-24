package view;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import business.BookingManager;
import business.BrandManager;
import business.CarManager;
import core.Helper;
import entity.AppUser;
import entity.Booking;
import entity.Brand;
import entity.Car;
import entity.Model;
import view.tablehandlers.AvailableCarsTableHandler;
import view.tablehandlers.BookingTableHandler;
import view.tablehandlers.BrandTableHandler;
import view.tablehandlers.CarTableHandler;

public class AdminView extends JFrame {
    // Database Table Handlers
    private final BrandTableHandler brandTableHandler;
    private final CarTableHandler carTableHandler;
    private final BookingTableHandler bookingTableHandler;
    private final AvailableCarsTableHandler availableCarsTableHandler;

    // Main UI Components
    private JPanel container;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JTabbedPane tab_menu;
    private JButton btn_logout;

    // New Booking UI Components
    private JPanel panel_newbooking;
    private JScrollPane scroll_newbooking;
    private JTable table_newbooking;

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

    private JButton button_newbooking_search;
    private JButton button_newbooking_clear;

    // Bookings Management UI Components
    private JPanel panel_bookings;
    private JScrollPane scroll_bookings;
    private JTable table_bookings;

    private JPanel panel_filter_booking;
    private JLabel lbl_booking_plate;
    private JComboBox<String> combo_booking_plates;

    private JButton button_booking_search;
    private JButton button_booking_clear;

    // Brands Management UI Components
    private JScrollPane scroll_brands;
    private JTable table_brands;
    private JPanel panel_brands;

    // Models Management UI Components
    private JPanel panel_models;

    // Cars Management UI Components
    private JPanel panel_cars;
    private JScrollPane scroll_cars;
    private JTable table_car;
    private ModelTabView models_tab;
    private JPanel test_panel;

    public AdminView(AppUser appUser) {
        bookingTableHandler = new BookingTableHandler(table_bookings, new BookingManager(), new BookingView());
        carTableHandler = new CarTableHandler(table_car, new CarManager(), new CarView());
        brandTableHandler = new BrandTableHandler(table_brands, new BrandManager(), new BrandView());
        availableCarsTableHandler = new AvailableCarsTableHandler(table_newbooking, new CarManager(), new CarView());

        this.setContentPane(container);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("admin");
        guiInitialize(700, 700);

        if (appUser == null) {
            dispose();
        }

        lbl_welcome.setText("Welcome " + appUser.getUsername());

        bookingTableHandler.initializeTable();
        carTableHandler.initializeTable();
        brandTableHandler.initializeTable();
        availableCarsTableHandler.initializeTable();

        initAvailableCarsFilters();
        initBookingFilters();

        resetAvailableCarsFilters();
        resetBookingFilters();

        filterAvailableCars();
        filterBookings();

    }

    // booking filters
    private void initBookingFilters() {
        List<String> plates = new BookingManager().loadLicensePlates();

        combo_booking_plates.setModel(new DefaultComboBoxModel<>(plates.toArray(new String[0])));

        button_booking_search.addActionListener(e -> filterBookings());
        button_booking_clear.addActionListener(e -> resetBookingFilters());
    }

    private void filterBookings() {
        String selectedPlate = (String) combo_booking_plates.getSelectedItem();
        ArrayList<Booking> filteredBookings = new BookingManager().filterBookingsByLicensePlate(selectedPlate);
        bookingTableHandler.loadTable(filteredBookings);
    }

    private void resetBookingFilters() {
        combo_booking_plates.setSelectedItem(null);
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

    // helper
    public void guiInitialize(int xSize, int ySize) {
        this.setSize(xSize, ySize);
        this.setLocation(Helper.getLocation("x", xSize), Helper.getLocation("y", ySize));
    }

}
