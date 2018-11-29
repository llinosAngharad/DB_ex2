import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PartyReport {

    private int inputpid;

    public PartyReport(int pid){
        inputpid = pid;
    }

    void makeReport(Connection c){
        try{
            Statement stmt = c.createStatement();
            int nog = 0;
            int partyPrice = 0;
            int vPrice = 0;
            int mPrice = 0;
            int ePrice = 0;
            ResultSet rs = stmt.executeQuery("SELECT pid, name, numberofguests, price" +
                    "                              FROM Party" +
                    "                              WHERE pid=" + inputpid);
            System.out.println("\nPARTY REPORT:\n");
            while(rs.next()){
                System.out.println("Party ID: " + rs.getInt("pid"));
                System.out.println("Party name: " + rs.getString("name"));
                nog = rs.getInt("numberofguests");
                partyPrice = rs.getInt("price");
            }
            rs = stmt.executeQuery("SELECT name, venuecost\n" +
                    "                    FROM Venue\n" +
                    "                    WHERE vid IN(SELECT vid\n" +
                    "                                FROM Party\n" +
                    "                                WHERE pid =" + inputpid + ")");
            while(rs.next()){
                System.out.println("Venue name: " + rs.getString("name"));
                vPrice = rs.getInt("venuecost");

            }
            rs = stmt.executeQuery("SELECT description, costprice\n" +
                    "                    FROM Menu\n" +
                    "                    WHERE mid IN(SELECT mid\n" +
                    "                                 FROM Party\n" +
                    "                                 WHERE pid =" + inputpid + ")");
            while(rs.next()){
                System.out.println("Menu description: " + rs.getString("description"));
                mPrice = rs.getInt("costprice");
            }
            rs = stmt.executeQuery("SELECT description, costprice\n" +
                    "                    FROM Entertainment\n" +
                    "                    WHERE eid IN(SELECT eid\n" +
                    "                                 FROM Party\n" +
                    "                                 WHERE pid =" + inputpid + ")");
            while(rs.next()){
                System.out.println("Entertainment description: " + rs.getString("description"));
                ePrice = rs.getInt("costprice");
            }
            System.out.println("Number of guests:" + nog);
            System.out.println("Price charged: £" + partyPrice);
            int totalCost = vPrice + ePrice + (mPrice*nog);
            System.out.println("Total cost price: £" + totalCost);
            int netProfit = partyPrice - totalCost;
            System.out.println("Net profit: £" + netProfit);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
