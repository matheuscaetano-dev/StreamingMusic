import java.util.ArrayList;

public class UsuarioPremium extends Usuario {

    private String plano;
    private ArrayList<Musica> musicasBaixadas;

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        this.plano = plano;
<<<<<<< HEAD
        this.musicasBaixadas = new ArrayList<>();
=======
        this.baixadas = new ArrayList<>();
>>>>>>> e3bd93d (implementa polimorfismo completo e sistema multi-usuário no streaming)
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        System.out.println("🎵 ALTA QUALIDADE: " + musica.getTitulo());
        historicoReproducao.add(musica);
    }

    public void baixarMusica(Musica m) {
<<<<<<< HEAD
        if (!musicasBaixadas.contains(m)) {
            musicasBaixadas.add(m);
            System.out.println("⬇️ Música baixada!");
        }
=======
        baixadas.add(m);
        System.out.println("⬇️ Música baixada!");
>>>>>>> e3bd93d (implementa polimorfismo completo e sistema multi-usuário no streaming)
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