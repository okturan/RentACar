package view.tablehandlers;

import javax.swing.*;

import business.Manager;
import entity.Car;
import view.car.CarUpdateView;

public class CarTableHandler extends TableHandler<Car> {

    private static final String[] HEADERS = {
            "id",
            "Model",
            "Color",
            "KM",
            "Plate"
    };

    public CarTableHandler(JTable table, Manager<Car> manager, CarUpdateView view) {
        super(HEADERS, table, manager, view);
    }

}
