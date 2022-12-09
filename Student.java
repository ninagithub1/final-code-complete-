import java.util.ArrayList;

	public class Student implements Comparable
	{
		private String myLastName;
		private String myFirstName;;
		private String myGradeLevel;

		public Student(String lastName, String firstName, String gradeLevel) {
			myLastName = lastName;
			myFirstName = firstName;
			myGradeLevel = gradeLevel;
		}

		public void setLastName(String last)
		{
			myLastName = last; 
		}
		
		public int getStudentSetNotation()
		{
			int value = 0; 
			for(int i = 0; i <myFirstName.length(); i++)
			{
				char temp = myFirstName.charAt(i);
				int holder = (int)temp;
				value +=holder;
			}
			for(int i = 0; i <myLastName.length(); i++)
			{
				char temp = myLastName.charAt(i);
				int holder = (int)temp;
				value +=holder;
			}
			return value;
		}

		public void setFirstName(String first)
		{
			myLastName = first; 
		}


		public void setGradeLevel(String level)
		{
			myGradeLevel = level;
		}

		public String getLastName() {
			return myLastName;
		}

		public String getFirstName() {
			return myFirstName;
		}


		public String getGradeLevel() {
			return myGradeLevel;
		}

		public String[] getStudentInfo() {
			String[] info = new String[3];
			info[0] = myLastName;
			info[1] = myFirstName;
			info[2] = myGradeLevel;
			return info;
		}

		public int compareTo(Object other)
		{
			Student otherStu = (Student) other; 
			int compare = myLastName.compareTo(otherStu.myLastName);
			if(compare ==0)
			{
				return myFirstName.compareTo(otherStu.myFirstName);
			}
			else
			{
				return compare; 
			}
		}

		public int findLoc(Student stu, ArrayList arr) 
		{

			for(int i =0; i <arr.size(); i++)
			{
				if(stu.compareTo(arr.get(i)) <0)
				{
					return i; 
				}
			}
			return arr.size(); 
		}

	}
