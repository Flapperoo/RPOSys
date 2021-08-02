/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RentFinal;

import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author paolo
 */
public abstract class SearchByClass extends javax.swing.JFrame{
   private MySqlClass mySQL = new MySqlClass();
    private ArrayList<Car> cars;
    DefaultTableModel model;
    
    public void clearTable(JTable jTable1)
    {
        model = (DefaultTableModel) jTable1.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged(); // notifies the JTable that the model has changed
    }
     
    protected void onFormWindowOpen(JTable jTable1, JComboBox jComboBox1){
        clearTable(jTable1);
        cars = mySQL.ShowTable();
        
        model = (DefaultTableModel) jTable1.getModel();
        for(Car c: cars)    
           model.addRow(new Object[] {c.getLicensePlate(), c.getBrand(), c.getModel(), c.getPrice(), c.getDescription()});
        showList(jComboBox1);
    }
    
    protected abstract void showList(JComboBox jComboBox1);
    protected abstract ArrayList<Car> setCarsToFilteredTable(String filter);
    
    void onUpdateButtonActionPerformed(JTable jTable1, JComboBox jComboBox1){
        if (jComboBox1.getSelectedItem() == "None"){
            clearTable(jTable1);
            cars = mySQL.ShowTable();

            model = (DefaultTableModel) jTable1.getModel();
            for(Car c: cars)    
               model.addRow(new Object[] {c.getLicensePlate(), c.getBrand(), c.getModel(), c.getPrice(), c.getDescription()}); 
        }
        
        else {
            clearTable(jTable1);
            String selectedBrand = jComboBox1.getSelectedItem().toString();
            cars = setCarsToFilteredTable(selectedBrand);
            //cars = mySQL.ShowFilteredBrandTable(selectedBrand);
            
            model = (DefaultTableModel) jTable1.getModel();
            for(Car c: cars)    
               model.addRow(new Object[] {c.getLicensePlate(), c.getBrand(), c.getModel(), c.getPrice(), c.getDescription()}); 
            
        }
    }
    
}
