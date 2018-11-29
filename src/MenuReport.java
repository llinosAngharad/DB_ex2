import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MenuReport {

    private int inputmid;

    public MenuReport(int mid){
        inputmid = mid;
    }

    void makeReport(Connection c){
        try{
            Statement stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT mid, description, costprice" +
                    "                              FROM Menu" +
                    "                              WHERE mid=" + inputmid);
            System.out.println("\nPARTY REPORT:\n");
            while(rs.next()){
                System.out.println("Menu ID: " + rs.getInt("mid"));
                System.out.println("Menu description: " + rs.getString("description"));
                System.out.println("Menu cost per person: £" + rs.getString("costprice"));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
