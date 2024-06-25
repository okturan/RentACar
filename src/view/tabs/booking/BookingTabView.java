package view.tabs.booking;

import java.util.List;

import javax.swing.*;

import business.BookingManager;
import dto.FilterCriteria;

public class BookingTabView extends JPanel {
    private final FilterCriteria filterCriteria;
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
        this.filterCriteria = new FilterCriteria();
        initializeFilters();

        bookingTableHandler = new BookingTableHandler(table_bookings, filterCriteria);
    }

    private void initializeFilters() {
        addActionListeners();
        initializeComboBoxes();
    }

    private void initializeComboBoxes() {
        List<String> plates = new BookingManager().loadLicensePlates();
        combo_booking_plates.setModel(new DefaultComboBoxModel<>(plates.toArray(new String[0])));
        resetComboBoxes();
    }

    private void addActionListeners() {
        button_booking_search.addActionListener(e -> searchBookings());
        button_booking_clear.addActionListener(e -> clearSearch());

        combo_booking_plates.addActionListener(
                e -> filterCriteria.setPlate((String) combo_booking_plates.getSelectedItem()));
    }

    private void searchBookings() {
        bookingTableHandler.getEntities();
    }

    private void clearSearch() {
        resetComboBoxes();
        searchBookings();
    }

    private void resetComboBoxes() {
        combo_booking_plates.setSelectedItem(null);
    }
}
