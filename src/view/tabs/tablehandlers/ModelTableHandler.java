package view.tabs.tablehandlers;

import javax.swing.*;

import business.Manager;
import entity.Model;
import view.tabs.model.ModelUpdateView;

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

    public ModelTableHandler(JTable table, Manager<Model> manager, ModelUpdateView view) {
        super(HEADERS, table, manager, view);
    }

}
