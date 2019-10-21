package com.haopn.transactional.repository;

import com.haopn.transactional.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcBookRepository implements BookRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        String sql = "select count(*) from books";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int save(Book book) {
        String sql = "insert into books (name, price) values(?,?)";
        return jdbcTemplate.update(sql, book.getName(), book.getPrice());
    }

    @Override
    public int update(Book book) {
        String sql = "update books set price = ? where id = ?";
        return jdbcTemplate.update(sql, book.getPrice(), book.getId());
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "delete from books where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Book> findAll() {
        String sql = "select * from books";
        return jdbcTemplate.query(sql,
                                      (rs, rowNum) ->
                                                        new Book(rs.getInt(1), rs.getNString(2), rs.getFloat(3))
                                 );
    }

    @Override
    public Optional<Book> findById(Integer id) {
        String sql = "select * from books where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                                           (rs, rowNum) ->
                                            Optional.of(new Book(rs.getInt(1), rs.getNString(2), rs.getFloat(3))));
    }

}


