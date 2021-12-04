package fr.uge.service_web.project.not_shared.exception;

import java.rmi.RemoteException;

public class UncheckedRemoteException extends RuntimeException {
    public UncheckedRemoteException(RemoteException e) {
        super(e);
    }
}
