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
    void testRandom() {
        double randomNr = g1.random(Risk.LOW);
        assertTrue(randomNr > -5 && randomNr < 5);
        System.out.println(randomNr);
    }

    @RepeatedTest(100)
    void testrandom100() {
        double randomNr = g1.random(Risk.HIGH);
        assertTrue(randomNr > -9 && randomNr < 9);
        System.out.println(randomNr);
    }

    @RepeatedTest(30)
    void changeStocksNeverZERO() {
        Stock TSLA = g1.getStockMarket().get(0);
        g1.changeStocks();
        assertNotEquals(0, TSLA.getCurrPrice(), 0.001);
        System.out.println("Andra:" + TSLA.getCurrPrice());
    }

    @Test
    void testAddRound() {
        g1.addRound();
        assertEquals(2, g1.getRound());
    }

    @Test
    void testCheckEndGameFail() {
        player.setBalance(117.00);
        g1.endGame(player);
        assertFalse(g1.endGame(player));
    }

    @Test
    void testCheckEndGameWin() {
        player.setBalance(118.00);
        g1.endGame(player);
        assertTrue(g1.endGame(player));
    }

    @Test
    void testCheckUserTicker() {
        assertThrows(InvalidTickerInputException.class, () -> {
            g1.checkUserTicker("kanin");
        });
    }

    @Test
    void testCheckUserTickerNULL()  {
        assertThrows(NullPointerException.class, () -> {
            g1.checkUserTicker(null);
        });
    }

    @Test
    void testCheckUserAmount() {
        assertThrows(InvalidAmountException.class, () -> {
            g1.checkUserAmount(-1);
        });
    }


}