package com.example.ecommerce;

import java.sql.*;
import java.util.Queue;

public class DbConnection {

    private   final String dbUrl="jdbc:mysql://localhost:3306/ecommerce";
    private  final String userName="root";
     private  final String password="1234";

     private Statement getStatement(){
         try{
             Connection connection = DriverManager.getConnection(dbUrl,userName,password);
             return  connection.createStatement();
     } catch (Exception e){
             e.printStackTrace();
         }
         return null;
     }
     public ResultSet getQueryTable(String Query){
         try{
             Statement statement = getStatement();
             return statement.executeQuery(Query);
         }catch(Exception e){
             e.printStackTrace();
         }
         return null;
     }
     public  int updateDb(String Query){
         try{
             Statement statement = getStatement();
             return statement.executeUpdate(Query);
         }catch(Exception e){
             e.printStackTrace();
         }
         return 0;

     }

    public static void main(String[] args) {
        DbConnection conn = new DbConnection();
        ResultSet rs= conn.getQueryTable("SELECT * FROM customer");
        if(rs!=null)
            System.out.println("Connection Successful");
        else
            System.out.println("Connection Failed.");
    }
}
