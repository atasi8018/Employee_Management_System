
package com.employee;

import com.employee.util.DbConnection;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

public class ConnectionTest {

    public static void main(String[] args) {

        try (Connection con = DbConnection.getConnection()) {

            System.out.println("Database connected successfully!");
            System.out.println("Database: "
                    + con.getCatalog());
            System.out.println("Connection valid: "
                    + con.isValid(2));

            DatabaseMetaData metaData = con.getMetaData();

            System.out.println("Database product: "
                    + metaData.getDatabaseProductName());
            System.out.println("JDBC driver: "
                    + metaData.getDriverName());

        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}
