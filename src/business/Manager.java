package business;

import java.util.ArrayList;

import dao.Dao;
import entity.BaseEntity;

public abstract class Manager<T extends BaseEntity> {

    final Dao<T> dao;

    public Manager(Dao<T> dao) {
        this.dao = dao;
    }

    public boolean save(T entity) {
        if (getId(entity) != 0) {
            return false;
        }
        return dao.save(entity);
    }

    public boolean update(T entity) {
        if (dao.findById(getId(entity)) == null) {
            return false;
        }
        return dao.update(entity);
    }

    public boolean delete(int id) {
        if (dao.findById(id) == null) {
            return false;
        }
        return dao.delete(id);
    }

    public T getById(int id) {
        return dao.findById(id);
    }

    public ArrayList<T> findAll() {
        return dao.findAll();
    }

    public int getId(T entity) {
        return entity.getId();
    }

    public abstract ArrayList<Object[]> formatDataForTable(ArrayList<T> entities);
}
