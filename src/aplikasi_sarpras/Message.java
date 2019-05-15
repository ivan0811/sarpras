/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi_sarpras;

import javax.swing.JOptionPane;

/**
 *
 * @author Ivan
 */
public class Message {
    private static int m_new;
    private static int m_save;
    private static int m_delete;
    private static int m_update;
    private static String NameClass;
    public static int getM_new(){
        int response=JOptionPane.showConfirmDialog(null, "Data "+Message.NameClass+" Akan Di Tambahkan?", 
                "Tambahkan Data "+Message.NameClass,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return m_new;
    }
    public static int getM_save(){
        int response=JOptionPane.showConfirmDialog(null, "Apakah Data "+Message.NameClass+" Sudah Benar?",
                "Simpan Data "+Message.NameClass,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return m_save;
    }
    public static int getM_delete(){
        int response=JOptionPane.showConfirmDialog(null, "Apakah Data "+Message.NameClass+" Akan Di Hapus?", 
                "Hapus Data "+Message.NameClass,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return m_delete;
    }
    public static int getM_update(){
        int response=JOptionPane.showConfirmDialog(null, "Apakah Data "+Message.NameClass+" Akan Di Ubah?", 
                "Hapus Data "+Message.NameClass,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return m_update;
    }
    public static void setNameClass(String NameClass){
        Message.NameClass=NameClass;
    }
}
