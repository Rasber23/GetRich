import java.util.List;
import java.util.Objects;

public class Player {

    private String name;
    private int age;
    private double balance;
    private List<Stock> listOfStocks;
    private double wealth;

    public Player(String name, int age) {
        this.name = name;
        this.age = age;
        this.balance = 0;
        this.wealth = 0;
        listOfStocks = new arrayList<Stock>();
    }

    public void sellStock(){

    }

    public void buyStock(){

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
