package Model;

/**
 * Classe responsável por criar e obter a rota, pontos e distância entre os
 * transbordos.
 */
public class Rota {

    private Transbordo transbordo1;
    private Transbordo transbordo2;
    private int peso = 0;

    /**
     * Construtor de Rota que cadastra suas informações no sistema.
     *
     * @param primeiro - Origem da rota.
     * @param segundo - Destino da rota.
     * @param peso - Distância da rota.
     */
    public Rota(Transbordo primeiro, Transbordo segundo, int peso) {
        this.transbordo1 = primeiro;
        this.transbordo2 = segundo;
        this.peso = peso;
    }

    /**
     * Construtor de Rota que cadastra suas informações no sistema.
     *
     * @param primeiro - Origem da rota.
     * @param segundo - Destino da rota.
     */
    public Rota(Transbordo primeiro, Transbordo segundo) {
        this.transbordo1 = primeiro;
        this.transbordo2 = segundo;
    }

    /**
     * Método que retorna o peso(distância) da Rota.
     *
     * @return int peso;
     */
    public int getPeso() {
        return peso;
    }

    /**
     * Método que retorna a origem de uma rota.
     *
     * @return Transbordo transbordo1;
     */
    public Transbordo getTransbordo1() {
        return transbordo1;
    }

    /**
     * Método que retorna o destino de uma rota.
     *
     * @return Transbordo transbordo2;
     */
    public Transbordo getTransbordo2() {
        return transbordo2;
    }

    /**
     * Método responsável por verificar se um transbordo é diferente do outro.
     *
     * @param a - Transbordo para a verificação.
     * @return Transbordo transbordo1;
     */
    public Transbordo getOutro(Transbordo a) {
        if (transbordo1.equals(a)) {
            return transbordo2;
        }
        return transbordo1;
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
        if (objeto instanceof Rota) {
            Rota rota = (Rota) objeto;
            if (rota.getTransbordo1().equals(transbordo1) && rota.getTransbordo2().equals(transbordo2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que dá uma representação em String de uma rota;
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Rotas{" + "Local1 = " + transbordo1 + ", Local2 = " + transbordo2 + ", Peso  = " + peso + '}';
    }
}
