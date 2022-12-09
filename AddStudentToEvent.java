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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class AddStudentToEvent extends JFrame {

	private JPanel contentPane;
	private ArrayList<Student> globalList = new ArrayList<Student>();
	private Event current;
	private Competition ourComp;
	private AddStudentToEvent ourFrame; 
	private CompInfoDisplay previousFrame; 

	public AddStudentToEvent(Event eve, CompetitionsDatabase comps, Competition currentComp, CompInfoDisplay frame) {
		ourComp = currentComp;
		current = eve;
		ourFrame = this; 
		previousFrame = frame; 

		contentPane = new JPanel();
		contentPane.setBackground(new Color(162, 197, 223));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		DefaultTableModel myStudentModel = new DefaultTableModel(new String[] { "Last Name", "First Name", "Grade" },
				0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JTable table = new JTable(myStudentModel); 

		JScrollPane scrollPane = new JScrollPane(table);

		scrollPane.setBounds(276, 70, 316, 253);
		contentPane.add(scrollPane);
		table.setModel(myStudentModel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 432);
		getContentPane().setLayout(null);
		
		JLabel DoubleClick = new JLabel("DOUBLE-CLICK ON A STUDENT BELOW TO ADD TO THE EVENT");
		DoubleClick.setFont(new Font("Georgia", Font.PLAIN, 18));
		DoubleClick.setBounds(10, 29, 648, 30);
		contentPane.add(DoubleClick);
		
		JButton Removefromevent = new JButton("Remove All Students From Event");
		Removefromevent.setHorizontalAlignment(SwingConstants.LEADING);
		Removefromevent.setFont(new Font("Georgia", Font.PLAIN, 20));
		Removefromevent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				current.clearStudentList();
				//remove the students from the set
				comps.refreshStudentFile();
			}
		});
		Removefromevent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Removefromevent.setBounds(30, 333, 422, 49);
		contentPane.add(Removefromevent);
		
		JButton Cancel = new JButton("Cancel");
		Cancel.setFont(new Font("Georgia", Font.PLAIN, 14));
		Cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				previousFrame.setVisible(true);
				ourFrame.setVisible(false);
				
			}
		});
		Cancel.setBounds(25, 141, 241, 49);
		contentPane.add(Cancel);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		while (myStudentModel.getRowCount() > 0) {
			myStudentModel.removeRow(0);
		}
		Scanner infile;
		ArrayList<Student> temp = new ArrayList<Student>();
		try {
			infile = new Scanner(new File("students.txt")); 
			while (infile.hasNextLine()) {
				String last = infile.nextLine();
				String first = infile.nextLine();
				String grade = infile.nextLine();
				Student stu = new Student(last, first, grade); 
				int index = stu.findLoc(stu, temp);
				temp.add(index, stu); 
			}
		} catch (Exception error) {
			System.out.println(error.getMessage());
		}
		globalList = temp;

		for (int i = 0; i < temp.size(); i++) {
			Student current = temp.get(i);
			myStudentModel.insertRow(i, current.getStudentInfo());
		}
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) {
					int row = table.getSelectedRow();
					Student selected = globalList.get(row); //tanya student
					 //tanya in anatomy
					boolean checker = currentComp.addStudentToSetBlock(selected, current.getTimeBlock()); 
					//add her to the set
					if(checker)
					{
					current.addStudentToEvent(selected);
					comps.refreshStudentFile();
					previousFrame.setVisible(true);
					ourFrame.setVisible(false);
					previousFrame.refreshTable();
					}
					else
					{
						String message = "Sorry, this student has already been added to an event in time block "+ current.getTimeBlock();
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									WarningPopup frame = new WarningPopup(ourFrame, message);
									frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
					
				}
			}
		});
	}
}