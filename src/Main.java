
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Main extends JFrame implements ActionListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JTextField txtID,txtFname,txtLname,txtMname,txtMajor,txtGpa,txtDoB;
    static JButton btnSearch,btnAdd,btnUpdate;
    static JMenuBar mbapp;
    static JMenu mFile,mManage,mHelp;
    static JMenuItem miAdd,miSearch,miUpdate,miLog,miAbout;
    static JPanel areapanel,bpanel,ipanel;
    
    static String username = "anonymous";
    static String password = "guest";
    static String url = "jdbc:odbc:Students";
    static Statement st;
    static Connection con;

    Main(){
        super("Student Information System");
        mbapp = new JMenuBar();
        mFile = new JMenu("File");
        mManage = new JMenu("Manage");
        mHelp = new JMenu("About");
        miAdd = new JMenuItem("Add Record");
        miSearch = new JMenuItem("Search Student Record");
        miUpdate = new JMenuItem("Update Student Record");
        miAbout = new JMenuItem("About The System");
        miLog = new JMenuItem("Exit");
        mFile.add(miLog);
        mManage.add(miSearch);
        mManage.add(miAdd);
        mManage.add(miUpdate);
        mHelp.add(miAbout);
        mbapp.add(mFile);
        mbapp.add(mManage);
        mbapp.add(mHelp);
        setJMenuBar(mbapp);

        areapanel = new JPanel();
        areapanel.setLayout(new GridLayout(0,2,2,0));
        areapanel.add(new JLabel("STUDENT ID"));
        areapanel.add(txtID = new JTextField(10));
        areapanel.add(new JLabel(""));
        areapanel.add(btnSearch = new JButton("Search"));
        areapanel.add(new JLabel("Last Name:"));
        areapanel.add(txtLname = new JTextField(10));
        areapanel.add(new JLabel("First Name:"));
        areapanel.add(txtFname = new JTextField(10));
        areapanel.add(new JLabel("Middle Name:"));
        areapanel.add(txtMname = new JTextField(10));
        areapanel.add(new JLabel("Major:"));
        areapanel.add(txtMajor = new JTextField(10));
        areapanel.add(new JLabel("DoB:"));
        areapanel.add(txtDoB = new JTextField(10));
        areapanel.add(new JLabel("Gpa:"));
        areapanel.add(txtGpa = new JTextField(10));
  
        
        
        
        
        ipanel = new JPanel();
        ipanel.setLayout(new GridLayout(3,2,10,10));
        ipanel.add(new JLabel(""));
        ipanel.add(new JLabel(""));
        ipanel.add(new JLabel(""));
        ipanel.add(new JLabel(""));
        ipanel.add(new JLabel(""));
        ipanel.add(new JLabel("Developer: Newton & Tolulope.April 20,2014"));

        bpanel = new JPanel();
        bpanel.setLayout(new BorderLayout());
        bpanel.add(areapanel,BorderLayout.NORTH);
        bpanel.add(ipanel,BorderLayout.SOUTH);
        add(bpanel);

        miAdd.addActionListener(this);
        miUpdate.addActionListener(this);

        miSearch.setEnabled(false);
        miLog.addActionListener(this);
        miAbout.addActionListener(this);
        btnSearch.addActionListener(this);
    }

    public static void main(String[] args) {
        Main mainapp = new Main();
        mainapp.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        mainapp.setResizable(false);
        mainapp.setSize(575, 250);
        mainapp.setVisible(true);
      
    }

   public static void retrieveRecords(){
        con = getConnection();
        String fname = null,mname = null, lname = null, Major = null, DoB = null , Gpa = null;
        String query = "Select * from tblStudents where ID like '" + txtID.getText() + "'";
        try{
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                lname = rs.getString("LastName");
                fname = rs.getString("FirstName");
                mname = rs.getString("MiddleName");
                 Major = rs.getString("Major");
                 DoB = rs.getString("DoB");
                 Gpa  = rs.getString("Gpa");
                 
                //result = name;
            }
        st.close();
        con.close();
        }

        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
            txtFname.setText(fname);
            txtMajor.setText(Major);
            txtLname.setText(lname);
            txtMname.setText(mname);
            txtDoB.setText(DoB);
            txtGpa.setText(Gpa);

            if(txtFname.getText().equals("") && txtMname.getText().equals("") && txtLname.getText().equals("") && txtMajor.getText().equals("")&& txtGpa.getText().equals("")&& txtDoB.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Record Not Found!");
                 txtFname.setText("");
                 txtMajor.setText("");
                txtLname.setText("");
                txtMname.setText("");
                txtDoB.setText("");
                txtGpa.setText("");
                txtID.setText("");
            }
            else
                txtFname.setText(fname);
                 txtMajor.setText(Major);
                txtLname.setText(lname);
                txtMname.setText(mname);
                txtDoB.setText(DoB);
                txtGpa.setText(Gpa);
           
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

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == miAdd){
            final AddRecord addapp = new AddRecord();
            addapp.setSize(350, 220);
            addapp.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                   addapp.setVisible(false);
                }
            });
            addapp.setVisible(true);
            addapp.setResizable(false);
        }

        if(e.getSource() == miUpdate){
            final UpdateRecord updateapp = new UpdateRecord();
            updateapp.setSize(350, 250);
            updateapp.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                   updateapp.setVisible(false);
                }
            });
            JOptionPane.showMessageDialog(null," *** If you wish to update any records.\n"+
                                               "First step =====> search for the record you want to update.\n"+
                                               " Step 2======> If you find the record you may proceed by editing the fields and then click Update button to update Thank you .");
            updateapp.setVisible(true);
           // updateapp.setResizable(false);
        }

        if(e.getSource() == miLog){
            JOptionPane.showMessageDialog(null,"Thank You .","Tolulope says . . .",1);
             System.exit(0);
        }

        if(e.getSource() == miAbout){
            final About aboutapp = new About();
            aboutapp.setSize(500, 100);
            aboutapp.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                   aboutapp.setVisible(false);
                }
            });
            aboutapp.setVisible(true);
            aboutapp.setResizable(false);
        }

        if(e.getSource() == btnSearch){
            if(txtID.getText().equals("")){
                JOptionPane.showMessageDialog(null,"no record to search!");
            }
            else{
                 retrieveRecords();
            }
           
            
        }
    }
}
