public class PlaylistPersonalizada extends Playlist {

    public PlaylistPersonalizada(String nome) {
        super(nome);
    }

    @Override
    public void reproduzir() {
        System.out.println("🎧 Playlist do Usuário: " + nome);
        super.reproduzir();
    }
}