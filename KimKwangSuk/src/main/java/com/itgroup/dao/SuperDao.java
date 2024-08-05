package com.itgroup.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SuperDao {
    //4)
    private String driver;
    private String url = null;
    private String id = null;
    private String password = null;

    public SuperDao(String driver, String url, String id, String password) {
        this.driver = driver;
        this.url = url;
        this.id = id;
        this.password = password;
    }

    //생성자 만들기
    public SuperDao() {

        this.driver = "oracle.jdbc.driver.OracleDriver";
        this.url = "jdbc:oracle:thin:@localhost:1521:xe";
        this.id = "KimKwangSuk";
        this.password = "kim";
        //3)
        try {
            //5)
            Class.forName(driver); //동적 개체 생성 //핵심
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, id, password); //핵심
            if (conn != null) {
                System.out.println("접속 성공");
            } else {
                System.out.println("접속 실패");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
        return conn;
    }
}