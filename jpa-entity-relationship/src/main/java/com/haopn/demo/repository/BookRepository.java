package com.haopn.demo.repository;

import com.haopn.demo.entity.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer>, QuerydslPredicateExecutor<Book> {

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("delete from Book b where b.id = ?1")
    void deleteBookById(int id);

    List<Book> findAll(Sort sort);

    Book findByAbc(String name);

}
