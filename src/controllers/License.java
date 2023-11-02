/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.LicenseModel;

/**
 *
 * @author alvaro
 */
public class License {
    private Connection con;
    private String ins;
    private Statement st;
    private CallableStatement cs;
    private ResultSet rs;
    private ArrayList<LicenseModel> licenses = new ArrayList<>();
    private LicenseModel selected;
    
    public License(Connection con){
        this.con = con;
    }
    
    public ArrayList<LicenseModel> getLicensesOfStudent(String cod_alu){
        this.licenses.removeAll(this.licenses);
        
        try{
            if(withConnection()){
                this.ins = "select CCODCURSO, NNOTAMEDIA from matriculas where CCODALU = '" + cod_alu + "'";
                
                this.st = this.con.createStatement();
                this.rs = this.st.executeQuery(ins);
                
                while(this.rs.next()){
                    this.licenses.add(new LicenseModel(cod_alu, this.rs.getString("ccodcurso"), this.rs.getDouble("nnotamedia")));
                }
            }
        }catch( SQLException ex){
        }finally{
            return this.licenses;
        }
    }
    
    public Boolean insert(String cod_alu, String cod_curso){
        try{
            if(withConnection()){
                this.ins = "{call SP_ALTAMATRICULA(?, ?)}";
                
                this.cs = this.con.prepareCall(ins);
                this.cs.setString(1, cod_alu);
                this.cs.setString(2, cod_curso);
                this.cs.execute();
                return true;
            }
            return false;
        }catch( SQLException ex){
            return false;
        }
        
    }
    
    public void setSelected(String cod_alu, String cod_curso, double nota_media){
        this.selected = new LicenseModel(cod_alu, cod_curso, nota_media);
    }
    
    public LicenseModel getSelected(){
        return this.selected;
    }
    
    public Boolean withConnection(){
        try{
            if(this.con != null && !this.con.isClosed()){
                return true;
            }
            return false;
        }catch( SQLException ex ){
            return false;
        }
    }
}
