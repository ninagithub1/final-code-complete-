import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EventInfo extends JFrame {

	private JPanel contentPane;
	private CompetitionsDatabase globalData; 
	
	public EventInfo(CompetitionsDatabase compData, Event eve, Integer location) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 709, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		globalData= compData;

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel CurrentStudents = new JLabel("current students in this event:");
		CurrentStudents.setBounds(70, 50, 260, 14);
		contentPane.add(CurrentStudents);
	}
}