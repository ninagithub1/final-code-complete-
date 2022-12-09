import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class AddEditCompetitionWindow extends CompetitionWindow 
{
	private Competition ourComp;
	private Integer rowNumber;
	private Competition previousComp;

	public AddEditCompetitionWindow(TestFrame test, CompetitionsDatabase compdatabase, Competition current, Integer location) //the current comp
	{
		super(test, compdatabase);
		getContentPane().setBackground(new Color(162, 197, 223));
		rowNumber = location;
		previousComp = current; 
	}

	public void save(Competition comp)
	{
		ourComp = new Competition(comp.getReferenceName(), comp.getCompMonth(), 
				comp.getCompetitionDay(), comp.getCompYear(), comp.getCompLocation());
		for(int i=0;i<previousComp.getEventList().size();i++)
		{
			ourComp.addEvent(previousComp.getEventList().get(i));
		}
		removeRow(rowNumber);
		getCompData().addWithoutRefresh(ourComp);
		getCompData().refreshStudentFile();
		cancel();
	}
}
