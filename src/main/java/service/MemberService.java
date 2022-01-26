package service;

import model.BookingMovieRepository;
import model.Member;
import model.MemberRepository;
import model.PostgresConnection;

import java.sql.Connection;

public class MemberService {
    private Connection connection = PostgresConnection.getConnection();
    private MemberRepository memberRepository = new MemberRepository();
    private BookingMovieRepository bookingMovieRepository = new BookingMovieRepository();

    public void singUp(Member member) {
        memberRepository.singUp(member);
    }

    public Integer singIn(String userName, String password) {
        return memberRepository.singIN(userName, password);
    }

    public void showAllMember() {
        memberRepository.showAllMember();
    }

    public void showProfile(Integer id) {
        memberRepository.showProfile(id);
    }

    public void chargeWallet(Integer id, String value) {
        memberRepository.chargeWallet(id, Double.parseDouble(value));
    }

    public void getAmountWallet(Integer id) {
        System.out.println(memberRepository.getAmountWallet(id));
    }

    public void bookingMovie(Integer id) {
        bookingMovieRepository.bookedMovies(id);
    }
    public void bookingMovie(String number , Integer id , String value){
        bookingMovieRepository.bookingMovie(Integer.valueOf(number),id, Integer.parseInt(value));
    }

}
