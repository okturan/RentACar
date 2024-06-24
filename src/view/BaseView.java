package view;

import javax.swing.*;

import business.Manager;
import core.Helper;
import entity.BaseEntity;

public abstract class BaseView<T extends BaseEntity> extends Layout {
    protected final Manager<T> manager;
    protected JButton btn_save;
    protected JButton btn_cancel;
    protected T currentEntity;

    protected BaseView(Manager<T> manager) {this.manager = manager;}

    public void setBtn_save(JButton btn_save) {
        this.btn_save = btn_save;
    }

    public void setBtn_cancel(JButton btn_cancel) {
        this.btn_cancel = btn_cancel;
    }

    public abstract void initializeUIComponents(T entity);

    protected abstract boolean validateFields();

    protected abstract T setFields(T entity);

    protected void save() {
        if (validateFields()) {
            currentEntity = setFields(currentEntity);
            if (currentEntity.getId() == 0) {
                Helper.showMessage(manager.save(currentEntity) ? "Save Successful" : "Save Error: Erroneous entry");
            } else {
                Helper.showMessage(manager.update(currentEntity) ? "Update Successful" : "Update Error: Erroneous entry");
            }
            dispose();
        } else {
            Helper.showMessage("fill");
        }
    }

    protected void initializeEventListeners() {
        btn_cancel.addActionListener(e -> dispose());
        btn_save.addActionListener(e -> save());
    }
}
