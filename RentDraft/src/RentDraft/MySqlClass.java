/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RentDraft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author paolo
 */
public class MySqlClass {
    Connection myConnection = null;
    Statement myStatement = null;
    ResultSet myRs = null;

    String user = "root";
    String pass = "root";

    public void getConnection()
    {
        try {
        // 1. Get a connection to database
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost/cms", user, pass);
        }catch(SQLException se)
	{
            System.out.println(se.getMessage());			
	}  
    }      
    
    public void AddRow(Car bnew){
        getConnection();
        try{
            myStatement = myConnection.createStatement();
            String query = "INSERT INTO cars VALUES("+ bnew.getLicensePlate()+", "+bnew.getBrand()+", "+bnew.getModel()+", "+bnew.getPrice()+", "+bnew.getDescription()+"')";
            myStatement.executeQuery(query);
            myStatement.close();
            JOptionPane.showMessageDialog(null,"New Rental Car Added Successfully");
        }catch (SQLException se){
            String message = "Cannot Add License Plate Number: "+bnew.getLicensePlate();
            JOptionPane.showMessageDialog(null, message);
        }
    }
        
    public ArrayList<Car> ShowTable()
    {
        ArrayList<Car> cars = new ArrayList<Car>();
        getConnection();
        try{
            myStatement = myConnection.createStatement();	
            String query = "SELECT * FROM CARS";
	    ResultSet rs = myStatement.executeQuery(query);
            
            while(rs.next())
            {
                cars.add(new Car(rs.getString("model"), rs.getString("brand"), rs.getString("description"), rs.getString("licensePlate"), rs.getDouble("price"), rs.getDate("dateRented"), rs.getDate("rentUntil")));
            }
            
            rs.close();
	    myStatement.close();				
        }
	catch(SQLException sex)
	{
            System.out.println(sex.getMessage());
	}
        return cars;
    }
}
