package view.tabs.newbooking;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import business.CarManager;
import dto.FilterCriteria;
import entity.Booking;
import entity.Car;
import view.tabs.BaseTableHandler;
import view.tabs.booking.BookingUpdateView;

public class AvailableCarsTableHandler extends BaseTableHandler<Car, CarManager, BookingUpdateView> {

    private static final String[] HEADERS = {
            "id",
            "Car",
            "Color",
            "KM",
            "Plate"
    };

    private final FilterCriteria filterCriteria;

    public AvailableCarsTableHandler(JTable table, FilterCriteria filterCriteria) {
        super(HEADERS, table, new CarManager());
        this.filterCriteria = filterCriteria;
        initializeTable();
    }

    @Override
    public void getEntities() {
        filterAvailableCars();
    }

    @Override
    public void populateRightClickMenu() {
        addMenuItem("Add Booking", handleAdd());
    }

    @Override
    public ActionListener handleAdd() {
        return e -> {
            int selectedId = Integer.parseInt(getTable().getValueAt(getSelectedRow(), 0).toString());
            Car car = getManager().getById(selectedId);

            Booking booking = new Booking();
            booking.setCar(car);
            booking.setStartDate(filterCriteria.getStartDate());
            booking.setEndDate(filterCriteria.getEndDate());

            setView(createViewInstance());
            getView().initializeUIComponents(booking);
            getView().disableFields();
        };
    }

    @Override
    protected BookingUpdateView createViewInstance() {
        return new BookingUpdateView();
    }

    public void filterAvailableCars() {
        ArrayList<Car> filteredCars = getManager().filterCarsForBooking(
                filterCriteria.getVehicleType(),
                filterCriteria.getFuelType(),
                filterCriteria.getTransmissionType(),
                filterCriteria.getStartDate(),
                filterCriteria.getEndDate()
        );
        loadTable(filteredCars);
    }
}
