package dto;

import entity.Model;
import java.util.Date;

public class FilterCriteria {

    private Date startDate;
    private Date endDate;
    private Model.VehicleType vehicleType;
    private Model.FuelType fuelType;
    private Model.TransmissionType transmissionType;

    public FilterCriteria() {}

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
}
