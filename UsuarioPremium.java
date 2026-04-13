import java.util.ArrayList;

public class UsuarioPremium extends Usuario {

    private String plano;
    private ArrayList<Musica> musicasBaixadas;

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        this.plano = plano;
        this.musicasBaixadas = new ArrayList<>();
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        System.out.println("🎵 ALTA QUALIDADE: " + musica.getTitulo());
        historicoReproducao.add(musica);
    }

    public void baixarMusica(Musica m) {
        if (!musicasBaixadas.contains(m)) {
            musicasBaixadas.add(m);
            System.out.println("⬇️ Música baixada!");
        }
    }

    public void listarDownloads() {
        if (musicasBaixadas.isEmpty()) {
            System.out.println("Nenhuma música baixada.");
            return;
        }

        for (Musica m : musicasBaixadas) {
            m.exibir();
        }
    }
}