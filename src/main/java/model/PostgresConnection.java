package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresConnection {
     public static Connection getConnection(){
         Connection connection;
         try {
             Class.forName("org.postgresql.Driver");
             connection = DriverManager.getConnection
                     ("jdbc:postgresql://localhost:5432/postgres",
                             "postgres","ali.1381");
             return connection;
         }catch (Exception e){
             System.out.println("connection was failed");
         }
         return null;
     }
}
