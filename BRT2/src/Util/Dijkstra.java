package Util;

import Exception.SemTransbordosException;
import Model.Transbordo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A classe Djikstra, é um algoritmo que busca o menor caminho existente entre
 * dois vértices(Transbordos).
 */
public class Dijkstra {

    private List<Transbordo> menorCaminhoEncontrado;
    private Transbordo comodoComMenorCaminho;
    private Transbordo atual;
    private Transbordo aux;
    private List<Transbordo> naoVisitados;

    /**
     * Construtor do Dijkstra, inicializando os objetos que serão utilizados no
     * algoritmo.
     */
    public Dijkstra() {
        menorCaminhoEncontrado = new ArrayList<>();
        comodoComMenorCaminho = new Transbordo("a");
        atual = new Transbordo("b");
        aux = new Transbordo("c");
        naoVisitados = new ArrayList<>();
    }

    /**
     * Calcula o menor caminho entre dois transbordos.
     *
     * @param grafo - Grafo.
     * @param a - Origem do caminho.
     * @param b - Destino do caminho.
     * @return Iterator - Lista do menor caminho.
     * 
     * @throws SemTransbordosException - Exceção.
     */
    public Iterator<Transbordo> encontrarMenorCaminho(Grafo grafo, Transbordo a, Transbordo b) throws SemTransbordosException {
        menorCaminhoEncontrado.add(a);
        a.setDistancia(0);
        // Adicionando distancias iniciais
        for (int i = 0; i < grafo.getTransbordos().size(); i++) {
            if (grafo.getTransbordos().get(i).equals(a)) {
                grafo.getTransbordos().get(i).setDistancia(0);
            } else {
                grafo.getTransbordos().get(i).setDistancia(Integer.MAX_VALUE);
            }
            this.naoVisitados.add(grafo.getTransbordos().get(i));
        }
        Collections.sort(naoVisitados);
        // O algoritmo percorre todos os vertices até que todos sejam visitados
        while (!this.naoVisitados.isEmpty()) {
            atual = this.naoVisitados.get(0);
            for (int i = 0; i < atual.getRotas().size(); i++) {
                aux = atual.getRotas().get(i).getOutro(atual);
                if (!aux.getVisitado()) {
                    if (aux.getDistancia() > (atual.getDistancia() + atual.getRotas().get(i).getPeso())) {
                        aux.setDistancia(atual.getDistancia() + atual.getRotas().get(i).getPeso());
                        aux.setPai(atual);
                        if (aux.equals(b)) {
                            menorCaminhoEncontrado.clear();
                            comodoComMenorCaminho = aux;
                            menorCaminhoEncontrado.add(aux);
                            while (comodoComMenorCaminho.getPai() != null) {
                                menorCaminhoEncontrado.add(comodoComMenorCaminho.getPai());
                                comodoComMenorCaminho = comodoComMenorCaminho.getPai();
                            }
                            Collections.sort(menorCaminhoEncontrado);
                        }
                    }
                }
            }
            atual.setVisitado(true);
            this.naoVisitados.remove(atual);
            Collections.sort(naoVisitados);
        }

        if (menorCaminhoEncontrado.size() == 1) {
            throw new SemTransbordosException();
        }
        this.reiniciar(grafo);
        return menorCaminhoEncontrado.iterator();
    }

    /**
     * Método responsável por reiniciar o grafo.
     *
     * @param grafo - Grafo a ser reiniciado.
     */
    public void reiniciar(Grafo grafo) {
        Iterator it = grafo.getTransbordos().listIterator();
        while (it.hasNext()) {
            Transbordo c = (Transbordo) it.next();
            c.setVisitado(false);
            c.setPai(null);
        }
    }
}
