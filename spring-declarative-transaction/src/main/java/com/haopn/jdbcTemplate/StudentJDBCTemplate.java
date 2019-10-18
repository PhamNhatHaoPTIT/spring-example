package com.haopn.jdbcTemplate;

import com.haopn.dao.StudentDao;
import com.haopn.model.StudentMarks;
import com.haopn.model.StudentMarksMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class StudentJDBCTemplate implements StudentDao {

    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource ds) {
        jdbcTemplateObject = new JdbcTemplate(ds);
    }

    public boolean create(String name, Integer age, Integer marks, Integer year) {
        boolean status = false;
        try {
            String SQL1 = "insert into Student (name, age) values (?, ?)";
            jdbcTemplateObject.update( SQL1, name, age);
            // Get the latest student id to be used in Marks table
            String SQL2 = "select max(id) from Student";
            int sid = jdbcTemplateObject.queryForInt( SQL2 );
            String SQL3 = "insert into Marks(sid, marks, year) " + "values (?, ?, ?)";
            jdbcTemplateObject.update( SQL3, sid, marks, year);
            status = true;
        }
        catch (DataAccessException e) {
            throw e;
        }
        return status;
    }

    public List<StudentMarks> listStudents() {
        String SQL = "select * from Student, Marks where Student.id = Marks.sid";
        List <StudentMarks> studentMarks = jdbcTemplateObject.query(SQL, new StudentMarksMapper());
        return studentMarks;
    }
}
