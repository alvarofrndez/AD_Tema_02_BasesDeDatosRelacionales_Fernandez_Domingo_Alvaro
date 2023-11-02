/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import models.ExamModel;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author alvaro
 */
public class ExportJSON {
    private ArrayList<ExamModel> exams;
    private Gson gson;
    
    public ExportJSON(ArrayList<ExamModel> exams){
        this.exams = exams;
        this.gson = new Gson();
    }
    
    public Boolean extractData(String path){
        String json = "";
        
        for(ExamModel exam : exams){
            json += "" + this.gson.toJson(exam) + "\n";
        }
        
        return writeJson(path, json);
    }
    
    public Boolean writeJson(String path, String json){
        File file = new File("" + path + ".json");
        
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            
            writer.write(json);
            
            writer.close();
            return true;
        }catch( IOException e){
            return false;
        }
    }
}
