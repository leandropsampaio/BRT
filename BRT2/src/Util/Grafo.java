package Util;

import Exception.RotaJaExisteException;
import Exception.RotaNaoExisteException;
import Exception.SemTransbordosException;
import Exception.TransbordoJaExisteException;
import Exception.TransbordoNaoExisteException;
import Model.Rota;
import Model.Transbordo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe Grafo, é responsável por todos os transbordos e rotas.
 *
 * @author Leandro Sampaio.
 */
public class Grafo {

    private Dijkstra busca;
    private List<Rota> rotas;
    private List<Transbordo> transbordos;

    /**
     * Construtor do grafo que inicializa o algoritmo de Dijkstra e a lista de
     * rotas e transbordos.
     */
    public Grafo() {
        busca = new Dijkstra();
        rotas = new ArrayList<>();
        transbordos = new ArrayList<>();
    }

    /**
     * Método responsável por adicionar rotas ao grafo.
     *
     * @param rota - Rota que será adicionada.
     */
    public void addRotas(Rota rota) {
        rotas.add(rota);
    }

    /**
     * Método responsável por adicionar transbordos ao grafo.
     *
     * @param transbordo - Transbordo que será adicionada.
     */
    public void addTransbordos(Transbordo transbordo) {
        transbordos.add(transbordo);
    }

    /**
     * Método responsável por remover as rotas do grafo.
     *
     * @param rota - Rota que será removida.
     */
    public void removerRotas(Rota rota) {
        rotas.remove(rota);
    }

    /**
     * Método responsável por remover transbordos do grafo.
     *
     * @param transbordo - Transbordo que será removido.
     */
    public void removerTransbordos(Transbordo transbordo) {
        transbordos.remove(transbordo);
    }

    /**
     * Metodo responsável por retornar as rotas.
     *
     * @return Iterator - Iterator de rotas;
     */
    public Iterator getRotas() {
        return rotas.iterator();
    }

    /**
     * Método responsável por retornar os transbordos.
     *
     * @return List - Lista de transbordos;
     */
    public List<Transbordo> getTransbordos() {
        return transbordos;
    }

    /**
     * Método responsável por buscar as rotas.
     *
     * @param transbordo1 - Origem da rota.
     * @param transbordo2 - Destino da rota.
     * @param peso - Distância entre os transbordos.
     * @return Rota r;
     * @throws RotaNaoExisteException - Exceção.
     */
    public Rota buscarRota(Transbordo transbordo1, Transbordo transbordo2, int peso) throws RotaNaoExisteException {
        for (Rota r : rotas) {
            if (r.getTransbordo1().equals(transbordo1)
                    && r.getTransbordo2().equals(transbordo2)
                    && r.getPeso() == peso) {
                return r;
            }
        }
        throw new RotaNaoExisteException();
    }

