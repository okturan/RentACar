package business;

import java.util.ArrayList;

import dao.BaseDao;
import entity.BaseEntity;

public abstract class BaseManager<E extends BaseEntity> {

    final BaseDao<E> baseDao;

    public BaseManager(BaseDao<E> baseDao) {
        this.baseDao = baseDao;
    }

    public boolean save(E entity) {
        if (getId(entity) != 0) {
            return false;
        }
        return baseDao.save(entity);
    }

    public boolean update(E entity) {
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

    public abstract ArrayList<Object[]> formatDataForTable(ArrayList<E> entities);

    public E getById(int id) {
        return baseDao.findById(id);
    }

    public ArrayList<E> findAll() {
        return baseDao.findAll();
    }

    public int getId(E entity) {
        return entity.getId();
    }
}
