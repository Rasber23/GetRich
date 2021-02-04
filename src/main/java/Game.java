import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents the game GetRichOrDieTrying
 */
public class Game {
    public final static double TAX = 0.15;
    private final List<Stock> stockMarket;
    Scanner scan = new Scanner(System.in);
    String userInputTicker;
    int userInputAmount;
    private int round;

    public Game() {
        this.round = 1;
        this.stockMarket = new ArrayList<Stock>();
        stockMarket.add(new Stock("Tesla", "TSLA", 50.64, 101.00, Risk.HIGH));
        stockMarket.add(new Stock("GameStop", "GME", 34.0, 69.69, Risk.HIGH));
        stockMarket.add(new Stock("Walt Disney", "DIS", 20.0, 25.00, Risk.MID));
        stockMarket.add(new Stock("JP Morgan", "JPM", 40.0, 45.00, Risk.MID));
        stockMarket.add(new Stock("Walmart Inc.", "WMT", 4.19, 4.20, Risk.LOW));
    }

    /**
     * Ask the user what stock they want to buy, and how many. Puts the user input into method buyStock().
     *
     * @param player  the user playing the game
     */
    public void userActionBuyStock(Player player) {
        System.out.println(displayStockMarket());
        System.out.println("What do you want to buy?");
        System.out.println("Type ticker");
        userInputTicker = scan.next();
        System.out.println("How many?");
        userInputAmount = scan.nextInt();
        //if sats med checker
        player.buyStock(userInputTicker, userInputAmount, stockMarket);

    }

    /**
     * Ask the user what stock they want to sell, and how many. Puts the user input into method sellStock().
     *
     * @param player  the user playing the game
     */
    public void userActionSellStock(Player player) {
        System.out.println(player.displayListOfStocks());
        System.out.println("What do you want to sell?");
        System.out.println("Type ticker");

        userInputTicker = scan.next();
        System.out.println("How many?");
        userInputAmount = scan.nextInt();
        //checker endast siffror.
        player.sellStock(userInputTicker, userInputAmount);
    }

    /**
     * Lets the user choose from multiple outcomes, 1-4 to keep playing the game and 9 to end game
     *
     * @param userinput  a number-input from user
     * @param player  the user playing the game
     */
    public void whatDoYouWant(int userinput, Player player) {
        if (userinput == 1) {
            try {
                this.userActionBuyStock(player);
            } catch (InsufficientBalanceException ex){
                System.out.println(ex.getMessage());
            }
        } else if (userinput == 2) {
            this.userActionSellStock(player);
        } else if (userinput == 3) {
            this.newRound(player);
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
    /**
     * Generate a random number based on the risk Enum of the stock.
     *
     * @param risk Enum risk provided by the stock.
     *
     *
     **/
    public double random(Enum<Risk> risk) {
        if (risk == Risk.HIGH) {
            return ThreadLocalRandom.current().nextDouble(-5, 5 + 1) * 1.5;
        } else if (risk == Risk.MID) {
            return ThreadLocalRandom.current().nextDouble(-5, 5 + 1) * 1.2;
        }
        return ThreadLocalRandom.current().nextDouble(-5, 5 + 1);
    }
    /**
     * calculate the new prise of all the stocks in stockMarket list and set them to the correct
     * prise based on the value provided by random.
     *
     **/
    public void changeStocks() {
        double stockRisk;
        for (Stock stock : stockMarket) {
            stockRisk = random(stock.getRisk());
            if (stockRisk < 0) {
                stock.setCurrPrice(stock.getCurrPrice() * (1-Math.abs(stockRisk) /10));

            } else {
                stock.setCurrPrice(stock.getCurrPrice() * ((stockRisk / 10) + 1));

            }

        }
    }

    /** newRound
     * When a player descides to sleep, a new round (day) will unfold.
     * It will add a new round, then set previous prices on all stocks,
     * then change all stocks prices and finally calculate players new wealth.
     *
     * @param player get player from calculating and setting a new wealth.
     */

    public void newRound(Player player) {
        this.addRound();
        for (Stock stock : stockMarket) {
            stock.upDatePrePrice();
        }
        this.changeStocks();
        player.calculateWealth();
    }

    public void addRound() {
        round++;
    }

    /** endGame
     * When the game is finished game will determent if the player wins or looses,
     * by comparing @players wealth minus tax with starting value.
     *
     * @param player get the players total wealth for comparing.
     * @return true if Player beats the game, false if Player lost the game.
     */

    public boolean endGame(Player player) {
        player.calculateWealth();
        double wealthAfterTax = player.getWealth() * (1 - TAX);
        if( wealthAfterTax < Player.INITIAL_MONEY)  {
            return false;
        }
        return true;
    }

    /**
     * Displays the stocks in stockmarket
     *
     * @return  a String containing the stocks available in the stockmarket
     * */
    public String displayStockMarket() {
        String displaySM = Arrays.toString(stockMarket.toArray()).replace(", N", "N").replace(", N", "N");
        return displaySM.substring(1,displaySM.length()-1);
    }

    @Override
    public String toString() {
        return "Game{" +
                "round=" + round +
                ", stockMarket=" + stockMarket +
                '}';
    }
}
