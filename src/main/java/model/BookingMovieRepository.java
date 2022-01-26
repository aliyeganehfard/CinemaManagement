package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookingMovieRepository {
    private Connection connection = PostgresConnection.getConnection();
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    private String query;

    public BookingMovieRepository(){
        try {
            query="create table if not exists c_BookingMovie\n" +
                    "(\n" +
                    "    ID        serial primary key,\n" +
                    "    member_ID integer,\n" +
                    "    movie_ID  integer,\n" +
                    "    number    integer,\n" +
                    "    constraint member_ID foreign key (member_ID) references c_Member (ID),\n" +
                    "    constraint movie_ID foreign key (movie_ID) references c_Movie (ID)\n" +
                    ")";
            connection.prepareStatement(query).execute();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    // booking movie
    public void bookingMovie(Integer movieID, Integer memberID, int number) {
        MemberRepository memberRepository = new MemberRepository();
        MovieRepository movieRepository = new MovieRepository();
        MovieIncomeRepository movieIncomeRepository = new MovieIncomeRepository();

        double rateOfMovie = movieRepository.getMovieRate(movieID);
        int seatNumber = movieRepository.getSeatNumber(movieID);
        double memberWallet = memberRepository.getAmountWallet(memberID);

        try {
            if (movieRepository.getMovieState(movieID)) {
                if (memberWallet >= rateOfMovie*number) {
                    if (seatNumber >= number) {
                        query = "insert into c_BookingMovie (member_ID, movie_ID, number) VALUES (?,?,?) ";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1, memberID);
                        preparedStatement.setInt(2, movieID);
                        preparedStatement.setInt(3, number);
                        preparedStatement.executeUpdate();

                        movieIncomeRepository.increaseIncome(movieID, rateOfMovie);
                        movieRepository.updateSeatNumber(movieID, number);
                        memberRepository.reduceAmountOfWallet(memberID, rateOfMovie*number);
                        System.out.println("booking movie was successful !");
                    } else {
                        System.out.println("we don't have enough space !");
                    }
                } else {
                    System.out.println("please charge your wallet and then try again !");
                }
            }else {
                System.out.println("The movie is not displayed !");
            }
//            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // show booked movies
    public void bookedMovies(Integer memberID) {
        try {
            query = "select c_BookingMovie.ID , cM.member_name , c.movie_title , c.movie_date , c.movie_time , c_BookingMovie.number from c_BookingMovie\n" +
                    "inner join c_Member cM on cM.ID = c_BookingMovie.member_ID\n" +
                    "inner join c_Movie c on c.ID = c_BookingMovie.movie_ID\n" +
                    "where cM.ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, memberID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.print("booked id : "+resultSet.getInt(1));
                System.out.print("\t member name : "+resultSet.getString(2));
                System.out.print("\t movie title : "+resultSet.getString(3));
                System.out.print("\t movie date : "+resultSet.getDate(4));
                System.out.print("\t movie time : "+resultSet.getTime(5));
                System.out.print("\t booked number : "+resultSet.getInt(6));
                System.out.println();
            }
//            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
