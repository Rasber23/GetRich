import java.util.ArrayList;
import java.util.List;

public class Game {
    private int round;
    private List<Stock> stockMarket;

    public Game() {
        this.round = 1;
        this.stockMarket = new ArrayList<Stock>();
        stockMarket.add(new Stock("Tesla", "TSLA", 0.0, 101.00, Risk.HIGH));
        stockMarket.add(new Stock("GameStop", "GME", 0.0, 69.69, Risk.HIGH));
        stockMarket.add(new Stock("Walt Disney", "DIS", 0.0, 25.00, Risk.MID));
        stockMarket.add(new Stock("JP Morgan", "JPM", 0.0, 45.00, Risk.MID));
        stockMarket.add(new Stock("Walmart Inc.", "WMT", 0.0, 4.20, Risk.LOW));
    }


    public int getRound() {
        return round;
    }

    public void addRound() {
        round++;
    }

    @Override
    public String toString() {
        return "Game{" +
                "round=" + round +
                ", stockMarket=" + stockMarket +
                '}';
    }
}
