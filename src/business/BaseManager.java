package business;

import java.util.ArrayList;

import dao.BaseDao;
import entity.BaseEntity;

public abstract class BaseManager<
        T extends BaseEntity,
        D extends BaseDao<T>
        > {

    final D baseDao;

    public BaseManager(D baseDao) {
        this.baseDao = baseDao;
    }

    public boolean save(T entity) {
        if (getId(entity) != 0) {
            return false;
        }
        return baseDao.save(entity);
    }

    public boolean update(T entity) {
        if (baseDao.findById(getId(entity)) == null) {
            return false;
        }
        return baseDao.update(entity);
    }

    public boolean delete(int id) {
        if (baseDao.findById(id) == null) {
            return false;
        }
        return baseDao.delete(id);
    }

    public T getById(int id) {
        return baseDao.findById(id);
    }

    public ArrayList<T> findAll() {
        return baseDao.findAll();
    }

    public int getId(T entity) {
        return entity.getId();
    }

    public abstract ArrayList<Object[]> formatDataForTable(ArrayList<T> entities);
}
