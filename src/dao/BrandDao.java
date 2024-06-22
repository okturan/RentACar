package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Brand;

public class BrandDao extends Dao<Brand> {

    public BrandDao() {
        super("brand");
    }

    @Override
    protected Brand mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Brand brand = new Brand();
        brand.setId(resultSet.getInt("brand_id"));
        brand.setName(resultSet.getString("name"));
        return brand;
    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, Brand brand) throws SQLException {
        preparedStatement.setString(1, brand.getName());
    }

}
