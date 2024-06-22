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

    protected void initializeEventListeners() {
        btn_cancel.addActionListener(e -> dispose());
        btn_save.addActionListener(e -> save());
    }

    protected abstract boolean validateFields();

    protected abstract T setFields(T entity);

    protected boolean saveEntity(T entity) {
        System.out.println(currentEntity);
        System.out.println(currentEntity.getId());
        return currentEntity.getId() == 0 ? manager.save(entity) : manager.update(entity);
    }

    public void save() {
        if (validateFields()) {
            boolean result;
            try {
                result = saveEntity(setFields(currentEntity));
                if (result) {
                    Helper.showMessage("done");
                    dispose();
                } else {
                    Helper.showMessage("error");
                }
            } catch (Exception e) {
                Helper.showMessage(e.getMessage());
            }
        } else {
            Helper.showMessage("fill");
        }
    }
}
