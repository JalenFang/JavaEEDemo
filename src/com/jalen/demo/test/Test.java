package com.jalen.demo.test;

import com.jalen.demo.util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Author: Jalen
 * Email: braveJalen@163.com
 * Date: 2018/11/14 11:45
 * Version: 1.0.0
 * Desc:
 */
public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        test.list();
    }

    public void list() {
        DbUtil dbUtil = new DbUtil();

        Connection connection = dbUtil.getConnection();

        String sql = "select * from mytable";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String string = resultSet.getString(1);
                String string1 = resultSet.getString(2);

                System.out.println("1: " + string + " 2: " + string1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.closeConnection(connection);
        }
    }

}
