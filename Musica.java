public class Musica {
    private String titulo;
    private String artista;
    private int duracao;
    private String genero;

    // Construtor padrão
    public Musica() {}

    // Construtor parametrizado
    public Musica(String titulo, String artista, int duracao, String genero) {
        setTitulo(titulo);
        setArtista(artista);
        setDuracao(duracao);
        setGenero(genero);
    }

    // Getters e Setters

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título inválido");
        }
        this.titulo = titulo.trim();
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        if (artista == null || artista.trim().isEmpty()) {
            throw new IllegalArgumentException("Artista inválido");
        }
        this.artista = artista.trim();
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        if (duracao <= 0 || duracao >= 3600) {
            throw new IllegalArgumentException("Duração deve ser entre 1 e 3599 segundos");
        }
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        if (genero == null) {
            throw new IllegalArgumentException("Gênero inválido");
        }

        String g = genero.trim().toLowerCase();

        if (!(g.equals("pop") || g.equals("rock") || g.equals("jazz") ||
              g.equals("eletrônica") || g.equals("eletronica") ||
              g.equals("hip-hop") || g.equals("classica") || g.equals("clássica"))) {

            throw new IllegalArgumentException("Gênero inválido");
        }

        this.genero = genero.trim();
    }

    public void exibir() {
        System.out.println(titulo + " - " + artista + " - " + duracao + "s - " + genero);
    }
}