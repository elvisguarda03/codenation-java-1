package br.com.codenation.desafio.exceptions;

public class JogadorNaoEncontradoException extends RuntimeException {
    public JogadorNaoEncontradoException(String msg) {
        super(msg);
    }

    public JogadorNaoEncontradoException() {}
}
