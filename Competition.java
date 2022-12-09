import java.util.*;
import java.util.Collections; 

public class Competition implements Comparable {
	private String myCompetititonName;
	private Integer myCompetitionMonth;
	private Integer myCompetitionDay;
	private Integer myCompetitionYear;
	private String myCompetitionLocation;
	private Integer totalDate;
	private ArrayList<Event> eventsList;
	private Set<Integer> block1;
	private Set<Integer> block2;
	private Set<Integer> block3;
	private Set<Integer> block4;
	private Set<Integer> block5;
	private Set<Integer> block6;
	private Set<Integer> block7;

	public Competition(String name, Integer month, Integer day, Integer year, String location) {
		myCompetititonName = name;
		myCompetitionMonth = month;
		myCompetitionDay = day;
		myCompetitionYear = year;
		myCompetitionLocation = location;
		eventsList = new ArrayList<Event>();
		totalDate = 0;
		block1 = new HashSet<Integer>();
		block3 = new HashSet<Integer>();
		block4 = new HashSet<Integer>();
		block5 = new HashSet<Integer>();
		block6 = new HashSet<Integer>();
		block7 = new HashSet<Integer>();
		block2 = new HashSet<Integer>();
	}

	public int compareTo(Object other) 
	{
		Competition temp = (Competition) other;
		int compare = getCompetitionSingleNumberDate() - temp.getCompetitionSingleNumberDate();
		return compare;
	}
	
	public boolean addStudentToSetBlock(Student stu, int setNum)
	{
		int value = stu.getStudentSetNotation();
		if(setNum == 1)
		{
			boolean check = block1.add(value);
			if(check)
			{
				System.out.println("student added to " + setNum + " time block successfully");
				return true;
			}
			else
			{
				System.out.println("sorry, this student is already in "+ setNum + " time block");
				return false;
			}
		}
		else if (setNum == 2)
		{
			boolean check = block2.add(value);
			if(check)
			{
				System.out.println("student added to " + setNum + " time block successfully");
				return true;

			}
			else
			{
				System.out.println("sorry, this student is already in "+ setNum + " time block");
				return false;

			}		}
		else if (setNum == 3)
		{
			boolean check = block3.add(value);
			if(check)
			{
				System.out.println("student added to " + setNum + " time block successfully");
				return true;

			}
			else
			{
				System.out.println("sorry, this student is already in "+ setNum + " time block");
				return false;

			}		}
		else if (setNum == 4)
		{
			boolean check = block4.add(value);
			if(check)
			{
				System.out.println("student added to " + setNum + " time block successfully");
				return true;
			}
			else
			{
				System.out.println("sorry, this student is already in "+ setNum + " time block");
				return false;

			}		}
		else if (setNum == 5)
		{
			boolean check = block5.add(value);
			if(check)
			{
				System.out.println("student added to " + setNum + " time block successfully");
				return true;
			}
			else
			{
				System.out.println("sorry, this student is already in "+ setNum + " time block");
				return false;

			}		}
		else if (setNum == 6)
		{
			boolean check = block6.add(value);
			if(check)
			{
				System.out.println("student added to " + setNum + " time block successfully");
				return true;
			}
			else
			{
				System.out.println("sorry, this student is already in "+ setNum + " time block");
				return false;

			}		}
		else
		{
			boolean check = block7.add(value);
			if(check)
			{
				System.out.println("student added to " + setNum + " time block successfully");
				return true;
			}
			else
			{
				System.out.println("sorry, this student is already in "+ setNum + " time block");
				return false;

			}		
			}

	}

	public void setCompetitionName(String comp) {
		myCompetititonName = comp;
	}

	public void setCompetitionMonth(Integer month) {
		myCompetitionMonth = month;
	}
	
	public void setCompetitionDay(Integer day) {
		myCompetitionDay = day;
	}
	
	public void setCompetitionYear(Integer year) {
		myCompetitionYear = year;
	}
	
	public void setCompetitionLocation(String location) {
		myCompetitionLocation = location;
	}
	
	public String getReferenceName() {
		return myCompetititonName;
	}
	
	public Integer getCompMonth()
	{
		return myCompetitionMonth;
	}
	
	public Integer getCompetitionDay()
	{
		return myCompetitionDay; 
	}
	
	public Integer getCompYear()
	{
		return myCompetitionYear;
	}
	
	public String getCompLocation()
	{
		return myCompetitionLocation;
	}


	public int getCompetitionSingleNumberDate() {
		String year = myCompetitionYear.toString();
		String month = "";
		String day = "";
		if (myCompetitionMonth > 0 && myCompetitionMonth < 10) {
			month = "0" + myCompetitionMonth.toString();
		} else {
			month = myCompetitionMonth.toString();
		}
		if (myCompetitionDay > 0 && myCompetitionDay < 10) {
			day = "0" + myCompetitionDay.toString();
		} else {
			day = myCompetitionDay.toString();
		}
		String total = year + month + day;

		return Integer.valueOf(total);
	}

	public int findLoc(Competition comp, ArrayList arr) {

		for (int i = 0; i < arr.size(); i++) {
			if (comp.compareTo(arr.get(i)) < 0) {
				return i;
			}
		}
		return arr.size();
	}
	
	public int findEventLoc(Event eve) {

		for (int i = 0; i < eventsList.size(); i++) {
			if (eve.compareToEventList(eventsList.get(i)) < 0) {
				return i;
			}
		}
		return eventsList.size();
	}

	public String[] getCompetitionInfo() {
		String[] info = new String[3];
		info[0] = ((Integer) getCompetitionSingleNumberDate()).toString();
		info[1] = myCompetititonName;
		info[2] = myCompetitionLocation;
		return info;
	}

	public String[] getCompetitionFormattedInfo() {
		String[] info = new String[3];
		info[0] = myCompetititonName;
		info[1] = myCompetitionLocation;
		info[2] = myCompetitionMonth + "/" + myCompetitionDay + "/" + myCompetitionYear;
		return info;
	}
	

	public void addEvent(Event temp) {
		// Event temp = new Event(name, time);
		
		int location = findEventLoc(temp);
		
		eventsList.add(location, temp);
		System.out.println(temp.getName() + " even was added");

	}

	public ArrayList<Event> getEventList() {
		return eventsList;
	}
	
	public void setEventList(ArrayList<Event> events)
	{
		eventsList = new ArrayList<Event>();
		eventsList = events;
	}
}