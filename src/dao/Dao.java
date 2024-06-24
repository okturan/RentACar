package dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import core.Db;
import entity.BaseEntity;

public abstract class Dao<T extends BaseEntity> {

    final Connection connection;
    private final String tableName;
    private ArrayList<String> columnNames;
    private String idColumnName;

    public Dao(String tableName) {
        this.connection = Db.getInstance();
        this.tableName = tableName;
        initializeColumnData();
    }

    protected abstract T mapResultSetToEntity(ResultSet resultSet) throws SQLException;

    protected void setParameters(PreparedStatement preparedStatement, T entity) throws SQLException {}

    protected String getTableName() {
        return tableName;
    }

    public Connection getConnection() {
        return connection;
    }

    protected int getId(T entity) {
        return entity.getId();
    }

    protected ArrayList<String> getColumnNames() {
        return columnNames;
    }

    protected String getIdColumnName() {
        return idColumnName;
    }

    private void initializeColumnData() {
        columnNames = new ArrayList<>();
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            try (ResultSet resultSet = metaData.getColumns(null, null, getTableName(), null)) {
                while (resultSet.next()) {
                    columnNames.add(resultSet.getString("COLUMN_NAME"));
                }
            }
            if (!columnNames.isEmpty()) {
                idColumnName = columnNames.remove(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (columnNames.isEmpty()) {
            throw new IllegalStateException("No columns found for table " + getTableName());
        }
    }

    public ArrayList<T> selectByQuery(String query) {
        System.out.println("Executing selectByQuery: " + query);
        ArrayList<T> entities = new ArrayList<>();
        try (Statement statement = this.connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    public boolean save(T entity) {
        String query = "INSERT INTO " +
                getTableName() +
                " (" +
                String.join(", ", getColumnNames()) +
                ") VALUES (" +
                getPlaceholders() +
                ")";
        return executeQuery(query, entity, false);
    }

    public boolean update(T entity) {
        String query = "UPDATE " +
                getTableName() +
                " SET " +
                getUpdateColumns() +
                " WHERE " +
                getIdColumnName() +
                " = ?";
        return executeQuery(query, entity, true);
    }

    public boolean delete(int id) {
        String query = "DELETE FROM " + getTableName() + " WHERE " + getIdColumnName() + " = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<T> findAll() {
        String query = "SELECT * FROM " + getTableName();
        return selectByQuery(query);
    }

    public T findById(int id) {
        String query = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + " = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToEntity(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public T findByColumns(Map<String, Object> columnValues) {
        T entity = null;

        String whereClause = columnValues.keySet().stream()
                .map(key -> key + " = ?")
                .collect(Collectors.joining(" AND "));

        String query = "SELECT * FROM " + getTableName() + " WHERE " + whereClause;

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);

            int index = 1;
            for (Map.Entry<String, Object> entry : columnValues.entrySet()) {
                preparedStatement.setObject(index++, entry.getValue());
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                entity = mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    private boolean executeQuery(String query, T entity, boolean isUpdate) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setParameters(preparedStatement, entity);
            if (isUpdate) {
                preparedStatement.setInt(getColumnNames().size() + 1, getId(entity));
            }
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String getPlaceholders() {
        return columnNames.stream()
                .map(col -> "?")
                .collect(Collectors.joining(", "));
    }

    private String getUpdateColumns() {
        return columnNames.stream()
                .map(col -> col + " = ?")
                .collect(Collectors.joining(", "));
    }

}
