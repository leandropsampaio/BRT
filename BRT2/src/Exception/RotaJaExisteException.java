package Exception;

public class RotaJaExisteException extends Exception {

    public RotaJaExisteException() {
        super("Rota já existe, tente novamente...");
    }
}
