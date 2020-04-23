package br.com.codenation.repository.impl;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.model.Jogador;
import br.com.codenation.model.Time;
import br.com.codenation.repository.JogadorRepository;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JogadorRepositoryImpl implements JogadorRepository {
    private final List<Time> times;

    public JogadorRepositoryImpl(List<Time> times) {
        this.times = times;
    }

    @Override
    public void saveCapitao(Long id) {
        Jogador player = getJogadorStream()
                .filter(jogador -> jogador.getId().equals(id))
                .findFirst()
                .orElseThrow(JogadorNaoEncontradoException::new);

        Time team = times.stream().filter(time -> time.getId().equals(player.getIdTime()))
                .findFirst()
                .get();

        team.setCapitao(player);
    }

    private Stream<Jogador> getJogadorStream() {
        Stream<Jogador> jogadores = times.stream()
                .map(Time::getJogadores)
                .flatMap(List::stream);
        return jogadores;
    }

    @Override
    public Long findBestPlayerById(Long id) {
        Integer nivelHabilidade = getJogadorStream()
                .filter(jogador -> jogador.getIdTime().equals(id))
                .mapToInt(Jogador::getNivelHabilidade)
                .max()
                .orElseThrow(TimeNaoEncontradoException::new);

        return getJogadorStream()
                .filter(jogador -> jogador.getNivelHabilidade().equals(nivelHabilidade))
                .mapToLong(Jogador::getId)
                .findFirst()
                .orElseThrow(JogadorNaoEncontradoException::new);
    }

    @Override
    public Long findOlderPlayerById(Long id) {
        Jogador player =  getJogadorStream()
                .filter(jogador -> jogador.getIdTime().equals(id))
                .min(Comparator.comparing(Jogador::getDataNascimento))
                .orElseThrow(TimeNaoEncontradoException::new);

        return player.getId();
    }

    @Override
    public Long findPlayerGreaterSallaryById(Long id) {
        BigDecimal greaterSallary = getJogadorStream()
                .filter(jogador -> jogador.getIdTime().equals(id))
                .map(Jogador::getSalario)
                .max(BigDecimal::compareTo)
                .orElseThrow(TimeNaoEncontradoException::new);

        return getJogadorStream()
                .filter(jogador -> jogador.getSalario()
                    .equals(greaterSallary))
                .mapToLong(Jogador::getId)
                .findFirst()
                .orElseThrow(JogadorNaoEncontradoException::new);
    }

    @Override
    public BigDecimal findSallaryById(Long id) {
        return getJogadorStream()
                .filter(jogador -> jogador.getId().equals(id))
                .map(Jogador::getSalario)
                .findFirst()
                .orElseThrow(JogadorNaoEncontradoException::new);
    }

    @Override
    public void save(Jogador jogador) {
        if (existsById(jogador.getId())) {
            throw new IdentificadorUtilizadoException("Identificador fornecido já foi registrado.");
        }

        Time team = times.stream()
                .filter(time -> time.getId().equals(jogador.getIdTime()))
                .findFirst()
                .orElseThrow(TimeNaoEncontradoException::new);
        team.addJogador(jogador);
    }

    @Override
    public boolean existsById(Long id) {
        return getJogadorStream()
                .anyMatch(jogador -> jogador.getId().equals(id));
    }

    @Override
    public String findById(Long id) {
        return getJogadorStream()
                .filter(jogador -> jogador.getId().equals(id))
                .map(Jogador::getNome)
                .findFirst()
                .orElseThrow(JogadorNaoEncontradoException::new);
    }

    @Override
    public List<Long> findAll() {
        return getJogadorStream()
                .map(Jogador::getId)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> findByIdTime(Long idTime) {
        List<Long> players = Optional.ofNullable(getJogadorStream()
                .filter(jogador -> jogador.getIdTime().equals(idTime))
                .mapToLong(Jogador::getId)
                .sorted()
                .boxed()
                .collect(Collectors.toList()))
                .orElseThrow(TimeNaoEncontradoException::new);

        return players;
    }

    @Override
    public List<Long> findTopPlayers(Integer top) {
        List<Long> players = getJogadorStream()
                .sorted(Comparator
                        .comparing(Jogador::getNivelHabilidade)
                        .reversed()
                        .thenComparing(Jogador::getId))
                .limit(top)
                .mapToLong(Jogador::getId)
                .boxed()
                .collect(Collectors.toList());

        if (players == null) {
            throw new IllegalArgumentException();
        }

        return players;
    }
}