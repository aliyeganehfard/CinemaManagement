package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminRepository {
    private String query;
    private PreparedStatement preparedStatement;
    private Connection connection = PostgresConnection.getConnection();

    public AdminRepository() {
        try {
                query="create table if not exists c_Admin\n" +
                        "(\n" +
                        "    ID          serial primary key,\n" +
                        "    user_name   varchar(100),\n" +
                        "    password    varchar(40),\n" +
                        "    admin_name  varchar(100),\n" +
                        "    admin_email varchar(100)\n" +
                        ")";
                connection.prepareStatement(query).execute();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public Integer singIn(String userName, String password) {
        Integer flag = -1;
        try {
            query = "select * from c_Admin " +
                    "where user_name = ? and password = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Admin admin = new Admin(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5));
                System.out.println(admin.toString());

                flag = resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return flag;
    }

    public void singUp(Admin admin) {
        try{
            query="insert into c_Admin(user_name, password, admin_name, admin_email) " +
                    "values (?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,admin.getUserName());
            preparedStatement.setString(2,admin.getPassword());
            preparedStatement.setString(3,admin.getName());
            preparedStatement.setString(4,admin.getEmail());
            preparedStatement.executeUpdate();
            System.out.println("operation was successful !");
        }catch (Exception e){
            System.out.println(e);
        }
    }


    public void addCinema() {
    }
}
