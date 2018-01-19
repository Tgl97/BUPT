package homework2;

public class _14211168_÷‹“¢∆Â_1_StudentList 
{
	_14211168_÷‹“¢∆Â_2_Student[] list;
	int total;
	int len;
	
	_14211168_÷‹“¢∆Â_1_StudentList()
	{
		list = new _14211168_÷‹“¢∆Â_2_Student[100]; 
		total = 0;
		len = 100;
	}
	_14211168_÷‹“¢∆Â_1_StudentList(int length)
	{
		len = length;
	}
	
	boolean add(_14211168_÷‹“¢∆Â_2_Student stu)
	{
		if(total <= len-1)
		{
			list[total] = stu;
			total ++;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	boolean remove(int no)
	{
		if(no <= 0 || no > total)
		{
			return false;
		}
		else
		{
			total --;
			for(int i=no-1; i<total; i++)
			{
				list[i] = list[i+1];
			}
			return true;
		}
	}
	
	boolean remove(String number)
	{
		for(int i=0; i<total; i++)
		{
			if(list[i].studentNumber.equals(number))
			{
				total --;
				for(int j=i; j<total-i; j++)
				{
					list[j] = list[j+1];
				}
				return true;
			}
		}
		return false;
	}
	
	boolean isEmpty()
	{
		if(total == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	_14211168_÷‹“¢∆Â_2_Student getItem(int no)
	{
		if(no <= 0 || no > total)
		{
			return null;
		}
		else
		{
			return list[no-1];
		}
	}
	
	_14211168_÷‹“¢∆Â_2_Student getItem(String number)
	{
		for(int i=0; i<total; i++)
		{
			if(list[i].studentNumber.equals(number))
			{
				return list[i];
			}
		}
		return null;
	}
	
	int getTotal()
	{
		return total;
	}
}
