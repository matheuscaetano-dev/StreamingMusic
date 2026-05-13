package br.com.streaming.modelo;

import br.com.streaming.util.Validador;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    protected final String nome;
    protected final String email;
    protected final List<Playlist> playlists;
    protected final List<Musica> historico;
    protected int totalReproducoes;

    public Usuario(String nome, String email) {
        this.nome = Validador.validarTexto(nome, "Nome");
        this.email = Validador.validarEmail(email);
        this.playlists = new ArrayList<>();
        this.historico = new ArrayList<>();
    }

    public void reproduzirMusica(Musica musica) {
        Validador.validarObjeto(musica, "Música");
        musica.reproduzir();
        historico.add(musica);
        totalReproducoes++;
        System.out.println("🎧 " + nome + " reproduzindo: " + musica.getNome());
    }

    public Playlist criarPlaylist(String nome) {
        Playlist playlist = new Playlist(Validador.validarTexto(nome, "Nome da playlist"));
        playlists.add(playlist);
        System.out.println("Playlist criada: " + playlist.getNome());
        return playlist;
    }

    public void adicionarPlaylist(Playlist playlist) {
        if (playlist == null) {
            throw new IllegalArgumentException("Playlist inválida");
        }
        playlists.add(playlist);
    }

    public List<Playlist> getPlaylists() {
        return List.copyOf(playlists);
    }

    public void listarPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist disponível.");
            return;
        }

        System.out.println("Playlists de " + nome + ":");
        for (int i = 0; i < playlists.size(); i++) {
            Playlist playlist = playlists.get(i);
            System.out.println((i + 1) + " - " + playlist.getNome() + " (" + playlist.getQuantidade() + " faixas)");
        }
    }

    public Playlist buscarPlaylist(String nomePlaylist) {
        return playlists.stream()
                .filter(playlist -> playlist.getNome().equalsIgnoreCase(nomePlaylist))
                .findFirst()
                .orElse(null);
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public int getTotalReproducoes() {
        return totalReproducoes;
    }

    public void setTotalReproducoes(int totalReproducoes) {
        this.totalReproducoes = totalReproducoes;
    }

    public List<Musica> getHistorico() {
        return List.copyOf(historico);
    }
}
