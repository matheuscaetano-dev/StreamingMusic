package br.com.streaming.servico;

import br.com.streaming.modelo.Musica;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GeradorRecomendacoes {

    public List<Musica> recomendarPorGenero(List<Musica> musicas, String genero) {
        if (musicas == null) {
            throw new IllegalArgumentException("Lista de músicas inválida");
        }
        if (genero == null || genero.isBlank()) {
            return List.copyOf(musicas);
        }
        return musicas.stream()
                .filter(musica -> genero.equalsIgnoreCase(musica.getGenero()))
                .collect(Collectors.toList());
    }

    public List<Musica> topMaisReproduzidas(List<Musica> musicas, int limite) {
        if (musicas == null) {
            throw new IllegalArgumentException("Lista de músicas inválida");
        }
        return musicas.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(Musica::getTotalReproducoes).reversed())
                .limit(limite)
                .collect(Collectors.toList());
    }
}
