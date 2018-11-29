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
            int nog = 0;
            int partyPrice = 0;
            int vPrice = 0;
            int mPrice = 0;
            int ePrice = 0;
            stmt.setInt(1, inputpid);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()){
                sb.append("null");
            }
            else{
                sb.append("\nPARTY REPORT:\n");
                sb.append("Party ID: " + rs.getInt(1) + "\n");
                sb.append("Party name: " + rs.getString(2)+"\n");
                nog = rs.getInt(3);
                partyPrice = rs.getInt(4);
                sb.append("Venue name: " + rs.getString(5)+"\n");
                sb.append("Menu description: " + rs.getString(7)+"\n");
                vPrice = rs.getInt(6);
                mPrice = rs.getInt(8);
                sb.append("Entertainment description: " + rs.getString("description")+"\n");
                ePrice = rs.getInt("costprice");

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
