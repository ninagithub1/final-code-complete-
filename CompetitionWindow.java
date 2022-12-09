import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;

public class CompetitionWindow extends JFrame {

	private JPanel contentPane;
	private JTextField CompetitionNameTextField;
	private JTextField YearTextField;
	private JTextField LocationTextField;
	private TestFrame OGTestFrame;
	CompetitionsDatabase competitionList;
	private CompetitionWindow myWindow;
	private TestFrame myTest;

	/**
	 * Create the frame.
	 */
	public CompetitionWindow(TestFrame test, CompetitionsDatabase comp) {

		competitionList = comp;
		myWindow = this;
		myTest = test;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(162, 197, 223));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		

		CancelPopup frame = new CancelPopup(myTest, myWindow);
		frame.setVisible(false);

		JButton CancelButton = new JButton("Cancel");
		CancelButton.setFont(new Font("Georgia", Font.PLAIN, 11));
		CancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		CancelButton.setBounds(329, 216, 89, 23);
		contentPane.add(CancelButton);

		JLabel CompName = new JLabel("Competition Name:");
		CompName.setFont(new Font("Georgia", Font.PLAIN, 11));
		CompName.setBounds(33, 56, 131, 14);
		contentPane.add(CompName);

		CompetitionNameTextField = new JTextField();
		CompetitionNameTextField.setBounds(174, 53, 86, 20);
		contentPane.add(CompetitionNameTextField);
		CompetitionNameTextField.setColumns(10);

		JLabel MonthLabel = new JLabel("Month: ");
		MonthLabel.setFont(new Font("Georgia", Font.PLAIN, 11));
		MonthLabel.setBounds(33, 101, 46, 14);
		contentPane.add(MonthLabel);

		JSpinner MonthSpinner = new JSpinner();
		MonthSpinner.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		MonthSpinner.setBounds(73, 98, 69, 20);
		contentPane.add(MonthSpinner);

		JLabel DayLabel = new JLabel("/Day:");
		DayLabel.setFont(new Font("Georgia", Font.PLAIN, 11));
		DayLabel.setBounds(177, 101, 46, 14);
		contentPane.add(DayLabel);

		JSpinner DaySpinner = new JSpinner();
		DaySpinner.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		DaySpinner.setBounds(209, 98, 51, 20);
		contentPane.add(DaySpinner);

		JLabel YearLabel = new JLabel("Year:");
		YearLabel.setFont(new Font("Georgia", Font.PLAIN, 11));
		YearLabel.setBounds(285, 101, 46, 14);
		contentPane.add(YearLabel);

		YearTextField = new JTextField();
		YearTextField.setBounds(319, 98, 86, 20);
		contentPane.add(YearTextField);
		YearTextField.setColumns(10);

		JLabel LocationLabel = new JLabel("Location:");
		LocationLabel.setFont(new Font("Georgia", Font.PLAIN, 11));
		LocationLabel.setBounds(33, 151, 75, 14);
		contentPane.add(LocationLabel);

		LocationTextField = new JTextField();
		LocationTextField.setBounds(118, 148, 86, 20);
		contentPane.add(LocationTextField);
		LocationTextField.setColumns(10);

		JButton SaveButton = new JButton("Save");
		SaveButton.setFont(new Font("Georgia", Font.PLAIN, 11));
		SaveButton.setBounds(250, 216, 69, 23);
		contentPane.add(SaveButton);
		SaveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String name = CompetitionNameTextField.getText();
				String location = LocationTextField.getText();

				int monthValue = (Integer) MonthSpinner.getValue();
				String monthName = String.valueOf(monthValue);

				int dayValue = (Integer) DaySpinner.getValue();
				String dayName = String.valueOf(dayValue);

				String year = YearTextField.getText();
				String errorMessage = "";
				try 
				{
				  int   intValue = Integer.parseInt(year);
				} 
				catch (NumberFormatException e1) 
				{
					errorMessage += "\nPlease enter numerical value for the year";			
				}

				
				if (name.equals("")) {
					errorMessage += "\nPlease enter a competition name";
				}
				if (year.equals("") || year.length() != 4 ) {
					errorMessage += "\nPlease enter a FOUR DIGIT year";
				}
				if (location.equals("")) {
					errorMessage += "\nPlease enter a competition location";
				}
				if (!errorMessage.equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
				}
				if (!(name.equals("") || year.equals("") || location.equals(""))) 
				{
					int yearInt = Integer.valueOf(year);
					Competition comp = new Competition(name, monthValue, dayValue, yearInt, location);
					save(comp);
					cancel();
				}
			}
		});
	}

	public void save(Competition comp) {
		
		competitionList.addCompetition(comp); 
		System.out.println("competition successfully added");
		System.out.println("using parent format");
	}

	public void removeRow(Integer Loc) {
		competitionList.removeComp(Loc);
	}

	public void cancel() {
		myWindow.setVisible(false);
		CompetitionNameTextField.setText(""); 
		LocationTextField.setText("");
		YearTextField.setText("");
		myTest.reloadCompetitionsTable();
		myTest.setVisible(true);
	}

	public TestFrame getTestFrame() {
		return myTest;
	}

	public CompetitionWindow getCurrentWindow() {
		return myWindow;
	}

	public CompetitionsDatabase getCompData() {
		return competitionList;
	}

}
