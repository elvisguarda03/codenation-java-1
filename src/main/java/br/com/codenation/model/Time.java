package br.com.codenation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Time implements Comparable<Time> {
    private Long id;
    private String nome;
    private String corUniformePrincipal;
    private String corUniformeSecundario;
    private LocalDate dataCriacao;

    @Builder.Default
    private List<Jogador> jogadores = new ArrayList<>();
    private Jogador capitao;


    @Override
    public int compareTo(Time time) {
        return this.getId().compareTo(time.getId());
    }

    public void addJogador(Jogador jogador) {
        jogadores.add(jogador);
    }
}
