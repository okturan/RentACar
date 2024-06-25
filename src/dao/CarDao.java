package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Car;

public class CarDao extends BaseDao<Car> {

    public CarDao() {
        super("car");
    }

    @Override
    protected Car mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getInt("car_id"));
        car.setColor(resultSet.getString("color"));
        car.setKm(resultSet.getInt("km"));
        car.setPlate(resultSet.getString("plate"));
        car.setModel(new ModelDao().findById(resultSet.getInt("model_id")));
        return car;
    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, Car car) throws SQLException {
        preparedStatement.setInt(1, car.getModel().getId());
        preparedStatement.setString(2, car.getColor());
        preparedStatement.setInt(3, car.getKm());
        preparedStatement.setString(4, car.getPlate());
    }

}
