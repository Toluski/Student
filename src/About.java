
import java.awt.*;


import javax.swing.*;

public class About extends JFrame{




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	About(){
          super("Student Information Application");
          setLayout(new GridLayout(3,2));
          add(new JLabel("Developer: "));
          add(new JLabel("Tolulope"));
          add(new JLabel("Location: "));
          add(new JLabel("LAMBTON COLLEGE =>TORONTO CAMPUS"));
          add(new JLabel("Instructor:"));
          add(new JLabel("Mr Ylber Ramadani"));
      }
}
