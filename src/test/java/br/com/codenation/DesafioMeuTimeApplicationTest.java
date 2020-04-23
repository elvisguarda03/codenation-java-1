package br.com.codenation;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DesafioMeuTimeApplicationTest {
    private DesafioMeuTimeApplication desafioMeuTimeApplication;

    @Before
    public void before() {
        desafioMeuTimeApplication = new DesafioMeuTimeApplication();

        if (desafioMeuTimeApplication.buscarTimes().size() == 0) {
            criarTimes();
            criarJogadores();
        }
    }

    private void criarJogadores() {
        desafioMeuTimeApplication.incluirJogador(1l, 1l, "Elvis de Sousa",
                LocalDate.of(1999, 1, 03), 100, BigDecimal.valueOf(3000000));
        desafioMeuTimeApplication.incluirJogador(2l, 1l, "Leo",
                LocalDate.of(1998, 2, 03), 31, BigDecimal.valueOf(10000));
        desafioMeuTimeApplication.incluirJogador(3l, 1l, "Pedro",
                LocalDate.of(1980, 3, 03), 100, BigDecimal.valueOf(100000));
        desafioMeuTimeApplication.incluirJogador(4l, 2l, "Santiago",
                LocalDate.of(1981, 4, 03), 100, BigDecimal.valueOf(200000));
        desafioMeuTimeApplication.incluirJogador(5l, 2l, "Gabi",
                LocalDate.of(1982, 5, 03), 95, BigDecimal.valueOf(1650000));
        desafioMeuTimeApplication.incluirJogador(6l, 2l, "Paulo",
                LocalDate.of(1983, 6, 03), 90, BigDecimal.valueOf(340000));
        desafioMeuTimeApplication.incluirJogador(7l, 2l, "Sergio",
                LocalDate.of(1996, 7, 03), 70, BigDecimal.valueOf(4310000));
        desafioMeuTimeApplication.incluirJogador(8l, 3l, "Busquets",
                LocalDate.of(1987, 8, 03), 20, BigDecimal.valueOf(4320000));
        desafioMeuTimeApplication.incluirJogador(9l, 3l, "Pires",
                LocalDate.of(1985, 9, 03), 30, BigDecimal.valueOf(4243000));
        desafioMeuTimeApplication.incluirJogador(10l, 3l, "Carlos",
                LocalDate.of(1998, 10, 03), 40, BigDecimal.valueOf(423000));
        desafioMeuTimeApplication.incluirJogador(11l, 4l, "Bruno",
                LocalDate.of(1997, 11, 03), 40, BigDecimal.valueOf(423000));
        desafioMeuTimeApplication.incluirJogador(12l, 4l, "Luis",
                LocalDate.of(1996, 12, 03), 80, BigDecimal.valueOf(1043220));
        desafioMeuTimeApplication.incluirJogador(13l, 4l, "Oveia",
                LocalDate.of(1991, 1, 03), 70, BigDecimal.valueOf(756000));
        desafioMeuTimeApplication.incluirJogador(14l, 5l, "Lucas",
                LocalDate.of(1992, 2, 03), 60, BigDecimal.valueOf(50000));
        desafioMeuTimeApplication.incluirJogador(15l, 5l, "Felipe",
                LocalDate.of(1993, 3, 03), 50, BigDecimal.valueOf(70000));
        desafioMeuTimeApplication.incluirJogador(16l, 5l, "Lipe",
                LocalDate.of(1994, 4, 03), 20, BigDecimal.valueOf(130000));
        desafioMeuTimeApplication.incluirJogador(17l, 6l, "Breno",
                LocalDate.of(1990, 5, 03), 17, BigDecimal.valueOf(90000));
        desafioMeuTimeApplication.incluirJogador(18l, 6l, "Gabi Gol",
                LocalDate.of(1990, 6, 07), 10, BigDecimal.valueOf(80000));
    }

    private void criarTimes() {
        desafioMeuTimeApplication.incluirTime(1l, "São Paulo", LocalDate.now(), "Branco", "Vermelho");
        desafioMeuTimeApplication.incluirTime(2l, "Corinthians", LocalDate.now(), "Branco", "Preto");
        desafioMeuTimeApplication.incluirTime(3l, "Palmeiras", LocalDate.now(), "Verde", "Branco");
        desafioMeuTimeApplication.incluirTime(4l, "Botafogo", LocalDate.now(), "Preto", "Branco");
        desafioMeuTimeApplication.incluirTime(5l, "Bahia", LocalDate.now(), "Branco", "Azul");
        desafioMeuTimeApplication.incluirTime(6l, "Vitória", LocalDate.now(), "Vermelho", "Preto");
        desafioMeuTimeApplication.incluirTime(7l, "Fluminense", LocalDate.now(), "Roxo", "Azul");
        desafioMeuTimeApplication.incluirTime(8l, "Flamengo", LocalDate.now(), "Vermelho", "Preto");
        desafioMeuTimeApplication.incluirTime(9l, "Curitiba", LocalDate.now(), "Verde", "Preto");
        desafioMeuTimeApplication.incluirTime(10l, "Santos", LocalDate.now(), "Branco", "Preto");
        desafioMeuTimeApplication.incluirTime(11l, "Atletico MG", LocalDate.now(), "Branco", "Preto");
        desafioMeuTimeApplication.incluirTime(12l, "Atletico Paranaense", LocalDate.now(), "Azul", "Rosa");
        desafioMeuTimeApplication.incluirTime(13l, "Tupynambás", LocalDate.now(), "Verde", "Laranja");
        desafioMeuTimeApplication.incluirTime(14l, "Sport", LocalDate.now(), "Vermelho", "Preto");
        desafioMeuTimeApplication.incluirTime(15l, "Vasco da Gama", LocalDate.now(), "Branco", "Preto");
        desafioMeuTimeApplication.incluirTime(16l, "Bragantino", LocalDate.now(), "Branco", "Preto");
        desafioMeuTimeApplication.incluirTime(17l, "Ceara", LocalDate.now(), "Branco", "Preto");
        desafioMeuTimeApplication.incluirTime(18l, "Gremio", LocalDate.now(), "Azul", "Branco");
        desafioMeuTimeApplication.incluirTime(19l, "Fortaleza", LocalDate.now(), "Branco", "Preto");
        desafioMeuTimeApplication.incluirTime(20l, "Goias", LocalDate.now(), "Verde", "Branco");
    }

    @Test
    public void buscarTopJogadores() {
        List<Long> tops = desafioMeuTimeApplication.buscarTopJogadores(2);

        assertEquals(2, tops.size());
        assertEquals(Arrays.asList(Long.valueOf(1),
                Long.valueOf(3)), tops);
    }

    @Test
    public void buscarNomeJogador() {
        assertEquals("Elvis de Sousa", desafioMeuTimeApplication.buscarNomeJogador(1l));
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirJogadorComValorMenorQue0ThrowIllegalArgumentException() {
        desafioMeuTimeApplication.incluirJogador(22l, 6l, "Gabi Gol",
                LocalDate.of(1990, 6, 07), -1, BigDecimal.valueOf(80000));
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirJogadorComValorMaiorQue100ThrowIllegalArgumentException() {
        desafioMeuTimeApplication.incluirJogador(22l, 6l, "Gabi Gol",
                LocalDate.of(1990, 6, 07), 101, BigDecimal.valueOf(80000));
    }

    @Test
    public void buscarMelhorJogadorDoTime() {
        assertEquals(Long.valueOf(1), desafioMeuTimeApplication.buscarMelhorJogadorDoTime(1l));
    }

    @Test
    public void buscarJogadorMaisVelho() {
        assertEquals(Long.valueOf(3), desafioMeuTimeApplication.buscarJogadorMaisVelho(1l));
    }

    @Test
    public void buscarJogadorMaiorSalario() {
        assertEquals(Long.valueOf(1), desafioMeuTimeApplication.buscarJogadorMaiorSalario(1l));
    }

    @Test
    public void buscarJogadoresDoTime() {
        assertEquals(3, desafioMeuTimeApplication.buscarJogadoresDoTime(1l).size());
    }

    @Test
    public void incluirCapitao() {
        desafioMeuTimeApplication.definirCapitao(2l);

        assertEquals(Long.valueOf(2), desafioMeuTimeApplication.buscarCapitaoDoTime(1l));

        desafioMeuTimeApplication.definirCapitao(1l);

        assertEquals(Long.valueOf(1), desafioMeuTimeApplication.buscarCapitaoDoTime(1l));
    }

    @Test(expected = CapitaoNaoInformadoException.class)
    public void buscarCapitaoThrowCapitaoNaoInformadoException() {
        desafioMeuTimeApplication.buscarCapitaoDoTime(20l);
    }

    @Test(expected = IdentificadorUtilizadoException.class)
    public void incluirTimeComIdDuplicadoThrowIdentificadorUtilizadoException() {
        desafioMeuTimeApplication.incluirTime(1l, "São Paulo", LocalDate.now(), "Branco", "Vermelho");
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirTimeSemOsDadosObrigatoriosThrowIllegalArgumentException() {
        desafioMeuTimeApplication.incluirTime(null, null, null, null, null);
    }

    @Test
    public void buscarTimes() {
        List<Long> times = desafioMeuTimeApplication.buscarTimes();
        assertEquals(20, times.size());
    }

    @Test
    public void buscarNomeTime() {
        assertEquals("Bahia", desafioMeuTimeApplication.buscarNomeTime(5l));
        assertEquals("São Paulo", desafioMeuTimeApplication.buscarNomeTime(1l));
    }

    @Test
    public void buscarCorCamisaTimesDeFora() {
        assertEquals("Azul", desafioMeuTimeApplication.buscarCorCamisaTimeDeFora(1l, 5l));
        assertEquals("Preto", desafioMeuTimeApplication.buscarCorCamisaTimeDeFora(8l, 14l));
        assertEquals("Azul", desafioMeuTimeApplication.buscarCorCamisaTimeDeFora(11l, 12l));
    }

}