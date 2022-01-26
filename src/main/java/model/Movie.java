package model;

import java.sql.Time;
import java.util.Date;

public class Movie {
    private Integer ID;
    private Integer cinemaID;
    private String title;
    private Date date ;
    private Time time;
    private Integer seatNumber;
    private double rate;
    private boolean state ;

    public Movie(Integer cinemaID, String title, Date date, Time time,
                 Integer seatNumber, double rate) {
        this.cinemaID = cinemaID;
        this.title = title;
        this.date = date;
        this.time = time;
        this.seatNumber = seatNumber;
        this.rate = rate;
        state = true;
    }

    public Movie(Integer ID, Integer cinemaID, String title, Date date, Time time, Integer seatNumber, double rate, boolean state) {
        this.ID = ID;
        this.cinemaID = cinemaID;
        this.title = title;
        this.date = date;
        this.time = time;
        this.seatNumber = seatNumber;
        this.rate = rate;
        this.state = state;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(Integer cinemaID) {
        this.cinemaID = cinemaID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "model.Movie{" +
                "ID=" + ID +
                ", cinemaID=" + cinemaID +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", seatNumber=" + seatNumber +
                ", rate=" + rate +
                ", state=" + state +
                '}';
    }
}
