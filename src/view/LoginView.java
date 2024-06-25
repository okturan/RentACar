package view;

import javax.swing.*;

import business.AppUserManager;
import entity.AppUser;

public class LoginView extends BaseLayout {
    private final AppUserManager appUserManager;
    private JPanel container;
    private JLabel lbl_welcome;
    private JLabel lbl_welcome2;
    private JPanel w_top;
    private JPanel w_bottom;
    private JLabel lbl_userName;
    private JTextField fld_userName;
    private JLabel lbl_password;
    private JPasswordField fld_password;
    private JButton btn_login;

    public LoginView() {
        this.appUserManager = new AppUserManager();

        this.add(container);
        this.setTitle("Login");
        this.guiInitialize(350, 500);

        AppUser admin = new AppUser();
        AdminView adminView = new AdminView(admin);
        dispose();

//        btn_login.addActionListener(e -> {
//            JTextField[] checkFieldList = {this.fld_userName, this.fld_password};
//            if (Helper.isFieldListEmpty(checkFieldList)) {
//                Helper.showMessage("fill");
//            } else {
//                AppUser loginUser = this.appUserManager.findByLogin(this.fld_userName.getText(), this.fld_password.getText());
//                if (loginUser == null) {
//                    Helper.showMessage("notFound");
//                } else {
//                    AdminView adminView = new AdminView(loginUser);
//                    dispose();
//                }
//            }
//        });
    }

}