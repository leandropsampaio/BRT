package Exception;

public class TransbordoJaExisteException extends Exception {

    public TransbordoJaExisteException() {
        super("Transbordo já existe, tente novamente...");
    }
}
