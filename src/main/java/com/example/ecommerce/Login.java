package com.example.ecommerce;

import java.sql.ResultSet;

public class Login {

    public Customer customerLogin(String user,String pass){
     String loginQuery ="SELECT * FROM customer WHERE email='"+user+"' AND password ='"+pass+"'";
     DbConnection connect= new DbConnection();
        ResultSet rs= connect.getQueryTable(loginQuery);
        try{
            if(rs.next()){
                return new Customer(rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getString("mobile"));
            }



        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Login login = new Login();
        Customer customer = login.customerLogin("er.moiz2102@gmail.com","abc123");
        System.out.println("Welcome : "+customer.getName());
    }
}
