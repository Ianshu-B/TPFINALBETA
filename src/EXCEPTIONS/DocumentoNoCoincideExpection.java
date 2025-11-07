package EXCEPTIONS;

public class DocumentoNoCoincideExpection extends RuntimeException {
    public DocumentoNoCoincideExpection(String message) {
        super(message);
    }
}
