package controller;

import model.*;

import java.sql.Connection;
import java.sql.Date;

public class CinemaService {
    private Connection connection = PostgresConnection.getConnection();
    private CinemaRepository cinemaRepository = new CinemaRepository();
    private MovieRepository movieRepository = new MovieRepository();
    private MovieIncomeRepository movieIncomeRepository = new MovieIncomeRepository();
    public void singUp(Cinema cinema){
        cinemaRepository.singUp(cinema);
    }

    public Integer singIn(String userName , String password){
        return cinemaRepository.singIn(userName,password);
    }

    public void showAllMovies(){
        movieRepository.showAllMovies();
    }

    public void showMoviesIncome(){
        movieIncomeRepository.showMoviesIncome();
    }

    public void showProfile(Integer id){
        cinemaRepository.showProfile(id);
    }

    public void showCinemaMovies(Integer id){
        cinemaRepository.showCinemaMovies(id);
    }

    public void showMoviesIncome(Integer id){
        movieIncomeRepository.showMoviesIncome(id);
    }

    public void addNewMovies(Movie movie){
        movieRepository.addNewMovie(movie);
    }
    public void cancelDisplay(String value){
        movieRepository.cancelDisplay(Integer.valueOf(value));
    }

    public void searchMovieByTitle(String value){
            movieRepository.searchMovieByTitle(value);
    }
    public void searchMovieBeDate(String date){
        movieRepository.searchMovieByDate(Date.valueOf(date));
    }
}
