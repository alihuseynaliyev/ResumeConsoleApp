package dao.impl;

import bean.User;
import dao.inter.AbstractDao;
import dao.inter.UserDaoInter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDaoInter {
    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();

        try (Connection connection = connection()) {
            Statement statement = connection.createStatement();
            statement.execute("select * from user");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                list.add(new User(id, name, surname, phone, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public User getById(int number) {
        User u = null;
        try (Connection connection = connection()) {
            Statement statement = connection.createStatement();
            statement.execute("select * from user where id =" + number);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                u = new User(id, name, surname, phone, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (u == null) {
            System.out.println("Duzgun id daxil ediniz");
            return u;
        } else
            return u;
    }

    @Override
    public boolean updateUser(User u) {
        try (Connection connection = connection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(
                    "update user set name=?, surname=?" +
                            ", phone=?,email=? where id=?");
            prepareStatement.setString(1, u.getName());
            prepareStatement.setString(2, u.getSurname());
            prepareStatement.setString(3, u.getPhone());
            prepareStatement.setString(4, u.getEmail());
            prepareStatement.setInt(5, u.getId());
            return prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection connection = connection()) {
            Statement statement = connection.createStatement();
            return statement.execute("delete from user where id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
