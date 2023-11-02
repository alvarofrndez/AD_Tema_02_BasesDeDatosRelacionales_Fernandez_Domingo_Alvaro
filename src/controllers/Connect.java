/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alvaro
 */
public class Connect {
    private Connection con = null;
    
    public Connect(String server, String port, String user, String password, String service){
        if(!checkData()){
            // throw error of data
            return;
        }
        
        this.con = createCon(server, port, user, password, service);
    }
    
    public Connection createCon(String server, String port, String user, String password, String service){
        // establish the connection with the database
        Connection con = null;
        try{
            String url = "jdbc:oracle:thin:@" + server + ":" + port + "/" + service;
            con = DriverManager.getConnection(url, user, password);
        }catch( SQLException e){
        }
        return con;
    }
    
    public Boolean checkData(){
        return true;
    }
    
    public Connection getConnect(){
        return this.con;
    }
}