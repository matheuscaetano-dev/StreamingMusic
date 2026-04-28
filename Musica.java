public class Musica {

    private String titulo;
    private String artista;
    private int duracao;
    private String genero;
    private int totalReproducoes;

    public Musica(String titulo, String artista, int duracao, String genero) {
        setTitulo(titulo);
        setArtista(artista);
        setDuracao(duracao);
        setGenero(genero);
    }

    public void reproduzir() {
        totalReproducoes++;
    }

    public void exibir() {
        System.out.println(titulo + " - " + artista + " (" + genero + ") ▶ " + totalReproducoes);
    }

    public String getGenero() { return genero; }
    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) throw new IllegalArgumentException();
        this.titulo = titulo;
    }

    public void setArtista(String artista) {
        if (artista == null || artista.isBlank()) throw new IllegalArgumentException();
        this.artista = artista;
    }

    public void setDuracao(int duracao) {
        if (duracao <= 0) throw new IllegalArgumentException();
        this.duracao = duracao;
    }

    public void setGenero(String genero) {
        if (genero == null || genero.isBlank()) throw new IllegalArgumentException();
        this.genero = genero;
    }
}