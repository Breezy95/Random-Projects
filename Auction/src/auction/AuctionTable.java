/*
 * author: Fabrice Benoit
 * id: 109108791
 * 
 */
package auction;

import java.io.Serializable;
import big.data.DataSource;
import java.util.Hashtable;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuctionTable implements Serializable {

    Hashtable<String, Auction> auctions = new Hashtable<String, Auction>();

    /**
     * Invariant 
     * auction : Hashtable object that represents the auction table
     * consists of a collection of Auction objects that have a key which is the
     * auction ID
     */

    /**
     * returns an auction table from a specified URL
     *
     * @param URL String variable that represents the URL that holds the auction
     * table
     * @return a variable of the type Auction table that represents an auction
     * table from the URL
     * @throws IllegalArgumentException thrown if the url variable does not
     * contain variable does not reference a valid link
     */
    public static AuctionTable buildFromUrl(String URL) throws IllegalArgumentException {
        if (DataSource.connect(URL).load().size() == 0) {
            throw new IllegalArgumentException("invalid URL");
        }
        DataSource ds;
        ds = DataSource.connect(URL);
        ds.load();
        ArrayList<String[]> nullFixer = new ArrayList<>();
        String[] sellerNames = ds.fetchStringArray("listing/seller_info/seller_name");
        String[] currentBid = ds.fetchStringArray("listing/auction_info/current_bid");
        String[] timeLeft = ds.fetchStringArray("listing/auction_info/time_left");
        String[] idNum = ds.fetchStringArray("listing/auction_info/id_num");
        String[] bidderName = ds.fetchStringArray("listing/auction_info/high_bidder/bidder_name");
        // the following should be combined to get the information of the item
        String[] memory = ds.fetchStringArray("listing/item_info/memory");
        String[] hardDrive = ds.fetchStringArray("listing/item_info/hard_drive");
        String[] cpu = ds.fetchStringArray("listing/item_info/cpu");

        AuctionTable neoTable = new AuctionTable();
        int i = 0;
        while (i < currentBid.length) {
//String sellerName,String auctionID, int timeLeft, String info
            String info = memory[i] + " " + hardDrive[i] + " " + cpu[i];
            Auction neoAuction = new Auction(sellerNames[i], idNum[i], daysToInt(timeLeft[i]), info, bidderName[i], moneyToDouble(currentBid[i]));
            neoTable.putAuction(idNum[i], neoAuction);
            i++;
        }
        return neoTable;
    }

    /**
     * reads a String that represents the time remaining of an auction and
     * converts it into an int variable in terms of hours
     *
     * @param time String variable that represents the time remaining before the
     * auction closes
     * @return returns an int variable that represents the total amount of time
     * in terms of hours
     */
    public static int daysToInt(String time) {
        String[] newTime = time.split(" ");
        //System.out.println("Begin \n");
        String days = "";
        String hours = "";
        for (int i = 0; i < newTime.length; i++) {
            if (newTime[i].toLowerCase().contains("day")) {
                days = newTime[i - 1];
            }
            if (newTime[i].toLowerCase().contains("hr")
                    || newTime[i].toLowerCase().contains("hour")) {
                hours = newTime[i - 1];
            }
        }
        int parsedHour = 0, parsedDay = 0;
        if (!days.isEmpty()) {
            parsedDay = Integer.parseInt(days);
        }
        if (!hours.isEmpty()) {
            parsedHour = Integer.parseInt(hours);
        }

        return parsedHour + (parsedDay * 24);
    }

    /**
     * converts a string of money and converts it into a double value that
     * represents money
     *
     * @param money String variable that represents the amount of money in a bid
     * @return returns a double value that represents the amount of money in the
     * bid
     */
    public static double moneyToDouble(String money) {
        //System.out.println(money);
        StringBuffer fixedMoney = new StringBuffer(money);
        fixedMoney.replace(0, 1, "");
        if (fixedMoney.toString().contains(",")) {
            fixedMoney.deleteCharAt(fixedMoney.indexOf(","));
        }
        //System.out.println(fixedMoney);
        return Double.parseDouble(fixedMoney.toString());
    }

    /**
     * places a new Auction into the auctions hashtable using the auction ID as
     * a key
     *
     * @param auctionId String variable that is the auction ID
     * @param auction Auction variable that will be placed into the auction
     * hashtable
     * @throws IllegalArgumentException thrown if the auctions hashtable already
     * contains an auction with this key
     */
    public void putAuction(String auctionId, Auction auction) throws IllegalArgumentException {
        if (auctions.containsKey(auctionId)) {
            throw new IllegalArgumentException();
        }
        auctions.put(auctionId, auction);
    }

    public Auction getAuction(String ID) {
        return auctions.get(ID);
    }

    /**
     * allows time to pass for all auctions in the hashtable
     *
     * @param numHours int variable that represents the number of hours that are
     * supposed to pass
     * @throws IllegalArgumentException thrown if the numHours variable is less
     * than 0
     */
    public void letTimePass(int numHours) throws IllegalArgumentException {
        if (numHours < 0) {
            throw new IllegalArgumentException();
        }
        auctions.forEach((k, v) -> v.decrementTimeRemaining(numHours));
    }

    /**
     * searches auctions hashtable for any auctions with time left as 0 and
     * removes them
     */
    public void removeExpiredAuctions() {
        Iterator<String> iter = auctions.keySet().iterator();
        while (iter.hasNext()) {
            String i = iter.next();
            if (auctions.get(i).getTimeLeft() == 0) {
                iter.remove();
            }

        }
    }

    /**
     * returns a formatted table that displays the information of the auctions
     */
    public void printTable() {
        System.out.printf("%-23s%-15s%-20s%-30s%-15s%-40s", "Auction ID", "Bid", "Seller", "Buyer", "Time", "Item Info\n");
        System.out.print("\n");
        String info = "";
        for (int i = 0; i < (23 + 15 + 20 + 30 + 15 + 40); i++) {
            info += "=";
        }
        System.out.println(info);
        auctions.forEach((k, v) -> {
            System.out.printf("%-23s%-15s%-20s%-30s%-15s%-40s", v.getAuctionID() + " ", blankBid(v.getCurrentBid()),
                     v.getSellerName(), v.getBidderName(), v.getTimeLeft() + " hours ",
                    (v.getInfo().length() > 30 ? v.getInfo().substring(0, v.getInfo().length() / 3) : v.getInfo()));
            System.out.println("\n");
        });
    }

    /**
     * returns a String value that represents the value of money with the
     * appropriate commas and decimal places
     *
     * @param mon double value that represents an amount of money
     * @return A string value that is mon but with appropriate commas and
     * decimal points
     */
    public String blankBid(double mon) {
        if (mon == 0) {
            return "no bids";
        } else {
            return "$" + String.format("%.2f", mon);
        }
    }

    public static void main(String[] args) {
        AuctionTable tab = AuctionTable.buildFromUrl("http://tinyurl.com/nbf5g2h");
        //String sellerName,String auctionID, int timeLeft, String info
        Auction add = new Auction("seller@name", "12343543", 0, "issa knife");
        try {
            tab.putAuction("12343543", add);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        System.out.println(tab.getAuction("12343543").toString());
        try {
            tab.getAuction("12343543").newBid("poop", 22122122);
        } catch (ClosedAuctionException ex) {
        }

        //tab.printTable();
    }

}
