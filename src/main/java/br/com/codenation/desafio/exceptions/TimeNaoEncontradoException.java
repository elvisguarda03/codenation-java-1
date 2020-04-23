package br.com.codenation.desafio.exceptions;

public class TimeNaoEncontradoException extends RuntimeException {
    public TimeNaoEncontradoException(String msg) {
        super(msg);
    }

    public TimeNaoEncontradoException() {}
}
