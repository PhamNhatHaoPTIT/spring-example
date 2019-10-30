package com.haopn.demo;

import com.haopn.demo.entity.Book;
import com.haopn.demo.entity.BookCategory;
import com.haopn.demo.service.BookCategoryService;
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

	public List<Book> initListBook(BookCategory bookCategory) {
		List<Book> books = new ArrayList<>();
		Book book_1 = new Book();
		book_1.setName("Java tutorial");
		book_1.setBookCategory(bookCategory);
		Book book_2 = new Book();
		book_2.setName("C tutorial");
		book_2.setBookCategory(bookCategory);
		return books;
	}

	@Test
	public void testInsertNewBookCategory() {
		BookCategory bookCategory = new BookCategory();
		List<Book> books = initListBook(bookCategory);
		bookCategory.setBooks(books);
		bookCategoryService.save(bookCategory);
		List<BookCategory> list = bookCategoryService.findAll();
		Assert.assertTrue(list.size() != 0);
	}

}
