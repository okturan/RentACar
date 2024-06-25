package business;

import java.util.ArrayList;
import java.util.Date;

import dao.CarDao;
import entity.Car;
import entity.Model;

public class CarManager extends BaseManager<Car> {

    public CarManager() {
        super(new CarDao());
    }

    public ArrayList<Object[]> formatDataForTable(ArrayList<Car> entities) {
        ArrayList<Object[]> carRows = new ArrayList<>();
        for (Car car : entities) {
            Object[] rowObject = new Object[]{
                    car.getId(),
                    car.getModel(),
                    car.getColor(),
                    car.getKm(),
                    car.getPlate()
            };
            carRows.add(rowObject);
        }
        return carRows;
    }

    public ArrayList<Car> filterCarsForBooking(Model.VehicleType selectedVehicleType,
                                               Model.FuelType selectedFuelType,
                                               Model.TransmissionType selectedTransmissionType,
                                               Date startDate, Date endDate) {
        StringBuilder query = new StringBuilder("SELECT DISTINCT car.*, model.name FROM public.car");
        query.append(" JOIN public.model ON car.model_id = model.model_id");
        query.append(" LEFT JOIN public.booking ON car.car_id = booking.car_id");
        query.append(" WHERE 1=1");

        // Filter by selected vehicle type if specified
        if (selectedVehicleType != null) {
            query.append(" AND model.vehicle_type = '").append(selectedVehicleType.name()).append("'");
        }

        // Filter by selected fuel type if specified
        if (selectedFuelType != null) {
            query.append(" AND model.fuel_type = '").append(selectedFuelType.name()).append("'");
        }

        // Filter by selected transmission type if specified
        if (selectedTransmissionType != null) {
            query.append(" AND model.transmission_type = '").append(selectedTransmissionType.name()).append("'");
        }

        // Check if startDate and endDate are provided
        if (startDate != null && endDate != null) {
            query.append(" AND (car.car_id NOT IN (SELECT car_id FROM public.booking WHERE ");
            query.append(" NOT (booking.end_date < '").append(new java.sql.Date(startDate.getTime())).append("' OR ");
            query.append(" booking.start_date > '").append(new java.sql.Date(endDate.getTime())).append("')))");
        }

        // Order the results by model name
        query.append(" ORDER BY model.name");

        // Execute the query and return the results
        return this.baseDao.selectByQuery(query.toString());
    }

}
