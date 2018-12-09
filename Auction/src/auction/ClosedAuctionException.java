package auction;

/**
 *
 * @author Fabrice Benoit 
 * id: 109108791
 */
class ClosedAuctionException extends Exception {

    public ClosedAuctionException(String mess) {
        super(mess);
    }

    public ClosedAuctionException() {
        super("Auction is closed");
    }
}
