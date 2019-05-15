/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi_sarpras;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Ivan
 */
public class UserSession {
    private static String s_user;
    private static int s_level;
    public static String getS_user(){
        return s_user;
    }
    public static int getS_level(){
        return s_level;
    }
    public static void setS_user(String s_user){
        UserSession.s_user=s_user;
    }
    public static void setS_level(int s_level){
        UserSession.s_level=s_level;
    }    
}
