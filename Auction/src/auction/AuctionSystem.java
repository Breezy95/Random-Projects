/**
 * author: Fabrice Benoit
 * ID: 109108791
 */
package auction;

import java.io.Serializable;
import big.data.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabrice Benoit
 */
public class AuctionSystem implements Serializable {

    public static void main(String[] args) {
        System.out.println("Starting...");
        FileInputStream file;
        Scanner input = new Scanner(System.in);
        AuctionTable table;
        System.out.println("Please select a username:");
        String userName = input.nextLine();
        try {
            file = new FileInputStream("auction.obj");
            ObjectInputStream inStream = new ObjectInputStream(file);
            table = (AuctionTable) inStream.readObject();
            System.out.println("Loading file");
            file.close();
        } catch (FileNotFoundException ex) {
            table = new AuctionTable();
            System.out.println("No previous auction table detected.\n"
                    + "Creating new table...");
        } catch (IOException ex) {
            table = new AuctionTable();
            System.out.println("No previous auction table detected.\n"
                    + "Creating new table...");
        } catch (ClassNotFoundException ex) {
            table = new AuctionTable();
            System.out.println("No previous auction table detected.\n"
                    + "Creating new table...");
        }
        String choice;
        boolean finished = false;

        while (!finished) {
            try {
                System.out.println("Please select an option");
                choice = input.nextLine().toUpperCase();
                if (choice.contentEquals("D")) {
                    System.out.println("Enter URL of website");
                    String url = input.nextLine();
                    try {
                        table = AuctionTable.buildFromUrl(url);
                        System.out.println("Loading...");
                        System.out.println("Auction data loaded successfully!");
                    } catch (IllegalArgumentException IAe) {
                        System.out.println("Could not load table from URL");
                    }
                } else if (choice.contentEquals("A")) {
                    System.out.println("Creating new Auction as " + userName);
                    System.out.println("Please enter an Auction ID");
                    String aucID = input.nextLine();
                    System.out.println("Please enter an Auction time (hours)");
                    int time = input.nextInt();
                    System.out.println("Please enter some Item Info:");
                    input.nextLine();
                    String info = input.nextLine();
                    System.out.println("Auction " + aucID + " inserted into table");
                    Auction insert = new Auction(userName, aucID, time, info);
                    try {
                        table.putAuction(aucID, insert);
                    } catch (IllegalArgumentException npE) {
                        System.out.println("Cannot enter an"
                                + " auction with the same ID");
                    }
                } else if (choice.contentEquals("B")) {
                    System.out.println("Please enter an Auction ID: ");
                    String aucID = input.nextLine();
                    try {
                        if (table.getAuction(aucID).getTimeLeft() > 0) {
                            System.out.println("Auction " + aucID + " is open");
                            System.out.println("Current Bid: $"
                                    + table.getAuction(aucID).getCurrentBid());
                            System.out.println("What would you like to bid?:");
                            double bid = input.nextDouble();
                            if (bid > table.getAuction(aucID).getCurrentBid()) {
                                try {
                                    table.getAuction(aucID).newBid(userName, bid);
                                    System.out.println("Bid accepted");
                                } catch (ClosedAuctionException ex) {
                                    System.out.println("Auction is closed");
                                }
                            } else {
                                System.out.println("Bid is lower than highest bidding price");
                                System.out.println("Bid is not accepted");
                            }
                        } else {
                            System.out.println("Auction " + aucID + " is closed");
                            System.out.println("Current Bid: $"
                                    + table.getAuction(aucID).getCurrentBid());
                            System.out.println("You can no longer bid on this item.");
                        }
                    } catch (NullPointerException nEx) {
                        System.out.println("Not a valid auction ID");
                    }
                } else if (choice.contentEquals("I")) {
                    System.out.println("Please enter an Auction ID: ");
                    String auctionID = input.nextLine();
                    try {
                        System.out.println(table.getAuction(auctionID).getInfo());
                    } catch (NullPointerException nE) {
                        System.out.println("Invalid Auction ID");
                    }
                } else if (choice.contentEquals("R")) {
                    System.out.println("Removing expired auctions...");
                    table.removeExpiredAuctions();
                    System.out.println("All expired auctions removed.");
                } else if (choice.contentEquals("T")) {
                    System.out.println("How many hours should pass: ");
                    int timePassed = input.nextInt();
                    table.letTimePass(timePassed);
                    System.out.println("Time passing...\n Auction Times updated.");
                } else if (choice.contentEquals("P")) {
                    table.printTable();
                } else if (choice.contentEquals("Q")) {
                    try {
                        FileOutputStream neoFile = new FileOutputStream("auction.obj");
                        ObjectOutputStream outStream = new ObjectOutputStream(neoFile);
                        outStream.writeObject(table);
                    } catch (FileNotFoundException ex) {
                    } catch (IOException ex) {
                    }
                    finished = true;
                    System.out.println("Writing Auction Table to file...   ");
                    System.out.println("Done!");
                }

            } catch (InputMismatchException IME) {
                System.out.println("Invalid input");
            }
        }

    }
}
