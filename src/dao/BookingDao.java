package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import business.CarManager;
import entity.Booking;

public class BookingDao extends Dao<Booking> {

    public BookingDao() {
        super("booking");
    }

    @Override
    protected Booking mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Booking booking = new Booking();
        booking.setId(resultSet.getInt("booking_id"));
        booking.setCustomerName(resultSet.getString("customer_name"));
        booking.setCustomerIdNo(resultSet.getString("customer_id_no"));
        booking.setCustomerMobileNo(resultSet.getString("customer_mobile_no"));
        booking.setCustomerEmail(resultSet.getString("customer_email"));
        booking.setStartDate(resultSet.getDate("start_date"));
        booking.setEndDate(resultSet.getDate("end_date"));
        booking.setBookingCase(resultSet.getString("booking_case"));
        booking.setNotes(resultSet.getString("notes"));
        booking.setPrice(resultSet.getInt("price"));
        booking.setCar(new CarManager().getById(resultSet.getInt("car_id")));
        return booking;
    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, Booking booking) throws SQLException {
        preparedStatement.setInt(1, booking.getCar().getId());
        preparedStatement.setString(2, booking.getCustomerName());
        preparedStatement.setString(3, booking.getCustomerIdNo());
        preparedStatement.setString(4, booking.getCustomerMobileNo());
        preparedStatement.setString(5, booking.getCustomerEmail());
        preparedStatement.setDate(6, new Date(booking.getStartDate().getTime()));
        preparedStatement.setDate(7, new Date(booking.getEndDate().getTime()));
        preparedStatement.setString(8, booking.getBookingCase());
        preparedStatement.setString(9, booking.getNotes());
        preparedStatement.setInt(10, booking.getPrice());
    }
}
