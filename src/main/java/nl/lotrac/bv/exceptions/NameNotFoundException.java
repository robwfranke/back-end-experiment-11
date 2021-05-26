package nl.lotrac.bv.exceptions;

public class NameNotFoundException extends RuntimeException {



    private static final long serialVersionUID = 1L;

    public NameNotFoundException() {
        super();
    }
    public NameNotFoundException(String message) {super(message);
    }
}