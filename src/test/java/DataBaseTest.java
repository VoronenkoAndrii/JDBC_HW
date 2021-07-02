import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBaseTest {

    public static void main(String[] args) throws SQLException {
        DataBaseTest dataBaseManipulations = new DataBaseTest();
       //   dataBaseManipulations.createDataBase();

       // dataBaseManipulations.createTable();
        dataBaseManipulations.insertTable();

    }

    static final String user = "root";
    static final String pass = "and!31AVO)*";
    static String url1 = "jdbc:mysql://127.0.0.1:3306/?serverTimezone=UTC";

    public void createDataBase() {
        Connection conn = null;
        String url = "jdbc:mysql://127.0.0.1:3306/test1?serverTimezone=UTC";

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


        public void insertTable () {
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
                String Insert = "INSERT INTO `test2`.`REGISTRATION` (`id`, `first`, `last`, `age`) " +
                        "VALUES ('1', 'Jhon', 'Jhon', 55)," +
                        "('2', 'Vasya', 'Vasya', 25)," +
                        "('3', 'Olga', 'Vasya',33)";
                if (stmt != null) {
                    stmt.executeUpdate(Insert);
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

    }





