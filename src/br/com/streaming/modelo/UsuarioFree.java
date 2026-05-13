package br.com.streaming.modelo;

public class UsuarioFree extends Usuario {
    private int contadorAnuncios;

    public UsuarioFree(String nome, String email) {
        super(nome, email);
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        super.reproduzirMusica(musica);
        contadorAnuncios++;
        if (contadorAnuncios % 3 == 0) {
            System.out.println("📢 ANÚNCIO: Assine o plano Premium para ouvir sem interrupções!");
        }
    }

    public int getContadorAnuncios() {
        return contadorAnuncios;
    }
}
