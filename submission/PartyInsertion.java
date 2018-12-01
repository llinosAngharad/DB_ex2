import java.sql.*;

public class PartyInsertion {

    PreparedStatement stmt = null;

    void insertParty(Connection c, int pid, String pName, int mid, int vid, int eid, int partyPrice, Timestamp timing, int nog) throws SQLException{
        String sqlQuery = "INSERT INTO Party(pid, name, mid, vid, eid, price, timing, numberofguests)\n" +
                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        stmt = c.prepareStatement(sqlQuery);
        stmt.setInt(1, pid);
        stmt.setString(2, pName);
        stmt.setInt(3, mid);
        stmt.setInt(4, vid);
        stmt.setInt(5, eid);
        stmt.setInt(6, partyPrice);
        stmt.setTimestamp(7, timing);
        stmt.setInt(8, nog);
        stmt.executeUpdate();
    }

    void close(){
        try{
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
