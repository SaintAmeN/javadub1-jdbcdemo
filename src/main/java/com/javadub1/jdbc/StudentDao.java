package com.javadub1.jdbc;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private JdbcConnection jdbcConnection;

    public StudentDao() {
        try {
            jdbcConnection = new JdbcConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Błąd konfiguracji bazodanowej!");
            System.exit(2);
        }
    }

    private final static String CREATE_TABLE_STATEMENT =
            "create table if not exists students(\n" +
                    "`id` INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                    "`indeks` VARCHAR(10) NOT NULL,\n" +
                    "`name` VARCHAR(255) NOT NULL,\n" +
                    "`age` INTEGER NOT NULL\n" +
                    ");";

    private final static String INSERT_STUDENT_STATEMENT =
            "INSERT INTO `student_database`.`students`\n" +
                    "(`id`, `indeks`, `name`, `age`)\n" +
                    "VALUES\n" +
                    "(NULL, ?, ?, ?);";

    private final static String SELECT_ALL_STUDENTS =
            "SELECT * FROM `student_database`.`students`";

    private final static String DELETE_STUDENT =
            "DELETE FROM `student_database`.`students` WHERE `id`=? ";

    public void insertStudent(Student student) {
        try (PreparedStatement statement = jdbcConnection.getConnection().prepareStatement(INSERT_STUDENT_STATEMENT)) {
            statement.setString(1, student.getIndeks());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getAge());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(Long studentId) {
        try (PreparedStatement statement = jdbcConnection.getConnection().prepareStatement(DELETE_STUDENT)) {
            statement.setLong(1, studentId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();

        try (PreparedStatement statement = jdbcConnection.getConnection().prepareStatement(SELECT_ALL_STUDENTS)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) { // dopóki jest następny rekord
//                czytaj
                Student pobrany = new Student();
                pobrany.setId(resultSet.getLong(1));
                pobrany.setIndeks(resultSet.getString(2));
                pobrany.setName(resultSet.getString(3));
                pobrany.setAge(resultSet.getInt(4));

                list.add(pobrany);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
