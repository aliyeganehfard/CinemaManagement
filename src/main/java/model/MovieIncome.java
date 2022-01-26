package model;

public class MovieIncome {
    private Integer ID;
    private Integer movieID;
    private double movieIncome;

    public MovieIncome() {
    }

    public MovieIncome(Integer movieID, double movieIncome) {
        this.movieID = movieID;
        this.movieIncome = movieIncome;
    }

    public MovieIncome(Integer ID, Integer movieID, double movieIncome) {
        this.ID = ID;
        this.movieID = movieID;
        this.movieIncome = movieIncome;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public double getMovieIncome() {
        return movieIncome;
    }

    public void setMovieIncome(double movieIncome) {
        this.movieIncome = movieIncome;
    }

    @Override
    public String toString() {
        return "model.MovieIncome{" +
                "ID=" + ID +
                ", movieID=" + movieID +
                ", movieIncome=" + movieIncome +
                '}';
    }
    //    TODO show movie income


}
