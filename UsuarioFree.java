public class UsuarioFree extends Usuario {

    private int contador;

    public UsuarioFree(String nome, String email) {
        super(nome, email);
    }

    @Override
    public void reproduzirMusica(Musica m) {
        contador++;

        if (contador % 3 == 0) {
            System.out.println("📢 ANÚNCIO: Assine Premium!");
        }

        super.reproduzirMusica(m);
    }
}