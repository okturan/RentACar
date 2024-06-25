package view.tabs.car;

import javax.swing.*;

public class CarTabView extends JPanel {

    private JPanel panel_cars;
    private JScrollPane scroll_cars;
    private JTable table_car;

    public CarTabView() {
        this.add(panel_cars);

        CarTableHandler carTableHandler = new CarTableHandler(table_car);
    }
}
