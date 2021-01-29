import java.util.Objects;

public class Stock {
    private String name;
    private String ticker;
    private double prePrice;
    private double currPrice;
    private Enum<Risk> risk;


    public Stock(String name, String ticker, double prePrice, double currPrice, Enum<Risk> risk) {
        this.name = name;
        this.ticker = ticker;
        this.prePrice = prePrice;
        this.currPrice = currPrice;
        this.risk = risk;
    }

    public void upDatePrePrice(){
        this.setPrePrice(this.currPrice);
    }

    public void setPrePrice(double prePrice) {
        this.prePrice = prePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getPrePrice() {
        return prePrice;
    }


    public double getCurrPrice() {
        return currPrice;
    }

    public void setCurrPrice(double currPrice) {
        this.currPrice = currPrice;
    }

    public Enum<Risk> getRisk() {
        return risk;
    }

    public void setRisk(Enum<Risk> risk) {
        this.risk = risk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(name, stock.name) && Objects.equals(ticker, stock.ticker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ticker);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "name='" + name + '\'' +
                ", ticker='" + ticker + '\'' +
                ", prePrice=" + prePrice +
                ", currPrice=" + currPrice +
                ", risk=" + risk +
                '}';
    }

}
