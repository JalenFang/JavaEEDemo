package com.jalen.demo.test;

import com.jalen.demo.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: Jalen
 * Email: braveJalen@163.com
 * Date: 2018/11/15 11:06
 * Version: 1.0.0
 * Desc: sql中可以使用？占位
 */
class PreparedStatementTest {

    public static void main(String[] args) {
        Student student = new Student();
        student.name = "荣伟";
        student.age = "28";
        student.sex = "男";
        student.hobby = "睡觉";
//        add(student);

//        delete(student);

//        update(student);

        select(student);
    }

    public static void add(Student student) {
        DbUtil dbUtil = new DbUtil();
        Connection connection = dbUtil.getConnection();
        if (connection != null) {
            String sql = "insert into  mytable(name,age,sex,hobby) values (?,?,?,?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, student.name);
                preparedStatement.setString(2, student.age);
                preparedStatement.setString(3, student.sex);
                preparedStatement.setString(4, student.hobby);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void delete(Student student) {
        DbUtil dbUtil = new DbUtil();
        Connection connection = dbUtil.getConnection();
        if (connection != null) {
            String sql = "delete from mytable where name = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, student.name);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static void update(Student student) {
        DbUtil dbUtil = new DbUtil();
        Connection connection = dbUtil.getConnection();
        if (connection != null) {
            String sql = "update mytable set name = '周荣伟' where name = ? and age = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, student.name);
                preparedStatement.setString(2, student.age);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void select(Student student) {
        DbUtil dbUtil = new DbUtil();
        Connection connection = dbUtil.getConnection();
        if (connection != null) {
            String sql = "select * from mytable where age >= ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, student.age);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String age = resultSet.getString("age");
                    String sex = resultSet.getString("sex");
                    String hobby = resultSet.getString("hobby");
                    System.out.println("name: " + name + " age: " + age + " sex: " + sex + " hobby: " + hobby + "\n");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Student {
        public String name;
        public String age;
        public String sex;
        public String hobby;
    }
}
