package com.cibt.springapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.cibt.springapp.dao.StudentDAO;
import com.cibt.springapp.models.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAOImpl implements StudentDAO {
    
    @Autowired
    private JdbcTemplate template;

    @Override
	public List<Student> fetchAll() {
        String sql = "SELECT * FROM tbl_students";

        return template.query(sql, new RowMapper<Student>() {

            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setStudentName(rs.getString("student_name"));
                student.setStudentAddress(rs.getString("student_address"));
                student.setStudentEmail(rs.getString("student_email"));
                return student;
			}

        });
	}

    @Override
    public int insert(final Student student) {

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        int result = template.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                String sql = "INSERT INTO tbl_students (student_name, student_address, student_email) "
                    + "VALUES (?, ?, ?)";
                
                PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, student.getStudentName());
                statement.setString(2, student.getStudentAddress());
                statement.setString(3, student.getStudentEmail());
				return statement;
			}

        }, keyHolder);

        int primaryKey = keyHolder.getKey().intValue();
        student.setId(primaryKey);

        return result;

        /* String sql = "INSERT INTO tbl_students (student_name, student_address, student_email) "
                    + "VALUES (?, ?, ?)";
        return template.update(sql, new Object[] {
            student.getStudentName(),
            student.getStudentAddress(),
            student.getStudentEmail()
        }); */
    }

    @Override
    public int update(Student student) {
        String sql = "UPDATE tbl_students SET "
                    + "student_name = ?, "
                    + "student_address = ?, "
                    + "student_email = ? "
                    + "WHERE id = ?";
        return template.update(sql, new Object[] {
            student.getStudentName(),
            student.getStudentAddress(),
            student.getStudentEmail(),
            student.getId()
        });
    }

    @Override
    public Student fetchById(int id) {
        String sql = "SELECT * FROM tbl_students WHERE id = ?";
        
        return template.queryForObject(sql, new RowMapper<Student>() {

            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setStudentName(rs.getString("student_name"));
                student.setStudentAddress(rs.getString("student_address"));
                student.setStudentEmail(rs.getString("student_email"));
                return student;

            }

        }, new Object[]{id});
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM tbl_students "
                    + "WHERE id = ?";
        return template.update(sql, new Object[]{id});
    }

    @Override
    public Student getLastInsertedId() {
        String sql = "SELECT id from students ORDER BY id DESC LIMIT 1";
        return template.queryForObject(sql, new RowMapper<Student>() {

            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                return student;
            }

        });
    }

    
}