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

public class Create {
    koneksi koneksi;
    Statement st;
    ResultSet rs;
    
    private static String field;
    private static String table;
    public Create(){
        koneksi = new koneksi();
        try {        
            st=koneksi.con.createStatement();
            String insert="insert into "+Create.table+" values("+Create.field+")";
            st.execute(insert);
        } catch (SQLException ex) {
            Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    public static void setField(String field){
        Create.field=field;
    }
    public static void setTable(String table){
        Create.table=table;
    }
}
