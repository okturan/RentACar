package view;

import javax.swing.*;

import business.Manager;
import entity.Car;

public class CarTableHandler extends TableHandler<Car> {

    private static final String[] HEADERS = {
            "id",
            "Model",
            "Color",
            "KM",
            "Plate"
    };

    public CarTableHandler(JTable table, Manager<Car> manager, CarView view) {
        super(HEADERS, table, manager, view);
    }

}
