import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;

public class WarningPopup extends JFrame {

	private JPanel sorrymessage;
private AddStudentToEvent prevWindow;
private WarningPopup ourWindow; 
private String msg;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	
	
	public WarningPopup(AddStudentToEvent Previouswindow, String message) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		sorrymessage = new JPanel();
		sorrymessage.setBackground(new Color(162, 197, 223));
		sorrymessage.setBorder(new EmptyBorder(5, 5, 5, 5));

		ourWindow= this;
		prevWindow= Previouswindow;
		msg= message;
		
		setContentPane(sorrymessage);
		sorrymessage.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(msg);
		lblNewLabel.setFont(new Font("Georgia", Font.PLAIN, 10));
		lblNewLabel.setBounds(28, 38, 396, 14);
		sorrymessage.add(lblNewLabel);
		
		JButton returnbutton = new JButton("OKAY");
		returnbutton.setFont(new Font("Georgia", Font.PLAIN, 11));
		returnbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ourWindow.setVisible(false);
				prevWindow.setVisible(true);
			}
		});
		returnbutton.setBounds(161, 126, 89, 23);
		sorrymessage.add(returnbutton);
	}
}
