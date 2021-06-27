
package library.management.system;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class IRReport extends JFrame implements ActionListener  {
    
    private JPanel contentPane;
    private JTable table;
    private JTable table_1;
    private JButton b1,b2;    
    public static void main(String[] args) {
        new IRReport().setVisible(true);
    }

    public void issueBook() {
	try {
            conn con =  new conn();
            String sql = "select * from issueBook";
            PreparedStatement st = con.c.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            table.setModel(DbUtils.resultSetToTableModel(rs));

	} catch (Exception e) {
			// TODO: handle exception
	}
    }
   public void returnBook() {
        try {
            conn con = new conn();
            String sql = "select * from returnBook";
            PreparedStatement st = con.c.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            table_1.setModel(DbUtils.resultSetToTableModel(rs));
	} catch (Exception e) {
			// TODO: handle exception
	}
    }
    public IRReport() {
                 super("IIC Smart Library v1.1   -Admin");
        setBounds(375, 100, 795, 590);
	contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(40, 51, 708, 217);
	contentPane.add(scrollPane);

        table = new JTable();
	table.setBackground(new Color(224, 255, 255));
	table.setForeground(new Color(128, 128, 0));
	table.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
	scrollPane.setViewportView(table);

	JPanel panel = new JPanel();
	panel.setBorder(new TitledBorder(new LineBorder(new Color(47, 79, 79), 2, true), "Issue-Book-Details",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 128)));
	panel.setForeground(new Color(0, 128, 128));
	panel.setBounds(26, 36, 737, 240);
        panel.setBackground(Color.WHITE);
	contentPane.add(panel);

	JLabel l1 = new JLabel("Back");
	l1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
		setVisible(false);
		Admin home = new Admin();
		home.setVisible(true);
            }
	});
	l1.setForeground(new Color(204, 0, 102));
	l1.setFont(new Font("Tahoma", Font.BOLD, 18));
	ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/tenth.png"));
        Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        l1.setIcon(i3);
	l1.setBounds(690, 13, 96, 27);
	contentPane.add(l1);

	JScrollPane scrollPane_1 = new JScrollPane();
	scrollPane_1.setBounds(40, 316, 717, 217);
	contentPane.add(scrollPane_1);
        
        b1 = new JButton("Export Issueed Book");
	b1.addActionListener(this);
	ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/Tenth.png"));
        Image i5 = i4.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        b1.setIcon(i6);
        b1.setForeground(new Color(199, 21, 133));
	b1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
	b1.setBorder(new LineBorder(new Color(233, 25, 200), 3, true));
	b1.setBounds(150, 7, 235, 33);
	contentPane.add(b1);
        
     b2 = new JButton("Export Received Book");
	b2.addActionListener(this);
	ImageIcon i8 = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/Tenth.png"));
        Image i7 = i8.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i7);
        b2.setIcon(i9);
        b2.setForeground(new Color(199, 21, 133));
	b2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
	b2.setBorder(new LineBorder(new Color(233, 25, 200), 3, true));
	b2.setBounds(425, 7, 235, 33);
	contentPane.add(b2);

	table_1 = new JTable();
	table_1.setBackground(new Color(204, 255, 255));
	table_1.setForeground(new Color(153, 51, 0));
	table_1.setFont(new Font("Sitka Small", Font.BOLD, 12));
	scrollPane_1.setViewportView(table_1);

	JPanel panel_1 = new JPanel();
	panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 204, 153), 2, true), "Return-Book-Details",
		TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 102, 51)));
	panel_1.setBounds(22, 299, 741, 240);
        panel_1.setBackground(Color.WHITE);
	contentPane.add(panel_1);
       	issueBook();
	returnBook(); 

}
        private void exportDataToExcel() {
     FileOutputStream excelFOU = null;
     BufferedOutputStream excelBOU = null;
     XSSFWorkbook excelJTableExporter = null;
     
     JFileChooser excelFileChooser = new JFileChooser("C:\\Users\\KDHR\\Desktop\\Excel");
     excelFileChooser.setDialogTitle("Save As:");
     FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx","xlsm");
     excelFileChooser.setFileFilter(fnef);
    int excelChooser = excelFileChooser.showSaveDialog(null); 
 
    if (excelChooser == JFileChooser.APPROVE_OPTION){

         try {
            excelJTableExporter = new XSSFWorkbook();
             XSSFSheet excelsheet= excelJTableExporter.createSheet("Issued Books");
             for(int i=0; i<table.getRowCount(); i++){
                 XSSFRow excelRow = excelsheet.createRow(i);
                 for(int j=0; j<table.getColumnCount(); j++){
                     XSSFCell excelCell = excelRow.createCell(j);
                     excelCell.setCellValue(table.getValueAt(i,j).toString());
                 }
             }        excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile() +".xlsx");
              excelBOU = new BufferedOutputStream(excelFOU);
             excelJTableExporter.write(excelBOU);
             JOptionPane.showMessageDialog(null,"Issued Book Exported Successfully.");
         } catch (FileNotFoundException ex) {
             ex.printStackTrace();
         } catch (IOException ex) {
             ex.printStackTrace();
         } finally {
             try {
                 
                 if(excelBOU != null){
                 excelBOU.close();}
                                  
                 if(excelFOU != null){
                 excelFOU.close();}
                 

                 if(excelJTableExporter != null){
                 excelJTableExporter.close();}
                 
                 
             } catch (IOException ex) {
                 ex.printStackTrace();
             }
         }
    }
 
 } 
        private void exportDataToExcel1() {
     FileOutputStream excelFOU = null;
     BufferedOutputStream excelBOU = null;
     XSSFWorkbook excelJTableExporter = null;
     
     JFileChooser excelFileChooser = new JFileChooser("C:\\Users\\KDHR\\Desktop\\Excel");
     excelFileChooser.setDialogTitle("Save As:");
     FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx","xlsm");
     excelFileChooser.setFileFilter(fnef);
    int excelChooser = excelFileChooser.showSaveDialog(null); 
 
    if (excelChooser == JFileChooser.APPROVE_OPTION){

         try {
            excelJTableExporter = new XSSFWorkbook();
             XSSFSheet excelsheet= excelJTableExporter.createSheet("Received Books");
             for(int i=0; i<table_1.getRowCount(); i++){
                 XSSFRow excelRow = excelsheet.createRow(i);
                 for(int j=0; j<table_1.getColumnCount(); j++){
                     XSSFCell excelCell = excelRow.createCell(j);
                     excelCell.setCellValue(table_1.getValueAt(i,j).toString());
                 }
             }        excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile() +".xlsx");
              excelBOU = new BufferedOutputStream(excelFOU);
             excelJTableExporter.write(excelBOU);
             JOptionPane.showMessageDialog(null,"Returned Book Exported Successfully.");
         } catch (FileNotFoundException ex) {
             ex.printStackTrace();
         } catch (IOException ex) {
             ex.printStackTrace();
         } finally {
             try {
                 
                 if(excelBOU != null){
                 excelBOU.close();}
                                  
                 if(excelFOU != null){
                 excelFOU.close();}
                 

                 if(excelJTableExporter != null){
                 excelJTableExporter.close();}
                 
                 
             } catch (IOException ex) {
                 ex.printStackTrace();
             }
         }
    }
 
 }     
        
        public void actionPerformed(ActionEvent ae){
                      if(ae.getSource() == b1){
                  exportDataToExcel();
                  }     
                  if(ae.getSource() == b2){
                  exportDataToExcel1();
                  }  
    }
}

