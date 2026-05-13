package br.com.streaming.modelo;

import java.util.ArrayList;
import java.util.List;

public class Playlist extends ItemReproducao {
    protected final List<Musica> musicas;

    public Playlist(String nome) {
        super(nome);
        this.musicas = new ArrayList<>();
    }

    @Override
    public void reproduzir() {
        super.reproduzir();
        System.out.println("▶ Reproduzindo playlist: " + nome);
        for (Musica musica : musicas) {
            musica.reproduzir();
        }
    }

    public void adicionarMusica(Musica musica) {
        if (musica == null) {
            throw new IllegalArgumentException("Música inválida");
        }
        musicas.add(musica);
        System.out.println("Música adicionada à playlist: " + musica.getNome());
    }

    public void listar() {
        if (musicas.isEmpty()) {
            System.out.println("A playlist está vazia.");
            return;
        }
        System.out.println("Playlist: " + nome + " (" + musicas.size() + " faixas)");
        for (int i = 0; i < musicas.size(); i++) {
            System.out.print((i + 1) + ". ");
            musicas.get(i).exibir();
        }
    }

    public int getQuantidade() {
        return musicas.size();
    }

    public List<Musica> getMusicas() {
        return List.copyOf(musicas);
    }

    @Override
    public int getDuracaoTotal() {
        return musicas.stream().mapToInt(Musica::getDuracaoTotal).sum();
    }
}
