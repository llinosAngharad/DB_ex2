import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PartyInsertion {

 String insertParty(Connection c, int pid, String pName, int mid, int vid, int eid, int partyPrice, int nog) {
        PreparedStatement stmt = null;
        StringBuilder sb = new StringBuilder();
        try{
            String sqlQuery = "";
            stmt = c.prepareStatement(sqlQuery);

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally{
            try{
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
