package view.tabs;

import javax.swing.*;

import business.BaseManager;
import core.Helper;
import dao.BaseDao;
import entity.BaseEntity;
import view.Layout;

public abstract class BaseUpdateView<
        T extends BaseEntity,
        M extends BaseManager<T, ? extends BaseDao<T>>
        > extends Layout{

    protected final M manager;
    protected JButton btn_save;
    protected JButton btn_cancel;
    protected T currentEntity;

    protected BaseUpdateView(M manager) {
        this.manager = manager;
    }

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
                Helper.showMessage(this.manager.save(currentEntity) ? "Save Successful" : "Save Error: Erroneous entry");
            } else {
                Helper.showMessage(this.manager.update(currentEntity) ? "Update Successful" : "Update Error: Erroneous entry");
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
