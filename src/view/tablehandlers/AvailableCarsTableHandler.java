package view.tablehandlers;


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

    public void populateRightClickMenu() {
        addMenuItem("Add Booking", event -> {
            int selectedId = Integer.parseInt(getTable().getValueAt(getSelectedRow(), 0).toString());
            Car car = getManager().getById(selectedId);
            BookingView bookingView = new BookingView();
            Booking booking = new Booking();

            booking.setCar(car);
//            booking.setStartDate(Helper.parseDate(fld_startDate.getText()));
//            booking.setEndDate(Helper.parseDate(fld_endDate.getText()));

            bookingView.initializeUIComponents(booking);
            bookingView.disableFields();
        });
    }
}
