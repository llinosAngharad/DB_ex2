import java.sql.*;

class Connect {

    private String userName;
    private String password;

    Connect(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    Connection getConnection() throws SQLException{
        Connection connection;

//		String connectionString = "jdbc:postgresql://mod-intro-databases/lxw404";	// from lab
        String connectionString = "jdbc:postgresql://mod-intro-databases.cs.bham.ac.uk/lxw404";	// from laptop

        connection = DriverManager.getConnection(connectionString, this.userName, this.password);
        System.out.println("Successful connection to database!\n");
        return connection;
    }
}
