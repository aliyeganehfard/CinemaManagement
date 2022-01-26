

import Exceptions.*;
import model.*;
import controller.AdminService;
import controller.CinemaService;
import controller.MemberService;
import utils.*;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AdminRepository adminRepository = new AdminRepository();
        CinemaRepository cinemaRepository = new CinemaRepository();
        MemberRepository memberRepository = new MemberRepository();
        MovieRepository movieRepository = new MovieRepository();
        BookingMovieRepository bookingMovieRepository = new BookingMovieRepository();
        MovieIncomeRepository movieIncomeRepository = new MovieIncomeRepository();

        AdminService adminService = new AdminService();
        CinemaService cinemaService = new CinemaService();
        MemberService memberService = new MemberService();
        boolean state = true;
        boolean flag = false;
        boolean cinemaState = true;
        String commendLine;
        String[] commend;
        Integer ID = -1;
        Integer accountType = -1;
        System.out.println("note : if cinema singUp and then singIn in the program . cinema account can't do anything ");
        System.out.println("note : when current date passed of movie date . the movie properties hide");
        System.out.println("note : im write financial system in this program");
        while (state) {
            printSingUp();
            commendLine = scanner.nextLine();
            commend = commendLine.trim().split(" ");
            if (commend[0].equals("singUp")) {
                switch (commend[1]) {
                    case "admin":
                        printSingUn("admin");
                        commendLine = scanner.nextLine();
                        commend = commendLine.trim().split(" ");
                        try {
                            ExceptionHandling.isWord(commend[2]);
//                            adminRepository.singUp(new Admin(commend[0], commend[1], commend[2], commend[3]));
                            adminService.singUp(new Admin(commend[0], commend[1], commend[2], commend[3]));
                        } catch (WordException wordException) {
                            System.out.println("incorrect name!");
                        } catch (Exception a) {
                            System.out.println("wrong input");
                        }
                        break;
                    case "cinema":
                        printSingUn("cinema");
                        commendLine = scanner.nextLine();
                        commend = commendLine.split(" ");
                        try {
                            ExceptionHandling.isWord(commend[2]);
                            ExceptionHandling.isCinemaName(commend[3]);
                            ExceptionHandling.isPhoneNumber(commend[4]);
                            cinemaService.singUp(new Cinema(commend[0], commend[1], commend[2], commend[3], commend[4], commend[5]));
//                            cinemaRepository.singUp(new Cinema(commend[0], commend[1], commend[2], commend[3], commend[4], commend[5]));
                        } catch (CinemaNameException cinemaNameException) {
                            System.out.println("incorrect cinema name!");
                        } catch (WordException wordException) {
                            System.out.println("incorrect manager name!");
                        } catch (PhoneNumberException phoneNumberException) {
                            System.out.println("incorrect phone number!");
                        } catch (MoneyException moneyException) {
                            System.out.println("incorrect income!");
                        } catch (Exception a) {
                            System.out.println("wrong input");
                        }

                        break;
                    case "member":
                        printSingUn("member");
                        commendLine = scanner.nextLine();
                        commend = commendLine.trim().split(" ");
                        try {
                            ExceptionHandling.isWord(commend[2]);
                            ExceptionHandling.isPhoneNumber(commend[3]);
                            ExceptionHandling.isMoney(commend[5]);
//                            memberRepository.singUp(new Member(commend[0], commend[1], commend[2], commend[3], commend[4], Double.parseDouble(commend[5])));
                            memberService.singUp(new Member(commend[0], commend[1], commend[2], commend[3], commend[4], Double.parseDouble(commend[5])));
                        } catch (WordException wordException) {
                            System.out.println("incorrect name!");
                        } catch (PhoneNumberException phoneNumberException) {
                            System.out.println("incorrect phone number!");
                        } catch (DigitException digitException) {
                            System.out.println("incorrect wallet value!");
                        } catch (Exception a) {
                            System.out.println("wrong input");
                        }
                        break;
                    default:
                        System.out.println("wrong commend !");
                        break;
                }
            } else if (commend[0].equals("singIn")) {
                switch (commend[1]) {
                    case "admin":
                        printSingIn("admin");
                        commendLine = scanner.nextLine();
                        commend = commendLine.trim().split(" ");
//                        ID = adminRepository.singIn(commend[0], commend[1]);
                        try{
                            ID = adminService.singIn(commend[0], commend[1]);
                        }catch (Exception e){
                            System.out.println("wrong input!");
                        }
                        if (ID != -1) {
                            flag = true;
                            accountType = 1;
                            printAdminMenu();
                        } else {
                            System.out.println("wrong userName of password !");
                        }
                        break;
                    case "cinema":
                        printSingIn("cinema");
                        commendLine = scanner.nextLine();
                        commend = commendLine.trim().split(" ");
//                        ID = cinemaRepository.singIn(commend[0], commend[1]);
                        try {
                            ID = cinemaService.singIn(commend[0], commend[1]);
                        }catch (Exception e){
                            System.out.println("wrong input!");
                        }
                        if (ID != -1) {
                            flag = true;
                            accountType = 2;
                            if (cinemaRepository.isCinemaAllowed(ID)) {
                                printCinemasMenu();
                            } else {
                                System.out.println("please wait for check you request");
                                cinemaState = false;
                            }
                        } else {
                            System.out.println("wrong userName of password !");
                        }
                        break;
                    case "member":
                        printSingIn("member");
                        commendLine = scanner.nextLine();
                        commend = commendLine.trim().split(" ");
//                        ID = memberRepository.singIN(commend[0], commend[1]);
                        try{
                            ID = memberService.singIn(commend[0], commend[1]);
                        }catch (Exception e){
                            System.out.println("wrong input!");
                        }
                        if (ID != -1) {
                            accountType = 3;
                            flag = true;
                            printMemberMenu();
                        } else {
                            System.out.println("wrong userName of password !");
                        }
                        break;
                    default:
                        System.out.println("wrong commend ! ");
                        break;
                }
            } else {
                System.out.println("wrong input ! ");
            }
            while (flag) {
                switch (accountType) {
                    case 1:
//                      admin
                        System.out.print("commend : ");
                        commendLine = scanner.nextLine();
                        commend = commendLine.trim().split(" ");
                        switch (commend[0]) {
                            case "showMovies":
//                                movieRepository.showAllMovies();
                                cinemaService.showAllMovies();
                                break;
                            case "showMembers":
//                                memberRepository.showAllMember();
                                memberService.showAllMember();
                                break;
                            case "showRequestCinemas":
                                cinemaRepository.cinemasRequest();
                                System.out.println("accept cinemaID cinemaName");
                                System.out.println("reject cinemaID cinemaName");
                                System.out.println("back");
                                System.out.print("request cinemas commend : ");
                                commendLine = scanner.nextLine();
                                commend = commendLine.trim().split(" ");
                                try {
                                    ExceptionHandling.isDigit(commend[1]);
                                    ExceptionHandling.isWord(commend[2]);
                                    if (commend[0].equals("accept")) {
                                        cinemaRepository.checkRequest(Integer.valueOf(commend[1]), commend[2], CinemaState.ACCEPT);
                                    } else if (commend[0].equals("reject")) {
                                        cinemaRepository.checkRequest(Integer.valueOf(commend[1]), commend[2], CinemaState.REJECT);
                                    }
                                } catch (WordException wordException) {
                                    System.out.println("incorrect cinema name!");
                                } catch (DigitException digitException) {
                                    System.out.println("incorrect cinema id!");
                                } catch (Exception a) {
                                    System.out.println("wrong input");
                                }

                                break;
                            case "showIncomeMovies":
//                                movieIncomeRepository.showMoviesIncome();
                                cinemaService.showMoviesIncome();
                                break;
                            case "help":
                                printAdminMenu();
                                break;
                            case "logout":
                                flag = false;
                                break;
                            case "exit":
                                flag = false;
                                state = false;
                                break;
                            default:
                                System.out.println("wrong commend !");
                                break;
                        }
                        break;
                    case 2:
//                        cinema
                        if (cinemaRepository.isCinemaAllowed(ID)) {
                            System.out.print("commend : ");
                            commendLine = scanner.nextLine();
                            commend = commendLine.trim().split(" ");
                            switch (commend[0]) {
                                case "showProfile":
//                                    cinemaRepository.showProfile(ID);
                                    cinemaService.showProfile(ID);
                                    break;
                                case "showCinemaMovies":
//                                    cinemaRepository.showCinemaMovies(ID);
                                    cinemaService.showCinemaMovies(ID);
                                    break;
                                case "showIncomeMovies":
//                                    movieIncomeRepository.showMoviesIncome(ID);
                                    cinemaService.showMoviesIncome(ID);
                                    break;
                                case "addMovie":
                                    try {
                                        ExceptionHandling.isTitle(commend[1]);
                                        ExceptionHandling.isDate(commend[2]);
                                        Time time = Time.valueOf(commend[3]);
                                        ExceptionHandling.isMoney(commend[5]);
                                        ExceptionHandling.isDigit(commend[4]);
//                                        movieRepository.addNewMovie(new Movie(ID, commend[1], Date.valueOf(commend[2]),
//                                                time, Integer.valueOf(commend[4]), Double.parseDouble(commend[5])));
                                        cinemaService.addNewMovies(new Movie(ID, commend[1], Date.valueOf(commend[2]),
                                                time, Integer.valueOf(commend[4]), Double.parseDouble(commend[5])));
                                    } catch (TitleException titleException) {
                                        System.out.println("incorrect title!");
                                    } catch (DateException dateException) {
                                        System.out.println("incorrect date!");
                                    } catch (IllegalArgumentException time) {
                                        System.out.println("incorrect time!");
                                    } catch (DigitException digitException) {
                                        System.out.println("incorrect seat number!");
                                    } catch (MoneyException moneyException) {
                                        System.out.println("incorrect money!");
                                    } catch (Exception a) {
                                        System.out.println("wrong input");
                                    }

                                    break;
                                case "cancelMovie":
                                    try {
                                        ExceptionHandling.isDigit(commend[1]);
//                                        movieRepository.cancelDisplay(Integer.valueOf(commend[1]));
                                        cinemaService.cancelDisplay(commend[1]);
                                    } catch (DigitException digitException) {
                                        System.out.println("incorrect id!");
                                    } catch (Exception e){
                                        System.out.println("wrong input!");
                                    }
                                    break;
                                case "help":
                                    printCinemasMenu();
                                    break;
                                case "logout":
                                    flag = false;
                                    break;
                                case "exit":
                                    flag = false;
                                    state = false;
                                    break;
                                default:
                                    System.out.println("wrong commend !");
                                    break;
                            }
                        } else {
                            System.out.println("logout");
                            System.out.println("exit");
                            System.out.print("commend : ");
                            commendLine = scanner.nextLine();
                            commend = commendLine.trim().split(" ");
                            switch (commend[0]) {
                                case "logout":
                                    flag = false;
                                    break;
                                case "exit":
                                    flag = false;
                                    state = false;
                                    break;
                                default:
                                    System.out.println("wrong commend !");
                                    break;
                            }
                        }
                        break;
                    case 3:
//                              member
                        System.out.print("commend : ");
                        commendLine = scanner.nextLine();
                        commend = commendLine.trim().split(" ");
                        switch (commend[0]) {
                            case "showProfile":
//                                memberRepository.showProfile(ID);
                                memberService.showProfile(ID);
                                break;
                            case "chargeWallet":
                                try {
                                    ExceptionHandling.isMoney(commend[1]);
//                                    memberRepository.chargeWallet(ID, Double.parseDouble(commend[1]));
                                    memberService.chargeWallet(ID,commend[1]);
                                } catch (MoneyException moneyException) {
                                    System.out.println("incorrect money!");
                                } catch (Exception e){
                                    System.out.println("wrong input!");
                                }
                                break;
                            case "showWallet":
//                                System.out.println(memberRepository.getAmountWallet(ID));
                                memberService.getAmountWallet(ID);
                                break;
                            case "showMovies":
//                                movieRepository.showAllMovies();
                                cinemaService.showAllMovies();
                                break;
                            case "showBooked":
//                                bookingMovieRepository.bookedMovies(ID);
                                memberService.bookingMovie(ID);
                                break;
                            case "reserveMovie":
                                try {
                                    ExceptionHandling.isDigit(commend[1]);
                                    ExceptionHandling.isMoney(commend[2]);
//                                    bookingMovieRepository.bookingMovie(Integer.valueOf(commend[1]), ID, Integer.parseInt(commend[2]));
                                    memberService.bookingMovie(commend[1], ID,commend[2]);
                                } catch (MoneyException moneyException) {
                                    System.out.println("incorrect number!");
                                } catch (DigitException digitException) {
                                    System.out.println("incorrect movie id!");
                                } catch (Exception a) {
                                    System.out.println("wrong input");
                                }
                                break;
                            case "searchByTitle":
                                try {
                                    ExceptionHandling.isTitle(commend[1]);
//                                    movieRepository.searchMovieByTitle(commend[1]);
                                    cinemaService.searchMovieByTitle(commend[1]);
                                } catch (TitleException titleException) {
                                    System.out.println("incorrect title!");
                                } catch (Exception e){
                                    System.out.println("wrong input!");
                                }
                                break;
                            case "searchByDate":
                                try {
                                    ExceptionHandling.isDate(commend[1]);
//                                    movieRepository.searchMovieByDate(Date.valueOf(commend[1]));
                                    cinemaService.searchMovieBeDate(commend[1]);
                                } catch (DateException dateException) {
                                    System.out.println("incorrect date!");
                                } catch (Exception a) {
                                    System.out.println("wrong input");
                                }
                                break;
                            case "help":
                                printMemberMenu();
                                break;
                            case "logout":
                                flag = false;
                                break;
                            case "exit":
                                flag = false;
                                state = false;
                                break;
                            default:
                                System.out.println("wrong commend !");
                                break;
                        }
                        break;
                }
            }
        }


    }

    public static void printMenu() {
        System.out.println("singUp admin||cinema||member");
        System.out.println("singIn admin||cinema||member userName password");
    }

    public static void printSingUn(String text) {
        switch (text) {
            case "admin":
                System.out.println("userName password name email");
                break;
            case "cinema":
                System.out.println("userName password managerName cinemaName cinemaPhone cinemaAddress cinemaIncome");
                break;
            case "member":
                System.out.println("userName password name phone email wallet(double) ");
                break;
        }
        System.out.print("commend : ");
    }

    public static void printSingUp() {
        System.out.println("singUp admin || cinema || member");
        System.out.println("singIn admin || cinema || member");
        System.out.print("commend : ");
    }

    public static void printSingIn(String text) {
        switch (text) {
            case "admin":
                System.out.println("userName password");
                break;
            case "cinema":
                System.out.println("userName password");
                break;
            case "member":
                System.out.println("userName password");
                break;
        }
        System.out.print("commend : ");
    }

    public static void printAdminMenu() {
        System.out.println("showMovies");
        System.out.println("showMembers");
        System.out.println("showRequestCinemas");
        System.out.println("showIncomeMovies");
        System.out.println("help");
        System.out.println("logout");
        System.out.println("exit");
    }

    public static void printCinemasMenu() {
        System.out.println("showProfile");
        System.out.println("showCinemaMovies");
        System.out.println("showIncomeMovies");
        System.out.println("addMovie title date time seatNumber rate (example date must like 2022-12-22 && time must like 23:00:00)");
        System.out.println("cancelMovie movieID  (when you cancel movies => state of movies turn to false but if date of movie passed of courant date , state not change" +
                "when state of movies is false . cinema can see this properties but member can't see )");
        System.out.println("help");
        System.out.println("logout");
        System.out.println("exit");

    }

    public static void printMemberMenu() {
        System.out.println("showProfile");
        System.out.println("showWallet");
        System.out.println("showMovies");
        System.out.println("showBooked");
        System.out.println("chargeWallet amountMoney");
        System.out.println("reserveMovie movieID number");
        System.out.println("searchByTitle title");
        System.out.println("searchByDate date");
        System.out.println("help");
        System.out.println("logout");
        System.out.println("exit");
    }
}
