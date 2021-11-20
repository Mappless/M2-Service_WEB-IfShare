package fr.uge.service_web.project.not_shared.database.exception;

public class FailedToRetrieveProductException extends RuntimeException {
    public FailedToRetrieveProductException(String errorMessage) {
        super(errorMessage);
    }
}
