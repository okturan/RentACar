package business;

import java.util.ArrayList;

import dao.ModelDao;
import entity.Brand;
import entity.Model;

public class ModelManager extends BaseManager<Model, ModelDao> {

    public ModelManager() {
        super(new ModelDao());
    }

    public ArrayList<Object[]> formatDataForTable(ArrayList<Model> entities) {
        ArrayList<Object[]> modelRows = new ArrayList<>();
        for (Model model : entities) {
            Object[] rowObject = new Object[]{
                    model.getId(),
                    model.getBrand().getName(),
                    model.getName(),
                    model.getYear(),
                    model.getTransmissionType(),
                    model.getFuelType(),
                    model.getVehicleType()
            };
            modelRows.add(rowObject);
        }
        return modelRows;
    }

    public ArrayList<Model> filterModels(Brand brand, Model.VehicleType vehicleType, Model.FuelType fuelType, Model.TransmissionType transmissionType) {
        StringBuilder query = new StringBuilder("SELECT * FROM public.model WHERE 1=1");

        if (brand != null) {
            query.append(" AND brand_id = ").append(brand.getId());
        }
        if (vehicleType != null) {
            query.append(" AND vehicle_type = '").append(vehicleType.name()).append("'");
        }
        if (fuelType != null) {
            query.append(" AND fuel_type = '").append(fuelType.name()).append("'");
        }
        if (transmissionType != null) {
            query.append(" AND transmission_type = '").append(transmissionType.name()).append("'");
        }

        query.append(" ORDER BY model.name");

        return this.baseDao.selectByQuery(query.toString());
    }
}
