import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PartyReport {

    private int pid;

    public PartyReport(int pid){
        this.pid = pid;
    }

    void dropReport(Connection c){
        try{
            String SQLdropReport = "DROP TABLE partyreport";
            Statement stmt = c.createStatement();
            stmt.executeUpdate(SQLdropReport);
            System.out.println("\nPrevious party report deleted");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    void makeReport(Connection c){
        try{
            dropReport(c);
            String SQLmakeReport = "SELECT p.pid, p.name, v.vid INTO PartyReport\n" +
                    "FROM\n" +
                    "    Party p\n" +
                    "    INNER JOIN Venue v\n" +
                    "        ON p.vid = v.vid";
            PreparedStatement stmt = c.prepareStatement(SQLmakeReport);
            stmt.execute();
            System.out.println("Party report made");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
