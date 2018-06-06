package Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RotaTest {

    private Rota rota;
    private Transbordo transbordo1;
    private Transbordo transbordo2;
    
    @Before
    public void setUp(){
        transbordo1 = new Transbordo("1");
        transbordo2 = new Transbordo("2");
        rota = new Rota(transbordo1, transbordo2, 10);
    }
    
    @Test
    public void testBasic(){
        assertEquals(rota.getTransbordo1(), transbordo1);
        assertEquals(rota.getTransbordo2(), transbordo2);
        assertEquals(rota.getPeso(), 10);
    }
}
