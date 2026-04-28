import java.util.ArrayList;

public class UsuarioPremium extends Usuario {

    private String plano;
    private ArrayList<Musica> baixadas;

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        this.plano = plano;
        baixadas = new ArrayList<>();
    }

    @Override
    public void reproduzirMusica(Musica m) {
        m.reproduzir();
        totalReproducoes++;
        System.out.println("🎵 ALTA QUALIDADE: " + m.getTitulo());
    }

    public void baixar(Musica m) {
        baixadas.add(m);
    }

    public String getPlano() { return plano; }
    public ArrayList<Musica> getBaixadas() { return baixadas; }
}