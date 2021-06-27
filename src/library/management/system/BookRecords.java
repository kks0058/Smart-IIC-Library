
package library.management.system;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import net.proteanit.sql.DbUtils;
import java.sql.*;
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

public class BookRecords extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTable table;
    private JTextField search;
    private JButton b1,b2,b3;

    public static void main(String[] args) {
	new BookRecords().setVisible(true);
    }  
       public BookRecords() {
                    super("IIC Smart Library v1.1   -Admin");
        setBounds(300, 150, 920, 485);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(79, 133, 771, 282);
	contentPane.add(scrollPane);

	table = new JTable();
	table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int row = table.getSelectedRow();
		search.setText(table.getModel().getValueAt(row, 1).toString());
            }
	});
	table.setBackground(new Color(240, 248, 255));
	table.setForeground(Color.DARK_GRAY);
	table.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
	scrollPane.setViewportView(table);

	 b1 = new JButton("Search");
	b1.addActionListener(this);
	b1.setBorder(new LineBorder(new Color(255, 20, 147), 2, true));
	ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/eight.png"));
        Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        b1.setIcon(i3);
	b1.setForeground(new Color(199, 21, 133));
	b1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
	b1.setBounds(564, 89, 138, 33);
	contentPane.add(b1);

	 b2 = new JButton("Export");
	b2.addActionListener(this);
	ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/nineth.png"));
        Image i5 = i4.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        b2.setIcon(i6);
        b2.setForeground(new Color(199, 21, 133));
	b2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
	b2.setBorder(new LineBorder(new Color(255, 20, 147), 2, true));
	b2.setBounds(712, 89, 138, 33);
	contentPane.add(b2);


	JLabel l1 = new JLabel("Book Details");
	l1.setForeground(new Color(107, 142, 35));
	l1.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 30));
	l1.setBounds(300, 15, 400, 47);
	contentPane.add(l1);

        
	search = new JTextField();
	search.setBackground(new Color(255, 240, 245));
	search.setBorder(new LineBorder(new Color(255, 105, 180), 2, true));
	search.setForeground(new Color(47, 79, 79));
	search.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 17));
	search.setBounds(189, 89, 357, 33);
	contentPane.add(search);
	search.setColumns(10);

	JLabel l3 = new JLabel("Back");
	l3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Admin home = new Admin();
				home.setVisible(true);
			}
		});
	l3.setForeground(Color.GRAY);
	l3.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
	ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/tenth.png"));
        Image i8 = i7.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        l3.setIcon(i9);
	l3.setBounds(97, 89, 72, 33);
	contentPane.add(l3);

	JPanel panel = new JPanel();
	panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 128, 128), 3, true), "Book-Details",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 0)));
	panel.setBounds(30, 54, 850, 375);
	contentPane.add(panel);
        panel.setBackground(Color.WHITE);
	Book();
    }
           public void Book() {
	try {
            conn con = new conn();
            String sql = "select * from book";
            PreparedStatement st = con.c.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            table.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
            st.close();
            con.c.close();
	} catch (Exception e) {
	
	}
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
             XSSFSheet excelsheet= excelJTableExporter.createSheet("Book Records");
             for(int i=0; i<table.getRowCount(); i++){
                 XSSFRow excelRow = excelsheet.createRow(i);
                 for(int j=0; j<table.getColumnCount(); j++){
                     XSSFCell excelCell = excelRow.createCell(j);
                     excelCell.setCellValue(table.getValueAt(i,j).toString());
                 }
             }        excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile() +".xlsx");
              excelBOU = new BufferedOutputStream(excelFOU);
             excelJTableExporter.write(excelBOU);
             JOptionPane.showMessageDialog(null,"Book Details Exported Successfully.");
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
                      if(ae.getSource() == b2){
                  exportDataToExcel();
                  }     
  
    }
}
