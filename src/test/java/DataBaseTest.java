import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class DataBaseTest {

    public static void main(String[] args) throws SQLException {
        DataBaseTest dataBaseManipulations = new DataBaseTest();
        //   dataBaseManipulations.createDataBase();
        // dataBaseManipulations.createTable();
        //  dataBaseManipulations.insertTable();
        // dataBaseManipulations.updateTable();
        //  dataBaseManipulations.assertToUpdate();
        //  dataBaseManipulations.deleteRowInTable();
        //  dataBaseManipulations.dropTable();
        dataBaseManipulations.dropDatabase();
    }

    static final String user = "root";
    static final String pass = "and!31AVO)*";
    static String url1 = "jdbc:mysql://127.0.0.1:3306/?serverTimezone=UTC";

    public void createDataBase() {
        Connection conn = null;
        String url = "jdbc:mysql://127.0.0.1:3306/test2?serverTimezone=UTC";

        try {
            conn = DriverManager.getConnection(url1, user, pass);
            if (conn != null) {
                System.out.println("Connected to the database test1");
            }
            Statement stmt = conn.createStatement();
            String sql = "CREATE DATABASE test2";
            stmt.execute(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException e) {
            System.out.println("Faild to connect");
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }


    public void createTable() {
        Connection conn = null;
        String url = "jdbc:mysql://127.0.0.1:3306/test2?serverTimezone=UTC";
        try {
            conn = DriverManager.getConnection(url, user, pass);
            if (conn != null) {
                System.out.println("Connected to the database test1");
            }
            Statement stmt = conn.createStatement();
            String createTable = "CREATE TABLE REGISTRATION " +
                    "(id INTEGER not NULL, " +
                    " first VARCHAR(255), " +
                    " last VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTable);
            System.out.println("Table created");
        } catch (SQLException e) {
            System.out.println("Faild to connect");
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void insertTable() {
        String url = "jdbc:mysql://127.0.0.1:3306/test2?serverTimezone=UTC";
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connection driver created");
            if (conn != null) {
                System.out.println("Connected to the database person");
                stmt = conn.createStatement();
            }
            String insert = "INSERT INTO `test2`.`REGISTRATION` (`id`, `first`, `last`, `age`) " +
                    "VALUES ('1', 'Jhon', 'Jhon', 55)," +
                    "('2', 'Vasya', 'Vasya', 25)," +
                    "('3', 'Olga', 'Vasya',33)";
            if (stmt != null) {
                stmt.executeUpdate(insert);
            }
            System.out.println("New rows are created");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateTable() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        String url = "jdbc:mysql://127.0.0.1:3306/test2?serverTimezone=UTC";

        try {
            conn = DriverManager.getConnection(url, user, pass);
            if (conn != null) {
                System.out.println("Connected to the database test1");
                stmt = conn.createStatement();
            }
            String updateRow = "UPDATE `test2`.`REGISTRATION` " +
                    "SET `age` = '44' " +
                    "WHERE (`id` = '2')";
            if (stmt != null) {
                stmt.execute(updateRow);
            } else {
                System.out.println("stmt creation error");
            }
            System.out.println("age is updated");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void assertToUpdate() throws SQLException {
        Connection conn;
        Statement stmt = null;
        ResultSet resultSet = null;
        String url = "jdbc:mysql://127.0.0.1:3306/test2?serverTimezone=UTC";
        conn = DriverManager.getConnection(url, user, pass);
        System.out.println("Connection driver created");

        if (conn != null) {
            stmt = conn.createStatement();
            System.out.println("Statement created");
        }
        if (conn != null) {
            resultSet = stmt.executeQuery("select * from test2.REGISTRATION");
            System.out.println("Execute query");
        }
        List<String[]> allRows = new ArrayList<>();
        int numberColumns = 4;
        if (resultSet != null) {
            System.out.println("founded records");
            while (resultSet.next()) {
                String[] currentRow = new String[numberColumns];
                for (int i = 1; i <= numberColumns; i++) {
                    currentRow[i - 1] = resultSet.getString(i);
                }
                allRows.add(currentRow);
            }
        } else {
            System.out.println("No records found");
        }

        boolean iscontains = false;
        for (String[] row : allRows) {
            if (row[0].equals("2")) {
                if (row[3].equals("44")) {
                    System.out.println("row[3]." + row[3]);
                    iscontains = true;
                }
            }
        }
        assertTrue(iscontains);

        conn.close();
        stmt.close();
    }

    public void deleteRowInTable() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        String url = "jdbc:mysql://127.0.0.1:3306/test2?serverTimezone=UTC";
        try {
            conn = DriverManager.getConnection(url, user, pass);
            if (conn != null) {
                System.out.println("Connected to the database test1");
                stmt = conn.createStatement();
            }
            String updateRow = "UPDATE `test2`.`REGISTRATION` " +
                    "SET `age` = '44' " +
                    "WHERE (`id` = '2')";
            if (stmt != null) {
                stmt.execute(updateRow);
            }

            String sqlInsertStatement = "DELETE FROM `test2`.`REGISTRATION` WHERE (`id` = '1')";
            if (stmt != null) {
                stmt.execute(sqlInsertStatement);
            }
            System.out.println("Row 2 was deleted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void dropTable() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        String url = "jdbc:mysql://127.0.0.1:3306/test2?serverTimezone=UTC";
        try {
            conn = DriverManager.getConnection(url, user, pass);
            if (conn != null) {
                System.out.println("Connected to the database test1");
                stmt = conn.createStatement();
            }
            stmt.executeUpdate("DROP TABLE REGISTRATION");
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, "REGISTRATION", null);
            assertTrue(resultSet != null);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void dropDatabase() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        String url = "jdbc:mysql://127.0.0.1:3306/test1?serverTimezone=UTC";
        try {
            conn = DriverManager.getConnection(url, user, pass);
            if (conn != null) {
                System.out.println("Connected to the database test1");
                stmt = conn.createStatement();
            }
            stmt.executeUpdate("DROP DATABASE test2");
            assertTrue(stmt != null);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}








