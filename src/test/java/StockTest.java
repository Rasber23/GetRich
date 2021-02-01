import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    private Player player;
    private Game g1;
    private Stock s1;

    public StockTest() {
        player = new Player("mrTestsson", 69);
        g1 = new Game();
        s1 = new Stock("Tesla", "TSLA", 50.64, 101.00, Risk.HIGH);
    }

    @Test
    void testPrePriceUpdate() {
        s1.upDatePrePrice();
        assertEquals(s1.getCurrPrice(),s1.getPrePrice(),00.1);
    }


}