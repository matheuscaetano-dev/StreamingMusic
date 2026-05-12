import java.util.ArrayList;

public class Playlist {

    protected String nome;
    protected ArrayList<Musica> musicas;

    public Playlist(String nome) {
        this.nome = nome;
        this.musicas = new ArrayList<>();
    }

    public void reproduzir() {
        System.out.println("🎵 Playlist: " + nome);
        for (Musica m : musicas) {
            System.out.println("▶ " + m.getTitulo());
        }
    }

    public void adicionarMusica(Musica m) {
        musicas.add(m);
    }
}