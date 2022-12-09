import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;



public class DatabaseFolder {

	private ArrayList<Student> studentData; 
	String file; 

	public DatabaseFolder()
	{
		file = "students.txt";
		studentData = new ArrayList<Student>(); 
		studentData = loadFile(); 
	}

	private ArrayList<Student> loadFile() 
	{
		Scanner infile;
		ArrayList<Student> complete = new ArrayList<Student>();
		try 
		{

			infile = new Scanner(new File(file));
			while(infile.hasNextLine())
			{
				String last = infile.nextLine();
				String first =infile.nextLine(); 
				String grade = infile.nextLine();
				Student stu = new Student(last, first, grade); 

				int index = stu.findLoc(stu, complete); 
				complete.add(index, stu); 
			}
		}
		catch(Exception error)
		{
			System.out.println(error.getMessage()); 
		}
		return complete; 
	}

	public ArrayList<Student> getStudentData()
	{
		return studentData; 
	}

	public void addStudent(Student stu) 
	{
		  int index = stu.findLoc(stu, studentData); 
		  studentData.add(index, stu); 
		    refreshStudentFile(); 
	}

	public void refreshStudentFile() 
	{
		{ 
		    try 
		    {
		    FileWriter fw = new FileWriter("students.txt", false);
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

		try 
		{
			FileWriter writer = new FileWriter("students.txt", true);
			for(Student stu : studentData)
			{
				String[] info = stu.getStudentInfo();
				for(String piece : info)
				{
					writer.write(piece + "\n");
				}
			}
			writer.close(); 
		}
		catch(Exception err)
		{
			System.out.println(err.getMessage()); 
		}
	} 
}
}
