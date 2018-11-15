package com.jalen.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbUtil {

    public static void main(String[] args) {
        DbUtil util = new DbUtil();
//        Connection connection = util.getConnection();

//        System.out.println(connection);

//        util.add();

        util.update();
    }

    public void add() {
        DbUtil dbUtil = new DbUtil();
        Connection connection = dbUtil.getConnection();
        String sql = "insert into mytable values('荣伟','28','f','唱歌')";

        try {
            connection.setAutoCommit(false);//是否自动提交
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

            connection.commit();//提交，执行sql语句
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        DbUtil dbUtil = new DbUtil();
        Connection connection = dbUtil.getConnection();
        if (connection != null) {
            try {
                connection.setAutoCommit(false);

                Statement statement = connection.createStatement();

                String sql1 = "update mytable set hobby = '游泳' where name = 'jalenFang'";//正确语句
                String sql2 = "update mytable set hobby = 游泳 where name = 123";//错误语句

                statement.executeUpdate(sql1);
                statement.executeUpdate(sql2);

                connection.commit();//提交

            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();//数据库，操作出错了，回滚
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } finally {
                dbUtil.closeConnection(connection);
            }
        }
    }

    public Connection getConnection1() {
        try {
            Class.forName("com.mysql.jdbc.Driver");//加载驱动
            //jdbc 协议  mysql 数据库  localhost ip地址  3306端口号 mydb连接的数据库名
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "fjl123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConnection(String url, String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");//加载驱动
            //jdbc 协议  mysql 数据库  localhost ip地址  3306端口号 mydb连接的数据库名
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //使用配置文件的方式获取数据库链接
    public Connection getConnection() {
        Properties properties = new Properties();
        String driver, url, username, password;
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("DBConfig.properties"));
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
