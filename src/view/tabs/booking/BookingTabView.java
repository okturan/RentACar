package view.tabs.booking;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import business.BookingManager;
import entity.Booking;
import view.tabs.tablehandlers.BookingTableHandler;

public class BookingTabView extends JPanel {
    private final BookingTableHandler bookingTableHandler;
    private JPanel panel_bookings;

    private JPanel panel_filter_bookings;
    private JLabel lbl_booking_plate;
    private JComboBox<String> combo_booking_plates;

    private JButton button_booking_search;
    private JButton button_booking_clear;

    private JScrollPane scroll_bookings;
    private JTable table_bookings;

    public BookingTabView() {
        this.add(panel_bookings);

        bookingTableHandler = new BookingTableHandler(table_bookings);
        bookingTableHandler.initializeTable();

        initBookingFilters();
        resetBookingFilters();
        filterBookings();
    }

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
}
