import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class MenuReport {

    private int inputmid;

    MenuReport(int mid){
        inputmid = mid;
    }

    void makeReport(Connection c){
        Statement stmt = null;
        try{
            stmt = c.createStatement();
            int nog = 0;

            ResultSet rs = stmt.executeQuery("SELECT mid, description, costprice" +
                    "                              FROM Menu" +
                    "                              WHERE mid=" + inputmid);
            System.out.println("\nMENU REPORT:\n");
            while(rs.next()){
                System.out.println("Menu ID: " + rs.getInt("mid"));
                System.out.println("Menu description: " + rs.getString("description"));
                System.out.println("Menu cost per person: £" + rs.getString("costprice"));
            }
            rs = stmt.executeQuery("SELECT COUNT(party)" +
                    "                    FROM party" +
                    "                    WHERE mid=" + inputmid);
            while(rs.next()){
                System.out.println("Total number of parties: " + rs.getInt(1));
            }
            rs = stmt.executeQuery("SELECT SUM(numberofguests)" +
                    "                    FROM party" +
                    "                    WHERE mid=" + inputmid);
            while(rs.next()){
                System.out.println("Total number of guests: " + rs.getInt(1));
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
    }
}
