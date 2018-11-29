import java.awt.*;
import java.sql.*;
public class PartyCompany {
    public static void main(String args[]){
        Connect connect = new Connect("lxw404", "6yl8m7fncq");
        Populate p = new Populate();
        Connection c = null;
        try {
            c = connect.getConnection();
//            p.populateTables(c);

//            PartyReport pr = new PartyReport(5);
//            pr.makeReport(c);

            MenuReport mr = new MenuReport(1);
            mr.makeReport(c);


        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                c.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

}