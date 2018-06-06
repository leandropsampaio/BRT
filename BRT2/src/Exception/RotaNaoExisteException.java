package Exception;

public class RotaNaoExisteException extends Exception {

    public RotaNaoExisteException() {
        super("Rota inexistente, tente novamente...");
    }
}
