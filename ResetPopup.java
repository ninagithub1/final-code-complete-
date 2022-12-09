import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.awt.Color;
import java.awt.Font;

public class ResetPopup extends JFrame {

	private JPanel contentPane;


	public static void main(String[] args) {
		
	}


	public ResetPopup(TestFrame previous) {
		ResetPopup ourFrame = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(162, 197, 223));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextPane txtpnDsfadsa = new JTextPane();
		txtpnDsfadsa.setText("dsfadsa");
		txtpnDsfadsa.setBounds(299, 101, -178, 20);
		contentPane.add(txtpnDsfadsa);

		JLabel ResetLabel = new JLabel("Are you sure you want to reset this program? ");
		ResetLabel.setFont(new Font("Georgia", Font.PLAIN, 11));
		ResetLabel.setBounds(62, 59, 309, 46);
		contentPane.add(ResetLabel);

		JButton YesButton = new JButton("Yes");
		YesButton.setFont(new Font("Georgia", Font.PLAIN, 11));
		YesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				previous.setVisible(true);
				ourFrame.setVisible(false);
				 try 
				    {
				    FileWriter fw = new FileWriter("competitions.txt", false);
				    PrintWriter pw = new PrintWriter(fw, false);
				    pw.flush();
				    pw.close();
				    fw.close();
				    FileWriter fw2 = new FileWriter("students.txt", false);
				    PrintWriter pw2 = new PrintWriter(fw, false);
				    pw2.flush();
				    pw2.close();
				    fw2.close();
				    previous.reloadCompetitionsTable();
				    previous.reloadStudentTable();
				    previous.clearLists(); 
				   System.out.println("FILE SUCCESSFULLY CLEARED");
				    }
				    catch(Exception exception)
				    {
				        System.out.println("Exception have been caught");
				    } 
				 
				 try 
				    {
				    FileWriter fw = new FileWriter("students.txt", false);
				    PrintWriter pw = new PrintWriter(fw, false);
				    pw.flush();
				    pw.close();
				    fw.close();
				   System.out.println("FILE SUCCESSFULLY CLEARED");
				    }
				    catch(Exception exception)
				    {
				        System.out.println("Exception have been caught");
				    } 
			}
		});
		YesButton.setBounds(82, 187, 89, 23);
		contentPane.add(YesButton);

		JButton NoButton = new JButton("No");
		NoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				previous.setVisible(true);
				ourFrame.setVisible(false);
				
			}
		});
		NoButton.setFont(new Font("Georgia", Font.PLAIN, 11));
		NoButton.setBounds(234, 187, 89, 23);
		contentPane.add(NoButton);
		
		JLabel lblNewLabel = new JLabel("This will permanently clear all of the scheduled competitions. ");
		lblNewLabel.setFont(new Font("Georgia", Font.PLAIN, 11));
		lblNewLabel.setBounds(23, 101, 376, 14);
		contentPane.add(lblNewLabel);
	}
}
