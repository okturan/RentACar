package view;

import javax.swing.*;

import business.Manager;
import entity.Model;

public class ModelTableHandler extends TableHandler<Model> {

    private static final String[] HEADERS = {
            "id",
            "Brand",
            "Model Name",
            "Year",
            "Transmission Type",
            "Fuel Type",
            "Vehicle Type"
    };

    public ModelTableHandler(JTable table, Manager<Model> manager, ModelView view) {
        super(HEADERS, table, manager, view);
    }

}
