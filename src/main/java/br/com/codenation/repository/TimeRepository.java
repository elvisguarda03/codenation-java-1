package br.com.codenation.repository;

import br.com.codenation.model.Time;

public interface TimeRepository extends BaseRepository<Time> {
    Long findCapitaoById(Long id);
    String findByIdTimeForaAndIdTimeCasa(Long idTimeDaCasa, Long idTimeDeFora);
}
