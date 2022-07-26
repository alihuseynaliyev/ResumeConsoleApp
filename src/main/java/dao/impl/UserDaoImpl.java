package dao.impl;

import bean.Country;
import bean.User;
import dao.inter.AbstractDao;
import dao.inter.UserDaoInter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDaoInter {
    private User getUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        int nationalityId = resultSet.getInt("nationality_id");
        int birthPlaceId = resultSet.getInt("birthplace_id");
        String nationalityStr = resultSet.getString("nationality");
        String birthPLaceStr = resultSet.getString("birthplace");
        Date birthDate = resultSet.getDate("birthdate");
        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthPlace = new Country(birthPlaceId, birthPLaceStr, null);

        return new User(id, name, surname, phone, email, birthDate, nationality, birthPlace);
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();

        try (Connection connection = connection()) {
            Statement statement = connection.createStatement();
            statement.execute("select " +
                    "u.*," +
                    "n.nationality, " +
                    "c.name AS birthplace " +
                    "from user u " +
                    "LEFT JOIN country n ON u.nationality_id = n.id " +
                    "LEFT JOIN country c ON u.birthplace_id = c.id ");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                User u = getUser(resultSet);
                list.add(u);
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
            statement.execute("SELECT" +
                    "u.*," +
                    "n.nationality," +
                    "c.name AS birthplace" +
                    "FROM" +
                    "USER u" +
                    "LEFT JOIN country n ON u.nationality_id = n.id" +
                    "LEFT JOIN country c ON u.birthplace_id = c.id where u.id = " + number);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                u = getUser(resultSet);
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
    public boolean addUser(User u) {
        try (Connection connection = connection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(
                    "insert into user(name,surname,phone,email) values (?,?,?,?)");
            prepareStatement.setString(1, u.getName());
            prepareStatement.setString(2, u.getSurname());
            prepareStatement.setString(3, u.getPhone());
            prepareStatement.setString(4, u.getEmail());
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
