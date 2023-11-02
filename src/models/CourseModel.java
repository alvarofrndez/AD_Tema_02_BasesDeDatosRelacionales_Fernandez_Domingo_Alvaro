/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author alvaro
 */
public class CourseModel {
    
    private String cCodCurso;    
    private String cNomCurso;
    private int nNumExa;

    public CourseModel(String cCodCurso, String cNomCurso, int nNumExa){
        this.cCodCurso = cCodCurso;
        this.cNomCurso = cNomCurso;
        this.nNumExa = nNumExa;
    }
    
    public String toString(){
        return "" + this.cCodCurso + " " + this.cNomCurso + " " + this.nNumExa;
    }
    
    public String getcCodCurso(){
        return this.cCodCurso;
    }
    
    public String getcNomCurso(){
        return this.cNomCurso;
    }
    
    public int getnNumExa(){
        return this.nNumExa;
    }
    
    public void setcCodCurso(String cCodCurso){
        this.cCodCurso = cCodCurso;
    }
    
    public void setcNomCurso(String cNomCurso){
        this.cNomCurso = cNomCurso;
    }
    
    public void setnNumExa(int nNumExa){
        this.nNumExa = nNumExa;
    }
}
