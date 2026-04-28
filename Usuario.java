import java.util.ArrayList;

public class Usuario {

    protected String nome;
    protected String email;
    protected ArrayList<Playlist> playlists;
    protected int totalReproducoes;

    public Usuario(String nome, String email) {
        validarEmail(email);
        this.nome = nome;
        this.email = email;
        playlists = new ArrayList<>();
    }

    public void reproduzirMusica(Musica m) {
        m.reproduzir();
        totalReproducoes++;
        System.out.println("🎵 " + m.getTitulo());
    }

    public void criarPlaylist(String nome) {
        playlists.add(new PlaylistPersonalizada(nome));
    }

    public final void validarEmail(String email) {
        if (!email.contains("@")) throw new IllegalArgumentException();
    }

    public String getNome() { return nome; }
    public int getTotalReproducoes() { return totalReproducoes; }
}