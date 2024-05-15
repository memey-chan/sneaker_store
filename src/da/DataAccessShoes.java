/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import ds.Shoes;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ethan
 */
public class DataAccessShoes {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost/sneaker_store";
    String username = "root";
    String password = "";
    
    public String newShoes(String name, double price, int stock, int size, String gender) {
	String query = "insert into shoes (shoe_name, shoe_price, shoe_stock, shoe_size, gender) values (?, ?, ?, ?, ?)";
	try {
        	Class.forName(driver);
		PreparedStatement conn = DriverManager.getConnection(url, username, password).prepareStatement(query);
		conn.setString(1, name);
		conn.setDouble(2, price);
                conn.setInt(3, stock);
                conn.setInt(4, size);
                conn.setString(5, gender);
		conn.execute();
            } catch(Exception e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
            }
	return "true";
    }
    
    public String removeShoes(String name, int size, String gender) {
	String query = "delete from shoes where shoe_name=? and shoe_size=? and gender=?";
	try {
        	Class.forName(driver);
		PreparedStatement conn = DriverManager.getConnection(url, username, password).prepareStatement(query);
		conn.setString(1, name);
                conn.setInt(2, size);
                conn.setString(3, gender);
		conn.execute();
            } catch(Exception e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
            }
	return "true";
    }
    
    public String changeShoes(double price, int stock, String name, int size, String gender) {
	String query = "update shoes set shoe_price=?, shoe_stock=? where shoe_name=? and shoe_size=? and gender=?";
	try {
        	Class.forName(driver);
		PreparedStatement conn = DriverManager.getConnection(url, username, password).prepareStatement(query);
		conn.setDouble(1, price);
                conn.setInt(2, stock);
                conn.setString(3, name);
                conn.setInt(4, size);
                conn.setString(5, gender);
		conn.execute();
            } catch(Exception e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
            }
	return "true";
    }
    
    public String checkID(String name, int size, String gender) {
	String query = "select shoe_id from shoes where shoe_name=? and shoe_size=? and gender=?";
        String id;
        String condition;
	try {
        	Class.forName(driver);
		PreparedStatement conn = DriverManager.getConnection(url, username, password).prepareStatement(query);
		conn.setString(1, name);
                conn.setInt(2, size);
                conn.setString(3, gender);
		conn.execute();
                ResultSet rs = conn.executeQuery();
                rs.next();
                id = String.valueOf(rs.getInt("shoe_id"));
                if (id.isEmpty()){
                    condition = "false";
                }else{
                    condition = "true";
                }
            } catch(Exception e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
                condition = "false";
            }
	return condition;
    }
    
    public String[] getStockAndPrice(String name, int size, String gender) {
	String query = "select * from shoes where shoe_name=? and shoe_size=? and gender=?";
        String[] info = new String[2];
	try {
        	Class.forName(driver);
		PreparedStatement conn = DriverManager.getConnection(url, username, password).prepareStatement(query);
		conn.setString(1, name);
                conn.setInt(2, size);
                conn.setString(3, gender);
		conn.execute();
                ResultSet rs = conn.executeQuery();
                rs.next();
                double price = rs.getDouble("shoe_price");
                info[0] = String.valueOf(price);
                int stock = rs.getInt("shoe_stock");
                info[1] = String.valueOf(stock);
            } catch(Exception e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
            }
	return info;
    }
    
    public ArrayList <String> showNameDropdown(){
        ArrayList <String> name = new ArrayList();
        String query = "select shoe_name from shoes";
        try {
            Class.forName(driver);
            PreparedStatement conn = DriverManager.getConnection(url, username, password).prepareStatement(query);
            ResultSet rs = conn.executeQuery();
            while (rs.next()) {
                name.add(rs.getString("shoe_name"));
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return name;
    }
    
    public ArrayList <String> showSizeDropdown(){
        ArrayList <String> size = new ArrayList();
        String query = "select shoe_size from shoes";
        try {
            Class.forName(driver);
            PreparedStatement conn = DriverManager.getConnection(url, username, password).prepareStatement(query);
            ResultSet rs = conn.executeQuery();
            while (rs.next()) {
                size.add(String.valueOf(rs.getInt("shoe_size")));
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return size;
    }
    
    public ArrayList <Shoes> showSpecific(String searchQuery){
        ArrayList <Shoes> Shoe = new ArrayList();
        String query = "select * from shoes where shoe_name like ?";
        try {
            Class.forName(driver);
            PreparedStatement conn = DriverManager.getConnection(url, username, password).prepareStatement(query);
            conn.setString(1, searchQuery);
            ResultSet rs = conn.executeQuery();
            while (rs.next()) {
                Shoes shoes = new Shoes();
                shoes.setId(rs.getInt("shoe_id"));
                shoes.setName(rs.getString("shoe_name"));
                shoes.setPrice(rs.getDouble("shoe_price"));
                shoes.setStock(rs.getInt("shoe_stock"));
                shoes.setSize(rs.getInt("shoe_size"));
                shoes.setGender(rs.getString("gender"));
                
                Shoe.add(shoes);
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return Shoe;
    }
    
    public ArrayList <Shoes> showShoes(){
        ArrayList <Shoes> Shoe = new ArrayList();
        String query = "select * from shoes";
        try {
            Class.forName(driver);
            PreparedStatement conn = DriverManager.getConnection(url, username, password).prepareStatement(query);
            ResultSet rs = conn.executeQuery();
            while (rs.next()) {
                Shoes shoes = new Shoes();
                shoes.setId(rs.getInt("shoe_id"));
                shoes.setName(rs.getString("shoe_name"));
                shoes.setPrice(rs.getDouble("shoe_price"));
                shoes.setStock(rs.getInt("shoe_stock"));
                shoes.setSize(rs.getInt("shoe_size"));
                shoes.setGender(rs.getString("gender"));
                
                Shoe.add(shoes);
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return Shoe;
    }
}
