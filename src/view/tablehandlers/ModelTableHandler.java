package view.tablehandlers;

import javax.swing.*;

import business.Manager;
import entity.Model;
import view.ModelView;

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
