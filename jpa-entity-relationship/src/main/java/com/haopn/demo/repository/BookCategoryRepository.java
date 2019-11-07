package com.haopn.demo.repository;

import com.haopn.demo.entity.BookCategory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {

    @EntityGraph(value = "BookCategory.books")
    BookCategory findBookCategoryByName(String name);
    BookCategory findBookCategoryByBooksId(int id);

    int countDistinctBookCategoryByBooksIdLessThan(int id);

    BookCategory findByBooks_Id(int id);

}
