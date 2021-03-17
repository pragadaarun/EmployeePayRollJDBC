import java.time.LocalDate;

public class EmployeePayrollData {
	public int id;
	public String name;
	public double basic_pay;
	public LocalDate start;
	
	public EmployeePayrollData(int id, String name, double basic_pay) {
		this.id = id;
		this.name = name;
		this.basic_pay = basic_pay;
	}

	public EmployeePayrollData(int id, String name, double basic_pay, LocalDate start) {
		this.id = id;
		this.name = name;
		this.basic_pay = basic_pay;
		this.start = start;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", salary=" + basic_pay+"; ";
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		EmployeePayrollData that = (EmployeePayrollData) o;
		return id == that.id && Double.compare(that.basic_pay, basic_pay) == 0 && name.equals(that.name);
	}

}