import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;
    private Game g1;
    private Stock stock;
    public PlayerTest(){
       player = new Player("mrTestsson",69);
       g1 = new Game();
       stock = new Stock("GameStop", "GME", 0.0, 69.69, Risk.HIGH);
    }

    @Test
    void testCreatePlayer(){
        assertEquals("mrTestsson",player.getName());
    }

    @Test
    void testBuyStock(){
        player.buyStock("GME",1,g1.getStockMarket());
        assertTrue(player.getListOfStocks().containsKey(g1.getStockMarket().get(1)));
    }

    @Test
    void testBuySameStock(){
        player.buyStock("GME",1,g1.getStockMarket());
        player.buyStock("GME",1,g1.getStockMarket());
        assertTrue(player.getListOfStocks().get(stock) == 2);
    }

    @Test
    void testSellStock(){
        player.buyStock("TSLA",1,g1.getStockMarket());
        player.sellStock("TSLA",1);
        assertEquals(0,player.getListOfStocks().size());
    }

    @Test
    void testSellHalfOfValueStock(){
        player.buyStock("TSLA",2,g1.getStockMarket());
        player.sellStock("TSLA",1);
        assertEquals(1,player.getListOfStocks().size());
    }
}