import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PartyCompany {
    static String dbAddress = "jdbc.postgresql://mod-intro-databases/";
    private String userName;
    private String password;

    private PartyCompany(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    private Connection getConnection() throws SQLException{
        Connection connection;
//		String connectionString = "jdbc:postgresql://mod-intro-databases/lxw404";	// from lab
        String connectionString = "jdbc:postgresql://mod-intro-databases.cs.bham.ac.uk/lxw404";	// from laptop
        connection = DriverManager.getConnection(connectionString, this.userName, this.password);
        return connection;
    }

    private static void createTables(Connection c){
        try{
            Statement stmt= c.createStatement();

            String SQLCreateVenue = "CREATE TABLE Venue(" +
                    "vid			INTEGER		PRIMARY KEY," +
                    "name			CHAR(20)	NOT NULL," +
                    "venue			DECIMAL		NOT NULL," +
                    "maxcapacity	INTEGER		NOT NULL" +
                    ")";

            String SQLCreateMenu = 	"CREATE TABLE Menu(" +
                    "mid			INTEGER		PRIMARY KEY," +
                    "description	CHAR(100)	NOT NULL," +
                    "costprice		DECIMAL		NOT NULL" +
                    ")";

            String SQLCreateEntertainment = "CREATE TABLE Entertainment(" +
                    "eid			INTEGER		PRIMARY KEY," +
                    "description	CHAR(100)	NOT NULL," +
                    "costprice		DECIMAL		NOT NULL" +
                    ")";

            String SQLCreateParty = "CREATE TABLE Party(" +
                    "pid			INTEGER		PRIMARY KEY," +
                    "name			CHAR(20)	NOT NULL," +
                    "mid			INTEGER		NOT NULL," +
                    "vid			INTEGER		NOT NULL," +
                    "eid			INTEGER		NOT NULL," +
                    "price			DECIMAL		NOT NULL," +
                    "timing			DATE		NOT NULL," +
                    "FOREIGN KEY (mid) REFERENCES Menu(mid)" +
                    "	ON UPDATE CASCADE," +
                    "FOREIGN KEY(vid) REFERENCES Venue(vid)" +
                    "	ON UPDATE CASCADE," +
                    "FOREIGN KEY (eid) REFERENCES Entertainment(eid)" +
                    "	ON UPDATE CASCADE)";

            stmt.execute(SQLCreateVenue);
            stmt.execute(SQLCreateMenu);
            stmt.execute(SQLCreateEntertainment);
            stmt.execute(SQLCreateParty);
        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    private static void dropTables(Connection c){
        try{
            Statement stmt= c.createStatement();

            String SQLDropVenue = "DROP TABLE Venue";
            String SQLDropMenu = "DROP TABLE Menu";
            String SQLDropEntertainment = "DROP TABLE Entertainment";
            String SQLDropParty = "DROP TABLE Party";

            stmt.execute(SQLDropParty);
            stmt.execute(SQLDropEntertainment);
            stmt.execute(SQLDropMenu);
            stmt.execute(SQLDropVenue);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        PartyCompany pc = new PartyCompany("lxw404", "6yl8m7fncq");
        try {
            Connection con = pc.getConnection();
            createTables(con);
//            dropTables(con);

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

}
