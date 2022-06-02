package com.springboot.thymeleafdemo;

import com.springboot.thymeleafdemo.controller.DepartmentControllerWithDB;
import com.springboot.thymeleafdemo.controller.EmployeeControllerWithDB;
import com.springboot.thymeleafdemo.dao.DepartmentDAO;
import com.springboot.thymeleafdemo.dao.EmployeeDAO;
import com.springboot.thymeleafdemo.entity.Department;
import com.springboot.thymeleafdemo.entity.Employee;
import com.springboot.thymeleafdemo.service.DepartmentService;
import com.springboot.thymeleafdemo.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringDemoAppTests {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@MockBean
	private Employee employee;

	@MockBean
	private Department department;

	@MockBean
	private EmployeeDAO employeeDAO;

	@MockBean
	private DepartmentDAO departmentDAO;

	@MockBean
	private EmployeeControllerWithDB employeeControllerWithDB;

	@MockBean
	private DepartmentControllerWithDB departmentControllerWithDB;

	// Employee

	@Test
	public void empGetTest() {
		Employee emp1 = new Employee(10,"John","Lenin","john.lenin@outlook.com");
		assertEquals(10, emp1.getId());
		assertEquals("John", emp1.getFirstName());
		assertEquals("Lenin", emp1.getLastName());
		assertEquals("john.lenin@outlook.com", emp1.getEmail());
	}

	@Test
	public void empSetTest() {
		Employee emp1 = new Employee();

		emp1.setId(10);
		emp1.setFirstName("John");
		emp1.setLastName("Lenin");
		emp1.setEmail("john.lenin@outlook.com");

		assertEquals(10, emp1.getId());
		assertEquals("John", emp1.getFirstName());
		assertEquals("Lenin", emp1.getLastName());
		assertEquals("john.lenin@outlook.com", emp1.getEmail());
	}

	//test findAll()
	@Test
	public void findAllTest(){
		Employee emp1 = new Employee("John","Lenin","john.lenin@outlook.com");
		Employee emp2 = new Employee("Vijay","Kumar","vijay.kumar@gmail.com");

		emp1.setTheDepartment(new Department(100,"Tech"));
		emp2.setTheDepartment(new Department(101,"Ops"));

		when(employeeDAO.findAll()).thenReturn(
				Stream.of(emp1, emp2).collect(Collectors.toList()));
		assertEquals(2, employeeService.findAll().size());

	}

	//test findById()
	@Test
	public void findByIdTest() {
		Employee emp = new Employee(10,"Vijay","Kumar","vijay.kumar@gmail.com");
		when(employeeDAO.findById(10)).thenReturn(emp);

		assertEquals(10, employeeService.findById(10).getId());
	}

	@Test
	public void findByNonIdTest() {
		Employee emp = new Employee(10,"Vijay","Kumar","vijay.kumar@gmail.com");
		when(employeeDAO.findById(10)).thenReturn(emp);

		Exception exception = assertThrows(RuntimeException.class, () -> {
			employeeService.findById(11).getId();
			//Employee ID - 11 doesn't exist, so exception must be thrown
		});

		String expectedMessage = "Did not find employee id - ";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	//test save()
	@Test
	public void saveTest() {
		Employee emp = new Employee("Sagar", "Chandra", "sagar.chandra@gmail.com");
		employeeService.save(emp);
		verify(employeeDAO, times(1)).save(emp);
	}

	//test update()
	@Test
	public void updateTest() {
		Employee emp = new Employee( "Sagar", "Chandra", "sagar.chandra@gmail.com");
		employeeService.update(emp);
		verify(employeeDAO, times(1)).update(emp);
	}

	//test deleteById()
	@Test
	public void deleteByIdTest() {
		Employee emp = new Employee(3, "Sagar", "Chandra", "sagar.chandra@gmail.com");
		employeeService.deleteById(3);
		verify(employeeDAO, times(1)).deleteById(3);
	}

	//Departments
	@Test
	public void deptGetTest() {
		Department dept = new Department(100,"Tech");
		assertEquals(100, dept.getDeptId());
		assertEquals("Tech", dept.getDeptName());
	}

	@Test
	public void deptSetTest() {
		Department dept = new Department();
		dept.setDeptId(100);
		dept.setDeptName("Tech");
		assertEquals(100, dept.getDeptId());
		assertEquals("Tech", dept.getDeptName());
	}

	@Test
	public void findAllDeptTest(){
		when(departmentDAO.findAll()).thenReturn(
				Stream.of(new Department(100, "Tech"), new Department(101, "Ops"), new Department(102, "HR")).collect(Collectors.toList()));
		assertEquals(3, departmentService.findAll().size());
	}

	@Test
	public void findDeptById(){
		Department dept = new Department(100, "Tech");
		when(departmentDAO.findById(100)).thenReturn(dept);
		assertEquals(100, departmentService.findById(100).getDeptId());
	}

	// Controller

}
