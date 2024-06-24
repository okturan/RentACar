package view.tablehandlers;

import javax.swing.*;

import business.Manager;
import entity.Booking;
import view.booking.BookingUpdateView;

public class BookingTableHandler extends TableHandler<Booking> {

    private static final String[] HEADERS = {
            "id",
            "License Plate",
            "Car Brand",
            "Car Model",
            "Customer Name",
            "Phone",
            "Email",
            "Personal ID",
            "Start Date",
            "End Date",
            "Price"
    };

    public BookingTableHandler(JTable table, Manager<Booking> manager, BookingUpdateView view) {
        super(HEADERS, table, manager, view);
    }

    @Override
    public void populateRightClickMenu() {
        addMenuItem("Edit", handleEdit());
        addMenuItem("Delete", e -> handleDelete());
    }

}
