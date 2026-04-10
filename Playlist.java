import java.util.ArrayList;

public class Playlist {
    private String nome;
    private ArrayList<Musica> musicas;

    public Playlist() {
        this.musicas = new ArrayList<>();
    }

    public Playlist(String nome) {
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

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void adicionarMusica(Musica m) {
        if (m == null) {
            throw new IllegalArgumentException("Música não pode ser null");
        }
        musicas.add(m);
    }

    public void removerMusica(int i) {
        if (i < 0 || i >= musicas.size()) {
            System.out.println("Índice inválido");
            return;
        }
        musicas.remove(i);
    }
}