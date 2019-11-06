package com.haopn.demo;

import com.haopn.demo.entity.Book;
import com.haopn.demo.entity.BookCategory;
import com.haopn.demo.service.BookCategoryService;
import com.haopn.demo.service.BookService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
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

	public void initData() {
		BookCategory bookCategory = new BookCategory("IT");
		List<Book> books = new ArrayList<>();
		Book book_1 = new Book();
		book_1.setName("Java tutorial");
		book_1.setBookCategory(bookCategory);

		Book book_2 = new Book();
		book_2.setName("C tutorial");
		book_2.setBookCategory(bookCategory);
		books.add(book_1);
		books.add(book_2);

		bookCategory.setBooks(books);
		bookCategoryService.save(bookCategory);
	}

	@Test
	public void testQualifyingAnnotation() {
		initData();
		bookService.deleteBookById(1);
	}

	@Test
	public void testLoadLazyList() {
		initData();
		bookCategoryService.getAllBookLabel("IT");
	}

	@Test
	public void testNestedPropertyFind() {
		initData();
		BookCategory bookCategory = bookCategoryService.findBookCategoryByBooksId(2);
		Assert.assertNotNull(bookCategory);
	}

}
