/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author alvaro
 */
public class StudentModel {
    private String cCodAlu;
    private String cNomAlu;
    
    public StudentModel(String cCodAlu, String cNomAlu){
        this.cCodAlu = cCodAlu;
        this.cNomAlu = cNomAlu;
    }
    
    public String toString(){
        return "" + this.cCodAlu + " " + this.cNomAlu;
    }
    
    public String getcCodAlu(){
        return this.cCodAlu;
    }
    
    public String getcNomAlu(){
        return this.cNomAlu;
    }
    
    public void setcCodAlu(String cCodAlu){
        this.cCodAlu = cCodAlu;
    }
    
    public void setcNomAlu(String cNomAlu){
        this.cNomAlu = cNomAlu;
    }
}
