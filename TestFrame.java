import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import java.util.*;
import java.io.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

public class TestFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldLastName;
	private JTextField textFieldFirstName;
	private JTextField textFieldID;
	private TestFrame testFr;
	private ArrayList<Student> studentList = new ArrayList<Student>();
	private DefaultTableModel model = new DefaultTableModel(new String[] { "Last Name", "First Name", "Grade" }, 0);
	private DefaultTableModel globalStudentModel;
	private DefaultTableModel globalCompetitionsModel;

	private DatabaseFolder data = new DatabaseFolder();
	private CompetitionsDatabase compData = new CompetitionsDatabase();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestFrame() {

		testFr = this;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 695);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(162, 197, 223));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
		scrollPane.setBounds(398, 58, 292, 253);
		contentPane.add(scrollPane);

		table.setModel(myStudentModel);
		globalStudentModel = myStudentModel;

		AddStudentWindow StudentAddFrame = new AddStudentWindow(studentList, model, testFr, data);
		StudentAddFrame.setVisible(false);

		JButton AddStudentWindow = new JButton("Add Student");
		AddStudentWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		AddStudentWindow.setFont(new Font("Georgia", Font.PLAIN, 11));
		AddStudentWindow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							StudentAddFrame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
						testFr.setVisible(false);
					}
				});

			}

		});

		DefaultTableModel myCompetitionsModel = new DefaultTableModel(new String[] { "Name", "Location", "Date" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;

			}
		};

		JTable competitionsTable = new JTable(myCompetitionsModel);
		JScrollPane CompScrollPane = new JScrollPane(competitionsTable);
		CompScrollPane.setBounds(128, 362, 441, 253);
		contentPane.add(CompScrollPane);

		competitionsTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) {
					int row = competitionsTable.getSelectedRow();
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								CompInfoDisplay frame = new CompInfoDisplay(compData, row, testFr);
								frame.setVisible(true);
								testFr.setVisible(false);

							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});

		competitionsTable.setModel(myCompetitionsModel);
		globalCompetitionsModel = myCompetitionsModel;

		CompetitionWindow CompetitionAddFrame = new CompetitionWindow(testFr, compData);
		CompetitionAddFrame.setVisible(false);

		loadList();

		AddStudentWindow.setBounds(64, 156, 188, 40);
		contentPane.add(AddStudentWindow);

		JButton AddCompButton = new JButton("Add Competition");
		AddCompButton.setFont(new Font("Georgia", Font.PLAIN, 11));
		AddCompButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							CompetitionAddFrame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				testFr.setVisible(false);
			}
		});

		AddCompButton.setBounds(64, 72, 188, 40);
		contentPane.add(AddCompButton);

		JButton reset = new JButton("Reset");
		reset.setFont(new Font("Georgia", Font.PLAIN, 11));
		reset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								ResetPopup frame = new ResetPopup(testFr);
								frame.setVisible(true);
								testFr.setVisible(false);
								} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		reset.setBounds(64, 241, 188, 40);
		contentPane.add(reset);
		
		JLabel lblNewLabel = new JLabel("Science Olympiad Competition Manager");
		lblNewLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblNewLabel.setBounds(245, 20, 349, 14);
		contentPane.add(lblNewLabel);
		
		

	}

	public void loadListAddCompetitionFunction() {
		while (globalStudentModel.getRowCount() > 0) { // A
			globalStudentModel.removeRow(0);
		}
		Scanner infile;
		ArrayList<Student> temp = new ArrayList<Student>();

		try {
			File myObj = new File("students.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

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

		for (int i = 0; i < temp.size(); i++) {
			Student current = temp.get(i);
			globalStudentModel.insertRow(i, current.getStudentInfo());
		}
		while (globalCompetitionsModel.getRowCount() > 0) { // 1) clear the competitions row (A)
			globalCompetitionsModel.removeRow(0);
		}
		ArrayList<Competition> compList = new ArrayList<Competition>(); // 2) create a new competition array list to
																		// read in the file
		Scanner infile2;

		try {
			File myObj = new File("competitions.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		try {
			infile2 = new Scanner(new File("competitions.txt")); // B

			while (infile2.hasNextLine()) {

				String CompetitionSingleNumberDate = infile2.nextLine();
				System.out.println(CompetitionSingleNumberDate);
				String myCompetititonName = infile2.nextLine();
				String myCompetitionLocation = infile2.nextLine();
				String year = CompetitionSingleNumberDate.substring(0, 4);
				String month = CompetitionSingleNumberDate.substring(4, 6);
				String day = CompetitionSingleNumberDate.substring(6, 8);
				String monthSeg = month.substring(0, 1);
				if (monthSeg.equals("0")) {
					month = month.substring(1); // 1 take in the location info
				}
				String daySeg = day.substring(0, 1);
				if (daySeg.equals("0")) {
					day = day.substring(1);
				}
				Competition comp = new Competition(myCompetititonName, Integer.valueOf(month), Integer.valueOf(day),
						Integer.valueOf(year), myCompetitionLocation); // C
				int index = comp.findLoc(comp, compList); // 2 find the location in the arraylist
				// System.out.print(index);
				compList.add(index, comp); // 3 add it to our temp array list //D
				System.out.println(" a new competition has been added");
				/*
				 * if (infile2.hasNextLine()) { //3) check to make sure there's an event list?
				 */

				while (infile2.hasNextLine()) {
					String read = infile2.nextLine();
					if (read.equals("EVENT LIST COMPLETE") || read.equals("STUDENT LIST COMPLETE")) {
						System.out.println("first loop broken");
						break;
					}
					// Competition tempCompetition = compList.get(index);
					String eventName = read.substring(0, read.length() - 1);
					String timeBlock = read.substring(read.length() - 1, read.length());
					Integer block = Integer.valueOf(timeBlock);
					Event tempEvent = new Event(eventName, block);
					comp.addEvent(tempEvent);
					// System.out.println("this point has been reached");
					/*
					 * String studentListCheck = infile2.nextLine();
					 * while(!(infile2.nextLine().equals("STUDENT LIST COMPLETE"))) { String
					 * firstName = }
					 */
					ArrayList<Student> tempStudentList = new ArrayList<Student>();

					while (infile2.hasNextLine()) {
						String studentLastName = infile2.nextLine();
						if (studentLastName.equals("STUDENT LIST COMPLETE")) {
							break;
						}

						String first = infile2.nextLine();
						System.out.print(first);
						String grade = infile2.nextLine();
						Student stu = new Student(studentLastName, first, grade); // C
						int index1 = stu.findLoc(stu, tempStudentList);
						tempStudentList.add(index1, stu); 
						tempEvent.setNewStudentList(tempStudentList);
						comp.addStudentToSetBlock(stu, block);
					}

				
				}
				System.out.println("NOT REACHING HERE");

				
				System.out.println("the competition has been added");
			}
			// compData.setCompetitionData(compList);
			// System.out.println("the numer of events in the first event is"+
			// compData.getCompetitionData().get(0).getEventList().size());
		} catch (Exception error) {
			System.out.println(error.getMessage());
		}
		for (int i = 0; i < compList.size(); i++) {
			Competition current = compList.get(i);
			globalCompetitionsModel.insertRow(i, current.getCompetitionFormattedInfo());
		}
	}

	public void loadList() {
		while (globalStudentModel.getRowCount() > 0) { // A
			globalStudentModel.removeRow(0);
		}
		Scanner infile;
		ArrayList<Student> temp = new ArrayList<Student>();

		try {
			File myObj = new File("students.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

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

		for (int i = 0; i < temp.size(); i++) {
			Student current = temp.get(i);
			globalStudentModel.insertRow(i, current.getStudentInfo());
		}
		while (globalCompetitionsModel.getRowCount() > 0) { // 1) clear the competitions row (A)
			globalCompetitionsModel.removeRow(0);
		}
		ArrayList<Competition> compList = new ArrayList<Competition>(); 
																		
		Scanner infile2;

		try {
			File myObj = new File("competitions.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		try {
			infile2 = new Scanner(new File("competitions.txt")); 

			while (infile2.hasNextLine()) {

				String CompetitionSingleNumberDate = infile2.nextLine();
				System.out.println(CompetitionSingleNumberDate);
				String myCompetititonName = infile2.nextLine();
				String myCompetitionLocation = infile2.nextLine();
				String year = CompetitionSingleNumberDate.substring(0, 4);
				String month = CompetitionSingleNumberDate.substring(4, 6);
				String day = CompetitionSingleNumberDate.substring(6, 8);
				String monthSeg = month.substring(0, 1);
				if (monthSeg.equals("0")) {
					month = month.substring(1); 
				}
				String daySeg = day.substring(0, 1);
				if (daySeg.equals("0")) {
					day = day.substring(1);
				}
				Competition comp = new Competition(myCompetititonName, Integer.valueOf(month), Integer.valueOf(day),
						Integer.valueOf(year), myCompetitionLocation); 
				int index = comp.findLoc(comp, compList); 
				
				compList.add(index, comp);
				System.out.println(" a new competition has been added");
				
				while (infile2.hasNextLine()) {
					String read = infile2.nextLine();
					if (read.equals("EVENT LIST COMPLETE") || read.equals("STUDENT LIST COMPLETE")) {
						System.out.println("first loop broken");
						break;
					}
					
					String eventName = read.substring(0, read.length() - 1);
					String timeBlock = read.substring(read.length() - 1, read.length());
					Integer block = Integer.valueOf(timeBlock);
					Event tempEvent = new Event(eventName, block);
					comp.addEvent(tempEvent);
					
					ArrayList<Student> tempStudentList = new ArrayList<Student>();

					while (infile2.hasNextLine()) {
						String studentLastName = infile2.nextLine();
						if (studentLastName.equals("STUDENT LIST COMPLETE")) {
							break;
						}

						String first = infile2.nextLine();
						System.out.print(first);
						String grade = infile2.nextLine();
						Student stu = new Student(studentLastName, first, grade); // C
						int index1 = stu.findLoc(stu, tempStudentList);

						tempStudentList.add(index1, stu); 

						tempEvent.setNewStudentList(tempStudentList);

						comp.addStudentToSetBlock(stu, block);

					}

				}
				System.out.println("NOT REACHING HERE");

				compData.addWithoutRefresh(comp); 
				System.out.println("the competition has been added");
			}
			
		} catch (Exception error) {
			System.out.println(error.getMessage());
		}
		for (int i = 0; i < compList.size(); i++) {
			Competition current = compList.get(i);
			globalCompetitionsModel.insertRow(i, current.getCompetitionFormattedInfo());
		}
		
		if(compList.isEmpty())
		{
			JLabel clickbutton = new JLabel("Click Below To Get Started");
			clickbutton.setBounds(64, 47, 188, 14);
			contentPane.add(clickbutton);
		}
	}

	public void reloadStudentTable() {
		while (globalStudentModel.getRowCount() > 0) { // A
			globalStudentModel.removeRow(0);
		}
		Scanner infile;
		ArrayList<Student> temp = new ArrayList<Student>();

		try {
			File myObj = new File("students.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		try {
			infile = new Scanner(new File("students.txt")); // B
			while (infile.hasNextLine()) {
				String last = infile.nextLine();
				String first = infile.nextLine();
				String grade = infile.nextLine();
				Student stu = new Student(last, first, grade); // C
				int index = stu.findLoc(stu, temp);
				temp.add(index, stu); // D
			}
		} catch (Exception error) {
			System.out.println(error.getMessage());
		}

		for (int i = 0; i < temp.size(); i++) {
			Student current = temp.get(i);
			globalStudentModel.insertRow(i, current.getStudentInfo());
		}
	}

	public void reloadCompetitionsTable() {
		while (globalCompetitionsModel.getRowCount() > 0) { // A
			globalCompetitionsModel.removeRow(0);
		}

		ArrayList<Competition> compList = new ArrayList<Competition>(); 
		Scanner infile2;
		try {
			infile2 = new Scanner(new File("competitions.txt")); // B

			while (infile2.hasNextLine()) {

				String CompetitionSingleNumberDate = infile2.nextLine();
				String myCompetititonName = infile2.nextLine();
				String myCompetitionLocation = infile2.nextLine();
				String year = CompetitionSingleNumberDate.substring(0, 4);
				String month = CompetitionSingleNumberDate.substring(4, 6);
				String day = CompetitionSingleNumberDate.substring(6, 8);
				String monthSeg = month.substring(0, 1);
				if (monthSeg.equals("0")) {
					month = month.substring(1);
				}
				String daySeg = day.substring(0, 1);
				if (daySeg.equals("0")) {
					day = day.substring(1);
				}
				Competition comp = new Competition(myCompetititonName, Integer.valueOf(month), Integer.valueOf(day),
						Integer.valueOf(year), myCompetitionLocation); 
				int index = comp.findLoc(comp, compList);
				// System.out.print(index);
				compList.add(index, comp); 
				

				while (infile2.hasNextLine()) {
					String read = infile2.nextLine();
					if (read.equals("EVENT LIST COMPLETE") || read.equals("STUDENT LIST COMPLETE")) {
						break;
					}
					
					String eventName = read.substring(0, read.length() - 1);
					String timeBlock = read.substring(read.length() - 1, read.length());
					Integer block = Integer.valueOf(timeBlock);
					Event tempEvent = new Event(eventName, block);
					comp.addEvent(tempEvent);
					
					ArrayList<Student> tempStudentList = new ArrayList<Student>();

					while (infile2.hasNextLine()) {
						String studentLastName = infile2.nextLine();
						if (studentLastName.equals("STUDENT LIST COMPLETE")) {
							break;
						}

						String first = infile2.nextLine();
						System.out.print(first);
						String grade = infile2.nextLine();
						Student stu = new Student(studentLastName, first, grade); 
						int index1 = stu.findLoc(stu, tempStudentList);

						tempStudentList.add(index1, stu);

						tempEvent.setNewStudentList(tempStudentList);

						comp.addStudentToSetBlock(stu, block);

					}
				}
				System.out.println("NOT REACHING HERE");
				System.out.println("the competition has been added");
			}
		} catch (Exception error) {
			System.out.println(error.getMessage());
		}
		for (int i = 0; i < compList.size(); i++) {
			Competition current = compList.get(i);
			globalCompetitionsModel.insertRow(i, current.getCompetitionFormattedInfo());
		}
	}
	
	public void clearLists()
	{
		studentList.clear();
	}

	public ArrayList<Student> getStudentList() {
		return studentList;
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}
}