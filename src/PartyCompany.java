import com.sun.org.apache.xpath.internal.operations.Bool;

import java.awt.*;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PartyCompany {
    public static void main(String args[]){
        Connect connect = new Connect("lxw404", "6yl8m7fncq");
        Populate p = new Populate();
        Connection c = null;
        Scanner reader = new Scanner(System.in);

        try {
            c = connect.getConnection();
            p.populateTables(c);
            System.out.println("\nHello there! Please select an option:\n" +
                    "   1. Get party report\n" +
                    "   2. Get menu report\n" +
                    "   3. Insert a new party\n");
            while(true){
                int n = reader.nextInt();

                if(n==1){
                    System.out.println("Enter the id of the party required:");
                    while(true){
                        n = reader.nextInt();
                        PartyReport pr = new PartyReport(n);
                        String returnString = pr.makeReport(c);
                        if(returnString.equals("")){
                            System.out.println("Input error. Please try again:\n");
                        }
                        else{
                            System.out.println(returnString);
                            break;
                        }
                    }
                    break;
                }

                else if(n==2){
                    System.out.println("Enter the id of the menu required:");
                    while(true){
                        n = reader.nextInt();
                        MenuReport mr = new MenuReport(n);
                        String returnString = mr.makeReport(c);
                        if(returnString.equals("")){
                            System.out.println("Input error. Please try again:\n");
                        }
                        else{
                            System.out.println(returnString);
                            break;
                        }
                    }
                    break;
                }

                else if(n==3){
                    PartyInsertion pi = null;
                    Boolean again = true;
                    try{
                        System.out.println("Please input the following:\n");
                        System.out.println("Party ID: ");
                        int pid = reader.nextInt();
                        reader.nextLine();
                        System.out.println("\nParty's name: ");
                        String pName = reader.nextLine();
                        System.out.println("\nMenu ID: ");
                        int mid = reader.nextInt();
                        System.out.println("\nVenue ID: ");
                        int vid = reader.nextInt();
                        System.out.println("\nEntertainment ID: ");
                        int eid = reader.nextInt();
                        System.out.println("\nQuoted price (£):");
                        int partyPrice = reader.nextInt();
                        reader.nextLine();
                        System.out.println("\nTiming (yyyy-mm-dd hh:mm):");
                        String stringTime = reader.nextLine();
                        stringTime += ":00.000000000";
                        Timestamp timing = Timestamp.valueOf(stringTime);
                        System.out.println("\nNumber of guests");
                        int nog = reader.nextInt();

                        pi = new PartyInsertion();
                        pi.insertParty(c, pid, pName, mid, vid, eid, partyPrice, timing, nog);
                        again = false;
                    }catch(SQLException e){
                        System.out.println("\nOops! There was an error with the input.");
                        System.err.println(e.getLocalizedMessage());
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("\nOops! There was an error with the input.");
                        System.err.println(e.getLocalizedMessage());
                    }catch(InputMismatchException e){
                        System.out.println("\nOops! There was an error with the input.");
                        e.printStackTrace();
                    }finally{
                        if(pi!=null){
                            pi.close();
                        }
                    }
                    break;
                }

                else{
                    System.out.println("Input error. Please select one of the following options:\n" +
                                      "   1. Get party report\n" +
                                      "   2. Get menu report\n" +
                                      "   3. Input new party\n");
                }
            }
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