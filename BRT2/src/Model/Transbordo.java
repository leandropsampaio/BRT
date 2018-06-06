package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe implementa os transbordos do sistema, contendo os atributos de um
 * transbordo.
 *
 */
public class Transbordo implements Comparable<Transbordo> {

    private String nomeTransbordo;
    private List<Rota> rotas;
    private boolean visitado = false;
    private int distancia;
    private Transbordo pai = null;
    private int x;
    private int y;

    /**
     * Construtor que incializa um transbordo.
     *
     * @param nome - Nome do transbordo.
     */
    public Transbordo(String nome) {
        this.nomeTransbordo = nome;
        rotas = new ArrayList<>();
    }

    /**
     * Método que retorna o nome do transbordo.
     *
     * @return String nomeTransbordo;
     */
    public String getNome() {
        return nomeTransbordo;
    }

    /**
     * Método responsável por adicionar rotas no transbordo.
     *
     * @param rota - Rota.
     */
    public void adicionarRota(Rota rota) {
        rotas.add(rota);
    }

    /**
     * Método responsável por remover rotas do transbordo.
     *
     * @param rota - Rota.
     */
    public void removerRota(Rota rota) {
        rotas.remove(rota);
    }

    /**
     * Método responsável por retornar a lista de rotas do transbordo..
     *
     * @return List - Lista de rotas.
     */
    public List<Rota> getRotas() {
        return rotas;
    }

    /**
     * Método que retorna a distância do transbordo.
     *
     * @return int distancia
     */
    public int getDistancia() {
        return distancia;
    }

    /**
     * Método que modifica o peso da distância do transbordo.
     *
     * @param distancia - Distancia.
     */
    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    /**
     * Método responsável por checar se o objeto recebido é uma instância da
     * classe, através da comparação dos atributos da classe com os atributos do
     * objeto recebido.
     *
     * @param objeto - objeto que será comparado pelo presente método
     *
     * @return Boolean - se o resultado da comparação estiver correto, será
     * retornado true, caso contrário, será retornado false.
     */
    @Override
    public boolean equals(Object objeto) {
        if (objeto instanceof Transbordo) {
            Transbordo comodo = (Transbordo) objeto;
            if (comodo.getNome().equals(nomeTransbordo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que retorna o nome do transbordo.
     *
     * @return String - Nome do transbordo;
     */
    @Override
    public String toString() {
        return nomeTransbordo;
    }

    /**
     * Método que retorna um boolean caso o transbordo tenha sido visitado.
     *
     * @return boolean visitado;
     */
    public boolean getVisitado() {
        return this.visitado;
    }

    /**
     * Método que modifica o transbordo caso tenha sido visitado.
     *
     * @param b - Boolean.
     */
    public void setVisitado(boolean b) {
        this.visitado = b;
    }

    /**
     * Método que modifica um boolean que tenha o transbordo pai.
     *
     * @param verticePai - Transbordo.
     */
    public void setPai(Transbordo verticePai) {
        this.pai = verticePai;
    }

    /**
     * Método responsável por retornar o valor do "pai" do transbordo.
     *
     * @return Transbordo pai;
     */
    public Transbordo getPai() {
        return this.pai;
    }

    /**
     * Método que recebe um transbordo e verifica de forma comparativa a sua
     * distancia.
     *
     * @param transbordo - Transbordo.
     * @return int
     */
    @Override
    public int compareTo(Transbordo transbordo) {
        if (distancia < transbordo.distancia) {
            return -1;
        }
        if (distancia > transbordo.distancia) {
            return 1;
        }
        return 0;
    }

    /**
     * Método que retorna a coordenada x do transbordo.
     *
     * @return int - Valor de x.
     */
    public int getX() {
        return x;
    }

    /**
     * Método que modifica o valor da coordenada x.
     *
     * @param x - Valor de x.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Método que retorna a coordenada y do transbordo.
     *
     * @return int - Valor de y.
     */
    public int getY() {
        return y;
    }

    /**
     * Método que modifica o valor da coordenada y.
     *
     * @param y - Valor de y.
     */
    public void setY(int y) {
        this.y = y;
    }
}
