/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alvaro
 */
public class JTableLicenses {
    private JTable table;
    private DefaultTableModel model;
 
    public JTableLicenses(){
        createTable();
    }
    
    public void createTable(){
        String[] columns = {"Código Alumno", "Nombre Alumno", "Código Curso", "Nombre Curso", "Nota media"};
        this.table = new JTable(new DefaultTableModel(columns, 0){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            }
        );
        this.table.setComponentPopupMenu(null);
        
        this.model = (DefaultTableModel) this.table.getModel();
    }
    
    public void addRow(String con_alu, String nom_alu, String con_curso, String nom_curso, double nota_media){
        this.model.addRow(new Object[]{con_alu, nom_alu, con_curso, nom_curso, nota_media});
    }
    
    public JTable getTable(){
        return this.table;
    }
    
    public DefaultTableModel getModel(){
        return this.model;
    }
}