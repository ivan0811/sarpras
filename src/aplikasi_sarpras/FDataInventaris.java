/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi_sarpras;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Ivan
 */
public class FDataInventaris extends javax.swing.JFrame {

    /**
     * Creates new form FDataInventaris
     */
    koneksi koneksi;
    Statement st;
    ResultSet rs;
    String user_id=UserSession.getS_user();
    String field=null;
    String table=null;
    File File;
    JFileChooser jfc;
    Create Create;
    Update Update;
    Delete Delete;
    public FDataInventaris() {
        initComponents();
        koneksi =new koneksi();
        ShowKD();
        ShowJenis();
        ShowRuang();
        Message.setNameClass("Inventaris");
        TabelInventaris();
        BSimpan.setEnabled(true);
        BEdit.setEnabled(false);
        BHapus.setEnabled(false);
    }
    public void ShowKD(){
        try{
            st=koneksi.con.createStatement();
            String sql="select KD_INVENTARIS from tbl_inventaris order by KD_INVENTARIS desc";
            rs=st.executeQuery(sql);
            if(rs.next()){
                String KD=rs.getString("KD_INVENTARIS").substring(3);
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
                KDInventaris.setText("INV"+Nol+AN);
            } else {
                KDInventaris.setText("INV0001");
            }            
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    public void ShowJenis(){
        try{
            st=koneksi.con.createStatement();
            String sql="select NAMA_JENIS from tbl_jenis";
            rs=st.executeQuery(sql);   
            while(rs.next()){
                JenisInventaris.addItem(rs.getString("NAMA_JENIS"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }        
    }
     public void ShowRuang(){
        try{
            st=koneksi.con.createStatement();
            String sql="select NAMA_RUANG from tbl_ruang";
            rs=st.executeQuery(sql);   
            while(rs.next()){
                RuangInventaris.addItem(rs.getString("NAMA_RUANG"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }        
    }
     public void TabelInventaris(){
         Object header[]={"Kode Inventaris", "Jenis", "Ruang", 
             "Nama Inventaris", "Kondisi", "Keterangan", "Jumlah", "Tanggal", "Foto"};
         DefaultTableModel data=new DefaultTableModel(null, header);
         TabelInventaris.setModel(data);
         String sql="SELECT tbl_inventaris.KD_INVENTARIS, tbl_jenis.NAMA_JENIS, tbl_ruang.NAMA_RUANG, "
                    + "tbl_inventaris.NAMA_INVENTARIS, "
                    + "tbl_inventaris.KONDISI_INVENTARIS, "
                    + "tbl_inventaris.KETERANGAN_INVENTARIS, tbl_inventaris.JUMLAH_INVENTARIS, "
                    + "tbl_inventaris.TANGGAL_REGISTER, tbl_inventaris.FOTO_INVENTARIS "
                    + "FROM tbl_inventaris "
                    + "INNER JOIN tbl_jenis ON tbl_inventaris.KD_JENIS=tbl_jenis.KD_JENIS "
                    + "INNER JOIN tbl_ruang ON tbl_inventaris.KD_RUANG=tbl_ruang.KD_RUANG";
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
                 String k7=rs.getString(7);
                 String k8=rs.getString(8);
                 String k9=rs.getString(9);                 
                 String k[]={k1,k2,k3,k4,k5,k6,k7,k8,k9};
                 data.addRow(k);
             }
         }catch(Exception e){
             JOptionPane.showMessageDialog(rootPane, e);
         }
     }
     public void UploadImage(){         
         String path;
        try {
            path = new File(".").getCanonicalPath();
            FileUtils.copyFileToDirectory(File, new File(path+"/image"));
        } catch (IOException ex) {
            Logger.getLogger(FDataInventaris.class.getName()).log(Level.SEVERE, null, ex);
        }
     }        
     public void SaveInventaris(){
         try{
             String kd_jenis=null, kd_ruang=null, tampil;
             tampil="yyyy-MM-dd";
             SimpleDateFormat format1=new SimpleDateFormat(tampil);
             String tanggal=String.valueOf(format1.format(TanggalRegister.getDate()));
             
             st=koneksi.con.createStatement();
             String jenis="select KD_JENIS from tbl_jenis where NAMA_JENIS='"+JenisInventaris.getSelectedItem()+"'";
             rs=st.executeQuery(jenis);   
             if(rs.next()){
                 kd_jenis=rs.getString("KD_JENIS");
             }
             
             String ruang="select KD_RUANG from tbl_ruang where NAMA_RUANG='"+RuangInventaris.getSelectedItem()+"'";
             rs=st.executeQuery(ruang);
             if(rs.next()){
                 kd_ruang=rs.getString("KD_RUANG");
             }
             
             table="tbl_inventaris";
             Create.setTable(table);
             field=" '"+KDInventaris.getText()
                     +"','"+kd_jenis
                     +"','"+user_id
                     +"','"+kd_ruang
                     +"','"+NamaInventaris.getText()
                     +"','"+KondisiInventaris.getSelectedItem()
                     +"','"+Keterangan.getText()
                     +"','"+Jumlah.getValue()
                     +"','"+tanggal
                     +"','"+NamaGambar.getText()+"'";
             Create.setField(field);
             JOptionPane.showMessageDialog(null, "Data Inventaris Berhasil Di Simpan");
         }catch(Exception e){
             JOptionPane.showMessageDialog(rootPane, e);
         }
     }
     public void Clear(){
         ShowKD();
         JenisInventaris.setSelectedIndex(0);
         RuangInventaris.setSelectedIndex(0);
         NamaInventaris.setText("");
         KondisiInventaris.setSelectedIndex(0);
         Keterangan.setText("");
         Jumlah.setValue(0);
         TanggalRegister.setDate(null);
         NamaGambar.setText("");
         BSimpan.setEnabled(true);
         BHapus.setEnabled(false);
         BEdit.setEnabled(false);
         Gambar.setIcon(null);
         TabelInventaris.clearSelection();
         Cari.setText("");
     }
     public void DeleteImage(){
        try{            
            st=koneksi.con.createStatement();
            String sql="select FOTO_INVENTARIS from tbl_inventaris where KD_INVENTARIS='"+KDInventaris.getText()+"'";
            rs=st.executeQuery(sql);
            if(rs.next()){                                
                File file=new File("/image/"+rs.getString("FOTO_INVENTARIS"));
                file.delete();                                     
            }
        }catch(Exception e){        
            JOptionPane.showMessageDialog(rootPane, e);
        }
     }
     public void CheckTabel(){
         if(TabelInventaris.getRowCount()==0){
             new FUtama().CheckLaporan();
         }
     }
     public void EditInventaris(){
         
         try{
             String kd_jenis=null, kd_ruang=null, tampil;
             tampil="yyyy-MM-dd";
             SimpleDateFormat format1=new SimpleDateFormat(tampil);
             String tanggal=String.valueOf(format1.format(TanggalRegister.getDate()));
             
             st=koneksi.con.createStatement();
             String jenis="select KD_JENIS from tbl_jenis where NAMA_JENIS='"+JenisInventaris.getSelectedItem()+"'";
             rs=st.executeQuery(jenis);   
             if(rs.next()){
                 kd_jenis=rs.getString("KD_JENIS");
             }
             
             String ruang="select KD_RUANG from tbl_ruang where NAMA_RUANG='"+RuangInventaris.getSelectedItem()+"'";
             rs=st.executeQuery(ruang);
             if(rs.next()){
                 kd_ruang=rs.getString("KD_RUANG");
             }
             
             table="tbl_inventaris";
             Update.setTable(table);
             field=" KD_JENIS='"+kd_jenis
                     +"', KD_PETUGAS='"+user_id
                     +"', KD_RUANG='"+kd_ruang
                     +"', NAMA_INVENTARIS='"+NamaInventaris.getText()
                     +"', KONDISI_INVENTARIS='"+KondisiInventaris.getSelectedItem()
                     +"', KETERANGAN_INVENTARIS='"+Keterangan.getText()
                     +"', JUMLAH_INVENTARIS='"+Jumlah.getValue()
                     +"', TANGGAL_REGISTER='"+tanggal
                     +"', FOTO_INVENTARIS='"+NamaGambar.getText()+"' where KD_INVENTARIS='"+KDInventaris.getText()+"'";
             Update.setField(field);
             JOptionPane.showMessageDialog(null, "Data Inventaris Berhasil Di Edit");
         }catch(Exception e){
             JOptionPane.showMessageDialog(rootPane, e);
         }
     }
     public void DeleteInventaris(){
         table="tbl_inventaris";
         Delete.setTable(table);
         field=" KD_INVENTARIS='"+KDInventaris.getText()+"'";
         Delete.setField(field);
         JOptionPane.showMessageDialog(null, "Data Inventaris Berhasil Di Hapus");
     }
     public void SearchInventaris(){
         Object header[]={"Kode Inventaris", "Jenis", "Ruang", "Nama Inventaris", "Kondisi", 
             "Keterangan", "Jumlah", "Tanggal", "Foto"};
         DefaultTableModel data=new DefaultTableModel(null, header);
         TabelInventaris.setModel(data);
         String sql="SELECT tbl_inventaris.KD_INVENTARIS, tbl_jenis.NAMA_JENIS, tbl_ruang.NAMA_RUANG, "
                    + "tbl_inventaris.NAMA_INVENTARIS, "
                    + "tbl_inventaris.KONDISI_INVENTARIS, "
                    + "tbl_inventaris.KETERANGAN_INVENTARIS, tbl_inventaris.JUMLAH_INVENTARIS, "
                    + "tbl_inventaris.TANGGAL_REGISTER, tbl_inventaris.FOTO_INVENTARIS "
                    + "FROM tbl_inventaris "
                    + "INNER JOIN tbl_jenis ON tbl_inventaris.KD_JENIS=tbl_jenis.KD_JENIS "
                    + "INNER JOIN tbl_ruang ON tbl_inventaris.KD_RUANG=tbl_ruang.KD_RUANG "
                    + "WHERE tbl_inventaris.NAMA_INVENTARIS LIKE '%"+Cari.getText()
                    + "%' OR tbl_inventaris.KONDISI_INVENTARIS LIKE '%"+Cari.getText()+"%'"
                    + "ORDER BY tbl_inventaris.KD_INVENTARIS";
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
                 String k7=rs.getString(7);
                 String k8=rs.getString(8);
                 String k9=rs.getString(9);                 
                 String k[]={k1,k2,k3,k4,k5,k6,k7,k8,k9};
                 data.addRow(k);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        BTambah = new javax.swing.JButton();
        TanggalRegister = new com.toedter.calendar.JDateChooser();
        Keterangan = new javax.swing.JTextField();
        Jumlah = new javax.swing.JSpinner();
        NamaInventaris = new javax.swing.JTextField();
        KondisiInventaris = new javax.swing.JComboBox<>();
        KDInventaris = new javax.swing.JTextField();
        RuangInventaris = new javax.swing.JComboBox<>();
        JenisInventaris = new javax.swing.JComboBox<>();
        BSimpan = new javax.swing.JButton();
        BEdit = new javax.swing.JButton();
        BHapus = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        NamaGambar = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        Gambar = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        Cari = new javax.swing.JTextField();
        BCari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelInventaris = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Kelola Data Inventaris"));

        jLabel1.setText("Kode Inventaris");

        jLabel2.setText("Nama Inventaris");

        jLabel3.setText("Jenis Inventaris");

        jLabel4.setText("Ruang Inventaris");

        jLabel5.setText("Kondisi Inventaris");

        jLabel6.setText("Keterangan Inventaris");

        jLabel7.setText("Jumlah Inventaris");

        jLabel8.setText("Tanggal Register");

        BTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_New_File_16.png"))); // NOI18N
        BTambah.setText("NEW");
        BTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTambahActionPerformed(evt);
            }
        });

        KondisiInventaris.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BAIK", "RUSAK" }));

        KDInventaris.setEnabled(false);

        RuangInventaris.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Ruang Inventaris" }));

        JenisInventaris.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Jenis Inventaris" }));

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

        BHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Delete_File_16.png"))); // NOI18N
        BHapus.setText("DELETE");
        BHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BHapusActionPerformed(evt);
            }
        });

        jLabel9.setText("Nama Gambar");

        NamaGambar.setEnabled(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(Gambar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Gambar, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
        );

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Opened_Folder_16.png"))); // NOI18N
        jButton5.setText("File Gambar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(KDInventaris)
                            .addComponent(JenisInventaris, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(RuangInventaris, 0, 166, Short.MAX_VALUE)
                            .addComponent(KondisiInventaris, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NamaInventaris)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BTambah)
                                .addGap(18, 18, 18)
                                .addComponent(BSimpan)
                                .addGap(18, 18, 18)
                                .addComponent(BEdit)
                                .addGap(18, 18, 18)
                                .addComponent(BHapus))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TanggalRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(NamaGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton5))
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(KDInventaris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(JenisInventaris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(RuangInventaris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(NamaInventaris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(KondisiInventaris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(Keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TanggalRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(NamaGambar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTambah)
                    .addComponent(BSimpan)
                    .addComponent(BEdit)
                    .addComponent(BHapus))
                .addGap(13, 13, 13))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Inventaris"));

        jLabel10.setText("Cari Inventaris");

        BCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Search_16.png"))); // NOI18N
        BCari.setText("Cari");
        BCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCariActionPerformed(evt);
            }
        });

        TabelInventaris.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelInventaris.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelInventarisMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelInventaris);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Synchronize_16.png"))); // NOI18N
        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BCari)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(Cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BCari)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jfc=new JFileChooser();
        if(jfc.showOpenDialog(Gambar)==JFileChooser.APPROVE_OPTION){
            Toolkit toolkit=Toolkit.getDefaultToolkit();
            Image image=toolkit.getImage(jfc.getSelectedFile().getAbsolutePath());
            Image ImageResized=image.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
            ImageIcon imageicon=new ImageIcon(ImageResized);
            Gambar.setIcon(imageicon);
            NamaGambar.setText(jfc.getSelectedFile().getName());
            File =new File(jfc.getSelectedFile().getPath());
        }
    }//GEN-LAST:event_jButton5ActionPerformed
    
    private void BSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSimpanActionPerformed
        // TODO add your handling code here:        
        if(KDInventaris.getText().isEmpty() || JenisInventaris.getSelectedItem().equals("Pilih Jenis Inventaris")
                || RuangInventaris.getSelectedItem().equals("Pilih Ruang Inventaris") 
                || NamaInventaris.getText().isEmpty()
                || Keterangan.getText().isEmpty()
                || Jumlah.getValue().equals(0)
                || TanggalRegister.getDate().equals(null)
                || NamaGambar.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Harap Isi Data Dengan Benar!");
        }
        else{
            int response=Message.getM_save();
            if(response==JOptionPane.YES_OPTION){
            SaveInventaris();
            UploadImage();           
            Create =new Create();            
            TabelInventaris();
            Clear();
            }
        }        
    }//GEN-LAST:event_BSimpanActionPerformed

    private void BTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTambahActionPerformed
        // TODO add your handling code here:
        int response=Message.getM_new();
        if(response==JOptionPane.YES_OPTION){
            Clear();
        }
    }//GEN-LAST:event_BTambahActionPerformed

    private void TabelInventarisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelInventarisMouseClicked
        // TODO add your handling code here:
        int bar=TabelInventaris.getSelectedRow();
        String a=TabelInventaris.getValueAt(bar, 0).toString();
        String b=TabelInventaris.getValueAt(bar, 1).toString();
        String c=TabelInventaris.getValueAt(bar, 2).toString();
        String d=TabelInventaris.getValueAt(bar, 3).toString();
        String e=TabelInventaris.getValueAt(bar, 4).toString();
        String f=TabelInventaris.getValueAt(bar, 5).toString();
        String g=TabelInventaris.getValueAt(bar, 6).toString();
        String h=TabelInventaris.getValueAt(bar, 7).toString();
        String i=TabelInventaris.getValueAt(bar, 8).toString();
        KDInventaris.setText(a);
        JenisInventaris.setSelectedItem(b);
        RuangInventaris.setSelectedItem(c);
        NamaInventaris.setText(d);
        KondisiInventaris.setSelectedItem(e);
        Keterangan.setText(f);
        Jumlah.setValue(Integer.valueOf(g));
        Date date=null;
        try {
            date=new SimpleDateFormat("yyyy-MM-dd").parse(h);
        } catch (ParseException ex) {
            Logger.getLogger(FDataInventaris.class.getName()).log(Level.SEVERE, null, ex);
        }
        TanggalRegister.setDate(date);        
        try {
            Toolkit toolkit=Toolkit.getDefaultToolkit();
            String path=new File(".").getCanonicalPath();
            Image image=toolkit.getImage(path+"/image/"+i);
            Image imageResized=image.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
            ImageIcon imageIcon=new ImageIcon(imageResized);
            Gambar.setIcon(imageIcon);
        } catch (IOException ex) {
            Logger.getLogger(FDataInventaris.class.getName()).log(Level.SEVERE, null, ex);
        }
        NamaGambar.setText(i);
        BSimpan.setEnabled(false);
        BHapus.setEnabled(true);
        BEdit.setEnabled(true);
    }//GEN-LAST:event_TabelInventarisMouseClicked

    private void BEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditActionPerformed
        // TODO add your handling code here:
        if(KDInventaris.getText().isEmpty() 
                || JenisInventaris.getSelectedItem().equals("Pilih Jenis Inventaris")
                || RuangInventaris.getSelectedItem().equals("Pilih Ruang Inventaris") 
                || NamaInventaris.getText().isEmpty()
                || Keterangan.getText().isEmpty()
                || Jumlah.getValue().equals(0)
                || TanggalRegister.getDate().equals(null)
                || NamaGambar.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Harap Isi Data Dengan Benar!");
        }
        else{
            int response=Message.getM_update();
            if(response==JOptionPane.YES_OPTION){
                EditInventaris();
                try{
                    st=koneksi.con.createStatement();
                    String sql="select FOTO_INVENTARIS from tbl_inventaris where "
                            + "KD_INVENTARIS='"+KDInventaris.getText()+"'";
                    rs=st.executeQuery(sql);
                    if(rs.next()){
                        if(NamaGambar.getText()!=rs.getString("FOTO_INVENTARIS")){
                            DeleteImage();
                            UploadImage();
                        }
                    }                
                }catch(Exception e){
    //                JOptionPane.showMessageDialog(rootPane, e);
                }
                Update =new Update();
                TabelInventaris();
                Clear();
            }
        }        
    }//GEN-LAST:event_BEditActionPerformed

    private void BHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BHapusActionPerformed
        // TODO add your handling code here:        
        int response=Message.getM_delete();
        if(response==JOptionPane.YES_OPTION){
            DeleteInventaris();
            DeleteImage();
            Delete = new Delete();
            TabelInventaris();
            Clear();
        }
    }//GEN-LAST:event_BHapusActionPerformed

    private void BCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCariActionPerformed
        // TODO add your handling code here:        
        SearchInventaris();
        Clear();
    }//GEN-LAST:event_BCariActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        TabelInventaris();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FDataInventaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FDataInventaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FDataInventaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FDataInventaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FDataInventaris().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BCari;
    private javax.swing.JButton BEdit;
    private javax.swing.JButton BHapus;
    private javax.swing.JButton BSimpan;
    private javax.swing.JButton BTambah;
    private javax.swing.JTextField Cari;
    private javax.swing.JLabel Gambar;
    private javax.swing.JComboBox<String> JenisInventaris;
    private javax.swing.JSpinner Jumlah;
    private javax.swing.JTextField KDInventaris;
    private javax.swing.JTextField Keterangan;
    private javax.swing.JComboBox<String> KondisiInventaris;
    private javax.swing.JTextField NamaGambar;
    private javax.swing.JTextField NamaInventaris;
    private javax.swing.JComboBox<String> RuangInventaris;
    private javax.swing.JTable TabelInventaris;
    private com.toedter.calendar.JDateChooser TanggalRegister;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
