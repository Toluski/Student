
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class AddRecord extends JFrame implements ActionListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JTextField txtID,txtFname,txtLname,txtMname,txtMajor,txtGpa,txtDoB;
    static JButton btnAdd;
    static JPanel areapanel,bpanel;
    static String username = "anonymous";
    static String password = "guest";
    static String url = "jdbc:odbc:Students";
    static Statement st;
    static Connection con;

    AddRecord(){
        super("ADD RECORD");
        areapanel = new JPanel();
        areapanel.setLayout(new GridLayout(0,2,0,5));
        areapanel.add(new JLabel("STUDENT ID"));
		
        areapanel.add(txtID = new JTextField(10));
        areapanel.add(new JLabel("Last Name:"));
        areapanel.add(txtLname = new JTextField(10));
        areapanel.add(new JLabel("First Name:"));
        areapanel.add(txtFname = new JTextField(10));
        areapanel.add(new JLabel("Middle Name:"));
        areapanel.add(txtMname = new JTextField(10));
        areapanel.add(new JLabel("Major:"));
        areapanel.add(txtMajor = new JTextField(10));
        areapanel.add(new JLabel("Gpa:"));
        areapanel.add(txtGpa = new JTextField(10));
        areapanel.add(new JLabel("DoB:"));
        areapanel.add(txtDoB = new JTextField(10));
         areapanel.add(new JLabel(""));
         
        areapanel.add(btnAdd = new JButton("Add Record"));

        bpanel = new JPanel();
        bpanel.setLayout(new BorderLayout());
        bpanel.add(areapanel,BorderLayout.NORTH);
        add(bpanel);
        

        btnAdd.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnAdd){
            insertRecord();
        }
    }

    public static Connection getConnection(){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection(url,username,password);
        }
        catch(java.lang.ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return con;
    }

     public static void insertRecord(){
        con = getConnection();
        String q = "Insert into tblStudents Values('" + txtID.getText() +"','" + txtLname.getText() + "','" + txtFname.getText() + "','" + txtMname.getName() + "','" + txtMajor.getText() + "')";
        try{
            st= con.createStatement();
            st.executeUpdate(q);
            st.close();
            con.close();
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        JOptionPane.showMessageDialog(null,"Successfully inserted a record!");
        txtFname.setText("");
        txtLname.setText("");
        txtMname.setText("");
        txtID.setText("");
        txtMajor.setText("");
        txtGpa.setText("");
        txtDoB.setText("");
        
     }
}
