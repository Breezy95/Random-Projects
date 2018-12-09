/**
 * author: Fabrice Benoit
 * id: 109108791
 */
package auction;

import big.data.DataSource;
import java.io.Serializable;

/**
 *
 * @author Fabrice Benoit Recitation 09 ID: 109108791
 */
public class Auction implements Serializable {

    private String sellerName, bidderName, info, auctionID;
    private int timeLeft;
    private double currentBid;

    /**
     * Invariants String: 
     * sellerName: Represents the person who created the
     * auction 
     * bidderName: represents the name of the highest bidder info
     * represents the info of the item 
     * auctionID: represents an auction specific
     * ID 
     * int: 
     * timeLeft
     * represents the amount of time left in hours
     */

    /**
     * returns am instance of the Auction class with no fields set
     */
    public Auction() {

    }

    /**
     * returns an instance of the auction class with specified variables
     * initialized
     *
     * @param sellerName represents the creator of the auction
     * @param auctionID represents the auction specific key for this auction
     * @param timeLeft represents the amount of time left before auction closes
     * in terms of hours
     * @param info represents the auctioned items description
     */
    public Auction(String sellerName, String auctionID, int timeLeft, String info) {
        this.sellerName = sellerName;
        this.auctionID = auctionID;
        this.timeLeft = timeLeft;
        this.info = info;
        bidderName = "";
        currentBid = 0;
    }

    /**
     *
     * @param sellerName represents the creator of the auction
     * @param auctionID represents the auction specific key for this auction
     * @param timeLeft represents the amount of time left before auction closes
     * @param info represents the auctioned items description
     * @param bidderName represents the name of the highest bidder
     */
    public Auction(String sellerName, String auctionID, int timeLeft, String info, String bidderName) {
        this.sellerName = sellerName;
        this.auctionID = auctionID;
        this.timeLeft = timeLeft;
        this.info = info;
        this.bidderName = bidderName;
        currentBid = 0;
    }

    /**
     *
     * @param sellerName represents the creator of the auction
     * @param auctionID represents the auction specific key for this auction
     * @param timeLeft represents the amount of time left before auction closes
     * @param info represents the auctioned items description
     * @param bidderName represents the name of the highest bidder
     * @param currentBid double value that represents the highest bid on this
     * Auction
     */
    public Auction(String sellerName, String auctionID, int timeLeft, String info, String bidderName, double currentBid) {
        this.sellerName = sellerName;
        this.auctionID = auctionID;
        this.timeLeft = timeLeft;
        this.info = info;
        this.bidderName = bidderName;
        this.currentBid = currentBid;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getBidderName() {
        return bidderName;
    }

    public String getInfo() {
        return info;
    }

    public String getAuctionID() {
        return auctionID;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public double getCurrentBid() {
        return currentBid;
    }

    /**
     * subtracts from the variable timeLeft by the amount specified in the
     * parameter
     *
     * @param time represents the amount of hours the timeLeft variable will
     * lose
     */
    public void decrementTimeRemaining(int time) {
        if (time > timeLeft) {
            timeLeft = 0;
        } else {
            timeLeft -= time;
        }
    }

    /**
     * replaces the highest bid with the bid amount set if the parameter bidamt
     * is higher than currentBid
     *
     * @param bidderName represents the name of the highest bidder
     * @param bidAmt represents the amount of money the bidder is placing
     * @throws ClosedAuctionException thrown if the amount of time in the
     * auction is 0
     *
     */
    public void newBid(String bidderName, double bidAmt) throws ClosedAuctionException {
        if (timeLeft == 0) {
            throw new ClosedAuctionException("Auction is closed");
        }
        if (bidAmt > currentBid) {
            currentBid = bidAmt;
            this.bidderName = bidderName;
            System.out.println("Bid accepted");
        } else {
            System.out.println("Bid not accepted");
        }

    }

    public String toString() {
        return "Auction " + auctionID + ":\n Seller: " + sellerName
                + "\n Buyer: " + bidderName
                + "\n  Time:" + timeLeft + "\n  Info: " + info;
    }

}
