package nl.lotrac.bv.exceptions;

public class NameExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NameExistsException() {
        super();
    }
    public NameExistsException(String message) {super(message);
    }
}
