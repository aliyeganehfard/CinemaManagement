package controller;

import model.Admin;
import model.AdminRepository;
import model.PostgresConnection;

import java.sql.Connection;

public class AdminService {
    private Connection connection = PostgresConnection.getConnection();
    private AdminRepository adminRepository = new AdminRepository();

    public void singUp(Admin admin){
        adminRepository.singUp(admin);
    }

    public Integer singIn(String username , String password){
        return adminRepository.singIn(username , password);
    }
}
