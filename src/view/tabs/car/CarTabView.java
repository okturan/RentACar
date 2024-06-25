package view.tabs.car;

import javax.swing.*;

public class CarTabView extends JPanel {
    private final CarTableHandler carTableHandler;

    private JPanel panel_cars;
    private JScrollPane scroll_cars;
    private JTable table_car;


    public CarTabView() {
        this.add(panel_cars);

        carTableHandler = new CarTableHandler(table_car);
    }
}
