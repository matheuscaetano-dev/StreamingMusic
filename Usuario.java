import java.util.ArrayList;

public class Usuario {
    protected String nome;
    protected String email;
    protected ArrayList<Playlist> playlists;
    protected ArrayList<Musica> historicoReproducao;

    public Usuario(String nome, String email) {
        setNome(nome);
        setEmail(email);
        this.playlists = new ArrayList<>();
        this.historicoReproducao = new ArrayList<>();
    }

    public void reproduzirMusica(Musica musica) {
        if (musica == null) {
            System.out.println("Música inválida.");
            return;
        }

        System.out.println("🎵 Reproduzindo: " + musica.getTitulo());
        historicoReproducao.add(musica);
    }

    public void exibirHistorico() {
        System.out.println("\n--- HISTÓRICO ---");

        if (historicoReproducao.isEmpty()) {
            System.out.println("Nenhuma música reproduzida.");
            return;
        }

        for (Musica m : historicoReproducao) {
            m.exibir();
        }
    }

    // 🎼 Criar playlist (método original)
    public void criarPlaylist(String nome) {
<<<<<<< HEAD
        if (nome == null || nome.isBlank()) {
            System.out.println("Nome inválido.");
            return;
        }

        playlists.add(new Playlist(nome));
        System.out.println("✅ Playlist criada!");
    }

    // 🔥 SOBRECARGA (overload)
    public void criarPlaylist(String nome, int quantidadeInicial) {
        if (nome == null || nome.isBlank()) {
            System.out.println("Nome inválido.");
            return;
        }

        if (quantidadeInicial < 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        Playlist p = new Playlist(nome);

        for (int i = 0; i < quantidadeInicial; i++) {
        }

        playlists.add(p);
        System.out.println("✅ Playlist criada com configuração inicial!");
=======
        playlists.add(new Playlist(nome)); // 👈 agora é só Playlist
        System.out.println("Playlist criada!");
    }

    public final void validarEmail(String email) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
>>>>>>> e3bd93d (implementa polimorfismo completo e sistema multi-usuário no streaming)
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome inválido");
        this.nome = nome.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email inválido");
        this.email = email.trim();
    }
}