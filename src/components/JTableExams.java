/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alvaro
 */
public class JTableExams {
    private JTable table;
    private DefaultTableModel model;
 
    public JTableExams(){
        createTable();
    }
    
    public void createTable(){
        String[] columns = {"Número Examen", "Fecha Examen", "Nota examen"};
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
    
    public void addRow(int num_exa, Date fec_exa, Double nota_exa){
        this.model.addRow(new Object[]{num_exa, fec_exa, nota_exa});
    }
    
    public JTable getTable(){
        return this.table;
    }
    
    public DefaultTableModel getModel(){
        return this.model;
    }
}
