public class Musica {
    private String titulo;
    private String artista;
    private int duracao;
    private String genero;

    public Musica() {}

    public Musica(String titulo, String artista, int duracao, String genero) {
        setTitulo(titulo);
        setArtista(artista);
        setDuracao(duracao);
        setGenero(genero);
    }

    public String getTitulo() {
        return titulo;
    }

<<<<<<< HEAD
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty())
            throw new IllegalArgumentException("Título inválido");
        this.titulo = titulo.trim();
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        if (artista == null || artista.trim().isEmpty())
            throw new IllegalArgumentException("Artista inválido");
        this.artista = artista.trim();
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        if (duracao <= 0 || duracao >= 3600)
            throw new IllegalArgumentException("Duração inválida");
=======
    public void exibir() {
        System.out.println(titulo + " - " + artista + " (" + genero + ") ▶ " + totalReproducoes);
    }

    public String getTitulo() { return titulo; }
    public String getGenero() { return genero; }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) throw new IllegalArgumentException("Título inválido");
        this.titulo = titulo;
    }

    public void setArtista(String artista) {
        if (artista == null || artista.isBlank()) throw new IllegalArgumentException("Artista inválido");
        this.artista = artista;
    }

    public void setDuracao(int duracao) {
        if (duracao <= 0) throw new IllegalArgumentException("Duração inválida");
>>>>>>> e3bd93d (implementa polimorfismo completo e sistema multi-usuário no streaming)
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
<<<<<<< HEAD
        if (genero == null || genero.trim().isEmpty())
            throw new IllegalArgumentException("Gênero inválido");
        this.genero = genero.trim();
    }

    public void exibir() {
        System.out.println(titulo + " - " + artista + " - " + duracao + "s - " + genero);
=======
        if (genero == null || genero.isBlank()) throw new IllegalArgumentException("Gênero inválido");
        this.genero = genero;
>>>>>>> e3bd93d (implementa polimorfismo completo e sistema multi-usuário no streaming)
    }
}