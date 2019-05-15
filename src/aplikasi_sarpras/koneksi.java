/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi_sarpras;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivan
 */
public class koneksi {
 Connection con;
 public koneksi(){
     String id,pass,url,driver;
     id="root";
     pass="";
     url="jdbc:mysql://localhost/db_sarpras";
     driver="com.mysql.jdbc.Driver";
     try{
         Class.forName(driver).newInstance();
         con=DriverManager.getConnection(url,id,pass);         
     }catch(Exception e){
         System.out.println(""+e.getLocalizedMessage());
     }
 }
 public static void main(String[]args){
     koneksi k=new koneksi();
 }
}
