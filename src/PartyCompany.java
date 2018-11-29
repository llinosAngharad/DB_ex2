import java.awt.*;
import java.sql.*;
import java.util.Scanner;

public class PartyCompany {
    public static void main(String args[]){
        Connect connect = new Connect("lxw404", "6yl8m7fncq");
        Populate p = new Populate();
        Connection c = null;
        Scanner reader = new Scanner(System.in);
        try {
            c = connect.getConnection();
            System.out.println("Hello there! Please select an option:\n" +
                    "   1. Get party report\n" +
                    "   2. Get menu report\n" +
                    "   3. Input new party\n");
            while(true){
                int n = reader.nextInt();

                if(n==1){
                    System.out.println("Enter the id of the party required:\n");
                    while(true){
                        n = reader.nextInt();
                        PartyReport pr = new PartyReport(n);
                        String returnString = pr.makeReport(c);
                        if(returnString.equals("null")){
                            System.out.println("Input error. Please try again:");
                        }
                        else{
                            System.out.println(returnString);
                            break;
                        }
                    }
                    break;
                }
                else if(n==2){
                    System.out.println("Enter the id of the menu required:\n");
                    n = reader.nextInt();
                    MenuReport mr = new MenuReport(n);
                    mr.makeReport(c);
                    break;
                }
                else if(n==3){
                    System.out.println("Input");
                    break;
                }
                else{
                    System.out.println("Input error. Please select one of the following options:\n" +
                                      "   1. Get party report\n" +
                                      "   2. Get menu report\n" +
                                      "   3. Input new party\n");
                }
            }
//            p.populateTables(c);
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                c.close();
                reader.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

}