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

/**
 *
 * @author Ivan
 */
public class Delete {
    koneksi koneksi;
    Statement st;
    ResultSet rs;
    private static String field;
    private static String table;
    public Delete(){
        koneksi = new koneksi();
        try {
            st=koneksi.con.createStatement();
            String delete="delete from "+Delete.table+" where "+Delete.field+"";
            st.executeUpdate(delete);
        } catch (SQLException ex) {
            Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void setField(String field){
        Delete.field=field;
    }
    public static void setTable(String table){
        Delete.table=table;
    }
}
