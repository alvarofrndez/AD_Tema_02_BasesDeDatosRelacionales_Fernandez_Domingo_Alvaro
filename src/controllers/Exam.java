/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import models.ExamModel;

/**
 *
 * @author alvaro
 */
public class Exam {
        private Connection con;
    private ArrayList<ExamModel> exams = new ArrayList<>(); 
    private String ins;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;
    private ExamModel selected;
    
    public Exam(Connection con){
        this.con = con;
    }
    
    public ArrayList<ExamModel> getAll(){
        try{
            if(withConnection()){
                this.ins = "select * from cursos";
                
                this.st = this.con.createStatement();
                this.rs = this.st.executeQuery(this.ins);
                
                while(this.rs.next()){
                    //this.exams.add(new ExamModel(this.rs.getString("CCODCURSO"), this.rs.getString("CNOMCURSO"), this.rs.getInt("NNUMEXA")));
                }
            }
        }catch( SQLException ex ){
            // throw error ¿the first register with the error?
        }
        return this.exams;
    }
    
    public void setSelected(String cod_alu, String cod_curso, int num_exa, Date fec_exam, double nota_exam){
        this.selected = new ExamModel(cod_alu, cod_curso, num_exa, fec_exam, nota_exam);
    }
    
    public ArrayList<ExamModel> getAll(String cod_alu, String cod_curso){
        this.exams.removeAll(this.exams);
        
        try{
            if(withConnection()){
                this.ins = "select NNUMEXAM, DFECEXAM, NNOTAEXAM from examenes where ccodcurso = '" + cod_curso  + "'" + " and CCODALU = '" + cod_alu + "'";
                
                this.st = this.con.createStatement();
                this.rs = this.st.executeQuery(this.ins);
                
                while(this.rs.next()){
                    Date new_date = this.rs.getDate("DFECEXAM");
                    if(new_date != null){
                        new_date = transformDate(new_date);
                    }
                    this.exams.add(new ExamModel(cod_alu, cod_curso, this.rs.getInt("NNUMEXAM"), new_date, this.rs.getDouble("NNOTAEXAM")));
                }
            }
        }catch( SQLException ex){
        }finally{
            return this.exams;
        }
    }

    public Date transformDate(Date old_date) {
        try {
            SimpleDateFormat sdfNuevo = new SimpleDateFormat("dd-MM-yyyy");
            String fechaFormateadaStr = sdfNuevo.format(old_date);
            
            java.sql.Date fechaSql = new java.sql.Date(sdfNuevo.parse(fechaFormateadaStr).getTime());
            return fechaSql;
        } catch (ParseException e) {
            return null;
        }
    }
    
    public Boolean updateExam(String cod_alu, String cod_curso, String date_exam, String mark, int num_exa){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try{
            if(withConnection()){
                if(!date_exam.equals("")){
                    Date date = new Date(format.parse(date_exam).getTime());
                    this.ins = "update examenes set dfecexam = ?, nnotaexam = ? where ccodalu = ? and ccodcurso = ? and NNUMEXAM = ?";
                    this.ps = this.con.prepareStatement(ins);
                    this.ps.setObject(1, date);
                    this.ps.setObject(2, Double.parseDouble(mark));
                    this.ps.setObject(3, cod_alu);
                    this.ps.setObject(4, cod_curso);
                    this.ps.setObject(5, num_exa);
                }else{
                    this.ins = "update examenes set nnotaexam = ? where ccodalu = ? and ccodcurso = ? and NNUMEXAM = ?";
                    this.ps = this.con.prepareStatement(ins);
                    this.ps.setObject(1,Double.parseDouble(mark));
                    this.ps.setObject(2, cod_alu);
                    this.ps.setObject(3, cod_curso);
                    this.ps.setObject(4, num_exa);
                }
                
                int result = this.ps.executeUpdate();

                if(result > 0){
                    return true;
                }
                return false;
            }
            return false;
        }catch( SQLException ex){
            return false;
        }catch ( Exception e){
            return false;
        }
    }
    
    public ExamModel getSelected(){
        return this.selected;
    }
    
    public String getCodAlu(){
        if(this.exams.size() > 0)
            return this.exams.get(0).getCodAlu();
        return "";
    }
    
    public String getCodCurso(){
        if(this.exams.size() > 0)
            return this.exams.get(0).getCodCurso();
        return "";
    }
    
    public Boolean withConnection(){
        try{
            if(this.con != null && !this.con.isClosed())
                return true;
            return false;
        }catch( SQLException ex){
            return false;
        }
    }
}
