package Main;

import bean.User;
import dao.inter.UserDaoInter;

public class Main {
    public static void main(String[] args) {


        UserDaoInter userDaoInter = Context.instanceUserDao();
        User dao = userDaoInter.getById(2);
        System.out.println(dao);
        dao.setName("Deli");
        userDaoInter.updateUser(dao);
        System.out.println(dao);
        System.out.println("-----------------");

        for (User users : userDaoInter.getAll()) {
            System.out.println(users);
        }
    }


}
