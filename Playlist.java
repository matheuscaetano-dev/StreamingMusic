import java.util.ArrayList;


// PLAYLIST
class Playlist {
    String nome;
    ArrayList<Musica> musicas = new ArrayList<>();

    void adicionarMusica(Musica m) {
        musicas.add(m);
    }

    void removerMusica(int i) {
        musicas.remove(i);
    }
}