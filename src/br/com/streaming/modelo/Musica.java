package br.com.streaming.modelo;

import br.com.streaming.util.FormatadorTempo;

public class Musica extends ItemReproducao {
    private String artista;
    private int duracao;
    private String genero;

    public Musica(String titulo, String artista, int duracao, String genero) {
        super(titulo);
        setArtista(artista);
        setDuracao(duracao);
        setGenero(genero);
    }

    @Override
    public void reproduzir() {
        super.reproduzir();
        System.out.println("🎵 Tocando: " + nome + " - " + artista + " [" + FormatadorTempo.formatarSegundos(duracao) + "]");
    }

    public void exibir() {
        System.out.println(nome + " - " + artista + " (" + genero + ") ▶ "
                + totalReproducoes + " | " + FormatadorTempo.formatarSegundos(duracao));
    }

    @Override
    public int getDuracaoTotal() {
        return duracao;
    }

    public String getGenero() {
        return genero;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        if (artista == null || artista.isBlank()) {
            throw new IllegalArgumentException("Artista inválido");
        }
        this.artista = artista.trim();
    }

    public void setDuracao(int duracao) {
        if (duracao <= 0) {
            throw new IllegalArgumentException("Duração inválida");
        }
        this.duracao = duracao;
    }

    public void setGenero(String genero) {
        if (genero == null || genero.isBlank()) {
            throw new IllegalArgumentException("Gênero inválido");
        }
        this.genero = genero.trim();
    }
}
