package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberRepository {
    private Connection connection = PostgresConnection.getConnection();
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    private String query;

    public MemberRepository(){
        try {
            query ="create table if not exists c_Member\n" +
                    "(\n" +
                    "    ID            serial primary key,\n" +
                    "    user_name     varchar(100),\n" +
                    "    password      varchar(100),\n" +
                    "    member_name   varchar(100),\n" +
                    "    member_phone  char(11),\n" +
                    "    member_email  varchar(100),\n" +
                    "    member_wallet decimal\n" +
                    ")";
            connection.prepareStatement(query).execute();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //    sing up
    public void singUp(Member member) {
        try {
            query = "insert into c_member (user_name, password, member_name, member_phone, member_email, member_wallet) \n" +
                    "values (?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, member.getUserName());
            preparedStatement.setString(2, member.getPassword());
            preparedStatement.setString(3, member.getName());
            preparedStatement.setString(4, member.getPhoneNumber());
            preparedStatement.setString(5, member.getEmail());
            preparedStatement.setDouble(6, member.getWallet());
            preparedStatement.executeUpdate();
            System.out.println("operation was successful !");
//            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void singUp(String userName, String password, String name,
                       String phoneNumber, String email, double wallet) {
        singUp(new Member(userName, password, name, phoneNumber, email, wallet));
    }

    //    sing in
    public Integer singIN(String userName, String password) {
        Integer flag = -1;
        try {
            query = "select * from c_member\n" +
                    "where user_name = ? and password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                flag = resultSet.getInt(1);
            }
//            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return flag;
    }

    //    add money to wallet
    public void chargeWallet(Integer id, double value) {
        try {
            query = "update c_member\n" +
                    "set member_wallet = member_wallet + ?\n" +
                    "where ID =?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, value);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("operation was successful !");
//            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //    get amount wallet
    public double getAmountWallet(Integer id) {
        double wallet = 0;
        try {
            query = "select member_wallet from c_Member \n" +
                    "where ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                wallet = resultSet.getInt(1);
            }
//            connection.close();
        } catch (Exception e) {
            System.out.println();
        }
        return wallet;
    }

    //    reduce the amount of wallet
    public void reduceAmountOfWallet(Integer id, double value) {
        try {
            query = "update c_member\n" +
                    "set member_wallet = member_wallet - ?\n" +
                    "where ID =?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, value);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
//            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    show all member
    public void showAllMember(){
        try {
            query ="select * from c_member";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Member member = new Member(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDouble(7)
                );
                System.out.println(member.toString());
            }
//            connection.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void showProfile(Integer id){
        try {
            query="select * from c_Member\n" +
                    "where ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Member member = new Member(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getDouble(7)
                );
                System.out.println(member.toString());
            }
//            connection.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }


}
