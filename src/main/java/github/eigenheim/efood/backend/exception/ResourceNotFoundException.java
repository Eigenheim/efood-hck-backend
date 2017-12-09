package github.eigenheim.efood.backend.exception;

import java.text.MessageFormat;

public class ResourceNotFoundException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Error, {0} with id {1} not found";

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(Class ofClass, String id) {
        super(MessageFormat.format(MESSAGE_TEMPLATE, ofClass, id));
    }
}
