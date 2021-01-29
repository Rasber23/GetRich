
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;
    private Game g1;
    public PlayerTest(){
       player = new Player("mrTestsson",69);
       g1 = new Game();

    }

    @Test
    void testCreatePlayer(){
        assertEquals("mrTestsson",player.getName());
    }

    @Test
    void testBuyStock(){
        player.buyStock("TSLA",1,g1.getStockMarket());
        assertEquals("TSLA",player.getListOfStocks().get(0).getTicker());
    }

    @Test
    void testSellStock(){
        player.buyStock("TSLA",1,g1.getStockMarket());
        player.sellStock("TSLA",1);

        assertEquals(0,player.getListOfStocks().size());
    }



}