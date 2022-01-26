package model;

import utils.CinemaState;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CinemaRepository {
    private Connection connection = PostgresConnection.getConnection();
    PreparedStatement preparedStatement;
    private String query;
    public CinemaRepository(){
        try {
            query="create table if not exists c_Cinema\n" +
                    "(\n" +
                    "    ID             serial primary key,\n" +
                    "    user_name      varchar(100),\n" +
                    "    password       varchar(100),\n" +
                    "    manager_name   varchar(100),\n" +
                    "    cinema_name    varchar(100),\n" +
                    "    cinema_phone   char(11),\n" +
                    "    cinema_address varchar(200),\n" +
                    "    cinema_income  decimal,\n" +
                    "    cinema_state   boolean\n" +
                    ")";
            connection.prepareStatement(query).execute();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //    sing up
    public void singUp(Cinema cinema) {
        try {
            query = "insert into c_cinema (user_name, password, manager_name, cinema_name, cinema_phone, cinema_address, cinema_income, cinema_state) \n" +
                    "VALUES (?,?,?,?,?,?,?,?); ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cinema.getUserName());
            preparedStatement.setString(2, cinema.getPassword());
            preparedStatement.setString(3, cinema.getManagerName());
            preparedStatement.setString(4, cinema.getCinemaName());
            preparedStatement.setString(5, cinema.getCinemaPhone());
            preparedStatement.setString(6, cinema.getAddress());
            preparedStatement.setDouble(7, cinema.getIncome());
            preparedStatement.setBoolean(8, cinema.isState());
            preparedStatement.executeUpdate();
            System.out.println("operation was successful !");
//            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void singUp(String userName, String password, String cinemaName,
                       String managerName, String cinemaPhone, String address) {
        singUp(new Cinema(userName, password, cinemaName, managerName, cinemaPhone, address));
    }

    //    sing in
    public Integer singIn(String userName, String password) {
        Integer flag = -1;
        try {
            query = "select * from c_Cinema\n" +
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

    //    show cinemas that send request
    public void cinemasRequest() {
        try {
            query = "select * from c_Cinema " +
                    "where cinema_state=false";
            PreparedStatement PreparedStatement = connection.prepareStatement(query);
            ResultSet ResultSet = PreparedStatement.executeQuery();
            while (ResultSet.next()) {
                Cinema cinema = new Cinema(
                        ResultSet.getInt(1),
                        ResultSet.getString(2),
                        ResultSet.getString(3),
                        ResultSet.getString(4),
                        ResultSet.getString(5),
                        ResultSet.getString(6),
                        ResultSet.getString(7),
                        ResultSet.getInt(8),
                        ResultSet.getBoolean(9));
                System.out.println(cinema.toString());
            }
//            connection.close();
        } catch (Exception e) {
        }
    }

    //  check request . at this method cinemas request accept or reject
    public void checkRequest(Integer id, String cinemaName, CinemaState state) {
        if (state.equals(CinemaState.ACCEPT)) {
            acceptRequest(id, cinemaName);
            System.out.println("operation was successful !");
        } else if (state.equals(CinemaState.REJECT)) {
            rejectRequest(id, cinemaName);
            System.out.println("operation was successful !");
        } else {
            System.out.println("wrong input");
        }
    }

    //  accept cinema request
    private void acceptRequest(Integer id, String cinemaName) {
        try {
            query = "update c_Cinema \n" +
                    "set cinema_state=true\n" +
                    "where ID = ? and cinema_name = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, cinemaName);
            preparedStatement.executeUpdate();
//            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //    reject cinema request
    private void rejectRequest(Integer id, String cinemaName) {
        try {
            query = "delete from c_Cinema\n" +
                    "where ID = ? and cinema_name =?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, cinemaName);
            preparedStatement.executeUpdate();
//            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // check if cinema allowed for show movie or not
    public boolean isCinemaAllowed(Integer id) {
        try {
            query = "select * from c_Cinema\n" +
                    "where ID = ? and cinema_state = true";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
//            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //    show profile
    public void showProfile(Integer id) {
        try {
            query = "select * from c_Cinema\n" +
                    "where ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cinema cinema = new Cinema(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getDouble(8),
                        resultSet.getBoolean(9)
                );
                System.out.println(cinema.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //    show cinema movies
    public void showCinemaMovies(Integer id) {
        try {
            query = "select m.ID ,c.ID , m.movie_title , m.movie_date, m.movie_time , m.movie_seatNumber , m.movie_rate , m.movie_state\n" +
                    "from c_Cinema c , c_Movie m\n" +
                    "where c.ID in (?) and m.cinema_ID in (?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getDate(4),
                        resultSet.getTime(5),
                        resultSet.getInt(6),
                        resultSet.getDouble(7),
                        resultSet.getBoolean(8)
                );
                System.out.println(movie.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
