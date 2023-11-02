/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
 * @author alvaro
 */
public class ExamModel {
    private String ccodalu;
    private String ccodcurso;
    private int nnumexa;
    private Date dfecexam;
    private double nnotaexam;
    
    public ExamModel(String ccodalu, String ccodcurso, int nnumexa, Date dfecexam, double nnotaexam){
        this.ccodalu = ccodalu;
        this.ccodcurso = ccodcurso;
        this.nnumexa = nnumexa;
        this.dfecexam = dfecexam;
        this.nnotaexam = nnotaexam;
    }
    
    public String getCodAlu(){
        return this.ccodalu;
    }
    
    public String getCodCurso(){
        return this.ccodcurso;
    }
    
    public int getNumExa(){
        return this.nnumexa;
    }
    
    public Date getFecExam(){
        return this.dfecexam;
    }
    
    public double getNotaExam(){
        return this.nnotaexam;
    }
    
    public String toString(){
        return "" + this.ccodalu + " " + this.ccodcurso + " " + this.nnumexa + " " + this.dfecexam + " " + this.nnotaexam;
    }
}
