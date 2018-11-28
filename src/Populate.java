import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
        System.out.println("Tables cleared");

    }

    void createTables(Connection c){
        try{
            Statement stmt= c.createStatement();

            String SQLCreateVenue = "CREATE TABLE Venue(\n" +
                    "    vid         SERIAL  PRIMARY KEY,\n" +
                    "    name        CHAR(20)    NOT NULL,\n" +
                    "    venuecost   INTEGER     NOT NULL CHECK (venuecost > 0),\n" +
                    "    maxcapacity INTEGER     NOT NULL CHECK (maxcapacity > 0)\n" +
                    ")";

            String SQLCreateMenu = 	"CREATE TABLE Menu(\n" +
                    "    mid             SERIAL      PRIMARY KEY,\n" +
                    "    description     CHAR(100)   NOT NULL,\n" +
                    "    costprice       INTEGER     NOT NULL CHECK (costprice > 0)\n" +
                    ")";

            String SQLCreateEntertainment = "CREATE TABLE Entertainment(\n" +
                    "    eid             SERIAL          PRIMARY KEY,\n" +
                    "    description     CHAR(100)       NOT NULL,\n" +
                    "    costprice       INTEGER         NOT NULL CHECK (costprice > 0)\n" +
                    ")";

            String SQLCreateParty = "CREATE TABLE Party(\n" +
                    "    pid                 SERIAL          PRIMARY KEY,\n" +
                    "    name                CHAR(20)        NOT NULL,\n" +
                    "    mid                 INTEGER         NOT NULL,\n" +
                    "    vid                 INTEGER         NOT NULL,\n" +
                    "    eid                 INTEGER         NOT NULL,\n" +
                    "    price               INTEGER         NOT NULL CHECK (price >= 0),\n" +
                    "    timing              TIMESTAMP       NOT NULL,\n" +
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

    void createTestVenues(Connection c) {
        try {
            String SQLInsertValue = "INSERT INTO Venue (name, venuecost, maxcapacity) " +
                    "VALUES (?, ?, ?)";

            PreparedStatement stmt = c.prepareStatement(SQLInsertValue);
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
            System.out.println("Test venue table created");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void createTestMenus(Connection c) {
        try {
            String SQLInsertValue = "INSERT INTO Menu (description, costprice) VALUES (?, ?)";

            ArrayList<String> menuDescriptionList = new ArrayList<>(Arrays.asList("Pan-fried Brussels Sprouts with Tofu Bacon", "Griddled leeks with hazelnut dressing", "Beetroot & squash Wellington",
                    "Roasted stuffed cauliflower", "Nut roast with seasonal roast vegetables", "Christmas pudding ice cream",
                    "Chestnut pate strudel with sauce", "Cranberry and pine nut stuffing", "Maple-roast parsnips and carrots", "Red cabbage & kale salad"));

            PreparedStatement stmt = c.prepareStatement(SQLInsertValue);

            for (int i = 0; i < 100; i++) {
                Random rand = new Random();

                String menuDescription = menuDescriptionList.get(rand.nextInt(10));
                int menuCost = rand.nextInt(100) + 1;

                stmt.setString(1, menuDescription);
                stmt.setInt(2, menuCost);
                stmt.addBatch();
            }
            stmt.executeBatch();
            System.out.println("Test menu table created");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void createTestEntertainment(Connection c) {
        try {
            String SQLInsertValue = "INSERT INTO Entertainment (description, costprice) VALUES (?, ?)";

            ArrayList<String> descriptionList = new ArrayList<>(Arrays.asList("Michael Buble", "Mariah Carey", "Wham!", "The Jackson 5",
                    "Diana Ross", "Frank Sinatra Tribute Act", "The Birmingham Christmas Choir", "Bing Crosby", "RuPaul", "Snoop Dogg"));

            PreparedStatement stmt = c.prepareStatement(SQLInsertValue);

            for (int i = 0; i < 100; i++) {
                Random rand = new Random();

                String description = descriptionList.get(rand.nextInt(10));
                int cost = rand.nextInt(10000) + 1;

                stmt.setString(1, description);
                stmt.setInt(2, cost);
                stmt.addBatch();
            }
            stmt.executeBatch();
            System.out.println("Test entertainment table created");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void createTestParties(Connection c) {
        try {
            String SQLInsertValue = "INSERT INTO party (name, mid, vid, eid, price, timing, numberofguests)" +
                    "                VALUES (?, ?, ?, ?, ?, ?, ?)";

            ArrayList<java.sql.Timestamp> timesList = new ArrayList<>(Arrays.asList(Timestamp.valueOf("2018-12-04 21:00:00.000000000"), Timestamp.valueOf("2018-12-05 18:30:00.000000000"),
                    Timestamp.valueOf("2018-12-07 12:00:00.000000000"), Timestamp.valueOf("2018-12-21 19:00:00.000000000"), Timestamp.valueOf("2019-01-02 20:00:00.000000000"),
                    Timestamp.valueOf("2018-12-18 15:30:00.000000000"), Timestamp.valueOf("2018-12-12 09:00:00.000000000"), Timestamp.valueOf("2018-12-11 17:15:00.000000000"),
                    Timestamp.valueOf("2018-12-25 09:00:00.000000000"), Timestamp.valueOf("2018-12-16 18:00:00.000000000")));

            PreparedStatement stmt = c.prepareStatement(SQLInsertValue);

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
            System.out.println("Test party table created");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void populateTables(Connection c){
        try{
            dropTables(c);
            createTables(c);
            createTestVenues(c);
            createTestMenus(c);
            createTestEntertainment(c);
            createTestParties(c);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
