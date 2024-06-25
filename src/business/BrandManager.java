package business;

import java.util.ArrayList;

import dao.BrandDao;
import entity.Brand;

public class BrandManager extends BaseManager<Brand, BrandDao> {

    public BrandManager() {
        super(new BrandDao());
    }


    public ArrayList<Object[]> formatDataForTable(ArrayList<Brand> entities) {
        return populateBrandRows(entities);
    }

    public ArrayList<Object[]> populateBrandRows(ArrayList<Brand> entities) {
        ArrayList<Object[]> brandRows = new ArrayList<>();
        for (Brand brand : entities) {
            Object[] rowObject = new Object[]{

                    brand.getId(),
                    brand.getName()
            };
            brandRows.add(rowObject);
        }
        return brandRows;
    }

}
