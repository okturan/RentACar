package entity;

import java.util.Date;

public class Booking extends AbstractEntity {
    private String customerName;
    private String customerIdNo;
    private String customerMobileNo;
    private String customerEmail;
    private Date startDate;
    private Date endDate;
    private String bookingCase;
    private String notes;
    private int price;
    private Car car;

    public Booking() {
    }

    public Booking(Car car, String customerName, String customerIdNo, String customerMobileNo, String customerEmail, Date startDate, Date endDate, String bookingCase, String notes, int price) {
        this.setCar(car);
        this.customerName = customerName;
        this.customerIdNo = customerIdNo;
        this.customerMobileNo = customerMobileNo;
        this.customerEmail = customerEmail;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bookingCase = bookingCase;
        this.notes = notes;
        this.price = price;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "customerName='" + customerName + '\'' +
                ", customerIdNo='" + customerIdNo + '\'' +
                ", customerMobileNo='" + customerMobileNo + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", bookingCase='" + bookingCase + '\'' +
                ", notes='" + notes + '\'' +
                ", price=" + price +
                ", car=" + car +
                '}';
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerIdNo() {
        return customerIdNo;
    }

    public void setCustomerIdNo(String customerIdNo) {
        this.customerIdNo = customerIdNo;
    }

    public String getCustomerMobileNo() {
        return customerMobileNo;
    }

    public void setCustomerMobileNo(String customerMobileNo) {
        this.customerMobileNo = customerMobileNo;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getBookingCase() {
        return bookingCase;
    }

    public void setBookingCase(String bookingCase) {
        this.bookingCase = bookingCase;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
