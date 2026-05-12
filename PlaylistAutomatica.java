import java.util.ArrayList;

public class PlaylistAutomatica extends Playlist {

    private String criterio;

    public PlaylistAutomatica(String nome, String criterio) {
        super(nome);
        this.criterio = criterio;
    }

    @Override
    public void reproduzir() {
        System.out.println("🤖 Playlist Automática: " + nome + " (" + criterio + ")");
        super.reproduzir();
    }

    public void atualizar(ArrayList<Musica> todas) {
        musicas.clear();

        for (Musica m : todas) {
            if (criterio.equalsIgnoreCase(m.getGenero())) {
                musicas.add(m);
            }
        }
    }
}