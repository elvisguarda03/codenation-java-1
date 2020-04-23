package br.com.codenation.repository.impl;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.model.Time;
import br.com.codenation.repository.TimeRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TimeRepositoryImpl implements TimeRepository {
    private final List<Time> times;

    public TimeRepositoryImpl(List<Time> times) {
        this.times = times;
    }

    @Override
    public void save(Time time) {
        if (existsById(time.getId())) {
            throw new IdentificadorUtilizadoException("Identificador fornecido já foi registrado.");
        }

        times.add(time);
    }

    @Override
    public boolean existsById(Long id) {
        return times.stream()
                .anyMatch(time -> time.getId().equals(id));
    }

    @Override
    public String findById(Long id) {
        return times.stream()
                    .filter(time -> time.getId().equals(id))
                    .map(time -> time.getNome())
                    .findFirst()
                    .orElseThrow(TimeNaoEncontradoException::new);
    }

    @Override
    public List<Long> findAll() {
        return times.stream().sorted()
                .mapToLong(time -> time.getId())
                .boxed()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public Long findCapitaoById(Long id) {
        Time team = times.stream()
                .filter(time -> time.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TimeNaoEncontradoException("Time inexistente"));

        if (team.getCapitao() == null) {
            throw new CapitaoNaoInformadoException("Time informando não possui um capitão");
        }

        return team.getCapitao().getId();
    }

    @Override
    public String findByIdTimeForaAndIdTimeCasa(Long idTimeDaCasa, Long idTimeDeFora) {
        Time timeCasa = findTimeById(idTimeDaCasa);
        Time timeFora = findTimeById(idTimeDeFora);

        if (timeCasa.getCorUniformePrincipal()
                .equalsIgnoreCase(timeFora.getCorUniformePrincipal())) {
            return timeFora.getCorUniformeSecundario();
        }

        return timeFora.getCorUniformePrincipal();
    }

    private Time findTimeById(Long idTimeDaCasa) {
        return times.stream()
                .filter(time -> time.getId().equals(idTimeDaCasa))
                .findFirst()
                .orElseThrow(TimeNaoEncontradoException::new);
    }
}