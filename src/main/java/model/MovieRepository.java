package model;

import java.sql.*;

public class MovieRepository {
    private Connection connection = PostgresConnection.getConnection();
    private String query;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public MovieRepository(){
        try {
            query="create table if not exists c_Movie\n" +
                    "(\n" +
                    "    ID               serial primary key,\n" +
                    "    cinema_ID        integer,\n" +
                    "    movie_title      varchar(100),\n" +
                    "    movie_date       date,\n" +
                    "    movie_time       time,\n" +
                    "    movie_seatNumber integer,\n" +
                    "    movie_rate       decimal,\n" +
                    "    movie_state      boolean,\n" +
                    "    constraint Cinema_ID foreign key (cinema_ID) references c_Cinema (ID)\n" +
                    ")";
            connection.prepareStatement(query).execute();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //    add new movie
    public void addNewMovie(Movie movie) {
        try {
            query = "insert into c_Movie(cinema_ID, movie_title, movie_date, movie_time, movie_seatNumber, movie_rate, movie_state) \n" +
                    "VALUES (?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, movie.getCinemaID());
            preparedStatement.setString(2, movie.getTitle());
            preparedStatement.setDate(3, (Date) movie.getDate());
            preparedStatement.setTime(4, movie.getTime());
            preparedStatement.setInt(5, movie.getSeatNumber());
            preparedStatement.setDouble(6, movie.getRate());
            preparedStatement.setBoolean(7, movie.isState());
            preparedStatement.executeUpdate();
            System.out.println("operation was successful !");
            query="select max(ID) from c_Movie";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                MovieIncomeRepository movieIncomeRepository= new MovieIncomeRepository();
                movieIncomeRepository.addIncome(resultSet.getInt(1));
            }
//            connection.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("her");
        }
    }

    public void addNewMovie(Integer cinemaID, String movieTitle, Date movieDate, Time movieTime,
                            Integer movieSeatNumber, double movieRate) {
        addNewMovie(new Movie(cinemaID, movieTitle, movieDate, movieTime, movieSeatNumber, movieRate));
    }

    //    get seat number
    public Integer getSeatNumber(Integer id) {
        Integer number = 0;
        try {
            query = "select movie_seatNumber from c_Movie\n" +
                    "where ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                number = resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return number;
    }

    //    update seat number
    public void updateSeatNumber(Integer id, Integer booked) {
        try {
            query = "update c_Movie\n" +
                    "set movie_seatNumber = movie_seatNumber - ?\n" +
                    "where ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, booked);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
//            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //    get rate
    public double getMovieRate(Integer id) {
        double rate = 0;
        try {
            query = "select movie_rate from c_Movie\n" +
                    "where ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                rate = resultSet.getDouble(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return rate;
    }

    //    get state of movie
    public boolean getMovieState(Integer id) {
        boolean flag = false;
        try {
            query = "select movie_state from c_Movie\n" +
                    "where ID= ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getBoolean(1)) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return flag;
    }

    //    cancel display movie
    public String cancelDisplay(Integer id) {
        boolean flag = false;
        try {
            query = "update c_Movie\n" +
                    "set movie_state = false\n" +
                    "where movie_date > current_date and ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            flag = true;
            System.out.println("operation was successful !");
//            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        if (flag)
            return "cancel display was successful !";
        return "you can't cancel display !";
    }

    //    show all movies
    public void showAllMovies() {
        try {
            query = "select * from c_Movie\n" +
                    "where movie_date>= current_date and movie_state = true";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
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

        }
    }

    //    search movie by title
    public void searchMovieByTitle(String title) {
        try {
            query = "select * from c_Movie \n" +
                    "where movie_title like ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,"%"+ title.trim()+"%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                System.out.println( resultSet.getInt(1));
//                System.out.println(resultSet.getInt(2));
//                System.out.println(resultSet.getString(3));
//                System.out.println(resultSet.getDate(4));
//                System.out.println(resultSet.getTime(5));
//                System.out.println(resultSet.getInt(6));
//                System.out.println(resultSet.getDouble(7));
//                System.out.println(resultSet.getBoolean(8));
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
            System.out.println();
        }
    }

    //    search by date
    public void searchMovieByDate(Date date) {
        try {
            query = "select * from c_Movie\n" +
                    "where movie_date = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, date);
            resultSet = preparedStatement.executeQuery();
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
            System.out.println();
        }
    }

    //    search by title and date
    public void searchMovie(String title, Date date) {
        try {
            query = "select * from c_Movie\n" +
                    "where movie_title like '%?%' or movie_date = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setDate(2, date);
            resultSet = preparedStatement.executeQuery();
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
            System.out.println();
        }
    }


}
