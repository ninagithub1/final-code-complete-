import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
//import java.util.PriorityQueue;
import java.util.Scanner;

public class CompetitionsDatabase 
{
	private ArrayList<Competition> CompetitionData; 
	String file; 
/*
 * /*
	 * for home: add success criteria table to the end of criterion B (design)- COMPLETE
	 * check final word counts
	 * adjust criterion B table of contents as necessary
	 * add last success criterion to criterion B success criteria-COMPLETE
	 * insert checkmarks into criterion E table
	 * double space everything
	 * 
	 * 
	 * cite all sources, add comments, change colors- COMPLETE
	 * organize UML diagram and upload to criterion B- COMPLETE
	 * add table of contents to Criterion C, and read through it
	 * check criterion A, check criterion B- COMPLETE
	 * alter record of tasks in criterion B 
	 * complete criterion E- COMPLETE
	 * clean up code, add nice images- COMPLETE
	 * record criterion D- COMPLETE
	 * add both necessary appendixes - COMPLETE
	 * create final client interaction in appendix 2- COMPLETE
	 * complete recommendations for further development- COMPLETE
	 */
 
	public CompetitionsDatabase()
	{
		file = "competitions.txt";
		CompetitionData = new ArrayList<Competition>(); 
	}

	private ArrayList<Competition> loadFile() 
	{ 
		Scanner infile;
		ArrayList<Competition> complete = new ArrayList<Competition>();
		try  
		{
			infile = new Scanner(new File(file));
			while(infile.hasNextLine())
			{
				String CompetitionSingleNumberDate = infile.nextLine();
				String myCompetititonName = infile.nextLine();
				String myCompetitionLocation = infile.nextLine();
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
				int index = comp.findLoc(comp, complete); 
				complete.add(index, comp);

				if (infile.hasNextLine()) {
						while (!infile.nextLine().equals("EVENT LIST COMPLETE")) {
							String eventName = infile.nextLine();
							Competition tempCompetition = complete.get(index);
							tempCompetition.addEvent(new Event(eventName, 0));
						}
				}
			}
			CompetitionData=complete;
		}
		catch(Exception error)
		{
			System.out.println(error.getMessage()); 
		}
		return complete; 
	}

	public ArrayList<Competition> getCompetitionData()
	{
		return CompetitionData; 
	}
	
	public void setCompetitionData(ArrayList<Competition> comps)
	{
		CompetitionData = comps;
	}

	public void addCompetition(Competition comp)
	{
		  int index = comp.findLoc(comp, CompetitionData); 
		  CompetitionData.add(index, comp); 
		  refreshStudentFile(); 
	}
	
	public void addWithoutRefresh(Competition comp)
	{
		 int index = comp.findLoc(comp, CompetitionData);
		 CompetitionData.add(index, comp);
	}
	
	public void addEvent(int location, Event temp)
	{
		Competition comp = CompetitionData.get(location);
		comp.addEvent(temp); 		
	}
	
	public void addSpecificEvent(int location, Event temp)
	{
		Competition comp = CompetitionData.get(location);
		comp.addEvent(temp); 	
		refreshStudentFile();
	}
	
	public void removeComp(int place)
	{
		CompetitionData.remove(place);
	}
	public void refreshStudentFile() 
	{ 
		{                                                  
		    try 
		    {
		    FileWriter fw = new FileWriter("competitions.txt", false);
		    PrintWriter pw = new PrintWriter(fw, false);
		    pw.flush();
		    pw.close();
		    fw.close();
		   System.out.println("FILE SUCCESSFULLY CLEARED");
		    }
		    catch(Exception exception)
		    {
		        System.out.println("Exception have been caught");
		    } 

  //  public void refreshStudentFile()     
		try 
		{
			FileWriter writer = new FileWriter("competitions.txt", true);
			for(Competition comp : CompetitionData)
			{
				String[] info = comp.getCompetitionInfo();
				for(String piece : info)
				{
					writer.write(piece + "\n");
				} 
				ArrayList<Event> events = comp.getEventList();
				for(int i =0; i<events.size(); i++)
				{
					String temp = events.get(i).getName()+events.get(i).getTimeBlock();
					writer.write(temp + "\n");
					ArrayList<Student> students = events.get(i).getStudentList();
					for(int j = 0; j<students.size(); j++)
					{
						Student tempStu = students.get(j);
						writer.write(tempStu.getLastName() + "\n");
						writer.write(tempStu.getFirstName() + "\n");
						writer.write(tempStu.getGradeLevel() + "\n");
						
					}
					writer.write("STUDENT LIST COMPLETE" + "\n");
				}
				writer.write("EVENT LIST COMPLETE" + "\n"); 
			}
			writer.close(); 
			System.out.print("we wrote to the file");
		}
		catch(Exception err)
		{
			System.out.println(err.getMessage()); 
		}
	} 
}
}