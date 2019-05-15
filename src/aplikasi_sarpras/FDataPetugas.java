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
public class FDataPetugas extends javax.swing.JFrame {

    /**
     * Creates new form FDataPetugas
     */
    koneksi koneksi;
    Statement st;
    ResultSet rs;
    String field=null;
    String table=null;
    String id_level=null;
    Create Create;
    Update Update;
    Delete Delete;
    public FDataPetugas() {
        initComponents();
        koneksi=new koneksi();
        ShowKD();
        TabelPetugas();
        ShowLevel();
        BSimpan.setEnabled(true);
        BDelete.setEnabled(false);
        BEdit.setEnabled(false);
        Message.setNameClass("Petugas");
        LimitPetugas();
    }
    public void ShowKD(){
        try{
            st=koneksi.con.createStatement();
            String sql="select KD_PETUGAS from tbl_petugas order by KD_PETUGAS desc";
            rs=st.executeQuery(sql);
            if(rs.next()){
                String KD=rs.getString("KD_PETUGAS").substring(7);
                String AN=""+(Integer.parseInt(KD)+1);
                KDPetugas.setText("PETUGAS"+AN);
            }else{
                KDPetugas.setText("PETUGAS1");
            }            
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    public void TabelPetugas(){
        Object header[]={"Kode Petugas", "Nama Petugas", "Level Petugas"};
        DefaultTableModel data=new DefaultTableModel(null, header);
        TabelPetugas.setModel(data);
        String sql="select tbl_petugas.KD_PETUGAS, tbl_petugas.NAMA_PETUGAS, tbl_level.NAMA_LEVEL "
                + "from tbl_petugas "
                + "inner join tbl_level on tbl_petugas.ID_LEVEL=tbl_level.ID_LEVEL";        
        try{
            st=koneksi.con.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                String k1=rs.getString(1);
                String k2=rs.getString(2);
                String k3=rs.getString(3);
                String k[]={k1,k2,k3};
                data.addRow(k);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    public void ShowLevel(){
        try{
            st=koneksi.con.createStatement();
            String sql="select NAMA_LEVEL from tbl_level";
            rs=st.executeQuery(sql);
            while(rs.next()){
                NamaLevel.addItem(rs.getString("NAMA_LEVEL"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    public void NameLevel(){
        try{
            st=koneksi.con.createStatement();
            String sql="select ID_LEVEL from tbl_level where NAMA_LEVEL='"+NamaLevel.getSelectedItem()+"'";
            rs=st.executeQuery(sql);
            if(rs.next()){
                id_level=rs.getString("ID_LEVEL");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    public void Clear(){
        ShowKD();
        NamaLevel.setSelectedItem("Pilih Level");
        NamaPetugas.setText("");
        Username.setText("");
        Password.setText("");
        Username.setEnabled(true);
        Password.setEnabled(true);
        BSimpan.setEnabled(true);
        BDelete.setEnabled(false);
        BEdit.setEnabled(false);
        TabelPetugas.clearSelection();
    }
    public void SavePetugas(){
        table="tbl_petugas";
        Create.setTable(table);
        field="'"+KDPetugas.getText()
               +"','"+id_level
                +"','"+NamaPetugas.getText()
                +"','"+Username.getText()
                +"','"+Password.getText()+"'";
        Create.setField(field);
        JOptionPane.showMessageDialog(null, "Data Petugas Berhasil Di Simpan");
    }
    public void EditPetugas(){
        table="tbl_petugas";
        Update.setTable(table);
        field="ID_LEVEL='"+id_level
                +"', NAMA_PETUGAS='"+NamaPetugas.getText()
                +"' where KD_PETUGAS='"+KDPetugas.getText()+"'";
        Update.setField(field);
        JOptionPane.showMessageDialog(null, "Data Petugas Berhasil Di Perbaharui");
    }
    public void DeletePetugas(){
        table="tbl_petugas";
        Delete.setTable(table);
        field=" KD_PETUGAS='"+KDPetugas.getText()+"'";
        Delete.setField(field);
        JOptionPane.showMessageDialog(null, "Data Petugas Berhasil Di Hapus");
    }
    public void LimitPetugas(){
        try{
            st=koneksi.con.createStatement();
            String sql="SELECT COUNT(KD_PETUGAS) AS kode_petugas FROM tbl_petugas";
            rs=st.executeQuery(sql);
            if(rs.next()){
                if(rs.getInt("kode_petugas")>9){
                    BTambah.setEnabled(false);
                    KDPetugas.setText("");                    
                    BSimpan.setEnabled(false);                    
                    Username.setEnabled(false);
                    Password.setEnabled(false);
                    NamaLevel.setEnabled(false);
                    NamaPetugas.setEnabled(false);
                }else{
                    BTambah.setEnabled(true);
                    ShowKD();
                    BSimpan.setEnabled(true);                                        
                    Username.setEnabled(true);
                    Password.setEnabled(true);
                    NamaLevel.setEnabled(true);
                    NamaPetugas.setEnabled(true);
                }                
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        NamaPetugas = new javax.swing.JTextField();
        NamaLevel = new javax.swing.JComboBox<>();
        KDPetugas = new javax.swing.JTextField();
        Username = new javax.swing.JTextField();
        BTambah = new javax.swing.JButton();
        BSimpan = new javax.swing.JButton();
        BEdit = new javax.swing.JButton();
        BDelete = new javax.swing.JButton();
        Password = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelPetugas = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Kelola Data Petugas"));

        jLabel1.setText("Kode Petugas");

        jLabel2.setText("Nama Level");

        jLabel3.setText("Nama Petugas");

        jLabel4.setText("Username");

        jLabel5.setText("Password");

        NamaLevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Level" }));
        NamaLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamaLevelActionPerformed(evt);
            }
        });

        KDPetugas.setEnabled(false);

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
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(BTambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BSimpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BDelete))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(KDPetugas, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(NamaLevel, javax.swing.GroupLayout.Alignment.LEADING, 0, 124, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Password, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                        .addComponent(Username, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(NamaPetugas, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(KDPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(NamaLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(NamaPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTambah)
                    .addComponent(BSimpan)
                    .addComponent(BEdit)
                    .addComponent(BDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Data Petugas"));

        TabelPetugas.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelPetugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelPetugasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelPetugas);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
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
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NamaLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaLevelActionPerformed
        // TODO add your handling code here:
        if(NamaLevel.getSelectedItem().equals("Pilih Level")){            
            id_level=null;
        }else{
            NameLevel();
        }
    }//GEN-LAST:event_NamaLevelActionPerformed

    private void TabelPetugasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelPetugasMouseClicked
        // TODO add your handling code here:
        int bar=TabelPetugas.getSelectedRow();
        String a=TabelPetugas.getValueAt(bar, 0).toString();
        String b=TabelPetugas.getValueAt(bar, 1).toString();
        String c=TabelPetugas.getValueAt(bar, 2).toString();
        KDPetugas.setText(a);
        NamaLevel.setSelectedItem(c);
        NamaPetugas.setText(b);
        if(a.equals("PETUGAS1")){
            BEdit.setEnabled(false);
            BDelete.setEnabled(false);
            BSimpan.setEnabled(false);
        }else{
            BEdit.setEnabled(true);
            BDelete.setEnabled(true);
            BSimpan.setEnabled(false);
        }   
        Username.setEnabled(false);
        Password.setEnabled(false);
        NamaLevel.setEnabled(true);
        NamaPetugas.setEnabled(true);
    }//GEN-LAST:event_TabelPetugasMouseClicked

    private void BTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTambahActionPerformed
        // TODO add your handling code here:
        int response=Message.getM_new();
        if(response==JOptionPane.YES_OPTION){
            Clear();
        }
    }//GEN-LAST:event_BTambahActionPerformed

    private void BSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSimpanActionPerformed
        // TODO add your handling code here:
        if(KDPetugas.getText().isEmpty() || NamaLevel.getSelectedItem().equals("Pilih Level")
                || NamaPetugas.getText().isEmpty() || Username.getText().isEmpty() || Password.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Petugas Tidak Boleh Kosong");
        }else{
         int response=Message.getM_save();
        if(response==JOptionPane.YES_OPTION){
            SavePetugas();
            Create=new Create();
            TabelPetugas();
            Clear();
            LimitPetugas();
        }   
        }        
    }//GEN-LAST:event_BSimpanActionPerformed

    private void BEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditActionPerformed
        // TODO add your handling code here:
        if(KDPetugas.getText().isEmpty() || NamaLevel.getSelectedItem().equals("Pilih Level")
                || NamaPetugas.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Nama Petugas Atau Nama Level Anda Kosong");
        }else{
         int response=Message.getM_update();
        if(response==JOptionPane.YES_OPTION){
            EditPetugas();
            Update=new Update();
            TabelPetugas();
            Clear();
        }   
        }        
    }//GEN-LAST:event_BEditActionPerformed

    private void BDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDeleteActionPerformed
        // TODO add your handling code here:
        int response=Message.getM_delete();
        if(response==JOptionPane.YES_OPTION){
            DeletePetugas();
            Delete=new Delete();
            TabelPetugas();
            Clear();
            LimitPetugas();
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
            java.util.logging.Logger.getLogger(FDataPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FDataPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FDataPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FDataPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FDataPetugas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BDelete;
    private javax.swing.JButton BEdit;
    private javax.swing.JButton BSimpan;
    private javax.swing.JButton BTambah;
    private javax.swing.JTextField KDPetugas;
    private javax.swing.JComboBox<String> NamaLevel;
    private javax.swing.JTextField NamaPetugas;
    private javax.swing.JPasswordField Password;
    private javax.swing.JTable TabelPetugas;
    private javax.swing.JTextField Username;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
