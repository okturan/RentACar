package business;

import java.util.ArrayList;

import dao.BrandDao;
import entity.Brand;

public class BrandManager extends BaseManager<Brand> {

    public BrandManager() {
        super(new BrandDao());
    }

    public ArrayList<Object[]> formatDataForTable(ArrayList<Brand> entities) {
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
