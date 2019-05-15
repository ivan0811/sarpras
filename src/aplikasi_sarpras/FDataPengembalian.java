/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi_sarpras;

import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
public class FDataPengembalian extends javax.swing.JFrame {

    /**
     * Creates new form FDataPengembalian
     */
    koneksi koneksi;
    Statement st;
    ResultSet rs;
    String field=null;
    String table=null;
    Update Update;
    JasperReport JasRep;
    JasperDesign JasDes;
    JasperPrint JasPri;
    Map param=new HashMap();
    public FDataPengembalian() {
        initComponents();
        koneksi =new koneksi();
        ShowTabel();
        CheckTabel();
    }   
    public void CheckTabel(){
        if(TabelPengembalian.getRowCount()==0){
            BSimpan.setEnabled(false);
            laporan.setEnabled(false);
        }
    }
    public void ShowTabel(){
        Object header[]={"Kode Peminjaman", "Nama Pegawai", "Nama Inventaris", "Jumlah Pinjam", "Tanggal Pinjam", "Status"};
        DefaultTableModel data=new DefaultTableModel(null, header);
        TabelPengembalian.setModel(data);
        String sql="SELECT tbl_peminjaman.KD_PEMINJAMAN, tbl_pegawai.NAMA_PEGAWAI, tbl_inventaris.NAMA_INVENTARIS, "
                   + "detail_pinjam.JUMLAH_PINJAM, tbl_peminjaman.TANGGAL_PINJAM, tbl_peminjaman.STATUS_PEMINJAMAN "
                   + "FROM tbl_peminjaman "
                   + "INNER JOIN tbl_pegawai ON tbl_peminjaman.NIP_PEGAWAI=tbl_pegawai.NIP_PEGAWAI "
                   + "INNER JOIN detail_pinjam ON detail_pinjam.KD_PEMINJAMAN=tbl_peminjaman.KD_PEMINJAMAN "
                   + "INNER JOIN tbl_inventaris ON detail_pinjam.KD_INVENTARIS=tbl_inventaris.KD_INVENTARIS "
                   + "WHERE tbl_peminjaman.STATUS_PEMINJAMAN='NO'";
        try{
            st=koneksi.con.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                String k1=rs.getString(1);
                String k2=rs.getString(2);
                String k3=rs.getString(3);
                String k4=rs.getString(4);
                String k5=rs.getString(5);
                String k6=rs.getString(6);
                String k[]={k1,k2,k3,k4,k5,k6};
                data.addRow(k);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }    
    public void Clear(){
        KDPeminjaman.setText("");
        NamaPegawai.setText("");
        NamaInventaris.setText("");
        Tanggal.setText("");
        Jumlah.setText("");
        Status.setSelectedItem("NO");        
    }
    public void UpdateInventaris(){
        int hasil=0;
        String kd_inventaris=null;
        try{
            st=koneksi.con.createStatement();
            String sql="SELECT tbl_inventaris.JUMLAH_INVENTARIS, tbl_inventaris.KD_INVENTARIS "
                       + "FROM tbl_peminjaman "
                       + "INNER JOIN detail_pinjam ON detail_pinjam.KD_PEMINJAMAN=tbl_peminjaman.KD_PEMINJAMAN " 
                       + "INNER JOIN tbl_inventaris ON detail_pinjam.KD_INVENTARIS=tbl_inventaris.KD_INVENTARIS "
                       + "WHERE tbl_peminjaman.KD_PEMINJAMAN='"+KDPeminjaman.getText()+"'";
            rs=st.executeQuery(sql);
            if(rs.next()){
                int Stok=rs.getInt("tbl_inventaris.JUMLAH_INVENTARIS");
                int JumlahPinjam=Integer.parseInt(Jumlah.getText());
                hasil=Stok+JumlahPinjam;             
                kd_inventaris=rs.getString("tbl_inventaris.KD_INVENTARIS");
            }            
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }        
        table="tbl_inventaris";
        Update.setTable(table);
        field=" JUMLAH_INVENTARIS='"+hasil+"' where KD_INVENTARIS='"+kd_inventaris+"'";
        Update.setField(field);
    }
    public void SavePengembalian(){
        Date now=new Date();
        SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
        String tanggal=format1.format(now);
        table="tbl_peminjaman";
        Update.setTable(table);
        field=" TANGGAL_KEMBALI='"+tanggal
                +"', STATUS_PEMINJAMAN='"+Status.getSelectedItem()
                +"' where KD_PEMINJAMAN='"+KDPeminjaman.getText()+"'";
        Update.setField(field);
        JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan");
    }
    public void Cetak(){
        try {        
            File file=new File("src\\ReportPengembalian.jrxml");
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelPengembalian = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        KDPeminjaman = new javax.swing.JTextField();
        NamaPegawai = new javax.swing.JTextField();
        NamaInventaris = new javax.swing.JTextField();
        Tanggal = new javax.swing.JTextField();
        Jumlah = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Status = new javax.swing.JComboBox<>();
        BSimpan = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        laporan = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Pengembalian"));

        TabelPengembalian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TabelPengembalian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelPengembalianMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelPengembalian);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Kelola Pengembalian"));

        jLabel1.setText("Kode Peminjaman");

        jLabel2.setText("Nama Pegawai");

        jLabel3.setText("Tanggal Pinjam");

        jLabel4.setText("Nama Inventaris");

        jLabel5.setText("Jumlah Pinjam");

        KDPeminjaman.setEnabled(false);

        NamaPegawai.setEnabled(false);

        NamaInventaris.setEnabled(false);

        Tanggal.setEnabled(false);

        Jumlah.setEnabled(false);

        jLabel6.setText("Status");

        Status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "YES" }));

        BSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Save_16.png"))); // NOI18N
        BSimpan.setText("Simpan Peminjaman");
        BSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSimpanActionPerformed(evt);
            }
        });

        jLabel7.setText("* Ubah Status Menjadi Yes");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BSimpan)
                    .addComponent(KDPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7))
                    .addComponent(NamaPegawai)
                    .addComponent(NamaInventaris)
                    .addComponent(Tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(KDPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(NamaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(NamaInventaris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(BSimpan)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        jMenuItem2.setText("Close");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Cetak");

        laporan.setText("Laporan");
        laporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laporanActionPerformed(evt);
            }
        });
        jMenu2.add(laporan);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TabelPengembalianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelPengembalianMouseClicked
        // TODO add your handling code here:
        int bar=TabelPengembalian.getSelectedRow();
        String a=TabelPengembalian.getValueAt(bar, 0).toString();
        String b=TabelPengembalian.getValueAt(bar, 1).toString();
        String c=TabelPengembalian.getValueAt(bar, 2).toString();
        String d=TabelPengembalian.getValueAt(bar, 3).toString();
        String e=TabelPengembalian.getValueAt(bar, 4).toString();
        String f=TabelPengembalian.getValueAt(bar, 5).toString();
        KDPeminjaman.setText(a);
        NamaPegawai.setText(b);
        NamaInventaris.setText(c);
        Tanggal.setText(e);
        Jumlah.setText(d);
        Status.setSelectedItem(f);
    }//GEN-LAST:event_TabelPengembalianMouseClicked

    private void BSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSimpanActionPerformed
        // TODO add your handling code here:     
        UpdateInventaris();
        Update =new Update();
        SavePengembalian();
        Update = new Update();
        ShowTabel();
        Clear();
        CheckTabel();
    }//GEN-LAST:event_BSimpanActionPerformed

    private void laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laporanActionPerformed
        // TODO add your handling code here:
        Cetak();
    }//GEN-LAST:event_laporanActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        new FUtama().show();
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
            java.util.logging.Logger.getLogger(FDataPengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FDataPengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FDataPengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FDataPengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FDataPengembalian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BSimpan;
    private javax.swing.JTextField Jumlah;
    private javax.swing.JTextField KDPeminjaman;
    private javax.swing.JTextField NamaInventaris;
    private javax.swing.JTextField NamaPegawai;
    private javax.swing.JComboBox<String> Status;
    private javax.swing.JTable TabelPengembalian;
    private javax.swing.JTextField Tanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem laporan;
    // End of variables declaration//GEN-END:variables
}
