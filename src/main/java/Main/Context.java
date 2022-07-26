package Main;

import dao.impl.UserDaoImpl;
import dao.inter.UserDaoInter;

public class Context {
    public static UserDaoInter instanceUserDao() {
        return new UserDaoImpl();
    }
}
