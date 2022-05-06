package com.springboot.thymeleafdemo;

import com.springboot.thymeleafdemo.dao.EmployeeDAO;
import com.springboot.thymeleafdemo.entity.Employee;
import com.springboot.thymeleafdemo.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
class ThymeleafdemoApplicationTests {

	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeDAO employeeDAO;

	//test findAll()
	@Test
	public void findAllTest(){
		when(employeeDAO.findAll()).thenReturn(
				Stream.of(new Employee("John","Lenin","john.lenin@outlook.com"), new Employee("Vijay","Kumar","vijay.kumar@gmail.com")).collect(Collectors.toList()));
		assertEquals(2, employeeService.findAll().size());
	}

	//test findById()
	@Test
	public void findByIdTest() {
		Employee emp = new Employee(10,"Vijay","Kumar","vijay.kumar@gmail.com");
		when(employeeDAO.findById(10)).thenReturn(emp);

		assertEquals(10, employeeService.findById(10).getId());
	}

	//test save()
	@Test
	public void saveTest() {
		Employee emp = new Employee(3, "Sagar", "Chandra", "sagar.chandra@gmail.com");
		employeeService.save(emp);
		verify(employeeDAO, times(1)).save(emp);
	}

	//test deleteById()
	@Test
	public void deleteByIdTest() {
		Employee emp = new Employee(3, "Sagar", "Chandra", "sagar.chandra@gmail.com");
		employeeService.deleteById(3);
		verify(employeeDAO, times(1)).deleteById(3);
	}

}
