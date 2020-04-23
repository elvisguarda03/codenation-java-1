package br.com.codenation.repository;

import br.com.codenation.model.Jogador;

import java.math.BigDecimal;
import java.util.List;

public interface JogadorRepository extends BaseRepository<Jogador> {
    void saveCapitao(Long id);
    Long findBestPlayerById(Long id);
    Long findOlderPlayerById(Long id);
    Long findPlayerGreaterSallaryById(Long id);
    BigDecimal findSallaryById(Long id);
    String findById(Long id);
    List<Long> findByIdTime(Long idTime);
    List<Long> findTopPlayers(Integer top);
}
