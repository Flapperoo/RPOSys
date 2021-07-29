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
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms", user, pass);
            
        }catch(SQLException se)
	{
            System.out.println(se.getMessage());			
	}  
    }      
    public void createTable()
    {
        getConnection();
        try{
            myStatement=myConnection.createStatement();	
            String sql="create table cars (licensePlate varchar(100) NOT NULL, brand varchar(100) NOT NULL, model varchar(100) NOT NULL, price double NOT NULL, description varchar(1000) DEFAULT NULL PRIMARY KEY(licensePlate))";
            myStatement.executeUpdate(sql);
	}
	catch(SQLException se)
	{
            System.out.println(se.getMessage());			
	}
    }
    
    public void AddRow(Car bnew){
        getConnection();
        try{
            myStatement = myConnection.createStatement();    
            String sql = "INSERT INTO CARS VALUES ('"+ bnew.getLicensePlate()+"', '"+bnew.getBrand()+"', '"+bnew.getModel()+"', "+bnew.getPrice()+", '"+bnew.getDescription()+"')";
            myStatement.executeUpdate(sql);
            myConnection.commit();        
            myStatement.close();    
            JOptionPane.showMessageDialog(null, "New Rental Car Added Successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
            
            
        }catch (SQLException se){
            
            System.out.print(se.getMessage());
            
        }
    }
    
    public void EditRow(Car bnew, int oldCar)
    {
        getConnection();
        try{
            myStatement=myConnection.createStatement();	
            String qry="UPDATE CARS SET licensePlate = " + bnew.getLicensePlate()+ ", brand = '" + bnew.getBrand()+ "' , model = '" + bnew.getModel()+ "', price = '" + bnew.getPrice()+ "', description = '" + bnew.getDescription()+ "' WHERE licensePlate = " + oldCar;
            myStatement.executeUpdate(qry);
            myStatement.close();
            JOptionPane.showMessageDialog(null,"Updated successfully!");
	}
	catch(SQLException se)
	{
            System.out.println(se.getMessage());			
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
                cars.add(new Car(rs.getString("licensePlate"), rs.getString("brand"), rs.getString("model"), rs.getDouble("price"), rs.getString("description")));
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
