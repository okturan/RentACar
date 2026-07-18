import business.AppUserManager;
import core.Db;
import entity.AppUser;
import view.AdminView;
import view.LoginView;

import javax.swing.SwingUtilities;

public final class UiBootstrapTest {
    private UiBootstrapTest() {
    }

    public static void main(String[] args) throws Exception {
        AppUser admin = new AppUserManager().findByLogin("admin", "123");
        if (admin == null || !"admin".equals(admin.getRole())) {
            throw new AssertionError("The isolated sample database must contain the documented admin account.");
        }

        SwingUtilities.invokeAndWait(() -> {
            LoginView login = new LoginView();
            assertWindow(login, "Login");
            login.dispose();

            AdminView adminView = new AdminView(admin);
            assertWindow(adminView, "Admin Panel");
            adminView.dispose();
        });
        Db.getInstance().close();

        System.out.println("Instrumented Swing login and admin views opened against the sample database.");
    }

    private static void assertWindow(javax.swing.JFrame window, String expectedTitle) {
        if (!expectedTitle.equals(window.getTitle())) {
            throw new AssertionError("Expected window title " + expectedTitle + ", got " + window.getTitle());
        }
        if (window.getContentPane().getComponentCount() == 0) {
            throw new AssertionError(expectedTitle + " did not load its IntelliJ GUI Designer form.");
        }
    }
}
