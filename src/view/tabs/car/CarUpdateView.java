package view.car;

import java.util.List;

import javax.swing.*;

import business.CarManager;
import business.ModelManager;
import core.Helper;
import entity.Car;
import entity.Model;
import view.BaseView;

public class CarUpdateView extends BaseView<Car> {
    private final ModelManager modelManager = new ModelManager();
    private JPanel container;
    private JLabel lbl_car;
    private JTextField fld_color;
    private JComboBox<Model> combo_model;
    private JTextField fld_km;
    private JTextField fld_plate;
    private JLabel lbl_model;
    private JLabel lbl_color;
    private JLabel lbl_km;
    private JLabel lbl_plate;
    private JButton btn_save;
    private JPanel pnl_action;
    private JButton btn_cancel;

    public CarUpdateView() {
        super(new CarManager());
        this.add(container);
        this.setBtn_save(btn_save);
        this.setBtn_cancel(btn_cancel);
        initializeEventListeners();
    }

    private void populateModelComboBox() {
        List<Model> models = modelManager.findAll();
        for (Model model : models) {
            combo_model.addItem(model);
        }
    }

    @Override
    protected boolean validateFields() {
        return !Helper.isFieldListEmpty(fld_color, fld_km, fld_plate);
    }

    @Override
    protected Car setFields(Car car) {
        if (car == null) {
            car = new Car();
        }
        car.setModel((Model) combo_model.getSelectedItem());
        car.setColor(fld_color.getText());
        car.setKm(Integer.parseInt(fld_km.getText()));
        car.setPlate(fld_plate.getText());
        return car;
    }

    @Override
    public void initializeUIComponents(Car car) {
        guiInitialize(500, 500);
        this.currentEntity = car;
        populateModelComboBox();

        if (car != null) {
            fld_color.setText(car.getColor());
            combo_model.setSelectedItem(car.getModel());
            fld_km.setText(String.valueOf(car.getKm()));
            fld_plate.setText(car.getPlate());
        }
    }
}
