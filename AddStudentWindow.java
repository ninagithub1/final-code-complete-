import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

public class AddStudentWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldLastName;
	private JTextField textFieldFirstName;
	private AddStudentWindow StudentWindow;
	private ArrayList<Student> studentList = new ArrayList<Student>();
	private DefaultTableModel model;
	private DatabaseFolder data;
	private TestFrame myTest;

	/**
	 * Launch the application. also- remove all these extra parameters
	 */

	/**
	 * Create the frame.
	 */
	public AddStudentWindow(ArrayList<Student> list, DefaultTableModel mod, TestFrame test, DatabaseFolder dataList) {
		data = dataList;
		model = mod;
		myTest = test;
		StudentWindow = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 323, 244);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(162, 197, 223));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Georgia", Font.PLAIN, 11));
		lblLastName.setBounds(47, 59, 81, 13);
		contentPane.add(lblLastName);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Georgia", Font.PLAIN, 11));
		lblFirstName.setBounds(47, 22, 81, 13);
		contentPane.add(lblFirstName);

		textFieldLastName = new JTextField();
		textFieldLastName.setBounds(123, 55, 96, 19);
		contentPane.add(textFieldLastName);
		textFieldLastName.setColumns(10);

		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(123, 22, 96, 19);
		contentPane.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);

		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setFont(new Font("Georgia", Font.PLAIN, 11));
		lblGrade.setBounds(20, 123, 45, 13);
		contentPane.add(lblGrade);

		JRadioButton rdbtn9th = new JRadioButton("9th");
		rdbtn9th.setFont(new Font("Georgia", Font.PLAIN, 11));
		rdbtn9th.setBounds(71, 119, 56, 21);
		contentPane.add(rdbtn9th);

		JRadioButton rdbtn10th = new JRadioButton("10th");
		rdbtn10th.setFont(new Font("Georgia", Font.PLAIN, 11));
		rdbtn10th.setBounds(123, 119, 65, 21);
		contentPane.add(rdbtn10th);

		JRadioButton rdbtn11th = new JRadioButton("11th");
		rdbtn11th.setFont(new Font("Georgia", Font.PLAIN, 11));
		rdbtn11th.setBounds(184, 119, 65, 21);
		contentPane.add(rdbtn11th);

		JRadioButton rdbtn12th = new JRadioButton("12th");
		rdbtn12th.setFont(new Font("Georgia", Font.PLAIN, 11));
		rdbtn12th.setBounds(247, 119, 65, 21);
		contentPane.add(rdbtn12th);

		ButtonGroup grades = new ButtonGroup();
		grades.add(rdbtn12th);
		grades.add(rdbtn11th);
		grades.add(rdbtn10th);
		grades.add(rdbtn9th);

		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Georgia", Font.PLAIN, 11));
		btnSave.setBounds(210, 165, 85, 21);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String last = textFieldLastName.getText();
				String first = textFieldFirstName.getText();
				String grade = "";
				Enumeration<AbstractButton> buttons = grades.getElements();
				while (buttons.hasMoreElements()) {
					AbstractButton button = buttons.nextElement();
					if (button.isSelected()) {
						grade = button.getText();
					}
				}
				String errorMessage = "";
				if (last.equals("")) {
					errorMessage += "Please enter a last name";
				}
				if (first.equals("")) {
					errorMessage += "\nPlease enter a first name";
				}
				if (grade.equals("")) {
					errorMessage += "\nPlease choose a grade level";
				}
				if (!errorMessage.equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
				}
				if (!(last.equals("") || first.equals("") || grade.equals(""))) 
				{
					Student stu = new Student(last, first, grade);
					data.addStudent(stu); 
					textFieldLastName.setText(""); 
					textFieldFirstName.setText("");
					grades.clearSelection();
					StudentWindow.setVisible(false);
					test.reloadStudentTable();    
					test.setVisible(true);
				}

			}
		});
		contentPane.add(btnSave);
		
		JButton Cancel = new JButton("Cancel");
		Cancel.setFont(new Font("Georgia", Font.PLAIN, 11));
		Cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldLastName.setText(""); 
				textFieldFirstName.setText("");
				grades.clearSelection();
				StudentWindow.setVisible(false);
				test.setVisible(true);
			}
		});
		Cancel.setBounds(34, 164, 89, 23);
		contentPane.add(Cancel);
	}
}