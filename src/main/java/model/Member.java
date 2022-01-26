package model;

public class Member {
    private Integer ID;
    private String userName;
    private String password;
    private String name ;
    private String phoneNumber;
    private String email;
    private double wallet;
    public Member(){}
    public Member(String userName, String password, String name,
                  String phoneNumber, String email , double wallet) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.wallet = wallet;
    }

    public Member(Integer ID, String userName, String password, String name, String phoneNumber, String email, double wallet) {
        this.ID = ID;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.wallet = wallet;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }


    @Override
    public String toString() {
        return "model.Member{" +
                "ID=" + ID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", wallet=" + wallet +
                '}';
    }
}
