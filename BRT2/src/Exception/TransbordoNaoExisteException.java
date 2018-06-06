package Exception;

public class TransbordoNaoExisteException extends Exception {
    
    public TransbordoNaoExisteException() { 
        super("Transbordo inexistente, tente novamente...");
    }
}