    /**
     * Método responsável por verificar a disponibilidade de existência de uma
     * rota;
     *
     * @param transbordo1 - Origem da rota.
     * @param transbordo2 - Destino da rota.
     * @return Boolena True/False (verdadeiro ou falso);
     */
    public boolean temRota(Transbordo transbordo1, Transbordo transbordo2) {
        for (Rota r : rotas) {
            if (r.getTransbordo1().equals(transbordo1)
                    && r.getTransbordo2().equals(transbordo2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo responsável por buscar os transbordo, verificando semelhança;
     *
     * @param nomeTransbordo - Nome do transbordo.
     * @return Transbordo t;
     * @throws TransbordoNaoExisteException - Exceção.
     */
    public Transbordo buscarTransbordo(String nomeTransbordo) throws TransbordoNaoExisteException {
        for (Transbordo t : transbordos) {
            if (t.getNome().equals(nomeTransbordo)) {
                return t;
            }
        }
        throw new TransbordoNaoExisteException();
    }

    /**
     * Método responsável por verificar a disponibilidade de existência do
     * transbordo;
     *
     * @param nomeTransbordo - Nome do transbordo.
     * @return boolean True/False (Verdadeiro ou Falso);
     */
    public boolean temTransbordo(String nomeTransbordo) {
        for (Transbordo t : transbordos) {
            if (t.getNome().equals(nomeTransbordo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método responsável por inserir as rotas no transbordo e no grafo;
     *
     * @param transbordo1 - Origem da rota.
     * @param transbordo2 - Destino da rota.
     * @param peso - Distância entre os transbordos.
     * @return Rota rota;
     * @throws RotaJaExisteException - Exceção.
     * @throws TransbordoJaExisteException - Exceção.
     */
    public Rota inserirRota(Transbordo transbordo1, Transbordo transbordo2, int peso) throws RotaJaExisteException, TransbordoJaExisteException {
        if (transbordo1 != transbordo2) {
            Rota rota = new Rota(transbordo1, transbordo2, peso);
            if (!temRota(transbordo1, transbordo2)) {
                transbordo1.adicionarRota(rota);
                transbordo2.adicionarRota(rota);
                addRotas(rota);
                return rota;
            }
            throw new RotaJaExisteException();
        }
        throw new TransbordoJaExisteException();
    }

    /**
     * Método responsável por inserir os transbordos ao grafo;
     *
     * @param nomeTransbordo - Nome do transbordo.
     * @return transbordo/nomeTransbordo;
     * @throws TransbordoJaExisteException - Exceção.
     */
    public Transbordo inserirTransbordo(String nomeTransbordo) throws TransbordoJaExisteException {
        Transbordo transbordo = new Transbordo(nomeTransbordo);
        if (!temTransbordo(nomeTransbordo)) {
            addTransbordos(transbordo);
            return transbordo;
        } else {
            throw new TransbordoJaExisteException();
        }
    }

    /**
     * Metodo responsável por remover as rotas;
     *
     * @param transbordo1 - Origem da rota.
     * @param transbordo2 - Destino da rota.
     * @return Rota
     * 
     * @throws RotaNaoExisteException - Exceção.
     */
    public Rota removerRota(Transbordo transbordo1, Transbordo transbordo2) throws RotaNaoExisteException {

        Rota rota = new Rota(transbordo1, transbordo2);
        if (temRota(transbordo1, transbordo2)) {
            transbordo1.removerRota(rota);
            transbordo2.removerRota(rota);
            removerRotas(rota);
            return rota;
        }
        throw new RotaNaoExisteException();
    }

    /**
     * Metodo responsável por remover os transbordos do grafo;
     *
     * @param nomeTransbordo - Nome do transbordo.
     * @return transbordo/nomeTransbordo;
     * @throws TransbordoNaoExisteException - Exceção.
     */
    public Transbordo removerTransbordo(String nomeTransbordo) throws TransbordoNaoExisteException {
        int x = 0;
        Transbordo transbordo = buscarTransbordo(nomeTransbordo);
        if (temTransbordo(nomeTransbordo)) {
            Iterator it;
            while (x == 0) {
                it = transbordo.getRotas().listIterator();
                if (it.hasNext()) {
                    try {
                        Rota rota = (Rota) it.next();
                        System.out.println("Local1: " + rota.getTransbordo1() + "Local2: " + rota.getTransbordo2() + "Peso: " + rota.getPeso());
                        this.removerRota(rota.getTransbordo1(), rota.getTransbordo2());
                        this.removerRota(rota.getTransbordo2(), rota.getTransbordo1());
                    } catch (RotaNaoExisteException ex) {
                    }
                } else {
                    x = 1;
                }
            }
            removerTransbordos(transbordo);
            return transbordo;
        } else {
            return buscarTransbordo(nomeTransbordo);
        }
    }

    /**
     * Método que identifica todas as possíveis rotas formada pela AuxGrafo e
     * retorna um Iterador sobre todos os caminhos possíveis.
     *
     * @param origem - Nome do transbordo de origem.
     * @param destino - Nome do transbordo de destino.
     * @param aux - AuxGrafo.
     * @return Iterator
     */
    public Iterator possiveisRotas(String origem, String destino, AuxGrafo aux) {
        aux.setInicio(origem);
        List a = aux.primeiraBorda(destino);
        aux.limpar();
        return a.iterator();
    }

    /**
     * Método que identifica o menor caminho entre determinados transbordo de origem
     * e destino, por meio do algoritmo de Djikstra.
     *
     * @param a - Origem do menor caminho.
     * @param b - Destino do menor caminho.
     * @return caminho List
     * @throws SemTransbordosException - Exceção.
     */
    public Iterator encontrarMenorCaminho(Transbordo a, Transbordo b) throws SemTransbordosException {
        return busca.encontrarMenorCaminho(this, a, b);
    }
}
