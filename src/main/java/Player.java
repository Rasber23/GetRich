import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
        for (Stock stock : stockMarket) {
            if (stock.getTicker().equals(ticker)) {
                listOfStocks.putIfAbsent(stock, 1);
                this.balance = balance - (stock.getCurrPrice() * amount);
            }
        }
    }

    public void sellStock(String ticker, int amount) {
        for (Stock stock : listOfStocks.keySet()) {
            if (stock.getTicker().equals(ticker)) {
                listOfStocks.remove(stock);
                this.balance = balance + (stock.getCurrPrice() * amount);
                break;
            }
        }
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
