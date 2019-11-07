package com.haopn.demo.repository;

import com.haopn.demo.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {
    BookCategory findBookCategoryByName(String name);
    BookCategory findBookCategoryByBooksId(int id);

    int countDistinctBookCategoryByBooksIdLessThan(int id);

    BookCategory findByBooks_Id(int id);

}
