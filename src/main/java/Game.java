import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    private int round;
    private final List<Stock> stockMarket;

    public Game() {
        this.round = 1;
        this.stockMarket = new ArrayList<Stock>();
        stockMarket.add(new Stock("Tesla", "TSLA", 50.64, 101.00, Risk.HIGH));
        stockMarket.add(new Stock("GameStop", "GME", 34.0, 69.69, Risk.HIGH));
        stockMarket.add(new Stock("Walt Disney", "DIS", 20.0, 25.00, Risk.MID));
        stockMarket.add(new Stock("JP Morgan", "JPM", 40.0, 45.00, Risk.MID));
        stockMarket.add(new Stock("Walmart Inc.", "WMT", 4.19, 4.20, Risk.LOW));
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
            this.newRound();
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

    public int random(Enum<Risk> risk) {
        if (risk == Risk.HIGH) {
            return ThreadLocalRandom.current().nextInt(-5, 5+1) * 5;
        } else if (risk == Risk.MID) {
            return ThreadLocalRandom.current().nextInt(-5, 5+1) * 2;
        }
        return ThreadLocalRandom.current().nextInt(-5, 5+1);
    }

    public void changeStocks() {
        for (Stock stock : stockMarket) {
            stock.setCurrPrice(stock.getCurrPrice() * random(stock.getRisk()));
        }
    }

    public void newRound() {
        this.addRound();
        for (Stock stock:stockMarket) {
            stock.upDatePrePrice();
        }
        this.changeStocks();

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
