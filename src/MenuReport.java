import java.sql.*;

class MenuReport {

    private int inputmid;

    MenuReport(int mid){
        inputmid = mid;
    }

    String makeReport(Connection c){
        PreparedStatement stmt = null;
        StringBuilder sb = new StringBuilder();
        try{
            String sqlQuery = "SELECT m.mid, m.description, m.costprice, COUNT(p.pid), coalesce(SUM(p.numberofguests),0)\n" +
                              "FROM Menu m\n" +
                              "LEFT JOIN party p on m.mid = p.mid\n" +
                              "WHERE m.mid=?\n" +
                              "GROUP BY m.mid;";
            stmt = c.prepareStatement(sqlQuery);
            stmt.setInt(1, inputmid);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                sb.append("\n------------");
                sb.append("\nMENU REPORT:");
                sb.append("\n------------");
                sb.append("\nMenu ID: " + rs.getInt(1) + "\n");
                sb.append("Menu description: " + rs.getString(2) + "\n");
                sb.append("Menu cost per person: £" + rs.getString(3) + "\n");
                sb.append("Total number of parties: " + rs.getInt(4) + "\n");
                sb.append("Total number of guests: " + rs.getInt(5) + "\n");
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
