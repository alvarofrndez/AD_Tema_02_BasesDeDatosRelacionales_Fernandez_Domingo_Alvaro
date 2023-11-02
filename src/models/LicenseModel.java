/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author alvaro
 */
public class LicenseModel {
    
    private String ccodalu;
    private String ccodcurso;
    private double nnotamedia;
    
    public LicenseModel(String ccodalu, String ccodcurso, double nnotamedia){
        this.ccodalu = ccodalu;
        this.ccodcurso = ccodcurso;
        this.nnotamedia = nnotamedia;
    }
    
    public String getCodAlu(){
        return this.ccodalu;
    }
    
    public String getCodCurso(){
        return this.ccodcurso;
    }
    
    public double getNotaMedia(){
        return this.nnotamedia;
    }
    
    public String toString(){
        return "" + this.ccodalu + " " + this.ccodcurso + " " + this.nnotamedia;
    }
}
