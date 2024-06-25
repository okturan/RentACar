package view.tabs.car;

import javax.swing.*;

import business.CarManager;
import entity.Car;
import view.tabs.BaseTableHandler;

public class CarTableHandler extends BaseTableHandler<Car, CarManager, CarUpdateView> {

    private static final String[] HEADERS = {
            "id",
            "Model",
            "Color",
            "KM",
            "Plate"
    };

    public CarTableHandler(JTable table) {
        super(HEADERS, table, new CarManager());
        initializeTable();
    }

    @Override
    protected CarUpdateView createViewInstance() {
        return new CarUpdateView();
    }
}
