/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.CourseModel;

/**
 *
 * @author alvaro
 */
public class JTableCourses {
    private ArrayList<CourseModel> courses;
    private JTable table;
    private DefaultTableModel model;
    
    public JTableCourses(ArrayList<CourseModel> courses){
        this.courses = courses;
        
        createTable();
    }
    
    public void createTable(){
        String[] columns = {"Código", "Nombre", "Nºexámenes"};
        this.table = new JTable(new DefaultTableModel(columns, 0){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            }
        );
        this.table.setComponentPopupMenu(null);
        
        
        this.model = (DefaultTableModel) this.table.getModel();
        
        for(CourseModel course : this.courses){
            this.model.addRow(new Object[]{course.getcCodCurso(), course.getcNomCurso(), course.getnNumExa()});
        }
    }
    
    public JTable getTable(){
        return this.table;
    }
}
