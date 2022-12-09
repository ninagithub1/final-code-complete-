import java.awt.EventQueue;
import java.util.ArrayList;

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
import java.awt.Color;
import java.awt.Font;

public class CompInfoDisplay extends JFrame {

	private JPanel contentPane;
	private CompetitionsDatabase data;
	private Integer rowLoc;
	private Competition myCompetition;
	private DefaultTableModel myEventsModel;
	private CompInfoDisplay frame; 
	private TestFrame Homepage;

	public CompInfoDisplay(CompetitionsDatabase compData, Integer location, TestFrame testFr) {

		frame = this; 
		data = compData;
		Homepage = testFr; 
		rowLoc = location;
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(162, 197, 223));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		ArrayList<Competition> temp = compData.getCompetitionData(); // compData is still size 0!
		if (!(temp.isEmpty()))
				{
		Competition ourComp = temp.get(location);
		myCompetition = ourComp;
		
				
		String referenceName = ourComp.getReferenceName();
		System.out.println("this point has been reached");

		myEventsModel = new DefaultTableModel(new String[] { "Time Block", "Event", "Students" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;

			}
		};

		JTable table = new JTable(myEventsModel); // this is what tests is.
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(217, 22, 292, 253);
		contentPane.add(scrollPane);
		
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) {
					int row = table.getSelectedRow();
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								AddStudentToEvent addFrame = new AddStudentToEvent(myCompetition.getEventList().get(row), data, myCompetition,frame );
								addFrame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			} 
		});
		table.setModel(myEventsModel);

		JLabel CompetitionName = new JLabel(referenceName);
		CompetitionName.setFont(new Font("Georgia", Font.PLAIN, 11));
		CompetitionName.setBounds(132, 23, 175, 14);
		contentPane.add(CompetitionName);
				}
		else
		{
			JLabel NoEvents = new JLabel("There are no events in this competition.");
			NoEvents.setFont(new Font("Georgia", Font.PLAIN, 15));
			NoEvents.setBounds(205, 44, 275, 43);
			contentPane.add(NoEvents);
		}

		JButton AddEvent = new JButton("Add New Event");
		AddEvent.setFont(new Font("Georgia", Font.PLAIN, 11));
		AddEvent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AddEvent AddFrame = new AddEvent(data, rowLoc, frame);
							AddFrame.setVisible(true);
							frame.setVisible(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
		});
		AddEvent.setBounds(45, 136, 133, 49);
		contentPane.add(AddEvent);

		JLabel CompEvents = new JLabel("Competition Events");
		CompEvents.setFont(new Font("Georgia", Font.PLAIN, 11));
		CompEvents.setBounds(266, 38, 133, 14);
		contentPane.add(CompEvents);
		
		JButton returnButton = new JButton("Return To Homepage");
		returnButton.setFont(new Font("Georgia", Font.PLAIN, 11));
		returnButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				Homepage.setVisible(true);
			}
		});
		returnButton.setBounds(35, 301, 372, 49);
		contentPane.add(returnButton);
		
		JButton EditCompetition = new JButton("Edit Competition");
		EditCompetition.setFont(new Font("Georgia", Font.PLAIN, 11));
		EditCompetition.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AddEditCompetitionWindow frame = new AddEditCompetitionWindow(Homepage,data, myCompetition, rowLoc);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		EditCompetition.setBounds(40, 206, 138, 49);
		contentPane.add(EditCompetition);
		
		

		refreshTable();
		
	}
	
	public void refreshTable()
	{
		while(myEventsModel.getRowCount()>0)
		{
			myEventsModel.removeRow(0);
		}
		
		for (int i = 0; i < myCompetition.getEventList().size(); i++) {
			System.out.println("number: " + i);
			myEventsModel.insertRow(i, myCompetition.getEventList().get(i).getEventFormattedInfo());
		}
	}
}