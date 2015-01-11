
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdateRecord extends JFrame implements ActionListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JTextField txtID,txtFname,txtLname,txtMname,txtMajor,txtGpa,txtDoB;
    static JButton btnUpdate,btnSearch;
    static JPanel areapanel,bpanel,tpanel;
    static String username = "anonymous";
    static String password = "guest";
    static String url = "jdbc:odbc:Students";
    static Statement st;
    static Connection con;
	

    UpdateRecord(){
        super("Search the record to update. . .");
        areapanel = new JPanel();
        tpanel = new JPanel();
        tpanel.setLayout(new GridLayout(0,1));
        tpanel.add(btnUpdate = new JButton("Update Record"));

        areapanel.setLayout(new GridLayout(0,2,0,2));
        areapanel.add(new JLabel("STUDENT ID"));
        areapanel.add(txtID = new JTextField(10));
        areapanel.add(new JLabel(""));
        areapanel.add(btnSearch = new JButton("Search"));
        areapanel.add(new JLabel("Last Name:"));
        areapanel.add(txtLname = new JTextField(10));
        areapanel.add(new Label("First Name:"));
        areapanel.add(txtFname = new JTextField(10));
        areapanel.add(new Label("Middle Name:"));
        areapanel.add(txtMname = new JTextField(10));
        areapanel.add(new JLabel("Major:"));
        areapanel.add(txtMajor = new JTextField(10));
        areapanel.add(new JLabel("Gpa:"));
        areapanel.add(txtGpa = new JTextField(10));
        areapanel.add(new JLabel("DoB:"));
        areapanel.add(txtDoB = new JTextField(10));
        
        
       

        bpanel = new JPanel();
        bpanel.setLayout(new BorderLayout());
        bpanel.add(areapanel,BorderLayout.NORTH);
        bpanel.add(new Label(""),BorderLayout.CENTER);
        bpanel.add(tpanel,BorderLayout.SOUTH);
        
        add(bpanel);
        btnSearch.addActionListener(this);
        btnUpdate.addActionListener(this);
    }

    public static void updateEmployees(){
        Connection con = getConnection();
        String updated = "Update Employees set Name = 'Sowale' where Employee_ID = '" ;
        try{
            st = con.createStatement();
            st.executeUpdate(updated);
            st.close();
            con.close();
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        JOptionPane.showMessageDialog(null,"Update Finished!");
    }

    public static void retrieveRecords(){
        con = getConnection();
        @SuppressWarnings("unused")
		String result = null;
        String fname = null,mname = null, lname = null, Major = null , Gpa = null ,DoB= null ;
        String query = "Select * from tblStudents where ID like '" + txtID.getText() + "'";
        try{
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                lname = rs.getString("LastName");
                fname = rs.getString("FirstName");
                mname = rs.getString("MiddleName");
                Major = rs.getString("Major");
                Gpa= rs.getString("Gpa");
                DoB= rs.getString("DoB");
                
                
                //result = name;
            }
        st.close();
        con.close();
        }
         catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        txtFname.setText(fname);
        txtLname.setText(lname);
        txtMname.setText(mname);
        txtMajor.setText(Major);
        txtGpa.setText(Gpa);
        txtDoB.setText(DoB);

        if(txtFname.getText().equals("") && txtMname.getText().equals("") && txtLname.getText().equals("") && txtMajor.getText().equals("") && txtGpa.getText().equals("")&& txtDoB.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Record Not Found!");
            }
        txtFname.setText("");
        txtLname.setText("");
        txtMname.setText("");
        txtID.setText("");
        txtMajor.setText("");
        txtGpa.setText("");
        txtDoB.setText("");
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

      public static void updateRecords(){
        con = getConnection();
        String update1 = "Update tblStudents set FirstName = '" + txtFname.getText() +"' where ID = '" + txtID.getText() + "'" ;
        String update2 = "Update tblStudents set MiddleName = '" + txtMname.getText() +"' where ID = '" + txtID.getText() + "'" ;
        String update3 = "Update tblStudents set LastName = '" + txtLname.getText() +"' where ID = '" + txtID.getText() + "'" ;
        String update4 = "Update tblStudents set Major = '" +   txtMajor.getText() +"' where ID = '" + txtID.getText() + "'" ;
        String update5 = "Update tblStudents set Gpa = '" +   txtGpa.getText() +"' where ID = '" + txtID.getText() + "'" ;
        String update6 = "Update tblStudents set Gpa = '" +   txtGpa.getText() +"' where ID = '" + txtID.getText() + "'" ;
        
      
        try{
            st = con.createStatement();
            st.executeUpdate(update1);
            st.executeUpdate(update2);
            st.executeUpdate(update3);
            st.executeUpdate(update4);
            st.executeUpdate(update5);
            st.executeUpdate(update6);
            
            st.close();
            con.close();
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        JOptionPane.showMessageDialog(null,"Update Finished!");
        txtFname.setText("");
        txtMajor.setText("");
        txtLname.setText("");
        txtMname.setText("");
        txtGpa.setText("");
        txtDoB.setText("");
        txtID.setText("");
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnSearch){
            retrieveRecords();
        }
        if(e.getSource() == btnUpdate){
            updateRecords();
        }
    }

}
