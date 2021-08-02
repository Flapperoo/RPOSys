/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RentFinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author paolo
 */
public class MySqlClass {
    Connection myConnection = null;
    Statement myStatement = null;
    ResultSet myRs = null;
   
    public boolean getConnection()
    {
        try {
        // 1. Get a connection to database
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms", AccountManager.getInstance().getCurrentUser(), AccountManager.getInstance().getCurrentPass());
            return true;
        }catch(SQLException se)
	{
            System.out.println(se.getMessage()); 
            return false;			
	}  
    }      
    public void createTable()
    {
        getConnection();
        try{
            myStatement=myConnection.createStatement();	
            String sql="create table cars (licensePlate varchar(100) NOT NULL, brand varchar(100) NOT NULL, model varchar(100) "
                    + "NOT NULL, price double NOT NULL, description varchar(1000), dateRented date, rentUntil date DEFAULT NULL PRIMARY KEY(licensePlate))";
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
            String sql = "INSERT INTO CARS (licensePlate, brand, model, price, description) VALUES ('"+bnew.getLicensePlate()+"', '"+bnew.getBrand()+"', '"+bnew.getModel()+"', '"+bnew.getPrice()+"', '"+bnew.getDescription()+"')";
            myStatement.executeUpdate(sql);        
            myStatement.close();    
            JOptionPane.showMessageDialog(null, "New Rental Car Added Successfully");
            
            
        }catch (SQLException se){
            
            System.out.print(se.getMessage());
            JOptionPane.showMessageDialog(null, bnew.getLicensePlate()+" Already Exists");
            
        }
    }
    
    public void ReplaceStatus(Car bnew, String oldLicensePlate){
        getConnection();
        try{
            myStatement = myConnection.createStatement();    
            String sql = "UPDATE CARS SET dateRented = '"+bnew.getDateRented()+"', rentUntil = '"+bnew.getRentUntil()+"' WHERE licensePlate = '"+ oldLicensePlate+"'";
            
            myStatement.executeUpdate(sql);        
            myStatement.close();    
            JOptionPane.showMessageDialog(null, "Status Updated Successfully");
            
            
        }catch (SQLException se){
            
            System.out.print(se.getMessage());
            
        }
    }
    
    public void EditRow(Car bnew, String oldLicensePlate)
    {
        getConnection();
        try{
            myStatement=myConnection.createStatement();	
            String qry="UPDATE CARS SET licensePlate = '" + bnew.getLicensePlate()+ "', brand = '" + bnew.getBrand()+ "' ,"
                    + " model = '" + bnew.getModel()+ "', price = '" + bnew.getPrice()+ "', description = '" + bnew.getDescription()+ "' "
                    + "WHERE licensePlate = '" + oldLicensePlate + "'";
            myStatement.executeUpdate(qry);
            myStatement.close();
            JOptionPane.showMessageDialog(null,"Updated successfully!");
	}
	catch(SQLException se)
	{
            System.out.println(se.getMessage());			
	}
    }
        
