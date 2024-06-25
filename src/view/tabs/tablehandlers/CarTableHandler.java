package view.tabs.tablehandlers;

import javax.swing.*;

import business.CarManager;
import entity.Car;
import view.tabs.car.CarUpdateView;

public class CarTableHandler extends BaseTableHandler<Car, CarManager, CarUpdateView> {

    private static final String[] HEADERS = {
            "id",
            "Model",
            "Color",
            "KM",
            "Plate"
    };

    public CarTableHandler(JTable table) {
        super(HEADERS, table, new CarManager(), new CarUpdateView());
    }

}
