/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi_sarpras;

import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Ivan
 */
public class FUtama extends javax.swing.JFrame {

    /**
     * Creates new form FUtama
     */    
    koneksi koneksi;
    Statement st;
    ResultSet rs;
    String user_id=UserSession.getS_user();
    int id_level=UserSession.getS_level();
    String level_id=String.valueOf(id_level);
    
    JasperDesign JasDes;
    JasperReport JasRep;
    JasperPrint JasPri;
    Map param=new HashMap();
    public FUtama() {
        initComponents();
        koneksi =new koneksi();
        ShowIdentity();
        switch(level_id){
            case "1":
                MenuAdmin();
                break;
            case "2":
                MenuPetugas();
                break;
        }            
        new FDataInventaris().CheckTabel();
        if(user_id.equals("PETUGAS1")){
            MenuProfil.setEnabled(false);
        }
    }
    public void CheckLaporan(){
        MenuLaporan.setEnabled(false);
    }
    public void MenuAdmin(){
        Inventaris.setEnabled(false);
        MenuInventaris.setEnabled(false);
    }
    public void MenuPetugas(){
        MenuPetugas.setEnabled(false);
        MenuPegawai.setEnabled(false);
    }
    public void ShowIdentity(){
        try{
            st=koneksi.con.createStatement();
            String sql="SELECT tbl_level.NAMA_LEVEL, tbl_petugas.NAMA_PETUGAS "
                       + "FROM tbl_petugas "
                       + "INNER JOIN tbl_level ON tbl_petugas.ID_LEVEL=tbl_level.ID_LEVEL "
                       + "WHERE tbl_petugas.KD_PETUGAS='"+user_id+"'";
            rs=st.executeQuery(sql);
            if(rs.next()){
                LabelName.setText(rs.getString("tbl_petugas.NAMA_PETUGAS"));
                LabelLevel.setText(rs.getString("tbl_level.NAMA_LEVEL"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    public void Cetak(){        
        try {        
            File file=new File("src\\ReportInventaris.jrxml");
            JasDes=JRXmlLoader.load(file);
            JasRep=JasperCompileManager.compileReport(JasDes);
            JasPri=JasperFillManager.fillReport(JasRep, param, koneksi.con);
            JasperViewer.viewReport(JasPri, false);
        } catch (JRException ex) {
            Logger.getLogger(FUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        LabelName = new javax.swing.JLabel();
        LabelLevel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        MenuProfil = new javax.swing.JMenuItem();
        MenuLogout = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MenuJenis = new javax.swing.JMenuItem();
        MenuRuang = new javax.swing.JMenuItem();
        MenuPetugas = new javax.swing.JMenuItem();
        MenuPegawai = new javax.swing.JMenuItem();
        MenuInventaris = new javax.swing.JMenuItem();
        Inventaris = new javax.swing.JMenu();
        MenuPeminjaman = new javax.swing.JMenuItem();
        MenuPengembalian = new javax.swing.JMenuItem();
        MenuLaporan = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Selamat Datang");

        jLabel2.setText("Aplikasi Inventaris Sarana Prasarana SMK Muhammadiyah 2 Kuningan");

        jLabel3.setText("Copyright Ivan Faathirza");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Workspace_96px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 186, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LabelLevel)
                        .addGap(153, 153, 153))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(161, 161, 161)
                                .addComponent(jLabel4)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(LabelName)
                    .addComponent(LabelLevel))
                .addGap(35, 35, 35)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        File.setText("File");

        MenuProfil.setText("Profil");
        MenuProfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuProfilActionPerformed(evt);
            }
        });
        File.add(MenuProfil);

        MenuLogout.setText("Logout");
        MenuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuLogoutActionPerformed(evt);
            }
        });
        File.add(MenuLogout);

        jMenuBar1.add(File);

        jMenu2.setText("Kelola Data");

        MenuJenis.setText("Jenis Inventaris");
        MenuJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuJenisActionPerformed(evt);
            }
        });
        jMenu2.add(MenuJenis);

        MenuRuang.setText("Ruang Inventaris");
        MenuRuang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuRuangActionPerformed(evt);
            }
        });
        jMenu2.add(MenuRuang);

        MenuPetugas.setText("Petugas");
        MenuPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuPetugasActionPerformed(evt);
            }
        });
        jMenu2.add(MenuPetugas);

        MenuPegawai.setText("Pegawai");
        MenuPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuPegawaiActionPerformed(evt);
            }
        });
        jMenu2.add(MenuPegawai);

        MenuInventaris.setText("Inventaris");
        MenuInventaris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuInventarisActionPerformed(evt);
            }
        });
        jMenu2.add(MenuInventaris);

        jMenuBar1.add(jMenu2);

        Inventaris.setText("Inventaris");

        MenuPeminjaman.setText("Peminjaman");
        MenuPeminjaman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuPeminjamanActionPerformed(evt);
            }
        });
        Inventaris.add(MenuPeminjaman);

        MenuPengembalian.setText("Pengembalian");
        MenuPengembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuPengembalianActionPerformed(evt);
            }
        });
        Inventaris.add(MenuPengembalian);

        MenuLaporan.setText("Laporan");
        MenuLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuLaporanActionPerformed(evt);
            }
        });
        Inventaris.add(MenuLaporan);

        jMenuBar1.add(Inventaris);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuInventarisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuInventarisActionPerformed
        // TODO add your handling code here:        
        new FDataInventaris().show();
        this.dispose();
    }//GEN-LAST:event_MenuInventarisActionPerformed

    private void MenuPengembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuPengembalianActionPerformed
        // TODO add your handling code here:
        new FDataPengembalian().show();
        this.dispose();
    }//GEN-LAST:event_MenuPengembalianActionPerformed

    private void MenuProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuProfilActionPerformed
        // TODO add your handling code here:
        new FProfil().show();
        this.dispose();
    }//GEN-LAST:event_MenuProfilActionPerformed

    private void MenuLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuLaporanActionPerformed
        // TODO add your handling code here:        
        Cetak();
    }//GEN-LAST:event_MenuLaporanActionPerformed

    private void MenuLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuLogoutActionPerformed
        // TODO add your handling code here:
        int response=JOptionPane.showConfirmDialog(null, "Apakah Anda Akan Logout?", "Logout",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response==JOptionPane.YES_OPTION){
            new FLogin().show();
            this.dispose();
        }
    }//GEN-LAST:event_MenuLogoutActionPerformed

    private void MenuJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuJenisActionPerformed
        // TODO add your handling code here:
        new FDataJenis().show();
        this.dispose();
    }//GEN-LAST:event_MenuJenisActionPerformed

    private void MenuRuangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuRuangActionPerformed
        // TODO add your handling code here:
        new FDataRuang().show();
        this.dispose();
    }//GEN-LAST:event_MenuRuangActionPerformed

    private void MenuPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuPetugasActionPerformed
        // TODO add your handling code here:
        new FDataPetugas().show();
        this.dispose();
    }//GEN-LAST:event_MenuPetugasActionPerformed

    private void MenuPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuPegawaiActionPerformed
        // TODO add your handling code here:
        new FDataPegawai().show();
        this.dispose();
    }//GEN-LAST:event_MenuPegawaiActionPerformed

    private void MenuPeminjamanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuPeminjamanActionPerformed
        // TODO add your handling code here:
        new FDataPeminjaman().show();
        this.dispose();
    }//GEN-LAST:event_MenuPeminjamanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu File;
    private javax.swing.JMenu Inventaris;
    private javax.swing.JLabel LabelLevel;
    private javax.swing.JLabel LabelName;
    private javax.swing.JMenuItem MenuInventaris;
    private javax.swing.JMenuItem MenuJenis;
    private javax.swing.JMenuItem MenuLaporan;
    private javax.swing.JMenuItem MenuLogout;
    private javax.swing.JMenuItem MenuPegawai;
    private javax.swing.JMenuItem MenuPeminjaman;
    private javax.swing.JMenuItem MenuPengembalian;
    private javax.swing.JMenuItem MenuPetugas;
    private javax.swing.JMenuItem MenuProfil;
    private javax.swing.JMenuItem MenuRuang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}