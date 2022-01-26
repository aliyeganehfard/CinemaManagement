package model;

public class Cinema {
    private Integer ID;
    private String userName;
    private String password;
    private String cinemaName ;
    private String managerName;
    private String cinemaPhone;
    private String address;
    private double income;
    private boolean state;

    public Cinema(){}
    public Cinema(String userName, String password, String managerName ,String cinemaName,
                  String cinemaPhone, String address) {
        this.userName = userName;
        this.password = password;
        this.cinemaName = cinemaName;
        this.managerName = managerName;
        this.cinemaPhone = cinemaPhone;
        this.address = address;
        this.state=false;
    }

    public Cinema( Integer id, String userName, String password,String managerName, String cinemaName , String cinemaPhone, String address, double income, boolean state) {
        this.ID = id;
        this.userName = userName;
        this.password = password;
        this.cinemaName = cinemaName;
        this.managerName = managerName;
        this.cinemaPhone = cinemaPhone;
        this.address = address;
        this.income = income;
        this.state = state;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getCinemaPhone() {
        return cinemaPhone;
    }

    public void setCinemaPhone(String cinemaPhone) {
        this.cinemaPhone = cinemaPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "model.Cinema{" +
                "ID=" + ID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", cinemaName='" + cinemaName + '\'' +
                ", managerName='" + managerName + '\'' +
                ", cinemaPhone='" + cinemaPhone + '\'' +
                ", address='" + address + '\'' +
                ", income=" + income +
                ", state=" + state +
                '}';
    }
}
