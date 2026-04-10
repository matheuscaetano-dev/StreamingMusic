import java.util.ArrayList;

public class Usuario {
    private String nome;
    private ArrayList<Playlist> playlists;

    // Construtor padrão
    public Usuario() {
        this.playlists = new ArrayList<>();
    }

    // Construtor parametrizado
    public Usuario(String nome) {
        this();
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }
        this.nome = nome.trim();
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void adicionarPlaylist(Playlist p) {
        if (p == null) {
            throw new IllegalArgumentException("Playlist não pode ser null");
        }
        playlists.add(p);
    }
}