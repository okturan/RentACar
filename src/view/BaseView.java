package view;

import javax.swing.*;

import core.Helper;
import entity.AbstractEntity;

public abstract class BaseView<T extends AbstractEntity> extends Layout {
    protected JButton btn_save;
    protected JButton btn_cancel;
    protected T currentEntity;

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

    protected abstract void setFields(T entity) throws Exception;

    protected abstract boolean saveEntity(T entity);

    public void save() {
        if (validateFields()) {
            boolean result;
            try {
                if (currentEntity == null) {
                    T newEntity = createNewEntityInstance();
                    setFields(newEntity);
                    result = saveEntity(newEntity);
                } else {
                    setFields(currentEntity);
                    result = saveEntity(currentEntity);
                }
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

    protected abstract T createNewEntityInstance();
}
