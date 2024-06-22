package business;

import dao.AppUserDao;
import entity.AppUser;

public class AppUserManager {
    private final AppUserDao dao;

    public AppUserManager() {
        this.dao = new AppUserDao();
    }

    public AppUser findByLogin(String username, String password) {
        return this.dao.findByLogin(username, password);
    }
}
