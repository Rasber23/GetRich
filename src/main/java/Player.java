import java.util.*;

public class Player {

    private String name;
    private int age;
    private double balance;
    private HashMap<Stock, Integer> listOfStocks;
    private double wealth;

    public Player(String name, int age) {
        this.name = name;
        this.age = age;
        this.balance = 100;
        this.wealth = 0;
        this.listOfStocks = new HashMap<Stock, Integer>();
    }

    public void buyStock(String ticker, int amount, List<Stock> stockMarket) {
        try {
        for (Stock stock : stockMarket) {
            if (stock.getTicker().equals(ticker)) {

                if (stock.getCurrPrice() * amount > getBalance()) {
                    throw new InsufficientBalanceException("Balance too low");
                }

                if (listOfStocks.containsKey(stock)) {
                    listOfStocks.compute(stock, (k, v) -> v + amount);
                } else {
                    listOfStocks.put(stock, amount);
                }
                this.balance = balance - (stock.getCurrPrice() * amount);
            }
        }
    } catch (InsufficientBalanceException ex) {
            System.out.println("Your balance is too low for this purchase, please try something else.\n");
        }
    }


    public void sellStock(String ticker, int amount) {
        for (Stock stock : listOfStocks.keySet()) {
            if (stock.getTicker().equals(ticker)) {

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

    /* Behövs ett test här, hur? */
    public String displayListOfStocks() {
        StringBuilder display = new StringBuilder();
        for (Stock stock : listOfStocks.keySet()) {
            display.append(stock + "Amount: " + listOfStocks.get(stock) + ". \n\n");
        }
        display.delete(display.length()-2, display.length()).append("\n");
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
