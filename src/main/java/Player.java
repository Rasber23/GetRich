import java.util.*;

/**
 * Represents a player in the game GetRichOrDieTrying
 */
public class Player {
    public final static double INITIAL_MONEY = 100;

    private String name;
    private int age;
    private double balance;
    private HashMap<Stock, Integer> listOfStocks;
    private double wealth;

    public Player(String name, int age) {
        this.name = name;
        this.age = age;
        this.balance = INITIAL_MONEY;
        this.wealth = 0;
        this.listOfStocks = new HashMap<Stock, Integer>();
    }

    /**
     * Lets you buy stock from the Stockmarket
     *
     * @param ticker      the stock ticker
     * @param amount      the amount of stocks the user wants to buy
     * @param stockMarket list with available stocks to buy
     * @throws InsufficientBalanceException if balance is too low for purchase
     **/
    public void buyStock(String ticker, int amount, List<Stock> stockMarket) {
        for (Stock stock : stockMarket) {
            if (stock.getTicker().toLowerCase().trim().equals(ticker.toLowerCase().trim())) {

                if (stock.getCurrPrice() * amount > getBalance()) {
                    throw new InsufficientBalanceException("Balance is too low");
                }

                if (listOfStocks.containsKey(stock)) {
                    listOfStocks.compute(stock, (k, v) -> v + amount);
                } else {
                    listOfStocks.put(stock, amount);
                }
                this.balance = balance - (stock.getCurrPrice() * amount);
            }
        }
    }

    /**
     * Lets you sell stock you bought previously
     *
     * @param ticker the stock ticker
     * @param amount the amount of stocks the user wants to sell
     **/
    public void sellStock(String ticker, int amount) {
        for (Stock stock : listOfStocks.keySet()) {
            if (stock.getTicker().toLowerCase().trim().equals(ticker.toLowerCase().trim())) {

                /* Om amount är större eller lika med än value ta bort och sätt amount till value*/
                if (amount >= listOfStocks.get(stock)) {
                    amount = listOfStocks.get(stock);
                    listOfStocks.remove(stock);
                } else {
                    int finalAmount = amount;
                    listOfStocks.compute(stock, (k, v) -> v - finalAmount);
                }
                this.balance = balance + (stock.getCurrPrice() * amount);
                break;
            }
        }
    }

    /**
     * Calculates what the users stocks are worth plus users existing balance
     */
    public void calculateWealth() {
        wealth = 0;

        for (Stock stock : listOfStocks.keySet()) {
            wealth = listOfStocks.get(stock) * stock.getCurrPrice();
        }

        wealth += balance;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public HashMap<Stock, Integer> getListOfStocks() {
        return listOfStocks;
    }

    public double getWealth() {
        return wealth;
    }

    public void setWealth(double wealth) {
        this.wealth = wealth;
    }

    /**
     * Displays the list of Stocks in users inventory and the amount of each one
     *
     * @return a String containing the stocks in the users inventory, and the amount of each one
     */
    public String displayListOfStocks() {
        StringBuilder display = new StringBuilder();
        for (Stock stock : listOfStocks.keySet()) {
            display.append(stock + "Amount: " + listOfStocks.get(stock) + ". \n\n");
        }
        display.delete(display.length() - 2, display.length()).append("\n");
        return display.toString();
    }


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", balance=" + balance +
                ", wealth=" + wealth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return age == player.age && name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
