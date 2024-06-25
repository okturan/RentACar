package view.tabs.tablehandlers;

import javax.swing.*;

import business.BookingManager;
import entity.Booking;
import view.tabs.booking.BookingUpdateView;

public class BookingTableHandler extends BaseTableHandler<Booking, BookingManager, BookingUpdateView> {

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

    public BookingTableHandler(JTable table) {
        super(HEADERS, table, new BookingManager(), new BookingUpdateView());
    }

    @Override
    public void populateRightClickMenu() {
        addMenuItem("Edit", handleEdit());
        addMenuItem("Delete", e -> handleDelete());
    }

}
