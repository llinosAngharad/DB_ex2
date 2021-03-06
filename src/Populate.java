import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class Populate {

    void dropTables(Connection c) throws SQLException {
        Statement stmt= c.createStatement();
        String SQLQuery = "DROP SCHEMA public CASCADE;" +
                          "CREATE SCHEMA public;";
        try{
            stmt.execute(SQLQuery);
        }catch(SQLException e){
            System.out.println("Tables do not exist\n");
        }
        finally {
            try{
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        System.out.println("Test tables cleared");
    }

    void createTables(Connection c){
        Statement stmt = null;
        try{
            stmt= c.createStatement();

            String SQLCreateVenue = "CREATE TABLE Venue(\n" +
                                    "vid         SERIAL      PRIMARY KEY CHECK(vid > 0),\n" +
                                    "name        CHAR(20)    NOT NULL,\n" +
                                    "venuecost   INTEGER     NOT NULL CHECK (venuecost >= 0),\n" +
                                    "maxcapacity INTEGER     NOT NULL CHECK (maxcapacity > 0)\n" +
                                    ")";

            String SQLCreateMenu = 	"CREATE TABLE Menu(\n" +
                                    "mid             SERIAL      PRIMARY KEY CHECK(mid >0),\n" +
                                    "description     CHAR(100)   NOT NULL,\n" +
                                    "costprice       INTEGER     NOT NULL CHECK (costprice >= 0)\n" +
                                    ")";

            String SQLCreateEntertainment = "CREATE TABLE Entertainment(\n" +
                                            "eid             SERIAL          PRIMARY KEY CHECK(eid > 0),\n" +
                                            "description     CHAR(100)       NOT NULL,\n" +
                                            "costprice       INTEGER         NOT NULL CHECK (costprice >= 0)\n" +
                                            ")";

            String SQLCreateParty = "CREATE TABLE Party(\n" +
                                    "pid                 SERIAL          PRIMARY KEY CHECK(pid > 0),\n" +
                                    "name                CHAR(20)        NOT NULL,\n" +
                                    "mid                 INTEGER         NOT NULL,\n" +
                                    "vid                 INTEGER         NOT NULL,\n" +
                                    "eid                 INTEGER         NOT NULL,\n" +
                                    "price               INTEGER         NOT NULL CHECK (price >= 0),\n" +
                                    "timing              TIMESTAMP       NOT NULL,\n" +
                                    "numberofguests      INTEGER         NOT NULL CHECK (numberofguests >= 0),\n" +
                                    "\n" +
                                    "FOREIGN KEY (mid) REFERENCES Menu(mid)\n" +
                                        "ON UPDATE CASCADE,\n" +
                                    "FOREIGN KEY (vid) REFERENCES Venue(vid)\n" +
                                        "ON UPDATE CASCADE,\n" +
                                    "FOREIGN KEY (eid) REFERENCES Entertainment(eid)\n" +
                                        "ON UPDATE CASCADE\n" +
                                    ")";

            stmt.execute(SQLCreateVenue);
            stmt.execute(SQLCreateMenu);
            stmt.execute(SQLCreateEntertainment);
            stmt.execute(SQLCreateParty);
            System.out.println("Test tables created\n");
        } catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

    }

    void createTestVenues(Connection c) {
        PreparedStatement stmt = null;
        try {
            String SQLInsertValue = "INSERT INTO Venue (name, venuecost, maxcapacity) " +
                                    "VALUES (?, ?, ?)";

            stmt = c.prepareStatement(SQLInsertValue);
            ArrayList<String> venueNameList = new ArrayList<>(Arrays.asList("Guild of Students", "O2 Academy", "The Jam House", "The Bristol Pear", "Ikon Gallery", "Hare & Hounds",
                    "Birmingham Town Hall", "Party Central", "NEC", "The S'Oak"));

            for (int i = 0; i < 100; i++) {
                Random rand = new Random();

                String venueName = venueNameList.get(rand.nextInt(10));
                int venueCost = rand.nextInt(1000);
                int maxCapacity = rand.nextInt(250) + 1;

                stmt.setString(1, venueName);
                stmt.setInt(2, venueCost);
                stmt.setInt(3, maxCapacity);
                stmt.addBatch();
            }
            stmt.executeBatch();
            System.out.println("Test venue table");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    void createTestMenus(Connection c) {
        PreparedStatement stmt = null;
        try {
            String SQLInsertValue = "INSERT INTO Menu (description, costprice) " +
                                    "VALUES (?, ?)";

            ArrayList<String> menuDescriptionList = new ArrayList<>(Arrays.asList("Around the world christmas dinner",
                    "Traditional christmas dinner with all the trimmings", "Vegan christmas delights",
                    "Essential christmas nibbles", "4 course christmas lunch", "Christmas desserts extravaganza",
                    "Christmas special bundle", "Cheese boards and wines", "Santa's christmas dinner party", "Kid's christmas special"));

            stmt = c.prepareStatement(SQLInsertValue);

            for (int i = 0; i < 100; i++) {
                Random rand = new Random();

                String menuDescription = menuDescriptionList.get(rand.nextInt(10));
                int menuCost = rand.nextInt(100) + 1;

                stmt.setString(1, menuDescription);
                stmt.setInt(2, menuCost);
                stmt.addBatch();
            }
            stmt.executeBatch();
            System.out.println("Test menu table");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    void createTestEntertainment(Connection c) {
        PreparedStatement stmt = null;
        try {
            String SQLInsertValue = "INSERT INTO Entertainment (description, costprice) " +
                                    "VALUES (?, ?)";

            ArrayList<String> descriptionList = new ArrayList<>(Arrays.asList("Michael Buble", "Mariah Carey", "Wham!", "The Jackson 5",
                    "Diana Ross", "Frank Sinatra Tribute Act", "The Birmingham Christmas Choir", "Bing Crosby", "RuPaul", "Snoop Dogg"));

            stmt = c.prepareStatement(SQLInsertValue);

            for (int i = 0; i < 100; i++) {
                Random rand = new Random();

                String description = descriptionList.get(rand.nextInt(10));
                int cost = rand.nextInt(10000) + 1;

                stmt.setString(1, description);
                stmt.setInt(2, cost);
                stmt.addBatch();
            }
            stmt.executeBatch();
            System.out.println("Test entertainment table");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    void createTestParties(Connection c) {
        PreparedStatement stmt = null;
        try {
            String SQLInsertValue = "INSERT INTO party (name, mid, vid, eid, price, timing, numberofguests)" +
                                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            ArrayList<java.sql.Timestamp> timesList = new ArrayList<>(Arrays.asList(Timestamp.valueOf("2018-12-04 21:00:00.000000000"), Timestamp.valueOf("2018-12-05 18:30:00.000000000"),
                    Timestamp.valueOf("2018-12-07 12:00:00.000000000"), Timestamp.valueOf("2018-12-21 19:00:00.000000000"), Timestamp.valueOf("2019-01-02 20:00:00.000000000"),
                    Timestamp.valueOf("2018-12-18 15:30:00.000000000"), Timestamp.valueOf("2018-12-12 09:00:00.000000000"), Timestamp.valueOf("2018-12-11 17:15:00.000000000"),
                    Timestamp.valueOf("2018-12-25 09:00:00.000000000"), Timestamp.valueOf("2018-12-16 18:00:00.000000000")));

            stmt = c.prepareStatement(SQLInsertValue);

            for (int i = 0; i < 1000; i++) {
                Random rand = new Random();


                String name = "Party" + i;
                int mid = rand.nextInt(100) + 1;
                int vid = rand.nextInt(100) + 1;
                int eid = rand.nextInt(100) + 1;
                int price = rand.nextInt(20000-14000+1) + 14000;
                Timestamp timing = timesList.get(rand.nextInt(10));
                int numberofguests = rand.nextInt(250) + 1;

                stmt.setString(1, name);
                stmt.setInt(2, mid);
                stmt.setInt(3, vid);
                stmt.setInt(4, eid);
                stmt.setInt(5, price);
                stmt.setTimestamp(6, timing);
                stmt.setInt(7, numberofguests);
                stmt.addBatch();
            }
            stmt.executeBatch();
            System.out.println("Test party table");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    void populateTables(Connection c){
        try{
            dropTables(c);
            createTables(c);
            System.out.println("Populating...");
            createTestVenues(c);
            createTestMenus(c);
            createTestEntertainment(c);
            createTestParties(c);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
