package br.com.codenation.desafio.exceptions;

public class IdentificadorUtilizadoException extends RuntimeException {

    public IdentificadorUtilizadoException(String msg) {
        super(msg);
    }

    public IdentificadorUtilizadoException(String msg, Exception ex) {
        super(msg, ex);
    }
}
