import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class Populate {

    void dropTables(Connection c) throws SQLException {
        Statement stmt= c.createStatement();
        String drop = "DROP TABLE ";

        try{
            stmt.execute(drop + " Party");
        }catch(SQLException e){
            System.out.println("Party table does not exist\n");
        }
        try{
            stmt.execute(drop + " Venue");
        }catch(SQLException e){
            System.out.println("Venue table does not exist\n");
        }
        try{
            stmt.execute(drop + " Menu");
        }catch(SQLException e){
            System.out.println("Menu table does not exist\n");
        }
        try{
            stmt.execute(drop + " Entertainment");
        }catch(SQLException e){
            System.out.println("Entertainment table does not exist\n");
        }
        System.out.println("Tables cleared\n");

    }

    void createTables(Connection c){
        try{
            Statement stmt= c.createStatement();

            String SQLCreateVenue = "CREATE TABLE Venue(\n" +
                    "    vid         SERIAL          PRIMARY KEY,\n" +
                    "    name        CHAR(20)        NOT NULL,\n" +
                    "    venuecost   NUMERIC(10, 2)  NOT NULL CHECK (venuecost > 0),\n" +
                    "    maxcapacity INTEGER         NOT NULL CHECK (maxcapacity > 0)\n" +
                    ")";

            String SQLCreateMenu = 	"CREATE TABLE Menu(\n" +
                    "    mid             SERIAL          PRIMARY KEY,\n" +
                    "    description     CHAR(100)       NOT NULL,\n" +
                    "    costprice       NUMERIC(10, 2)  NOT NULL CHECK (costprice > 0)\n" +
                    ")";

            String SQLCreateEntertainment = "CREATE TABLE Entertainment(\n" +
                    "    eid             SERIAL          PRIMARY KEY,\n" +
                    "    description     CHAR(100)       NOT NULL,\n" +
                    "    costprice       NUMERIC(10, 2)  NOT NULL CHECK (costprice > 0)\n" +
                    ")";

            String SQLCreateParty = "CREATE TABLE Party(\n" +
                    "    pid                 SERIAL          PRIMARY KEY,\n" +
                    "    name                CHAR(20)        NOT NULL,\n" +
                    "    mid                 INTEGER         NOT NULL,\n" +
                    "    vid                 INTEGER         NOT NULL,\n" +
                    "    eid                 INTEGER         NOT NULL,\n" +
                    "    price               NUMERIC(10, 2)  NOT NULL CHECK (price >= 0),\n" +
                    "    timing              DATE            NOT NULL,\n" +
                    "    numberofguests      INTEGER         NOT NULL CHECK (numberofguests >= 0),\n" +
                    "\n" +
                    "    FOREIGN KEY (mid) REFERENCES Menu(mid)\n" +
                    "        ON UPDATE CASCADE,\n" +
                    "    FOREIGN KEY (vid) REFERENCES Venue(vid)\n" +
                    "        ON UPDATE CASCADE,\n" +
                    "    FOREIGN KEY (eid) REFERENCES Entertainment(eid)\n" +
                    "        ON UPDATE CASCADE\n" +
                    ")";

            stmt.execute(SQLCreateVenue);
            stmt.execute(SQLCreateMenu);
            stmt.execute(SQLCreateEntertainment);
            stmt.execute(SQLCreateParty);
            System.out.println("Tables created\n");
        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    private static void createTestData(){
        List<Integer> pidList = new ArrayList<>();
        List<Integer> midList = new ArrayList<>();
        List<Integer> vidList = new ArrayList<>();
        List<Integer> eidList = new ArrayList<>();
        List<Integer> customerNamesList = new ArrayList<>();
        List<Integer> venueNamesList = new ArrayList<>();

    }
}
