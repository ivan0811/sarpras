/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi_sarpras;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivan
 */
public class Update {
    koneksi koneksi;
    Statement st;
    ResultSet rs;
    public static String field;
    public static String table;
    public Update(){
        koneksi = new koneksi();
        try {
            st=koneksi.con.createStatement();
            String update="update "+Update.table+" set "+Update.field+"";
            st.executeUpdate(update);
        } catch (SQLException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    public static void setField(String field){
        Update.field=field;
    }
    public static void setTable(String table){
        Update.table=table;
    }
}
