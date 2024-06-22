package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import business.Manager;
import core.Helper;
import entity.AbstractEntity;

public abstract class TableHandler<T extends AbstractEntity> extends JPanel {

    private final String[] HEADERS;
    private final JTable table;
    private final Manager<T> manager;
    private final DefaultTableModel defaultTableModel;
    private final BaseView<T> view;
    private final JPopupMenu rightClickMenu;
    private int selectedRow = -1;

    public TableHandler(String[] headers, JTable table, Manager<T> manager, BaseView<T> view) {
        this.HEADERS = headers;
        this.table = table;
        this.manager = manager;
        this.view = view;
        this.defaultTableModel = new DefaultTableModel();
        this.table.setModel(defaultTableModel);
        this.rightClickMenu = new JPopupMenu();
    }

    public void initializeTable() {
        loadTable(manager.findAll());
        populateRightClickMenu();
        setUpTableMouseListener();
        setupWindowClosedListener();
    }

    private void setupWindowClosedListener() {
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                loadTable(manager.findAll());
            }
        });
    }

    void setUpTableMouseListener() {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    selectedRow = table.rowAtPoint(e.getPoint());
                    rightClickMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    protected void populateRightClickMenu() {
        addMenuItem("Edit", handleEdit());
        addMenuItem("Add", handleAdd());
        addMenuItem("Delete", e -> handleDelete());
    }

    protected void addMenuItem(String title, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.addActionListener(actionListener);
        rightClickMenu.add(menuItem);
    }

    // crud
    public ActionListener handleAdd() {
        return e -> view.initializeUIComponents(null);
    }

    ActionListener handleEdit() {
        return e -> {
            int selectedId = Integer.parseInt(this.getTable().getValueAt(getSelectedRow(), 0).toString());
            T entity = this.getManager().getById(selectedId);
            getView().initializeUIComponents(entity);
        };
    }

    protected void handleDelete() {
        if (Helper.confirm("sure")) {
            int selectedId = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
            if (manager.delete(selectedId)) {
                Helper.showMessage("done");
                loadTable(manager.findAll());
            } else {
                Helper.showMessage("error");
            }
        }
    }

    // table
    public void loadTable(ArrayList<T> entities) {
        defaultTableModel.setRowCount(0);
        defaultTableModel.setColumnIdentifiers(HEADERS);
        ArrayList<Object[]> entityRows = getManager().formatDataForTable(entities);
        fillRows(entityRows);
    }

    private void fillRows(ArrayList<Object[]> rows) {
        if (rows == null) {
            rows = new ArrayList<>();
        }
        for (Object[] row : rows) {
            defaultTableModel.addRow(row);
        }
        configureTableAppearance();
    }

    private void configureTableAppearance() {
        resizeColumnWidth();
        table.getTableHeader().setReorderingAllowed(true);
        table.setEnabled(false);
    }

    public void resizeColumnWidth() {
        final TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(35);
        columnModel.getColumn(0).setMinWidth(25);
        for (int column = 1; column < table.getColumnCount(); column++) {
            int width = 25; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) width = 300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    // getters
    public int getSelectedRow() {
        return selectedRow;
    }

    public JTable getTable() {
        return table;
    }

    public Manager<T> getManager() {
        return manager;
    }

    public JPopupMenu getRightClickMenu() {
        return rightClickMenu;
    }

    public BaseView<T> getView() {
        return view;
    }
}
