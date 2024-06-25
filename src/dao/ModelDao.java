package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Model;

public class ModelDao extends BaseDao<Model> {

    public ModelDao() {
        super("model");
    }

    @Override
    protected Model mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Model model = new Model();
        model.setId(resultSet.getInt(getIdColumnName()));
        model.setName(resultSet.getString("name"));
        model.setYear(resultSet.getString("year"));
        model.setTransmissionType(Model.TransmissionType.valueOf(resultSet.getString("transmission_type").toUpperCase()));
        model.setVehicleType(Model.VehicleType.valueOf(resultSet.getString("vehicle_type").toUpperCase()));
        model.setFuelType(Model.FuelType.valueOf(resultSet.getString("fuel_type").toUpperCase()));
        model.setBrand(new BrandDao().findById(resultSet.getInt("brand_id")));
        return model;
    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, Model model) throws SQLException {
        preparedStatement.setInt(1, model.getBrand().getId());
        preparedStatement.setString(2, model.getName());
        preparedStatement.setString(3, model.getYear());
        preparedStatement.setString(4, model.getTransmissionType().name());
        preparedStatement.setString(5, model.getFuelType().name());
        preparedStatement.setString(6, model.getVehicleType().name());
    }

}
