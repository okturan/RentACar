package view.tabs.model;

import java.util.ArrayList;

import javax.swing.*;

import business.ModelManager;
import dto.FilterCriteria;
import entity.Model;
import view.tabs.BaseTableHandler;

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

    private final FilterCriteria filterCriteria;

    public ModelTableHandler(JTable table, FilterCriteria filterCriteria) {
        super(HEADERS, table, new ModelManager());
        this.filterCriteria = filterCriteria;
        initializeTable();
    }

    @Override
    public void getEntities() {
        filterModels();
    }

    @Override
    protected ModelUpdateView createViewInstance() {
        return new ModelUpdateView();
    }

    public void filterModels() {
        ArrayList<Model> filteredModels = getManager().filterModels(
                filterCriteria.getBrand(),
                filterCriteria.getVehicleType(),
                filterCriteria.getFuelType(),
                filterCriteria.getTransmissionType()
        );
        loadTable(filteredModels);
    }
}
