import java.sql.*;
public class PartyCompany {
    public static void main(String args[]){
        Connect connect = new Connect("lxw404", "6yl8m7fncq");
        Populate p = new Populate();
        try {
            Connection c = connect.getConnection();
            p.dropTables(c);
            p.createTables(c);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}