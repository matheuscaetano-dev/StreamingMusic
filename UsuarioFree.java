public class UsuarioFree extends Usuario {

    private static final int MAX_PLAYLISTS = 3;
    private int contador;

    public UsuarioFree(String nome, String email) {
        super(nome, email);
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        contador++;

        if (contador % 3 == 0) {
            System.out.println("📢 ANÚNCIO: Assine Premium!");
        }

        super.reproduzirMusica(musica);
    }

    @Override
    public void criarPlaylist(String nome) {
        if (playlists.size() >= MAX_PLAYLISTS) {
            System.out.println("❌ Limite de playlists atingido!");
            return;
        }
        super.criarPlaylist(nome);
    }
}
