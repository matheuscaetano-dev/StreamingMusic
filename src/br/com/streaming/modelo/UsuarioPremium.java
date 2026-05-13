package br.com.streaming.modelo;

import br.com.streaming.servico.Baixavel;
import br.com.streaming.util.Validador;
import java.util.ArrayList;
import java.util.List;

public class UsuarioPremium extends Usuario implements Baixavel {
    private final String plano;
    private final List<Musica> baixadas;

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        this.plano = Validador.validarTexto(plano, "Plano");
        this.baixadas = new ArrayList<>();
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        super.reproduzirMusica(musica);
        System.out.println("🌟 Qualidade Premium: " + musica.getNome());
    }

    @Override
    public void baixar(Musica musica) {
        Validador.validarObjeto(musica, "Música");
        if (estaBaixada(musica)) {
            System.out.println("Esta música já está baixada: " + musica.getNome());
            return;
        }
        baixadas.add(musica);
        System.out.println("⬇️ Música baixada: " + musica.getNome());
    }

    @Override
    public void removerDownload(Musica musica) {
        if (baixadas.remove(musica)) {
            System.out.println("🗑️ Download removido: " + musica.getNome());
        } else {
            System.out.println("Não há download dessa música: " + musica.getNome());
        }
    }

    @Override
    public boolean estaBaixada(Musica musica) {
        return baixadas.contains(musica);
    }

    @Override
    public int getTamanhoBaixados() {
        return baixadas.size();
    }

    public List<Musica> getBaixadas() {
        return List.copyOf(baixadas);
    }

    public String getPlano() {
        return plano;
    }
}
