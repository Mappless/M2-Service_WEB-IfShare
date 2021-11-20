package fr.uge.service_web.project.not_shared.database.exception;

public class FailedToConnectToDatabaseException extends RuntimeException {
    public FailedToConnectToDatabaseException(String errorMessage) {
        super(errorMessage);
    }
}
