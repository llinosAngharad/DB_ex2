import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PartyReport {

    private int inputpid;

    public PartyReport(int pid){
        inputpid = pid;
    }

    String makeReport(Connection c){
        PreparedStatement stmt = null;
        StringBuilder sb = new StringBuilder();
        try{
            String sqlQuery = "SELECT p.pid, p.name, p.numberofguests, p.price, v.name, v.venuecost, m.description, m.costprice, e.description, e.costprice\n" +
                              "FROM\n" +
                                "Party p, Venue v, Menu m, entertainment e\n" +
                                "WHERE p.vid = v.vid and p.mid = m.mid and p.eid = e.eid and p.pid = ?";
            stmt = c.prepareStatement(sqlQuery);
            int nog;
            int partyPrice;
            int vPrice;
            int mPrice;
            int ePrice;
            stmt.setInt(1, inputpid);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                sb.append("\n-------------");
                sb.append("\nPARTY REPORT:");
                sb.append("\n-------------");
                sb.append("\nParty ID: " + rs.getInt(1) + "\n");
                sb.append("Party name: " + rs.getString(2)+"\n");
                nog = rs.getInt(3);
                partyPrice = rs.getInt(4);
                sb.append("Venue name: " + rs.getString(5)+"\n");
                vPrice = rs.getInt(6);
                sb.append("Menu description: " + rs.getString(7)+"\n");
                mPrice = rs.getInt(8);
                sb.append("Entertainment description: " + rs.getString(9)+"\n");
                ePrice = rs.getInt(10);

                sb.append("Number of guests:" + nog + "\n");
                sb.append("Price charged: £" + partyPrice +"\n");
                int totalCost = vPrice + ePrice + (mPrice*nog);
                sb.append("Total cost price: £" + totalCost + "\n");
                int netProfit = partyPrice - totalCost;
                sb.append("Net profit: £" + netProfit + "\n");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
