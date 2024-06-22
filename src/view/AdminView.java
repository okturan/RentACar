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
import business.ModelManager;
import core.Helper;
import entity.AppUser;
import entity.Booking;
import entity.Brand;
import entity.Car;
import entity.Model;

public class AdminView extends JFrame {
    private final ModelTableHandler modelTableHandler;
    private final BrandTableHandler brandTableHandler;
    private final CarTableHandler carTableHandler;
    private final BookingTableHandler bookingTableHandler;
    private final AvailableCarsTableHandler availableCarsTableHandler;
    private JPanel container;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JTabbedPane tab_menu;
    private JButton btn_logout;
    private JScrollPane scroll_models;
    private JTable table_models;
    private JTable table_bookings;
    private JScrollPane scroll_bookings;
    private JScrollPane scroll_cars;
    private JScrollPane scroll_brands;
    private JTable table_car;
    private JTable table_brands;
    private JPanel panel_brands;
    private JPanel panel_models;
    private JPanel panel_cars;
    private JPanel panel_bookings;
    private JComboBox<Brand> combo_brand;
    private JComboBox<Model.TransmissionType> combo_transmission;
    private JComboBox<Model.FuelType> combo_fuel;
    private JButton button_search;
    private JButton button_clear;
    private JComboBox<Model.VehicleType> combo_vehicletype;
    private JLabel lbl_brand;
    private JLabel lbl_transmission;
    private JPanel panel_filter_models;
    private JLabel lbl_vehicletype;
    private JLabel lbl_fuel;
    private JPanel panel_newbooking;
    private JScrollPane scroll_newbooking;
    private JTable table_newbooking;
    private JTextField fld_startdate;
    private JTextField fld_enddate;
    private JComboBox<Model.TransmissionType> combo_filter_transmissiontype;
    private JComboBox<Model.FuelType> combo_filter_fueltype;
    private JComboBox<Model.VehicleType> combo_filter_vehicletype;
    private JButton button_booking_search;
    private JButton button_booking_clear;
    private JLabel lbl_startdate;
    private JLabel lbl_enddate;
    private JLabel lbl_transmissiontype;
    private JLabel lbl_fueltype;
    private JLabel lbl_filter_vehicletype;
    private JComboBox<String> combo_filter_plates;
    private JPanel panel_booking;
    private JButton button_filter_booking_search;
    private JButton button_filter_booking_clear;
    private JLabel lbl_filter_booking_plate;

    public AdminView(AppUser appUser) {
        modelTableHandler = new ModelTableHandler(table_models, new ModelManager(), new ModelView());
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

        modelTableHandler.initializeTable();
        bookingTableHandler.initializeTable();
        carTableHandler.initializeTable();
        brandTableHandler.initializeTable();

        initAvailableCarsFilters();
        initModelFilters();
        initBookingFilters();

        availableCarsTableHandler.addRightClickContextMenu(fld_startdate, fld_enddate);

        resetAvailableCarsFilters();
        filterAvailableCars();
        resetBookingFilters();
        filterBookings();

    }

    // model filters
    private void initModelFilters() {
        this.combo_vehicletype.setModel(new DefaultComboBoxModel<>(Model.VehicleType.values()));
        this.combo_fuel.setModel(new DefaultComboBoxModel<>(Model.FuelType.values()));
        this.combo_transmission.setModel(new DefaultComboBoxModel<>(Model.TransmissionType.values()));

        List<Brand> brands = new BrandManager().findAll();
        for (Brand brand : brands) {
            this.combo_brand.addItem(brand);
        }

        button_clear.addActionListener(e -> {
            resetModelFilters();
        });

        button_search.addActionListener(e -> {
            filterModels();
        });
    }

    private void filterModels() {
        Brand selectedBrand = (Brand) combo_brand.getSelectedItem();
        Model.VehicleType selectedVehicleType = (Model.VehicleType) combo_vehicletype.getSelectedItem();
        Model.FuelType selectedFuel = (Model.FuelType) combo_fuel.getSelectedItem();
        Model.TransmissionType selectedTransmission = (Model.TransmissionType) combo_transmission.getSelectedItem();

        ArrayList<Model> filteredModels = new ModelManager().filterModels(selectedBrand, selectedVehicleType, selectedFuel, selectedTransmission);
        modelTableHandler.loadTable(filteredModels);
    }

    private void resetModelFilters() {
        this.combo_vehicletype.setSelectedItem(null);
        this.combo_fuel.setSelectedItem(null);
        this.combo_transmission.setSelectedItem(null);
        this.combo_brand.setSelectedItem(null);
    }

    // booking filters
    private void initBookingFilters() {
        List<String> plates = new BookingManager().loadLicensePlates();

        combo_filter_plates.setModel(new DefaultComboBoxModel<>(plates.toArray(new String[0])));

        button_filter_booking_search.addActionListener(e -> {
            filterBookings();
        });

        button_filter_booking_clear.addActionListener(e -> {
            resetBookingFilters();
        });
    }

    private void filterBookings() {
        String selectedPlate = (String) combo_filter_plates.getSelectedItem();
        ArrayList<Booking> filteredBookings = new BookingManager().filterBookingsByLicensePlate(selectedPlate);
        bookingTableHandler.loadTable(filteredBookings);
    }

    private void resetBookingFilters() {
        combo_filter_plates.setSelectedItem(null);
    }

    // cars for booking filters
    private void initAvailableCarsFilters() {
        this.combo_filter_vehicletype.setModel(new DefaultComboBoxModel<>(Model.VehicleType.values()));
        this.combo_filter_fueltype.setModel(new DefaultComboBoxModel<>(Model.FuelType.values()));
        this.combo_filter_transmissiontype.setModel(new DefaultComboBoxModel<>(Model.TransmissionType.values()));

        String defaultStartDate = "2024-01-01"; // Example start date
        String defaultEndDate = "2024-12-31"; // Example end date

        this.fld_startdate.setText(defaultStartDate);
        this.fld_enddate.setText(defaultEndDate);

        button_booking_clear.addActionListener(e -> {
            resetAvailableCarsFilters();
        });

        button_booking_search.addActionListener(e -> {
            filterAvailableCars();
        });
    }

    private void filterAvailableCars() {
        Model.VehicleType selectedVehicleType = (Model.VehicleType) combo_filter_vehicletype.getSelectedItem();
        Model.FuelType selectedFuelType = (Model.FuelType) combo_filter_fueltype.getSelectedItem();
        Model.TransmissionType selectedTransmissionType = (Model.TransmissionType) combo_filter_transmissiontype.getSelectedItem();

        String startDateStr = fld_startdate.getText();
        String endDateStr = fld_enddate.getText();

        // Parse the text into Date objects
        Date startDate = null;
        Date endDate = null;
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
        this.combo_filter_vehicletype.setSelectedItem(null);
        this.combo_filter_fueltype.setSelectedItem(null);
        this.combo_filter_transmissiontype.setSelectedItem(null);
    }

    // helper
    public void guiInitialize(int xSize, int ySize) {
        this.setSize(xSize, ySize);
        this.setLocation(Helper.getLocation("x", xSize), Helper.getLocation("y", ySize));
    }
}
