import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SpinnerNumberModel;

public class AddEvent extends JFrame {

	private JPanel contentPane;
	private JTextField EventName;
	private Integer rowLoc; 
	private CompetitionsDatabase myCompetition;
	private AddEvent ourFrame; 

	public AddEvent(CompetitionsDatabase compData, Integer location, CompInfoDisplay window) {
		
		myCompetition = compData;
		rowLoc = location; 
		ourFrame = this; 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel AddNewEvent = new JLabel("Add New Event");
		AddNewEvent.setBounds(174, 26, 89, 34);
		contentPane.add(AddNewEvent);
		
		EventName = new JTextField();
		EventName.setBounds(193, 91, 86, 20);
		contentPane.add(EventName);
		EventName.setColumns(10);
		
		JLabel EventLabel = new JLabel("Event Name:");
		EventLabel.setBounds(70, 94, 113, 14);
		contentPane.add(EventLabel);
		
		JLabel EventSlot = new JLabel("Event Time Block:");
		EventSlot.setBounds(70, 130, 113, 14);
		contentPane.add(EventSlot);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 7, 1));
		spinner.setBounds(193, 127, 43, 20);
		contentPane.add(spinner);
		
		JButton SaveButton = new JButton("Save");
		SaveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = EventName.getText();
				int timeBlock = (Integer)spinner.getValue();
				Event temp = new Event(name, timeBlock);
				myCompetition.addSpecificEvent(rowLoc, temp);  
				ourFrame.setVisible(false);
				window.setVisible(true);		
				window.refreshTable();
				}
		});
		SaveButton.setBounds(281, 190, 106, 41);
		contentPane.add(SaveButton);
		
		JButton cancel = new JButton("Cancel");
		cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ourFrame.setVisible(false);
				window.setVisible(true);
			}
		});
		cancel.setBounds(53, 190, 130, 41);
		contentPane.add(cancel);
	}
}
