package view;

import javax.swing.*;

import entity.AppUser;
import view.tabs.booking.BookingTabView;
import view.tabs.brand.BrandTabView;
import view.tabs.car.CarTabView;
import view.tabs.model.ModelTabView;
import view.tabs.newbooking.NewBookingTabView;

public class AdminView extends Layout {
    private JPanel container;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JTabbedPane tab_menu;
    private JButton btn_logout;

    private JPanel panel_newbooking;
    private JPanel panel_bookings;
    private JPanel panel_brands;
    private JPanel panel_models;
    private JPanel panel_cars;

    private NewBookingTabView newbooking_tab;
    private BookingTabView bookings_tab;
    private BrandTabView brands_tab;
    private ModelTabView models_tab;
    private CarTabView cars_tab;

    public AdminView(AppUser appUser) {

        this.setContentPane(container);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("admin");
        guiInitialize(700, 700);

        if (appUser == null) {
            dispose();
        }
        lbl_welcome.setText("Welcome " + appUser.getUsername());
    }

}
