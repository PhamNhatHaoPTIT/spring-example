package com.haopn.demo;

import com.haopn.demo.entity.Employee;
import com.haopn.demo.entity.QEmployee;
import com.haopn.demo.repository.EmployeeRepository;
import com.haopn.demo.util.EmployeeExpressions;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
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

	@Autowired
	EntityManager entityManager;

	private List<Employee> createEmployees() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee("AHao", "IT", 100, LocalDate.of(1998, Month.NOVEMBER, 7),
																		LocalDate.of(2016, Month.AUGUST, 2)));
		employees.add(new Employee("Hao_1", "IT", 200, LocalDate.of(1998, Month.NOVEMBER, 7),
																		LocalDate.of(2015, Month.APRIL, 4)));
		employees.add(new Employee("BHao_2", "KT", 300, LocalDate.of(1997, Month.JANUARY, 1),
																		LocalDate.of(2014, Month.DECEMBER, 3)));
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

	@Test
	public void test_FindEmployeeHasBirthdayAndLongTerm() {
		// GIVEN: 3 employees,
		// inside have 2 employee have birthday today and they are long term employee (work over 2 year at company)
		Predicate predicate = EmployeeExpressions.hasBirthday().and(EmployeeExpressions.isLongTermEmployee());
		// WHEN: execute find list employee birthday and long term
		Iterable<Employee> employees = employeeRepository.findAll(predicate);
		int size = 0;
		if(employees instanceof Collection<?>) {
			size = ((Collection<?>)employees).size();
		}
		// THEN: list.size() == 2, total 2 employees
		Assert.assertTrue(size == 2);
	}

	// write sub-query using QueryDSL
	@Test
	public void test_FindEmployeeHighestSalary() {
		JPAQueryFactory query = new JPAQueryFactory(entityManager);
		QEmployee employee = QEmployee.employee;
		QEmployee e = new QEmployee("e");
		Employee employee1 = query.selectFrom(employee).where(employee.salary.eq(
				JPAExpressions.select(e.salary.max()).from(e)
		)).fetchOne();
		Assert.assertNotNull(employee1);
	}

	// write test to verify order query using QueryDSL
	@Test
	public void test_OrderEmployee() {
		JPAQueryFactory query = new JPAQueryFactory(entityManager);
		QEmployee employee = QEmployee.employee;
		List<Employee> employees = query.selectFrom(employee).orderBy(employee.salary.desc(), employee.name.asc()).fetch();
		for(Employee employee1 : employees) {
			System.out.println(employee1.getName() + ", " + employee1.getSalary());
		}
	}

	// write test to demo group by in QueryDSL
	@Test
	public void test_GroupEmployee_Depart() {
		JPAQueryFactory query = new JPAQueryFactory(entityManager);
		QEmployee employee = QEmployee.employee;
		List<String> employeeDeparts = query.select(employee.dept).from(employee).groupBy(employee.dept).fetch();
		Assert.assertTrue(employeeDeparts.size() == 2);
	}

}



