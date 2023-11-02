/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.StudentModel;

/**
 *
 * @author alvaro
 */
public class JTableStudents extends JTable{
    private ArrayList<StudentModel> students;
    private JTable table;
    private DefaultTableModel model;
    
    public JTableStudents(ArrayList<StudentModel> students){
        this.students = students;
        
        createTable();
    }
    
    public void createTable(){
        String[] columns = {"Código", "Nombre"};
        this.table = new JTable(new DefaultTableModel(columns, 0){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            }
        );
        this.table.setComponentPopupMenu(null);
        
        this.model = (DefaultTableModel) this.table.getModel();
        
        for(StudentModel student : this.students){
            this.model.addRow(new Object[]{student.getcCodAlu(), student.getcNomAlu()});
        }
    }
    
    public JTable getTable(){
        return this.table;
    }
}
