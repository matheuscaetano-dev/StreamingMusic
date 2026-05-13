package br.com.streaming.modelo;

import br.com.streaming.servico.Reproduzivel;

public abstract class ItemReproducao implements Reproduzivel {
    protected String nome;
    protected int totalReproducoes;

    protected ItemReproducao(String nome) {
        setNome(nome);
    }

    protected void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome inválido");
        }
        this.nome = nome.trim();
    }

    public String getNome() {
        return nome;
    }

    public int getTotalReproducoes() {
        return totalReproducoes;
    }

    @Override
    public void reproduzir() {
        totalReproducoes++;
    }

    @Override
    public void pausar() {
        System.out.println("⏸ Pausado: " + nome);
    }

    @Override
    public void parar() {
        System.out.println("⏹ Parado: " + nome);
    }

    @Override
    public abstract int getDuracaoTotal();
}
