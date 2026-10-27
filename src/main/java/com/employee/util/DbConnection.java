package com.employee.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    private static Properties properties = new Properties();
    static {
    try(FileInputStream fis = new FileInputStream("C:\\Users\\atasi\\OneDrive\\Desktop\\ja\\section2\\Employee_management_System\\src\\main\\resources\\db.properties")){

    properties.load(fis);
    }catch (IOException e){
        e.printStackTrace();
    }
    }
        public static Connection getConnection()  throws SQLException{
            return DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.username"),
                    properties.getProperty("db.password")
            );
        }
    }
