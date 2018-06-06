package Model;

import java.util.Iterator;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TransbordoTest {

    private Transbordo transbordo1;
    private Transbordo transbordo2;
    private Rota rota;
    private Rota rota2;

    @Before
    public void setUp() {
        transbordo1 = new Transbordo("1");
        transbordo2 = new Transbordo("2");
        rota = new Rota(transbordo1, transbordo2);
        rota2 = new Rota(transbordo1, transbordo2);
    }

    @Test
    public void testBasic() {
        assertNotNull(transbordo1);
 
        assertEquals(transbordo1.getNome(), "1");
        assertEquals(transbordo2.getNome(), "2");

        transbordo1.setVisitado(true);
        transbordo2.setVisitado(true);
        assertTrue(transbordo1.getVisitado());
        assertTrue(transbordo2.getVisitado());
    }

    @Test
    public void testAdicionarRota() {
        Iterator it = transbordo1.getRotas().listIterator();
        assertFalse(it.hasNext());

        transbordo1.adicionarRota(rota);
        transbordo1.adicionarRota(rota2);
        it = transbordo1.getRotas().iterator();
        assertTrue(it.hasNext());
        assertSame(it.next(), rota);
        assertTrue(it.hasNext());
        assertSame(it.next(), rota2);
        assertFalse(it.hasNext());
    }

    @Test
    public void testRemoverRota() {
        Iterator it = transbordo1.getRotas().listIterator();
        assertFalse(it.hasNext());

        transbordo1.adicionarRota(rota);
        transbordo1.adicionarRota(rota2);

        transbordo1.removerRota(rota);
        transbordo1.removerRota(rota2);

        assertFalse(it.hasNext());
    }
}
