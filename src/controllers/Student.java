/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.StudentModel;

/**
 *
 * @author alvaro
 */
public class Student {
    private Connection con;
    private ArrayList<StudentModel> students = new ArrayList<>();
    private String ins;
    private Statement st;
    private ResultSet rs;
    private StudentModel selected;
    
    public Student(Connection con){
        this.con = con;
    }
    
    public ArrayList<StudentModel> getAll(){
        this.students.removeAll(this.students);
        
        try{
            if(withConnection()){
                this.ins = "SELECT * FROM alumnos";
                
                this.st = this.con.createStatement();
                this.rs = this.st.executeQuery(this.ins);
                
                while(this.rs.next()){
                    this.students.add(new StudentModel(this.rs.getString("CCODALU"), this.rs.getString("CNOMALU")));
                }
            }else{
                // throw error of conection
            }
        }catch(SQLException ex){
            // throw the error
            ex.printStackTrace();
        }finally{
            return this.students;
        }
    }
    
    public void setSelected(String cod, String nom){
        this.selected = new StudentModel(cod, nom);
    }
    
    public StudentModel getSelected(){
        return this.selected;
    }
    
    public Boolean withConnection(){
        try {
            if(this.con != null && !this.con.isClosed()){
                return true;
            }
            return false;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public ArrayList<StudentModel> getStudents(){
        return this.students;
    }
}
