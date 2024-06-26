package view.tabs.booking;

import java.util.List;

import javax.swing.*;

import business.BookingManager;
import business.CarManager;
import core.Helper;
import entity.Booking;
import entity.Car;
import view.tabs.BaseUpdateView;

public class BookingUpdateView extends BaseUpdateView<Booking> {
    private final CarManager carManager;
    private JPanel container;
    private JLabel lbl_booking;
    private JLabel lbl_car_id;
    private JLabel lbl_customer_name;
    private JLabel lbl_customer_id_no;
    private JLabel lbl_customer_mobile_no;
    private JLabel lbl_customer_email;
    private JLabel lbl_start_date;
    private JLabel lbl_end_date;
    private JLabel lbl_booking_case;
    private JLabel lbl_notes;
    private JLabel lbl_price;
    private JTextField fld_car_id;
    private JTextField fld_customer_name;
    private JTextField fld_customer_id_no;
    private JTextField fld_customer_mobile_no;
    private JTextField fld_customer_email;
    private JTextField fld_start_date;
    private JTextField fld_end_date;
    private JTextField fld_booking_case;
    private JTextField fld_notes;
    private JTextField fld_price;
    private JButton btn_save;
    private JPanel pnl_action;
    private JButton btn_cancel;
    private JComboBox<Car> combo_car;

    public BookingUpdateView() {
        super(new BookingManager());
        this.carManager = new CarManager();
        this.add(container);
        this.setBtn_save(btn_save);
        this.setBtn_cancel(btn_cancel);
        initializeEventListeners();
    }

    private void populateModelComboBox() {
        List<Car> cars = carManager.findAll();
        for (Car car : cars) {
            combo_car.addItem(car);
        }
    }

    public void disableFields() {
        combo_car.setEnabled(false);
        fld_start_date.setEnabled(false);
        fld_end_date.setEnabled(false);
    }

    @Override
    protected boolean validateFields() {
        return !Helper.isFieldListEmpty(
                fld_customer_name,
                fld_customer_id_no,
                fld_customer_mobile_no,
                fld_customer_email,
                fld_start_date,
                fld_end_date,
                fld_price
        );
    }

    @Override
    protected Booking setFields(Booking booking) throws NumberFormatException {
        if (booking == null) {
            booking = new Booking();
        }
        booking.setCar((Car) combo_car.getSelectedItem());
        booking.setCustomerName(fld_customer_name.getText());
        booking.setCustomerIdNo(fld_customer_id_no.getText());
        booking.setCustomerMobileNo(fld_customer_mobile_no.getText());
        booking.setCustomerEmail(fld_customer_email.getText());
        booking.setStartDate(Helper.parseDate(fld_start_date.getText()));
        booking.setEndDate(Helper.parseDate(fld_end_date.getText()));
        booking.setBookingCase(fld_booking_case.getText());
        booking.setNotes(fld_notes.getText());
        booking.setPrice(Integer.parseInt(fld_price.getText()));
        return booking;
    }

    @Override
    public void initializeUIComponents(Booking booking) {
        this.guiInitialize(500, 500);
        this.currentEntity = booking;
        populateModelComboBox();

        if (booking != null) {
            combo_car.setSelectedItem(booking.getCar());
            fld_customer_name.setText(booking.getCustomerName());
            fld_customer_id_no.setText(booking.getCustomerIdNo());
            fld_customer_mobile_no.setText(booking.getCustomerMobileNo());
            fld_customer_email.setText(booking.getCustomerEmail());
            fld_start_date.setText(Helper.formatDate(booking.getStartDate()));
            fld_end_date.setText(Helper.formatDate(booking.getEndDate()));
            fld_booking_case.setText(booking.getBookingCase());
            fld_notes.setText(booking.getNotes());
            fld_price.setText(String.valueOf(booking.getPrice()));
        }
    }
}
