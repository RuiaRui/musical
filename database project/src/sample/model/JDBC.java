package sample.model;


import java.sql.*;

public class JDBC {
    //���ݿ��û���
    private static final String USERNAME = "root";
    //���ݿ�����
    private static final String PASSWORD = "010825";
    //������Ϣ
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    //���ݿ��ַ
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
     * ������ݿ������
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


