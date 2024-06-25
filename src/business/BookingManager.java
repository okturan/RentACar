package business;

import java.util.ArrayList;
import java.util.List;

import dao.BookingDao;
import dao.CarDao;
import entity.Booking;
import entity.Car;

public class BookingManager extends BaseManager<Booking, BookingDao> {

    public BookingManager() {
        super(new BookingDao());
    }

    public ArrayList<Object[]> formatDataForTable(ArrayList<Booking> entities) {
        return populateBookingRows(entities);
    }

    private ArrayList<Object[]> populateBookingRows(ArrayList<Booking> entities) {
        ArrayList<Object[]> bookingRows = new ArrayList<>();
        for (Booking booking : entities) {
            Object[] rowObject = new Object[]{
                    booking.getId(),
                    booking.getCar().getPlate(),
                    booking.getCar().getModel().getBrand(),
                    booking.getCar().getModel(),
                    booking.getCustomerName(),
                    booking.getCustomerMobileNo(),
                    booking.getCustomerEmail(),
                    booking.getCustomerIdNo(),
                    booking.getStartDate(),
                    booking.getEndDate(),
                    booking.getPrice()
            };
            bookingRows.add(rowObject);
        }
        return bookingRows;
    }

    // Method to load all unique license plates
    public List<String> loadLicensePlates() {
        List<Car> cars = new CarDao().findAll();
        List<String> licensePlates = new ArrayList<>();

        for (Car car : cars) {
            licensePlates.add(car.getPlate());
        }

        return licensePlates;
    }

    // Method to filter bookings by license plate
    public ArrayList<Booking> filterBookingsByLicensePlate(String plate) {
        if (plate == null || plate.isEmpty()) {
            return findAll();
        }

        // Construct the query
        String query = "SELECT b.* FROM public.booking b " +
                "JOIN public.car c ON b.car_id = c.car_id " +
                "WHERE c.plate = '" +
                plate +
                "' ORDER BY b.car_id";

        // Execute query using DAO
        return this.baseDao.selectByQuery(query);
    }
}
