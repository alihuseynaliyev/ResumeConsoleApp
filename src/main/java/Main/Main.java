package Main;

import bean.User;
import dao.impl.UserDaoImpl;
import dao.inter.UserDaoInter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDaoInter userDaoInter = new UserDaoImpl();
        List<User> list = userDaoInter.getAll();
        for(User u:list){
            System.out.println(u);
        }
    }
}
