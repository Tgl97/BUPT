package homework2;

public class _14211168_周尧棋_2_Student 
{
	String studentNumber;
	String studentName;
	int markForMaths;
	int markForEnglish;
	int markForScience;
	
	public _14211168_周尧棋_2_Student(String number, String name)
	{
		studentNumber = number;
		studentName = name;
	}
	
	public _14211168_周尧棋_2_Student()
	{
		
	}
	
	public String getNumber()
	{
		return studentNumber;
	}
	
	public String getName()
	{
		return studentName;
	}
	
	public void enterMarks(int markForMaths, int markForEnglish, int markForScience)
	{
		this.markForMaths = markForMaths;
		this.markForEnglish = markForEnglish;
		this.markForScience = markForScience;
	}
	
	public int getMathsMark()
	{
		return markForMaths;
	}
	
	public int getEnglishMark()
	{
		return markForEnglish;
	}
	
	public int getScienceMark()
	{
		return markForScience;
	}
	
	public double calculateAverage()
	{
		return (markForMaths + markForEnglish + markForScience)/3.0;
	}
	
	public String toString()
	{
		String info = "学生信息如下：";
		String num = "学号：";
		String name = "姓名：";
		String math = "数学成绩：";
		String eng = "英语成绩：";
		String sci = "科学成绩：";
		String ave = "平均成绩：";
		return info + "\r\n" +
			   num + getNumber() + "\r\n" +
			   name + getName() + "\r\n" +
			   math + Integer.toString(markForMaths) + "\r\n" +
			   eng + Integer.toString(markForEnglish) + "\r\n" +
			   sci + Integer.toString(markForScience) + "\r\n" +
			   ave + Double.toString(calculateAverage()) + "\r\n";
	}
}

