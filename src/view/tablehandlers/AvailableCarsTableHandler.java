package view.tablehandlers;

import java.text.ParseException;

import javax.swing.*;

import business.Manager;
import core.Helper;
import entity.Booking;
import entity.Car;
import view.BookingView;
import view.CarView;

public class AvailableCarsTableHandler extends TableHandler<Car> {

    private static final String[] HEADERS = {
            "id",
            "Car",
            "Color",
            "KM",
            "Plate"
    };

    public AvailableCarsTableHandler(JTable table, Manager<Car> manager, CarView view) {
        super(HEADERS, table, manager, view);
    }

    public void addRightClickContextMenu(JTextField startDate, JTextField endDate) {
        JMenuItem addBookingItem = new JMenuItem("Add Booking");
        getRightClickMenu().add(addBookingItem);

        addBookingItem.addActionListener(event -> {
            int selectedId = Integer.parseInt(this.getTable().getValueAt(getSelectedRow(), 0).toString());
            Car car = this.getManager().getById(selectedId);
            BookingView bookingView = new BookingView();
            Booking booking = new Booking();
            try {
                booking.setStartDate(Helper.parseDate(startDate.getText()));
                booking.setEndDate(Helper.parseDate(endDate.getText()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            bookingView.setVisible(true);
            bookingView.initializeUIComponents(booking);
            bookingView.disableFields(car);
        });

        setUpTableMouseListener();
    }
}
