import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class StudentHomepage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentHomepage frame = new StudentHomepage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public StudentHomepage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
		Object[][] data = {
			    {"Kathy", "Smith",
			     "Snowboarding", new Integer(5), new Boolean(false)},
			    {"John", "Doe",
			     "Rowing", new Integer(3), new Boolean(true)},
			    {"Sue", "Black",
			     "Knitting", new Integer(2), new Boolean(false)},
			    {"Jane", "White",
			     "Speed reading", new Integer(20), new Boolean(true)},
			    {"Joe", "Brown",
			     "Pool", new Integer(10), new Boolean(false)}
			};
		JTable competitionsTable = new JTable(data, columnNames);

		getContentPane().add(competitionsTable);
		competitionsTable.setBounds(60, 48, 299, 156);
		competitionsTable.setBackground(Color.red);
		competitionsTable.setBackground(Color.white);
		competitionsTable.setSelectionBackground(Color.red);
		competitionsTable.setGridColor(Color.red);
		competitionsTable.setSelectionForeground(Color.white);
		competitionsTable.setRowHeight(30);

		JButton RemoveButton = new JButton("Remove Student from Event");
		RemoveButton.setBounds(10, 224, 190, 23);
		contentPane.add(RemoveButton);

		JButton AddEventButton = new JButton("Add Event");
		AddEventButton.setBounds(316, 224, 89, 23);
		contentPane.add(AddEventButton);
	}

}