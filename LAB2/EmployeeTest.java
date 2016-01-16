import java.util.*;

/**
 *Employee Test
*/

public class EmployeeTest
{
	public static void main(String[] args) {
	  
	  Employee[] staff = new Employee[3];
	  staff[0]=new Employee("zhangtianle", 75000,2015,12,15);
	  staff[1]=new Employee("lina", 43000,2013,1,18);
	  staff[2]=new Employee("Bossss", 789000,2015,11,17);
      
      for (Employee e : staff) 
      	e.raiseSalary(5);
      for (Employee e : staff)
      	System.out.println("name="+e.getName()+",salary="+e.getSalary()+",hireday="+e.getHireDay());
	}
}

class Employee
{
	public Employee(String n, double s, int year, int month, int day)
	{
      name = n;
      salary = s;
      GregorianCalendar calendar = new GregorianCalendar(year, month-1,day);
      hireDay= calendar.getTime();
   	}

   	public String getName()
   	{
   		return name;
   	}
   	public double getSalary()
   	{
   		return salary;
   	}
   	public Date getHireDay()
   	{
   		return hireDay;
   	}
   	public void raiseSalary(double percent)
   	{
        double raise = salary*percent/100;
        salary += raise;
   	}

   	private String name;
   	private double salary;
   	private Date hireDay;
}