    public ArrayList<Car> ShowTable(){
        ArrayList<Car> cars = new ArrayList<Car>();
        getConnection();
        try{
            myStatement = myConnection.createStatement();	
            String query = "SELECT * FROM CARS";
	    ResultSet rs = myStatement.executeQuery(query);
            
            while(rs.next())
            {
                cars.add(new Car(rs.getString("licensePlate"), rs.getString("brand"), rs.getString("model"), rs.getDouble("price"), rs.getString("description"), rs.getString("dateRented"), rs.getString("rentUntil")));
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
    
    private ArrayList<String> returnDistinctBrands(ResultSet rs){
        
        ArrayList<String> carBrands = new ArrayList<String>();
        try{            
            while(rs.next())
            {
                String carBrand = rs.getString("brand");
                if(!carBrands.contains(carBrand))
                    carBrands.add(carBrand);
            }            
            rs.close();				
        }
	catch(SQLException sex)
	{
            System.out.println(sex.getMessage());
	}
        return carBrands;
    }
    
     public void ShowBrandList(JComboBox jComboBox){
        getConnection();
        try{
            myStatement = myConnection.createStatement();	
            String query = "SELECT BRAND FROM CARS";
	    ResultSet rs = myStatement.executeQuery(query);
            
            ArrayList<String> carBrands = returnDistinctBrands(rs);
            for(int i = 0; i < carBrands.size();i++){
                jComboBox.addItem(carBrands.get(i));
            }
            
            rs.close();
	    myStatement.close();				
        }
	catch(SQLException sex)
	{
            System.out.println(sex.getMessage());
	}
    }
    
     public ArrayList<Car> ShowFilteredBrandTable(String selectedBrand){
        ArrayList<Car> cars = new ArrayList<Car>();
        getConnection();
        try{
            myStatement = myConnection.createStatement();	
            String query = "SELECT * FROM CARS WHERE BRAND = '" + selectedBrand + "'";
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
     private ArrayList<String> returnDistinctModels(ResultSet rs){
        
        ArrayList<String> carModels = new ArrayList<String>();
        try{            
            while(rs.next())
            {
                String carModel = rs.getString("model");
                if(!carModels.contains(carModel))
                    carModels.add(carModel);
            }            
            rs.close();				
        }
	catch(SQLException sex)
	{
            System.out.println(sex.getMessage());
	}
        return carModels;
    }
    
     public void ShowModelList(JComboBox jComboBox){
        getConnection();
        try{
            myStatement = myConnection.createStatement();	
            String query = "SELECT MODEL FROM CARS";
	    ResultSet rs = myStatement.executeQuery(query);
            
            ArrayList<String> carModels = returnDistinctModels(rs);
            for(int i = 0; i < carModels.size();i++){
                jComboBox.addItem(carModels.get(i));
            }
            
            rs.close();
	    myStatement.close();				
        }
	catch(SQLException sex)
	{
            System.out.println(sex.getMessage());
	}
    }
    
     public ArrayList<Car> ShowFilteredModelTable(String selectedModel){
        ArrayList<Car> cars = new ArrayList<Car>();
        getConnection();
        try{
            myStatement = myConnection.createStatement();	
            String query = "SELECT * FROM CARS WHERE MODEL = '" + selectedModel + "'";
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
     
    public ArrayList<Car> ShowFilteredPriceRangeTable(String selectedMinimumPrice, String selectedMaximumPrice, String order){
        ArrayList<Car> cars = new ArrayList<Car>();
        getConnection();
        String orderQuery;
        
        if (order == "ascending"){
            orderQuery = " ORDER BY PRICE ASC";
        }
        else if (order == "descending"){
            orderQuery = " ORDER BY PRICE DESC";
        }
        else {
            orderQuery = "";
        }
        
        try{
            myStatement = myConnection.createStatement();	
            String query = "SELECT * FROM CARS WHERE PRICE BETWEEN " + selectedMinimumPrice + " AND " + selectedMaximumPrice + orderQuery;
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
    
    public ArrayList<Car> ShowFilteredDate(String date){
        ArrayList<Car> cars = new ArrayList<>();
        getConnection();
        
        try{
            myStatement = myConnection.createStatement();
            String query = "SELECT * FROM CARS WHERE ((rentUntil BETWEEN '0000/0/0' AND '" + date + "') AND (dateRented NOT BETWEEN '" + date + "' AND '9999/12/12')) OR (dateRented IS NULL)";
            ResultSet rs = myStatement.executeQuery(query);
            while(rs.next())
            {
                cars.add(new Car(rs.getString("licensePlate"), rs.getString("brand"), rs.getString("model"), rs.getDouble("price"), rs.getString("description"), rs.getString("dateRented"), rs.getString("rentUntil")));
            }
            
            rs.close();
	    myStatement.close();
        } catch(SQLException sex) {
            System.out.println(sex.getMessage());
        }
        return cars;
    }
}



