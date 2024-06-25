package view.tabs;

import javax.swing.*;

import business.BaseManager;
import core.Helper;
import entity.BaseEntity;
import view.BaseLayout;

public abstract class BaseUpdateView<
        E extends BaseEntity
        > extends BaseLayout {

    protected final BaseManager<E> manager;
    protected JButton btn_save;
    protected JButton btn_cancel;
    protected E currentEntity;

    protected BaseUpdateView(BaseManager<E> manager) {
        this.manager = manager;
    }

    public void setBtn_save(JButton btn_save) {
        this.btn_save = btn_save;
    }

    public void setBtn_cancel(JButton btn_cancel) {
        this.btn_cancel = btn_cancel;
    }

    public abstract void initializeUIComponents(E entity);

    protected abstract boolean validateFields();

    protected abstract E setFields(E entity);

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
