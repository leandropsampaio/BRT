package Controller;

import Exception.*;
import Model.*;
import Util.AuxGrafo;
import Util.Grafo;
import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * Classe ControllerBRT, faz todos os requisitos do programa relacionados as
 * funções do programa.
 *
 * @author Leandro Sampaio e Lucas.
 */
public class ControllerBRT {

    private Grafo grafo;
    private String nomeArquivo = null;
    private AuxGrafo aux = new AuxGrafo();

    /**
     * Construtor da classe, que inicializa o grafo.
     */
    public ControllerBRT() {
        grafo = new Grafo();
    }

    /**
     * Método que retorna o grafo.
     *
     * @return Grafo grafo;
     */
    public Grafo getTransbordos() {
        return grafo;
    }

    /**
     * Método responsável por realizar a leitura das informações do arquivo
     * quando necessário para o programa.
     *
     * @param arquivo - Endereço do arquivo.
     * @throws RotaJaExisteException - Exceção.
     * @throws TransbordoJaExisteException - Exceção.
     * @throws TransbordoNaoExisteException - Exceção.
     */
    public void lerArquivo(String arquivo) throws RotaJaExisteException, TransbordoJaExisteException, TransbordoNaoExisteException {

        this.nomeArquivo = arquivo;
        String linha;
        String[] arrayLinha;
        try {
            BufferedReader StrR = new BufferedReader(new FileReader(arquivo)); // Criando buffer para leitura
            //int numero = Integer.parseInt(linha); // Lendo número
            while ((linha = StrR.readLine()) != null) {
                arrayLinha = linha.split("		");
                if (arrayLinha.length > 2) {
                    String transbordo1 = arrayLinha[0]; // Lendo transbordo1
                    String transbordo2 = arrayLinha[1]; // Lendo transbordo1
                    String pesoCorredor = arrayLinha[2]; // Lendo peso
                    int peso = Integer.parseInt(pesoCorredor); // Transformando o peso em int
                    inserirRota(inserirTransbordo(transbordo1), inserirTransbordo(transbordo2), peso);
                    aux.adicionar(transbordo1, transbordo2);
                    System.out.print("Local: " + transbordo1);
                    System.out.print("Local2: " + transbordo2);
                    System.out.println("Distância: " + peso);
                }
            }
            StrR.close(); // Fechando buffer.
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    /**
     * Método responsável por realizar o salvamento das informações do programa
     * quando necessário ao arquivo de texto.
     *
     * @throws RotaJaExisteException - Exceção.
     * @throws TransbordoJaExisteException - Exceção.
     * @throws TransbordoNaoExisteException - Exceção.
     */
    public void salvarArquivo() throws RotaJaExisteException, TransbordoJaExisteException, TransbordoNaoExisteException {

        try {
            if (nomeArquivo != null) {
                BufferedWriter escrever = new BufferedWriter(new FileWriter(nomeArquivo));

                Iterator it = (Iterator) grafo.getRotas();
                escrever.write("Ponto Origem    Ponto Destino   KM");
                escrever.newLine();
                escrever.write("____________________________________");
                escrever.newLine();
                while (it.hasNext()) {
                    Rota rota = (Rota) it.next();
                    escrever.write(rota.getTransbordo1().getNome() + "		" + rota.getTransbordo2().getNome()
                            + "		" + rota.getPeso());
                    escrever.newLine();
                }
                escrever.close();
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    /**
     * Método responsável por listar os transbordos existentes no grafo.
     *
     * @return List grafo.
     */
    public List<Transbordo> listarTransbordos() {
        return grafo.getTransbordos();
    }

    /**
     * Método responsável por inserir as rotas do transbordo.
     *
     * @param transbordo1 - Origem da rota.
     * @param transbordo2 - Destino da rota.
     * @param peso - Distância entre os transbordos.
     * @return Rota grafo.
     * @throws RotaJaExisteException - Exceção.
     * @throws TransbordoJaExisteException - Exceção.
     */
    public Rota inserirRota(Transbordo transbordo1, Transbordo transbordo2, int peso) throws RotaJaExisteException, TransbordoJaExisteException {
        return grafo.inserirRota(transbordo1, transbordo2, peso);
    }

    /**
     * Método responsável por inserir os transbordos.
     *
     * @param nomeTransbordo - Nome do transbordo.
     * @return Transbordo grafo.
     * @throws TransbordoNaoExisteException - Exceção.
     */
    public Transbordo inserirTransbordo(String nomeTransbordo) throws TransbordoNaoExisteException {
        try {
            return grafo.inserirTransbordo(nomeTransbordo);
        } catch (TransbordoJaExisteException ex) {
        }
        return grafo.buscarTransbordo(nomeTransbordo);
    }

    /**
     * Método responsável por remover a rota entre os transbordos.
     *
     * @param transbordo1 - Origem da rota.
     * @param transbordo2 - Destino da rota.
     * @return Rota grafo.
     * @throws TransbordoJaExisteException - Exceção.
     * @throws RotaNaoExisteException - Exceção.
     */
    public Rota removerRota(Transbordo transbordo1, Transbordo transbordo2) throws TransbordoJaExisteException, RotaNaoExisteException {
        return grafo.removerRota(transbordo1, transbordo2);
    }

    /**
     * Método responsável por remover os transbordos.
     *
     * @param nomeTransbordo - Nome do transbordo.
     * @return Transbordo grafo.
     * @throws TransbordoJaExisteException - Exceção.
     * @throws TransbordoNaoExisteException - Exceção.
     * @throws RotaNaoExisteException - Exceção.
     */
    public Transbordo removerTransbordo(String nomeTransbordo) throws TransbordoJaExisteException, TransbordoNaoExisteException, RotaNaoExisteException {
        return grafo.removerTransbordo(nomeTransbordo);
    }

    /**
     * Método responsável por encontrar o menor caminho entre os transbordos.
     *
     * @param a - Origem do caminho.
     * @param b - Destino do caminho.
     * @return List busca.
     * @throws SemTransbordosException - Exceção.
     */
    public Iterator encontrarMenorCaminho(Transbordo a, Transbordo b) throws SemTransbordosException {
        return grafo.encontrarMenorCaminho(a, b);
    }

    /**
     * Método responsável por buscar um transbordo.
     *
     * @param transbordo - Nome do transbordo.
     * @return Transbordo grafo.
     * @throws TransbordoNaoExisteException - Exceção.
     */
    public Transbordo buscarTransbordo(String transbordo) throws TransbordoNaoExisteException {
        return grafo.buscarTransbordo(transbordo);
    }

    /**
     * Método responsável por encontrar todas as rotas possiveis entre dois
     * transbordos.
     *
     * @param origem - Origem das possiveis rotas.
     * @param destino - Destino das possiveis rotas.
     * @return Iterator - Iterator da lista de possíveis rotas.
     */
    public Iterator IdentificarPossiveisRotas(String origem, String destino) {
        return grafo.possiveisRotas(origem, destino, aux);
    }
}
