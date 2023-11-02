/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.CourseModel;

/**
 *
 * @author alvaro
 */
public class Course {
    
    private Connection con;
    private ArrayList<CourseModel> courses = new ArrayList<>(); 
    private String ins;
    private Statement st;
    private ResultSet rs;
    private CourseModel selected;
    
    public Course(Connection con){
        this.con = con;
    }
    
    public ArrayList<CourseModel> getAll(){
        try{
            if(withConnection()){
                this.ins = "select * from cursos";
                
                this.st = this.con.createStatement();
                this.rs = this.st.executeQuery(this.ins);
                
                while(this.rs.next()){
                    this.courses.add(new CourseModel(this.rs.getString("CCODCURSO"), this.rs.getString("CNOMCURSO"), this.rs.getInt("NNUMEXA")));
                }
            }
        }catch( SQLException ex ){
            // throw error ¿the first register with the error?
        }
        return this.courses;
    }
    
    public void setSelected(String cod, String nom, int num_exa){
        this.selected = new CourseModel(cod, nom, num_exa);
    }
    
    public String getCourseName(String cod_curso){
        try{
            if(withConnection()){
                this.ins = "select cnomcurso from cursos where ccodcurso = '" + cod_curso  + "'";
                
                this.st = this.con.createStatement();
                this.rs = this.st.executeQuery(this.ins);
                
                while(this.rs.next()){
                    return this.rs.getString("cnomcurso");
                }
            }
            return "";
        }catch( SQLException ex){
            return "";
        }
    }
    
    public CourseModel getSelected(){
        return this.selected;
    }
    
    public Boolean withConnection(){
        try{
            if(this.con != null && !this.con.isClosed())
                return true;
            return false;
        }catch( SQLException ex){
            // throw error
            return false;
        }
    }
}
