import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Player player;
    private Game g1;

    public GameTest() {
        player = new Player("mrTestsson", 69);
        g1 = new Game();

    }

    @Test
    void testChangeValueOfStocks() {
        g1.changeStocks();
        Stock TSLA = g1.getStockMarket().get(0);
        Stock GME = g1.getStockMarket().get(1);
        Stock DIS = g1.getStockMarket().get(2);
        Stock JPM = g1.getStockMarket().get(3);
        Stock WMT = g1.getStockMarket().get(4);
        assertAll("Correctly changed value",
                () -> assertNotEquals(101.00, TSLA.getCurrPrice()),
                () -> assertNotEquals(69.69, GME.getCurrPrice()),
                () -> assertNotEquals(25.00, DIS.getCurrPrice()),
                () -> assertNotEquals(45.00, JPM.getCurrPrice()),
                () -> assertNotEquals(4.20, WMT.getCurrPrice()));
    }

    @Test
    void testRandom(){
        double randomNr=g1.random(Risk.LOW);
        assertTrue(randomNr>-5 && randomNr<10);
    }

    @RepeatedTest(100)
    void changeStocksNeverZERO(){
        g1.changeStocks();
        Stock TSLA = g1.getStockMarket().get(0);
        assertNotEquals(0,TSLA.getCurrPrice(),0.001);
    }

    @Test
    void whatDoYouWant() {

    }

    @Test
    void addRound() {
    }
    
}