package com.example.ecommerce;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {

    private SimpleIntegerProperty id;

    private SimpleStringProperty name;

    private SimpleDoubleProperty price;

    public Product(int id, String name, double price) {
        this.id = new SimpleIntegerProperty(id);
        this.name =new  SimpleStringProperty(name);
        this.price =new SimpleDoubleProperty(price);
    }

    public static ObservableList<Product> getAllProd(){
        String selectAll="SELECT id, name ,price FROM product";
        return fetchdataDB(selectAll);
    }
    public static ObservableList<Product> fetchdataDB(String query){
        ObservableList<Product> data = FXCollections.observableArrayList();
        DbConnection conn = new DbConnection();
        try{
            ResultSet rs = conn.getQueryTable(query);
            while (rs.next()) {

                Product product = new Product(rs.getInt("id"),rs.getString("name"),rs.getDouble("price"));
                data.add(product);
            }
            return data;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public int getId() {
        return id.get();
    }



    public String getName() {
        return name.get();
    }



    public double getPrice() {
        return price.get();
    }


}
