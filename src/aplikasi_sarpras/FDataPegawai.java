/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi_sarpras;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ivan
 */
public class FDataPegawai extends javax.swing.JFrame {

    /**
     * Creates new form FDataPegawai
     */
    koneksi koneksi;
    Statement st;
    ResultSet rs;
    String field=null;
    String table=null;    
    String jk=null;
    Create Create;
    Update Update;
    Delete Delete;
    public FDataPegawai() {
        initComponents();
        koneksi=new koneksi();
        TabelPegawai();
        BDelete.setEnabled(false);
        BEdit.setEnabled(false);
        BSimpan.setEnabled(true);
        Message.setNameClass("Pegawai");
    }
    public void Gender(){
        if(l.isSelected()==true){
            jk="L";
        }else if(p.isSelected()==true){
            jk="P";
        }
    }
    public void TabelPegawai(){
        Object header[]={"NIP","Nama Pegawai","Jenis Kelamin","Alamat"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        TabelPegawai.setModel(data);
        String sql="select * from tbl_pegawai";
        try{
            st=koneksi.con.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                String k1=rs.getString(1);
                String k2=rs.getString(2);
                String k3=rs.getString(3);
                String k4=rs.getString(4);
                String k[]={k1,k2,k3,k4};
                data.addRow(k);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }    
    public void Clear(){
        NIP.setText("");
        NamaPegawai.setText("");
        group.clearSelection();
        BDelete.setEnabled(false);
        BEdit.setEnabled(false);
        BSimpan.setEnabled(true);
        TabelPegawai.clearSelection();
        Alamat.setText("");
        TabelPegawai.clearSelection();
    }
    public void SavePegawai(){
        Gender();
        table="tbl_pegawai";
        Create.setTable(table);
        field="'"+NIP.getText()
                +"','"+NamaPegawai.getText()
                +"','"+jk
                +"','"+Alamat.getText()+"'";
        Create.setField(field);
        JOptionPane.showMessageDialog(null, "Data Pegawai Berhasil Di Simpan");
    }
    public void EditPegawai(){
        Gender();
        table="tbl_pegawai";
        Update.setTable(table);
        field=" NAMA_PEGAWAI='"+NamaPegawai.getText()
                +"', JK_PEGAWAI='"+jk
                +"', ALAMAT_PEGAWAi='"+Alamat.getText()
                +"' where NIP_PEGAWAI='"+NIP.getText()+"'";
        Update.setField(field);
        JOptionPane.showMessageDialog(null, "Data Pegawai Berhasil Di Perbaharui");
    }
    public void DeletePegawai(){
        table="tbl_pegawai";
        Delete.setTable(table);
        field="NIP_PEGAWAI='"+NIP.getText()+"'";
        Delete.setField(field);
        JOptionPane.showMessageDialog(null, "Data Pegawai Berhasil Di Hapus");
    }
    public void CheckPegawai(){
        try{
            st=koneksi.con.createStatement();
            String sql="select * from tbl_peminjaman where NIP_PEGAWAI='"+NIP.getText()+"' AND STATUS_PEMINJAMAN='NO'";
            rs=st.executeQuery(sql);
            if(rs.next()){
                BDelete.setEnabled(false);
                BSimpan.setEnabled(false);
                BEdit.setEnabled(false);
            }else{
                BDelete.setEnabled(true);
                BSimpan.setEnabled(false);
                BEdit.setEnabled(true);
            }            
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
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

        group = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        l = new javax.swing.JRadioButton();
        p = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Alamat = new javax.swing.JTextArea();
        NamaPegawai = new javax.swing.JTextField();
        NIP = new javax.swing.JTextField();
        BTambah = new javax.swing.JButton();
        BSimpan = new javax.swing.JButton();
        BEdit = new javax.swing.JButton();
        BDelete = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelPegawai = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Kelola Data Pegawai"));

        jLabel1.setText("NIP");

        jLabel2.setText("Nama Pegawai");

        jLabel3.setText("Jenis Kelamin");

        jLabel4.setText("Alamat");

        group.add(l);
        l.setText("L");

        group.add(p);
        p.setText("P");

        Alamat.setColumns(20);
        Alamat.setRows(5);
        jScrollPane1.setViewportView(Alamat);

        BTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_New_File_16.png"))); // NOI18N
        BTambah.setText("NEW");
        BTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTambahActionPerformed(evt);
            }
        });

        BSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Save_16.png"))); // NOI18N
        BSimpan.setText("SAVE");
        BSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSimpanActionPerformed(evt);
            }
        });

        BEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Edit_File_16.png"))); // NOI18N
        BEdit.setText("EDIT");
        BEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEditActionPerformed(evt);
            }
        });

        BDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Delete_File_16.png"))); // NOI18N
        BDelete.setText("DELETE");
        BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(l)
                                .addGap(18, 18, 18)
                                .addComponent(p))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(BTambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BSimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BEdit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BDelete))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(NamaPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(NIP))))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(NIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(NamaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(l)
                    .addComponent(p))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTambah)
                    .addComponent(BSimpan)
                    .addComponent(BEdit)
                    .addComponent(BDelete))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Data Pegawai"));

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        TabelPegawai.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelPegawaiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TabelPegawai);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setText("File");

        jMenuItem1.setText("Close");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:       
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void TabelPegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelPegawaiMouseClicked
        // TODO add your handling code here:
        int bar=TabelPegawai.getSelectedRow();
        String a=TabelPegawai.getValueAt(bar, 0).toString();
        String b=TabelPegawai.getValueAt(bar, 1).toString();
        String c=TabelPegawai.getValueAt(bar, 2).toString();
        String d=TabelPegawai.getValueAt(bar, 3).toString();
        NIP.setText(a);
        NamaPegawai.setText(b);
        if(c.equals("L")){
            l.setSelected(true);
        }else if(c.equals("P")){
            p.setSelected(true);
        }
        Alamat.setText(d);
        CheckPegawai();             
        NIP.setEnabled(false);
    }//GEN-LAST:event_TabelPegawaiMouseClicked

    private void BTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTambahActionPerformed
        // TODO add your handling code here:
        int response=Message.getM_new();
        if(response==JOptionPane.YES_OPTION){
            Clear();
        }
    }//GEN-LAST:event_BTambahActionPerformed

    private void BSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSimpanActionPerformed
        // TODO add your handling code here:
        if(NIP.getText().isEmpty() || NamaPegawai.getText().isEmpty() || group.isSelected(null) 
                || Alamat.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Ada Yang Kosong");
        }else{
             int response=Message.getM_save();
            if(response==JOptionPane.YES_OPTION){
                SavePegawai();
                Create =new Create();
                TabelPegawai();
                Clear();
            }   
        }        
    }//GEN-LAST:event_BSimpanActionPerformed

    private void BEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditActionPerformed
        // TODO add your handling code here:
        if(NIP.getText().isEmpty() || NamaPegawai.getText().isEmpty() || group.isSelected(null) 
                || Alamat.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Ada Yang Kosong");
        }else{
         int response=Message.getM_update();
        if(response==JOptionPane.YES_OPTION){
            EditPegawai();
            Update =new Update();
            TabelPegawai();
            Clear();
        }   
        }        
    }//GEN-LAST:event_BEditActionPerformed

    private void BDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDeleteActionPerformed
        // TODO add your handling code here:
        int response=Message.getM_delete();
        if(response==JOptionPane.YES_OPTION){
            DeletePegawai();
            Delete =new Delete();
            TabelPegawai();
            Clear();
        }
    }//GEN-LAST:event_BDeleteActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new FUtama().show();
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(FDataPegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FDataPegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FDataPegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FDataPegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FDataPegawai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Alamat;
    private javax.swing.JButton BDelete;
    private javax.swing.JButton BEdit;
    private javax.swing.JButton BSimpan;
    private javax.swing.JButton BTambah;
    private javax.swing.JTextField NIP;
    private javax.swing.JTextField NamaPegawai;
    private javax.swing.JTable TabelPegawai;
    private javax.swing.ButtonGroup group;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton l;
    private javax.swing.JRadioButton p;
    // End of variables declaration//GEN-END:variables
}
