import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;



public class CancelPopup extends JFrame {

	private JPanel contentPane;
	private CancelPopup window; 
	private TestFrame ogTest;
	private CompetitionWindow oldComp;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 * 
	 * public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CancelPopup frame = new CancelPopup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 */
	public CancelPopup(TestFrame test, CompetitionWindow compWindow) {

		window = this;
		ogTest = test;
		oldComp = compWindow;


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 308, 190);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(162, 197, 223));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel CancelLAbel = new JLabel("Are you sure you want to cancel?");
		CancelLAbel.setFont(new Font("Georgia", Font.PLAIN, 11));
		CancelLAbel.setBounds(52, 32, 200, 46);
		contentPane.add(CancelLAbel);

		JButton YesButton = new JButton("Yes");
		YesButton.setFont(new Font("Georgia", Font.PLAIN, 11));
		YesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				window.setVisible(false); 
				ogTest.setVisible(true);
				oldComp.setVisible(false);

			}
		});
		YesButton.setBounds(38, 89, 89, 23);
		contentPane.add(YesButton);

		JButton NoButton = new JButton("No");
		NoButton.setFont(new Font("Georgia", Font.PLAIN, 11));
		NoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				window.setVisible(false); 
			}
		});
		NoButton.setBounds(151, 89, 89, 23);
		contentPane.add(NoButton);
	}

}


/*
EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {  
							CompetitionWindow frame = new CompetitionWindow();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
*/