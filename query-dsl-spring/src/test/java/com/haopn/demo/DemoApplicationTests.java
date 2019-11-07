package com.haopn.demo;

import com.haopn.demo.entity.Employee;
import com.haopn.demo.entity.QEmployee;
import com.haopn.demo.repository.EmployeeRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@AutoConfigureJdbc
@AutoConfigureTestDatabase
@TestPropertySource(properties = {
		"logging.level.ROOT=INFO",
		"logging.level.org.springframework.jdbc.core=DEBUG",
		"logging.level.org.springframework.transaction=TRACE"
})
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	EmployeeRepository employeeRepository;

	private List<Employee> createEmployees() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee("Hao", "IT", 100));
		employees.add(new Employee("Hao_1", "IT", 200));
		employees.add(new Employee("Hao_2", "IT", 300));
		return employees;
	}

	@PostConstruct
	public void initData() {
		List<Employee> employees = createEmployees();
		employeeRepository.saveAll(employees);
	}

	@Test
	public void testFindEmployee() {
		BooleanExpression booleanExpression = QEmployee.employee.name.eq("Hao_1");
		Optional<Employee> employeeOptional = employeeRepository.findOne(booleanExpression);
		Assert.assertTrue(employeeOptional.isPresent());
	}

	@Test
	public void testFindListEmployeeUseLikeQuery() {
		BooleanExpression booleanExpression = QEmployee.employee.name.contains("a");
		Iterable<Employee> employees = employeeRepository.findAll(booleanExpression);
		employees.forEach(employee -> System.out.println(employee.getName()));
	}

}



