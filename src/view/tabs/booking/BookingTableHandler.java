package view.tabs.booking;

import java.util.ArrayList;

import javax.swing.*;

import business.BookingManager;
import dto.FilterCriteria;
import entity.Booking;
import view.tabs.BaseTableHandler;

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

    private final FilterCriteria filterCriteria;

    public BookingTableHandler(JTable table, FilterCriteria filterCriteria) {
        super(HEADERS, table, new BookingManager());
        this.filterCriteria = filterCriteria;
        initializeTable();
    }

    @Override
    public void getEntities() {
        filterBookings();
    }

    @Override
    public void populateRightClickMenu() {
        addMenuItem("Edit", handleEdit());
        addMenuItem("Delete", e -> handleDelete());
    }

    @Override
    protected BookingUpdateView createViewInstance() {
        return new BookingUpdateView();
    }


    public void filterBookings() {
        ArrayList<Booking> filteredBookings = getManager().filterBookingsByLicensePlate(filterCriteria.getPlate());
        loadTable(filteredBookings);
    }

}
