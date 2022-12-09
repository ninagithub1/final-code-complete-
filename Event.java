import java.util.ArrayList;

public class Event {
	private String myEventName;
	private int eventTime;
	private ArrayList<Student> myStudentList; 

	public Event(String name, Integer timeBlock) {
		myEventName = name;
		eventTime = timeBlock;
		myStudentList = new ArrayList<Student>();
	}
	
	public void setNewStudentList(ArrayList<Student> tempStudentList)
	{
		myStudentList = tempStudentList;
	}

	public String getName()
	{
		return myEventName;
	}

	public Integer getTimeBlock()
	{
		return eventTime; 
	}
	
	public ArrayList<Student> getStudentList()
	{
		return myStudentList; 
	}
	
	public void addStudentToEvent(Student stu)
	{
		
		myStudentList.add(stu);
	}
	
	public void clearStudentList()
	{
		while(myStudentList.size()>0)
		{
			myStudentList.remove(0);
		}
	}
	
	public String[] getEventFormattedInfo()
	{
		String[] info = new String[3];
		info[0]= String.valueOf(eventTime);
		info[1]= myEventName;
		String studentsString ="";
		for(int i = 0; i<myStudentList.size(); i++)
		{
			studentsString = studentsString +" " +myStudentList.get(i).getFirstName();
		}
		info[2] = studentsString;
		return info; 
	}
	
	public int compareToEventList(Event eve)
	{
		int compare = getTimeBlock() - eve.getTimeBlock();
		return compare;

	}

}