import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import EmployeePayrollService.IOService;

public class EmployeePayrollTest {
	@Test
	public void givenDatabase_ShouldReturn_SizeOfEmplList() {
		EmployeePayrollService service = new EmployeePayrollService();
		List<EmployeePayrollData> empList = service.readEmployeePayrollData(IOService.DB_IO);
		System.out.println(empList);
		Assert.assertEquals(7, empList.size());
	}

	@Test
	public void givenUpdatedSalaryForEmployee_WhenUpdated_ShouldMatchWithDB() {
		EmployeePayrollService service = new EmployeePayrollService();
		List<EmployeePayrollData> employeePayrollData = service.readEmployeePayrollData(IOService.DB_IO);
		service.updateEmployeeSalary("Terisa", 3000000.00);
		boolean result = service.checkEmployeePayrollInSyncWithDB("Terisa",3000000.00);
		Assert.assertTrue(result);
	}
	
	@Test
	public void givenUpdatedSalaryForEmployee_WhenUpdatedUsingPreparedStatement_ShouldSyncWithDB() {
		EmployeePayrollService service = new EmployeePayrollService();
		List<EmployeePayrollData> employeePayrollData = service.readEmployeePayrollData(IOService.DB_IO);
		service.updateEmployeeSalaryUsingPrepareStatement("Terisa", 2000000.00);
		boolean result = service.checkEmployeePayrollInSyncWithDB("Terisa", 2000000.00);
		Assert.assertTrue(result);
	}
	
	@Test
	public void givenSpecifiesDateRange_WhenDataRetrieved_ShouldMatchEmployeeCount() {
		EmployeePayrollService service = new EmployeePayrollService();
		service.readEmployeePayrollData(IOService.DB_IO);
		LocalDate startDate = LocalDate.of(2018, 01, 01);
		LocalDate endDate = LocalDate.now();
		List<EmployeePayrollData> employeePayrollData = service
				.readEmployeeDetailsForDateRange(IOService.DB_IO, startDate, endDate);
		Assert.assertEquals(6, employeePayrollData.size());
	}
	
	@Test
	public void givenEmployeePayrollData_WhenRetrievedAvg_SalaryByGender_ShouldReturnValuesAsExpected() {
		EmployeePayrollService service = new EmployeePayrollService();
		service.readEmployeePayrollData(IOService.DB_IO);
		Map<String, Double> averageSalaryByGender = service.readAverageSalaryByGender(IOService.DB_IO);
		Assert.assertEquals(125750.25, averageSalaryByGender.get("M"), 0.0);
		Assert.assertEquals(1340000, averageSalaryByGender.get("F"), 0.0);
	}
	
	@Test
	public void givenNewEmployee_WhenAdded_ShouldSyncWityhDB() {
		EmployeePayrollService service = new EmployeePayrollService();
		service.readEmployeePayrollData(IOService.DB_IO);
		service.addEmployeeToPayroll("Mark", 5000000.00, LocalDate.now(), "M", "Sales");
		boolean result = service.checkEmployeePayrollInSyncWithDB("Mark", 5000000.00);
		Assert.assertTrue(result);
	}

}