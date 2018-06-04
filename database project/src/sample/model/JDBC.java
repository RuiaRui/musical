package sample.model;


import java.sql.*;

public class JDBC {
    //数据库用户名
    private static final String USERNAME = "root";
    //数据库密码
    private static final String PASSWORD = "010825";
    //驱动信息
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    //数据库地址
    private static final String URL = "jdbc:mysql://localhost:3306/database";
    private Connection connection;

    public JDBC() {
        // TODO Auto-generated constructor stub
        try {
            Class.forName(DRIVER);


        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * 获得数据库的连接
     *
     * @return
     */
    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database connection successful!");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return connection;
    }
}


