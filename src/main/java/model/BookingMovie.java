package model;

public class BookingMovie {
    private Integer ID;
    private Integer memberID;
    private Integer movieID;
    private Integer number;
    public BookingMovie(){}

    public BookingMovie(Integer memberID, Integer movieID, Integer number) {
        this.memberID = memberID;
        this.movieID = movieID;
        this.number = number;
    }

    public BookingMovie(Integer ID, Integer memberID, Integer movieID, Integer number) {
        this.ID = ID;
        this.memberID = memberID;
        this.movieID = movieID;
        this.number = number;
    }


    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getMemberID() {
        return memberID;
    }

    public void setMemberID(Integer memberID) {
        this.memberID = memberID;
    }

    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "model.BookingMovie{" +
                "ID=" + ID +
                ", memberID=" + memberID +
                ", movieID=" + movieID +
                ", number=" + number +
                '}';
    }

    //   TODO show memberID , movieID , cinemaID , cinemaName , title ,
//   TODO rete , seatNumber , address , date , time


}
