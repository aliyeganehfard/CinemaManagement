package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MovieIncomeRepository {
    private Connection connection = PostgresConnection.getConnection();
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    private String query;

    public MovieIncomeRepository() {
        try {
                query="create table if not exists c_MovieIncome\n" +
                        "(\n" +
                        "    ID           serial primary key,\n" +
                        "    movie_id     integer,\n" +
                        "    movie_income decimal,\n" +
                        "    constraint movie_income_ID foreign key (movie_id) references c_Movie (ID)\n" +
                        ")";
                connection.prepareStatement(query).execute();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //    add movie income
    public void addIncome(Integer movieId){
        try {
            query= "insert into c_MovieIncome(movie_id, movie_income) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,movieId);
            preparedStatement.setDouble(2,0);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //    increase income of movie
    public void increaseIncome(Integer movieID, double rate) {
        try {
            query = "update c_MovieIncome \n" +
                    "set movie_income = movie_income + ?\n" +
                    "where movie_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, rate);
            preparedStatement.setInt(2, movieID);
            preparedStatement.executeUpdate();
//            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    show movies income

    public void showMoviesIncome() {
        try {
            query = "select cinema_name , movie_title, movie_income  from c_MovieIncome , c_Movie , c_Cinema\n" +
                    "where c_Cinema.ID=c_Movie.cinema_ID and c_Movie.ID = c_MovieIncome.movie_id\n" +
                    "order by movie_income desc";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.print("cinema name : "+resultSet.getString(1));
                System.out.print("\t movie title : "+resultSet.getString(2));
                System.out.print("\t income : "+resultSet.getDouble(3));
                System.out.println();
            }
//            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //    show cinema movies income
    public void showMoviesIncome(Integer cinemaID) {
        try {
            query = "select cinema_name, movie_title, movie_income\n" +
                    "from c_MovieIncome,\n" +
                    "     c_Movie,\n" +
                    "     c_Cinema\n" +
                    "where c_Cinema.ID = ? and c_Movie.cinema_ID = ? and c_Movie.ID = c_MovieIncome.movie_id\n" +
                    "order by movie_income desc";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cinemaID);
            preparedStatement.setInt(2, cinemaID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.print("cinema name : "+resultSet.getString(1));
                System.out.print("\t movie title : "+resultSet.getString(2));
                System.out.print("\t income : "+resultSet.getDouble(3));
                System.out.println();
            }
//            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
