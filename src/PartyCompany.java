import java.awt.*;
import java.sql.*;
public class PartyCompany {
    public static void main(String args[]){
        Connect connect = new Connect("lxw404", "6yl8m7fncq");
        Populate p = new Populate();
        try {
            Connection c = connect.getConnection();
//            p.populateTables(c);

            PartyReport pr = new PartyReport(5);
            pr.makeReport(c);

            Menu


        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}