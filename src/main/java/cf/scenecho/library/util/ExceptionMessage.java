package cf.scenecho.library.util;

public enum ExceptionMessage {
    DUPLICATED_ID("Duplicated Id."),
    NON_EXISTING_ID("Non-existing Id."),
    INVALID_PASSWORD("Invalid Password."),
    NOT_AVAILABLE("Not Available.");

    String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
