package dto;

import java.util.Date;

import entity.Brand;
import entity.Model;

public class FilterCriteria {

    private Date startDate;
    private Date endDate;
    private Model.VehicleType vehicleType;
    private Model.FuelType fuelType;
    private Model.TransmissionType transmissionType;
    private String Plate;
    private Brand brand;

    public FilterCriteria() {}

    public String getPlate() {
        return Plate;
    }

    public void setPlate(String plate) {
        Plate = plate;
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

    public Model.VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Model.VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Model.FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(Model.FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Model.TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(Model.TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
