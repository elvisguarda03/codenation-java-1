package br.com.codenation;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.model.Jogador;
import br.com.codenation.model.Time;
import br.com.codenation.repository.JogadorRepository;
import br.com.codenation.repository.TimeRepository;
import br.com.codenation.repository.impl.JogadorRepositoryImpl;
import br.com.codenation.repository.impl.TimeRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
	private final List<Time> times = new ArrayList<>();
	private final TimeRepository timeRepository = new TimeRepositoryImpl(times);
	private final JogadorRepository jogadorRepository = new JogadorRepositoryImpl(times);

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		validateTime(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);

		Time time = Time.builder()
				.id(id)
				.nome(nome)
				.dataCriacao(dataCriacao)
				.corUniformePrincipal(corUniformePrincipal)
				.corUniformeSecundario(corUniformeSecundario)
				.build();
		timeRepository.save(time);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		validateJogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);

		Jogador jogador = Jogador.builder()
				.id(id)
				.idTime(idTime)
				.nome(nome)
				.dataNascimento(dataNascimento)
				.nivelHabilidade(nivelHabilidade)
				.salario(salario)
				.build();
		jogadorRepository.save(jogador);
	}

	private void validateJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		if (id == null || idTime == null || nome == null || nome.isEmpty()
				|| dataNascimento == null || nivelHabilidade == null
				|| salario == null || salario.signum() != 1) {
			throw new IllegalArgumentException("Todos os parâmetros são obrigatórios");
		}

		if (nivelHabilidade < 0 || nivelHabilidade > 100) {
			throw new IllegalArgumentException("");
		}
	}

	private void validateTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		if (id == null || id < 0 || nome == null || nome.isEmpty()
				|| dataCriacao == null || corUniformePrincipal == null || corUniformePrincipal.isEmpty()
				|| corUniformeSecundario == null || corUniformeSecundario.isEmpty()) {
			throw new IllegalArgumentException("Todos os parâmetros são obrigatórios");
		}
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		jogadorRepository.saveCapitao(idJogador);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		return timeRepository.findCapitaoById(idTime);
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		return jogadorRepository.findById(idJogador);
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		return timeRepository.findById(idTime);
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		return jogadorRepository.findByIdTime(idTime);
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		return jogadorRepository.findBestPlayerById(idTime);
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		return jogadorRepository.findOlderPlayerById(idTime);
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return timeRepository.findAll();
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		return jogadorRepository.findPlayerGreaterSallaryById(idTime);
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		return jogadorRepository.findSallaryById(idJogador);
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		return jogadorRepository.findTopPlayers(top);
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		return timeRepository.findByIdTimeForaAndIdTimeCasa(timeDaCasa, timeDeFora);
	}
}