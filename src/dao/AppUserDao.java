package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import entity.AppUser;

public class AppUserDao extends Dao<AppUser> {

    public AppUserDao() {
        super("app_user");
    }

    public AppUser findByLogin(String username, String password) {
        Map<String, Object> columnValues = new HashMap<>();
        columnValues.put("username", username);
        columnValues.put("password", password);

        return findByColumns(columnValues);
    }

    @Override
    public AppUser mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        AppUser appUser = new AppUser();
        appUser.setId(resultSet.getInt("user_id"));
        appUser.setUsername(resultSet.getString("username"));
        appUser.setPassword(resultSet.getString("password"));
        appUser.setRole(resultSet.getString("role"));
        return appUser;
    }

}