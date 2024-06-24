package view.tabs.car;

import javax.swing.*;

import business.CarManager;
import view.tabs.tablehandlers.CarTableHandler;

public class CarTabView extends JPanel {
    private JPanel panel_cars;
    private JScrollPane scroll_cars;
    private JTable table_car;

    private final CarTableHandler carTableHandler;

    public CarTabView() {
        this.add(panel_cars);

        carTableHandler = new CarTableHandler(table_car, new CarManager(), new CarUpdateView());
        carTableHandler.initializeTable();
    }
}
