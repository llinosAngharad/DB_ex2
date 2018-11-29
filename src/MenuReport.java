import java.sql.*;

class MenuReport {

    private int inputmid;

    MenuReport(int mid){
        inputmid = mid;
    }

    void makeReport(Connection c){
        PreparedStatement stmt = null;
        try{
            String sqlQuery = "SELECT m.mid, m.description, m.costprice, COUNT(p.pid), coalesce(SUM(p.numberofguests),0)\n" +
                              "FROM Menu m\n" +
                              "LEFT JOIN party p on m.mid = p.mid\n" +
                              "WHERE m.mid=?\n" +
                              "GROUP BY m.mid;";
            stmt = c.prepareStatement(sqlQuery);
            stmt.setInt(1, inputmid);
            ResultSet rs = stmt.executeQuery();
            System.out.println("\nMENU REPORT:\n");
            while(rs.next()){
                System.out.println("Menu ID: " + rs.getInt(1));
                System.out.println("Menu description: " + rs.getString(2));
                System.out.println("Menu cost per person: £" + rs.getString(3));
                System.out.println("Total number of parties: " + rs.getInt(4));
                System.out.println("Total number of guests: " + rs.getInt(5));

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
