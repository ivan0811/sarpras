/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplikasi_sarpras;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivan
 */
public class FTambahDataPeminjaman extends javax.swing.JFrame {

    /** Creates new form DTambahDataPeminjaman */
    Statement st;
    ResultSet rs;
    koneksi koneksi;
    String field=null;
    String table=null;
    Update Update;
    Create Create;
    public FTambahDataPeminjaman() {
        initComponents();
        koneksi=new koneksi();
        ShowNIP();
        ShowInventaris();
        ShowKD();
    }
    public void ShowKD(){
        try{
            st=koneksi.con.createStatement();
            String sql="select KD_PEMINJAMAN from tbl_peminjaman order by KD_PEMINJAMAN desc";
            rs=st.executeQuery(sql);
            if(rs.next()){
                String KD=rs.getString("KD_PEMINJAMAN").substring(6);
                String AN=""+(Integer.parseInt(KD)+1);
                String Nol="";
                switch(AN.length()){
                    case 1:
                        Nol="000";
                        break;
                    case 2:
                        Nol="00";
                        break;
                    case 3:
                        Nol="0";
                        break;
                }
                KDPeminjaman.setText("PINJAM"+Nol+AN);
            }
            else{
                KDPeminjaman.setText("PINJAM0001");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    public void ShowNIP(){
        try{
            st=koneksi.con.createStatement();
            String sql="select NIP_PEGAWAI from tbl_pegawai";
            rs=st.executeQuery(sql);
            while(rs.next()){
                NIP.addItem(rs.getString("NIP_PEGAWAI"));
            }
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    public void ShowInventaris(){
        try{
            st=koneksi.con.createStatement();
            String sql="select KD_INVENTARIS from tbl_inventaris where JUMLAH_INVENTARIS!='0'";
            rs=st.executeQuery(sql);
            while(rs.next()){
                KDInventaris.addItem(rs.getString("KD_INVENTARIS"));
            }
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    public void ShowNamePegawai(){
        try{
            st=koneksi.con.createStatement();
            String sql="select NAMA_PEGAWAI from tbl_pegawai where NIP_PEGAWAI='"+NIP.getSelectedItem()+"'";
            rs=st.executeQuery(sql);
            while(rs.next()){
                NamaPegawai.setText(rs.getString("NAMA_PEGAWAI"));
            }            
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    public void ShowNameInventaris(){
        try{
            st=koneksi.con.createStatement();
            String sql="select NAMA_INVENTARIS from tbl_inventaris where KD_INVENTARIS='"+KDInventaris.getSelectedItem()+"'";
            rs=st.executeQuery(sql);
            while(rs.next()){
                NamaInventaris.setText(rs.getString("NAMA_INVENTARIS"));
            }            
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    public void ShowStok(){
        Jumlah.removeAllItems();
        try{            
            st=koneksi.con.createStatement();
            String sql="select JUMLAH_INVENTARIS from tbl_inventaris WHERE KD_INVENTARIS='"+KDInventaris.getSelectedItem()+"'";
            rs=st.executeQuery(sql);
            while(rs.next()){
                Stok.setText(rs.getString("JUMLAH_INVENTARIS"));
                for(int x=1;x <= rs.getInt("JUMLAH_INVENTARIS"); x++){
                    Jumlah.addItem(Integer.toString(x));
                }
                break;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    public void UpdateInventaris(){
        int stok=Integer.parseInt(Stok.getText());
        int pinjam=Integer.parseInt((String) Jumlah.getSelectedItem());
        int hasil=stok-pinjam;
        table="tbl_inventaris";
        Update.setTable(table);
        field="JUMLAH_INVENTARIS='"+hasil
                +"' where KD_INVENTARIS='"+KDInventaris.getSelectedItem()+"'";
        Update.setField(field);        
    }
    public void SavePeminjaman(){
        Date now=new Date();
        SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
        String tanggal=format1.format(now);
        table="tbl_peminjaman";
        Create.setTable(table);
        field="'"+KDPeminjaman.getText()
                +"','"+NIP.getSelectedItem()
                +"','"+tanggal
                +"','0000-00-00','NO'";
        Create.setField(field);        
    }
    public void SaveDetail(){
        try{
            st=koneksi.con.createStatement();
            String sql="insert into detail_pinjam (KD_INVENTARIS,KD_PEMINJAMAN,JUMLAH_PINJAM) "
                    + "values('"+KDInventaris.getSelectedItem()
                    +"','"+KDPeminjaman.getText()
                    +"','"+Jumlah.getSelectedItem()+"')";
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "Data Peminjaman Berhasil Di Tambahkan");
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    public void Clear(){
        ShowKD();
        NIP.setSelectedIndex(0);
        KDInventaris.setSelectedIndex(0);
        NamaPegawai.setText("");
        NamaInventaris.setText("");
        Stok.setText("");
        Jumlah.removeAllItems();        
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Jumlah = new javax.swing.JComboBox<>();
        Stok = new javax.swing.JTextField();
        KDInventaris = new javax.swing.JComboBox<>();
        NIP = new javax.swing.JComboBox<>();
        KDPeminjaman = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        NamaInventaris = new javax.swing.JTextField();
        NamaPegawai = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tambah Peminjaman"));

        jLabel1.setText("Kode Peminjaman");

        jLabel2.setText("NIP");

        jLabel3.setText("Kode Inventaris");

        jLabel5.setText("Stok");

        jLabel6.setText("Jumlah Pinjam");

        Stok.setEnabled(false);

        KDInventaris.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kode Inventaris" }));
        KDInventaris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KDInventarisActionPerformed(evt);
            }
        });

        NIP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih NIP" }));
        NIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NIPActionPerformed(evt);
            }
        });

        KDPeminjaman.setEnabled(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_New_File_16.png"))); // NOI18N
        jButton1.setText("Simpan Peminjaman");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Nama Pegawai");

        jLabel7.setText("Nama Inventaris");

        NamaInventaris.setEnabled(false);

        NamaPegawai.setEnabled(false);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Close_Window_16.png"))); // NOI18N
        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(KDPeminjaman)
                            .addComponent(NIP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(KDInventaris, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Jumlah, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Stok)
                            .addComponent(jButton1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(NamaPegawai)
                                    .addComponent(NamaInventaris, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)))
                            .addComponent(jButton2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(KDPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(NIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(NamaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(KDInventaris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(NamaInventaris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Stok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void KDInventarisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KDInventarisActionPerformed
        // TODO add your handling code here:
        if(KDInventaris.getSelectedItem().equals("Pilih Kode Inventaris")){
            NamaInventaris.setText("Nama Inventaris");            
        }else{
            ShowNameInventaris();
            ShowStok();            
        }
    }//GEN-LAST:event_KDInventarisActionPerformed

    private void NIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NIPActionPerformed
        // TODO add your handling code here:
        if(NIP.getSelectedItem().equals("Pilih NIP")){
            NamaPegawai.setText("Nama Pegawai");
        }else{
            ShowNamePegawai();
        }
    }//GEN-LAST:event_NIPActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:        
        if(KDPeminjaman.getText().isEmpty() || NIP.getSelectedItem().equals("Pilih NIP")
                || KDInventaris.getSelectedItem().equals("Pilih Kode Inventaris")){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong");
        }else{
            UpdateInventaris();
            Update =new Update();
            SavePeminjaman();
            Create = new Create();
            SaveDetail();
            Clear();
        }                
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new FDataPeminjaman().show();
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(FTambahDataPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FTambahDataPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FTambahDataPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FTambahDataPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FTambahDataPeminjaman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Jumlah;
    private javax.swing.JComboBox<String> KDInventaris;
    private javax.swing.JTextField KDPeminjaman;
    private javax.swing.JComboBox<String> NIP;
    private javax.swing.JTextField NamaInventaris;
    private javax.swing.JTextField NamaPegawai;
    private javax.swing.JTextField Stok;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

}
