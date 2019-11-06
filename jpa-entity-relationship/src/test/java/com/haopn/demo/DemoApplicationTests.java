package com.haopn.demo;

import com.haopn.demo.entity.Book;
import com.haopn.demo.entity.BookCategory;
import com.haopn.demo.repository.BookRepository;
import com.haopn.demo.service.BookCategoryService;
import com.haopn.demo.service.BookService;
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
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

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
	BookCategoryService bookCategoryService;
	@Autowired
	BookService bookService;

	@PersistenceContext
	EntityManager entityManager;

	public List<Book> initListBook(BookCategory bookCategory) {
		List<Book> books = new ArrayList<>();
		Book book_1 = new Book();
		book_1.setName("Java tutorial");
		book_1.setBookCategory(bookCategory);

		Book book_2 = new Book();
		book_2.setName("C tutorial");
		book_2.setBookCategory(bookCategory);
		books.add(book_1);
		books.add(book_2);
		return books;
	}

	@Test
	public void testInsertNewBookCategory() {
		BookCategory bookCategory = new BookCategory("IT");
		bookCategory.setBooks(initListBook(bookCategory));
		bookCategoryService.save(bookCategory);

		bookService.deleteBookById(1);
		//bookCategoryService.getAllBookLabel("IT");
	}

}
