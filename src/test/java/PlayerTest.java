
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;
    private Game g1;
    private Stock stock;
    private Stock tesla;
    private Stock gme;
    private Stock dis;

    public PlayerTest(){
       player = new Player("mrTestsson",69);
       g1 = new Game();
       tesla = new Stock("Tesla", "TSLA", 50.64, 101.00, Risk.HIGH);
       gme = new Stock("GameStop", "GME", 0.0, 69.69, Risk.HIGH);
       dis = new Stock("Walt Disney", "DIS", 20.0, 25.00, Risk.MID);
    }

    @Test
    void testCreatePlayer(){
        assertEquals("mrTestsson",player.getName());
    }

    @Test
    void testBuyStock(){
        player.buyStock("GME",1,g1.getStockMarket());
        assertTrue(player.getListOfStocks().containsKey(gme));
    }

    @Test
    void testIfBalanceIsValid(){
        player.buyStock("DIS",1,g1.getStockMarket());
        assertEquals(75.00, player.getBalance());
    }


    @Test
    void testIfBalanceIsInsufficient(){
        PlayerTest playerTest = new PlayerTest();
        assertThrows(InsufficientBalanceException.class, () -> {
            player.buyStock("TSLA", 1, g1.getStockMarket());
        });
    }

    @Test
    void testBuySameStock(){
        player.buyStock("DIS",1,g1.getStockMarket());
        player.buyStock("DIS",1,g1.getStockMarket());
        assertTrue(player.getListOfStocks().get(dis) == 2);
    }

    @Test
    void testSellStock(){
        player.buyStock("GME",1,g1.getStockMarket());
        player.sellStock("GME",1);
        assertEquals(0,player.getListOfStocks().size());
    }

    @Test
    void testSellHalfOfValueStock(){
        player.buyStock("DIS",2,g1.getStockMarket());
        player.sellStock("DIS",1);
        assertEquals(1,player.getListOfStocks().size());
    }


    @Test
    void testCalculateWealth() {
        PlayerTest p1 = new PlayerTest();
        player.buyStock("GME", 1, g1.getStockMarket());
        player.calculateWealth();
        assertEquals(100, player.getWealth());
    }
}
