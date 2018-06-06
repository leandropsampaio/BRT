package Util;

import Exception.RotaJaExisteException;
import Exception.RotaNaoExisteException;
import Exception.SemTransbordosException;
import Exception.TransbordoJaExisteException;
import Exception.TransbordoNaoExisteException;
import Model.Rota;
import Model.Transbordo;
import java.util.Iterator;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Leandro Pereira Sampaio
 */
public class GrafoTest {

    private Rota r1, r2;
    private Transbordo v1, v2, v3;
    private String s1, s2, s3;
    private Grafo grafo;

    @Before
    public void setUp() throws Exception {
        s1 = "1"; s2 = "2"; s3 = "3";
        v1 = new Transbordo(s1);
        v2 = new Transbordo(s2);
        v3 = new Transbordo(s3);
        r1 = new Rota(v1, v2, 3);
        r2 = new Rota(v3, v1, 7);
        grafo = new Grafo();
    }

    @Test
    public void testMenorCaminho() throws TransbordoJaExisteException, RotaJaExisteException, SemTransbordosException {
        v1 = grafo.inserirTransbordo(s1);
        v2 = grafo.inserirTransbordo(s2);
        v3 = grafo.inserirTransbordo(s3);
        grafo.inserirRota(v1, v2, 3);
        grafo.inserirRota(v1, v3, 7);
        grafo.inserirRota(v2, v1, 3);
        grafo.inserirRota(v2, v3, 2);
        grafo.inserirRota(v3, v1, 7);
        grafo.inserirRota(v3, v2, 2);

        Iterator it = grafo.encontrarMenorCaminho(v1, v3);
        assertTrue(it.hasNext());
        assertSame(v1, it.next());
        assertTrue(it.hasNext());
        assertSame(v2, it.next());
        assertTrue(it.hasNext());
        assertSame(v3, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void testAdicionarRemoverRotas() throws RotaJaExisteException, TransbordoJaExisteException, RotaNaoExisteException {
        Iterator it = grafo.getRotas();
        assertFalse(it.hasNext());
        
        grafo.inserirRota(v1, v2, 3);
        grafo.inserirRota(v3, v1, 7);

        it = grafo.getRotas();
        assertTrue(it.hasNext());
        assertEquals(r1, it.next());
        assertTrue(it.hasNext());
        assertEquals(r2, it.next());
        assertFalse(it.hasNext());
        
        grafo.removerRota(v1, v2);
        grafo.removerRota(v3, v1);
        
        it = grafo.getRotas();
        assertFalse(it.hasNext());
    }
    
    @Test
    public void testAdicionarRemoverTransbordos() throws TransbordoJaExisteException {
        List lista = (List) grafo.getTransbordos();
        Iterator it = lista.iterator();
        assertFalse(it.hasNext());
        
        v1 = grafo.inserirTransbordo(s1);
        v2 = grafo.inserirTransbordo(s2);
        v3 = grafo.inserirTransbordo(s3);
        
        lista = grafo.getTransbordos();
        it = lista.iterator();
        assertTrue(it.hasNext());
        assertEquals(v1, it.next());
        assertTrue(it.hasNext());
        assertEquals(v2, it.next());
        assertTrue(it.hasNext());
        assertEquals(v3, it.next());
        assertFalse(it.hasNext());
        
        grafo.removerTransbordos(v1);
        grafo.removerTransbordos(v2);
        grafo.removerTransbordos(v3);
        
        lista = grafo.getTransbordos();
        it = lista.iterator();
        assertFalse(it.hasNext());
    }
    
    @Test
    public void testBuscarTemTransbordos() throws TransbordoNaoExisteException, TransbordoJaExisteException {
        grafo.inserirTransbordo(s1);
        grafo.inserirTransbordo(s2);
        grafo.inserirTransbordo(s3);
        
        assertTrue(grafo.temTransbordo(s1));
        assertTrue(grafo.temTransbordo(s2));
        assertTrue(grafo.temTransbordo(s3));
        
        assertEquals(v1, grafo.buscarTransbordo(s1));
        assertEquals(v2, grafo.buscarTransbordo(s2));
        assertEquals(v3, grafo.buscarTransbordo(s3));
    }
    
    @Test
    public void testBuscarTemRotas() throws TransbordoNaoExisteException, TransbordoJaExisteException, RotaJaExisteException, RotaNaoExisteException {
        grafo.inserirRota(v1, v2, 3);
        grafo.inserirRota(v3, v1, 7);
        
        assertTrue(grafo.temRota(v1, v2));
        assertTrue(grafo.temRota(v3, v1));
        
        assertEquals(r1, grafo.buscarRota(v1, v2, 3));
        assertEquals(r2, grafo.buscarRota(v3, v1, 7));
    }
}
