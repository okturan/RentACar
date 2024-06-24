package view.tabs.tablehandlers;

import javax.swing.*;

import business.Manager;
import entity.Booking;
import entity.Car;
import view.tabs.booking.BookingUpdateView;
import view.tabs.car.CarUpdateView;

public class AvailableCarsTableHandler extends TableHandler<Car> {

    private static final String[] HEADERS = {
            "id",
            "Car",
            "Color",
            "KM",
            "Plate"
    };

    public AvailableCarsTableHandler(JTable table, Manager<Car> manager, CarUpdateView view) {
        super(HEADERS, table, manager, view);
    }

    public void populateRightClickMenu() {
        addMenuItem("Add Booking", event -> {
            int selectedId = Integer.parseInt(getTable().getValueAt(getSelectedRow(), 0).toString());
            Car car = getManager().getById(selectedId);
            BookingUpdateView bookingUpdateView = new BookingUpdateView();
            Booking booking = new Booking();

            booking.setCar(car);
//            booking.setStartDate(Helper.parseDate(fld_startDate.getText()));
//            booking.setEndDate(Helper.parseDate(fld_endDate.getText()));

            bookingUpdateView.initializeUIComponents(booking);
            bookingUpdateView.disableFields();
        });
    }
}
