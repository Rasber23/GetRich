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

    /* Tog bort pga came to my senses....... lol /h */
    /*public Player createNewUser(String name, int age) {
        Player player = new Player(name, age);
        return player;
    }*/

    public void whatDoYouWant(int userinput, Player player) {
        if (userinput == 1) {
            System.out.println("What do you want to buy?");
            System.out.println(getStockMarket());
        } else if (userinput == 2) {
            System.out.println("What do you want to sell?");
            System.out.println("These stocks are yours");
        } else if (userinput == 3) {
            addRound();
        } else if (userinput == 4) {
            System.out.println("Your current balance is: " + player.getBalance() + " $.");
        } else {
            if (userinput == 9) {
                System.exit(-1);
            }
            System.out.println("Choose a valid number 1-4");
        }
    }


    public List<Stock> getStockMarket() {
        return stockMarket;
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
