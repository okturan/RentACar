package view.tabs.tablehandlers;

import javax.swing.JTable;

import business.ModelManager;
import entity.Model;
import view.tabs.model.ModelUpdateView;

public class ModelTableHandler extends BaseTableHandler<Model, ModelManager, ModelUpdateView> {

    private static final String[] HEADERS = {
            "id",
            "Brand",
            "Model Name",
            "Year",
            "Transmission Type",
            "Fuel Type",
            "Vehicle Type"
    };

    public ModelTableHandler(JTable table) {
        super(HEADERS, table, new ModelManager(), new ModelUpdateView());
    }
}
