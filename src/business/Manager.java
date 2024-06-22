package business;

import java.util.ArrayList;

import dao.Dao;
import entity.AbstractEntity;

public abstract class Manager<T extends AbstractEntity> {

    final Dao<T> dao;

    public Manager(Dao<T> dao) {
        this.dao = dao;
    }

    public boolean save(T entity) {
        if (getId(entity) != 0) {
            core.Helper.showMessage("Erroneous entry");
            return false;
        }
        return dao.save(entity);
    }

    public boolean update(T entity) {
        if (dao.findById(getId(entity)) == null) {
            core.Helper.showMessage("Not found");
            return false;
        }
        return dao.update(entity);
    }

    public boolean delete(int id) {
        if (dao.findById(id) == null) {
            core.Helper.showMessage("Not found");
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
