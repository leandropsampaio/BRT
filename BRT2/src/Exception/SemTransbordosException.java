package Exception;

public class SemTransbordosException extends Exception {

    public SemTransbordosException() {
        super("Não existe transbordo, tente novamente...");
    }
}
