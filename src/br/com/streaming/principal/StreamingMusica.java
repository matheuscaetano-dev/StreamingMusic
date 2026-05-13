package br.com.streaming.principal;

import br.com.streaming.modelo.Musica;
import br.com.streaming.modelo.Playlist;
import br.com.streaming.modelo.Usuario;
import br.com.streaming.modelo.UsuarioFree;
import br.com.streaming.modelo.UsuarioPremium;
import br.com.streaming.servico.GeradorRecomendacoes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StreamingMusica {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final List<Usuario> USUARIOS = new ArrayList<>();
    private static final List<Musica> MUSICAS = new ArrayList<>();
    private static final GeradorRecomendacoes RECOMENDADOR = new GeradorRecomendacoes();
    private static Usuario usuarioLogado;

    public static void main(String[] args) {
        popularCatalogo();
        exibirMenuPrincipal();
    }

    private static void popularCatalogo() {
        MUSICAS.add(new Musica("Shape of You", "Ed Sheeran", 200, "pop"));
        MUSICAS.add(new Musica("Bohemian Rhapsody", "Queen", 300, "rock"));
        MUSICAS.add(new Musica("Billie Jean", "Michael Jackson", 250, "pop"));
        MUSICAS.add(new Musica("Imagine", "John Lennon", 210, "rock"));
        MUSICAS.add(new Musica("Summertime", "Ella Fitzgerald", 180, "jazz"));
    }

    private static void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("\n=== SISTEMA DE STREAMING ===");
            System.out.println("1. Criar novo usuário");
            System.out.println("2. Login");
            System.out.println("3. Listar usuários");
            System.out.println("4. Relatório de músicas");
            System.out.println("5. Recomendações por gênero");
            System.out.println("6. Playlists automáticas");
            System.out.println("7. Estatísticas por Tipo de Usuário");
            System.out.println("0. Sair");
            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> criarUsuario();
                case 2 -> login();
                case 3 -> listarUsuarios();
                case 4 -> relatorioMusicas();
                case 5 -> recomendarPorGenero();
                case 6 -> abrirMenuPlaylistsAutomaticas();
                case 7 -> verEstatisticasSistema();
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void criarUsuario() {
        System.out.println("=== CRIAR NOVO USUÁRIO ===");
        System.out.print("Digite seu nome: ");
        String nome = SCANNER.nextLine();
        System.out.print("Digite seu email: ");
        String email = SCANNER.nextLine();

        System.out.println("\nEscolha o tipo de conta:");
        System.out.println("1. Free (Gratuito)");
        System.out.println("2. Premium (Pago)");
        System.out.print("Escolha: ");
        int tipo = lerInteiro();

        if (tipo == 1) {
            USUARIOS.add(new UsuarioFree(nome, email));
            System.out.println("✅ Conta Free criada com sucesso!");
        } else if (tipo == 2) {
            System.out.println("\nEscolha o plano Premium:");
            System.out.println("1. Mensal (R$ 19,90)");
            System.out.println("2. Anual (R$ 199,00)");
            System.out.println("3. Familiar (R$ 29,90)");
            System.out.print("Escolha: ");
            int plano = lerInteiro();
            String planoNome = switch (plano) {
                case 1 -> "Mensal";
                case 2 -> "Anual";
                case 3 -> "Familiar";
                default -> "Mensal";
            };
            USUARIOS.add(new UsuarioPremium(nome, email, planoNome));
            System.out.println("✅ Conta Premium criada com sucesso!");
        } else {
            System.out.println("Opção inválida. Criando conta Free por padrão.");
            USUARIOS.add(new UsuarioFree(nome, email));
        }
    }

    private static void login() {
        if (USUARIOS.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        System.out.println("\nUsuários cadastrados:");
        listarUsuarios();
        System.out.print("Escolha o usuário: ");
        int indice = lerInteiro() - 1;

        if (indice < 0 || indice >= USUARIOS.size()) {
            System.out.println("Usuário inválido.");
            return;
        }

        usuarioLogado = USUARIOS.get(indice);
        String tipo = usuarioLogado instanceof UsuarioPremium ? "Premium" : "Free";
        System.out.println("✅ Login realizado: " + usuarioLogado.getNome() + " (" + tipo + ")");
        exibirMenuUsuario();
    }

    private static void exibirMenuUsuario() {
        int opcao;
        do {
            if (usuarioLogado instanceof UsuarioFree) {
                System.out.println("\n=== MENU FREE ===");
                System.out.println("1. Reproduzir música");
                System.out.println("2. Ver histórico");
                System.out.println("3. Criar playlist (máx. 3)");
                System.out.println("4. 💎 Fazer upgrade para Premium");
                System.out.println("0. Sair");
            } else if (usuarioLogado instanceof UsuarioPremium) {
                System.out.println("\n=== MENU PREMIUM ===");
                System.out.println("1. Reproduzir música (Alta Qualidade)");
                System.out.println("2. Ver histórico");
                System.out.println("3. Criar playlist (ilimitado)");
                System.out.println("4. Baixar música");
                System.out.println("5. Ver músicas baixadas");
                System.out.println("0. Sair");
            }
            opcao = lerInteiro();

            if (usuarioLogado instanceof UsuarioFree) {
                switch (opcao) {
                    case 1 -> reproduzirMusica();
                    case 2 -> verHistorico();
                    case 3 -> criarPlaylist();
                    case 4 -> fazerUpgradeParaPremium();
                    case 0 -> {
                        System.out.println("Saindo da conta...");
                        usuarioLogado = null;
                    }
                    default -> System.out.println("Opção inválida.");
                }
            } else if (usuarioLogado instanceof UsuarioPremium) {
                switch (opcao) {
                    case 1 -> reproduzirMusicaAltaQualidade();
                    case 2 -> verHistorico();
                    case 3 -> criarPlaylist();
                    case 4 -> baixarMusica();
                    case 5 -> verMusicasBaixadas();
                    case 0 -> {
                        System.out.println("Saindo da conta...");
                        usuarioLogado = null;
                    }
                    default -> System.out.println("Opção inválida.");
                }
            }
        } while (opcao != 0);
    }

    private static void abrirMenuPlaylistsAutomaticas() {
        System.out.println("\n=== PLAYLISTS AUTOMÁTICAS ===");
        System.out.println("1. Top 10 Mais Tocadas");
        System.out.println("2. Recomendadas para Você");
        System.out.println("3. Adicionadas Recentemente");
        System.out.println("0. Voltar");
        int escolha = lerInteiro();

        switch (escolha) {
            case 1 -> criarPlaylistAutomaticaTop10();
            case 2 -> criarPlaylistAutomaticaRecomendadas();
            case 3 -> criarPlaylistAutomaticaRecentes();
            case 0 -> System.out.println("Voltando ao menu principal.");
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void verHistorico() {
        System.out.println("\n--- Histórico de Reprodução ---");
        if (usuarioLogado.getHistorico().isEmpty()) {
            System.out.println("Nenhuma música reproduzida ainda.");
        } else {
            for (int i = 0; i < usuarioLogado.getHistorico().size(); i++) {
                Musica musica = usuarioLogado.getHistorico().get(i);
                System.out.println((i + 1) + ". " + musica.getNome() + " - " + musica.getArtista());
            }
        }
    }

    private static void criarPlaylist() {
        if (usuarioLogado instanceof UsuarioFree && usuarioLogado.getPlaylists().size() >= 3) {
            System.out.println("Usuários Free podem criar no máximo 3 playlists.");
            return;
        }
        System.out.print("Nome da playlist: ");
        String nome = SCANNER.nextLine();
        usuarioLogado.criarPlaylist(nome);
    }

    private static void fazerUpgradeParaPremium() {
        if (!(usuarioLogado instanceof UsuarioFree)) {
            System.out.println("Você já é um usuário Premium.");
            return;
        }

        System.out.println("\nEscolha o plano Premium:");
        System.out.println("1. Mensal (R$ 19,90)");
        System.out.println("2. Anual (R$ 199,00)");
        System.out.println("3. Familiar (R$ 29,90)");
        System.out.print("Escolha: ");
        int plano = lerInteiro();
        String planoNome = switch (plano) {
            case 1 -> "Mensal";
            case 2 -> "Anual";
            case 3 -> "Familiar";
            default -> "Mensal";
        };

        // Criar novo UsuarioPremium com os dados do Free
        UsuarioPremium novoPremium = new UsuarioPremium(usuarioLogado.getNome(), usuarioLogado.getEmail(), planoNome);
        // Copiar histórico e playlists
        novoPremium.getHistorico().addAll(usuarioLogado.getHistorico());
        novoPremium.getPlaylists().addAll(usuarioLogado.getPlaylists());
        novoPremium.setTotalReproducoes(usuarioLogado.getTotalReproducoes());

        // Substituir na lista
        int index = USUARIOS.indexOf(usuarioLogado);
        USUARIOS.set(index, novoPremium);
        usuarioLogado = novoPremium;

        System.out.println("✅ Upgrade para Premium realizado com sucesso!");
    }

    private static void reproduzirMusica() {
        exibirCatalogo();
        System.out.print("Escolha a música: ");
        int indice = lerInteiro() - 1;
        if (indice < 0 || indice >= MUSICAS.size()) {
            System.out.println("Seleção inválida.");
            return;
        }
        usuarioLogado.reproduzirMusica(MUSICAS.get(indice));
    }

    private static void reproduzirMusicaAltaQualidade() {
        System.out.println("🎵 Reproduzindo em Alta Qualidade!");
        reproduzirMusica();
    }

    private static void baixarMusica() {
        if (!(usuarioLogado instanceof UsuarioPremium)) {
            System.out.println("Esta funcionalidade é apenas para usuários Premium.");
            return;
        }
        UsuarioPremium premium = (UsuarioPremium) usuarioLogado;
        exibirCatalogo();
        System.out.print("Escolha a música para baixar: ");
        int indice = lerInteiro() - 1;
        if (indice < 0 || indice >= MUSICAS.size()) {
            System.out.println("Seleção inválida.");
            return;
        }
        premium.baixar(MUSICAS.get(indice));
        System.out.println("✅ Música baixada com sucesso!");
    }

    private static void verMusicasBaixadas() {
        if (!(usuarioLogado instanceof UsuarioPremium)) {
            System.out.println("Esta funcionalidade é apenas para usuários Premium.");
            return;
        }
        UsuarioPremium premium = (UsuarioPremium) usuarioLogado;
        System.out.println("\n--- Músicas Baixadas ---");
        if (premium.getBaixadas().isEmpty()) {
            System.out.println("Nenhuma música baixada ainda.");
        } else {
            for (int i = 0; i < premium.getBaixadas().size(); i++) {
                Musica musica = premium.getBaixadas().get(i);
                System.out.println((i + 1) + ". " + musica.getNome() + " - " + musica.getArtista());
            }
        }
    }

    private static void criarPlaylistAutomaticaTop10() {
        System.out.println("🤖 Gerando playlist \"Top 10 Mais Tocadas\"...");
        Playlist playlist = new Playlist("Top 10 Mais Tocadas");
        RECOMENDADOR.topMaisReproduzidas(MUSICAS, 10).forEach(playlist::adicionarMusica);
        usuarioLogado.adicionarPlaylist(playlist);
        System.out.println("✅ Playlist criada com " + playlist.getQuantidade() + " músicas!");
    }

    private static void criarPlaylistAutomaticaRecomendadas() {
        System.out.println("🤖 Gerando playlist \"Recomendadas para Você\"...");
        Playlist playlist = new Playlist("Recomendadas para Você");

        if (usuarioLogado.getHistorico().isEmpty()) {
            RECOMENDADOR.topMaisReproduzidas(MUSICAS, 10).forEach(playlist::adicionarMusica);
        } else {
            Map<String, Long> generoContagem = new HashMap<>();
            for (Musica musica : usuarioLogado.getHistorico()) {
                generoContagem.merge(musica.getGenero(), 1L, Long::sum);
            }
            String generoPreferido = generoContagem.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse("");
            List<Musica> sugestoes = RECOMENDADOR.recomendarPorGenero(MUSICAS, generoPreferido);
            sugestoes.stream()
                    .filter(musica -> !usuarioLogado.getHistorico().contains(musica))
                    .limit(10)
                    .forEach(playlist::adicionarMusica);
            if (playlist.getQuantidade() == 0) {
                RECOMENDADOR.topMaisReproduzidas(MUSICAS, 10).forEach(playlist::adicionarMusica);
            }
        }

        usuarioLogado.adicionarPlaylist(playlist);
        System.out.println("✅ Playlist criada com " + playlist.getQuantidade() + " músicas!");
    }

    private static void criarPlaylistAutomaticaRecentes() {
        System.out.println("🤖 Gerando playlist \"Adicionadas Recentemente\"...");
        Playlist playlist = new Playlist("Adicionadas Recentemente");
        int quantidade = 0;
        for (int i = MUSICAS.size() - 1; i >= 0 && quantidade < 10; i--) {
            playlist.adicionarMusica(MUSICAS.get(i));
            quantidade++;
        }
        usuarioLogado.adicionarPlaylist(playlist);
        System.out.println("✅ Playlist criada com " + playlist.getQuantidade() + " músicas!");
    }

    private static void verRelatoriosUsuario() {
        System.out.println("\n--- Relatórios de " + usuarioLogado.getNome() + " ---");
        System.out.println("Total de reproduções: " + usuarioLogado.getTotalReproducoes());
        System.out.println("Histórico de reprodução:");
        if (usuarioLogado.getHistorico().isEmpty()) {
            System.out.println("Nenhuma música reproduzida ainda.");
        } else {
            usuarioLogado.getHistorico().forEach(musica -> System.out.println("- " + musica.getNome() + " (" + musica.getGenero() + ")"));
        }
        usuarioLogado.listarPlaylists();
    }

    private static void verEstatisticasSistema() {
        long totalUsuarios = USUARIOS.size();
        long freeUsuarios = USUARIOS.stream().filter(usuario -> usuario instanceof UsuarioFree).count();
        long premiumUsuarios = USUARIOS.stream().filter(usuario -> usuario instanceof UsuarioPremium).count();
        int totalReproducoes = USUARIOS.stream().mapToInt(Usuario::getTotalReproducoes).sum();
        int freeReproducoes = USUARIOS.stream()
                .filter(usuario -> usuario instanceof UsuarioFree)
                .mapToInt(Usuario::getTotalReproducoes)
                .sum();
        int premiumReproducoes = USUARIOS.stream()
                .filter(usuario -> usuario instanceof UsuarioPremium)
                .mapToInt(Usuario::getTotalReproducoes)
                .sum();
        int anunciosExibidos = USUARIOS.stream()
                .filter(usuario -> usuario instanceof UsuarioFree)
                .map(usuario -> ((UsuarioFree) usuario).getContadorAnuncios())
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("\n=== ESTATÍSTICAS DO SISTEMA ===");
        System.out.println("Total de usuários: " + totalUsuarios);
        System.out.println("- Free: " + freeUsuarios + " usuário(s)");
        System.out.println("- Premium: " + premiumUsuarios + " usuário(s)");
        System.out.println();
        System.out.println("Reproduções totais: " + totalReproducoes);
        System.out.println("- Free: " + freeReproducoes + " reproduções (" + porcentagem(freeReproducoes, totalReproducoes) + "%)");
        System.out.println("- Premium: " + premiumReproducoes + " reproduções (" + porcentagem(premiumReproducoes, totalReproducoes) + "%)");
        System.out.println();
        System.out.println("Anúncios exibidos: " + anunciosExibidos);
    }

    private static int porcentagem(int parte, int total) {
        if (total == 0) {
            return 0;
        }
        return Math.round((parte * 100f) / total);
    }

    private static void gerenciarDownloadsPremium(UsuarioPremium premium) {
        int opcao;
        do {
            System.out.println("\n=== DOWNLOADS PREMIUM ===");
            System.out.println("1 - Baixar música");
            System.out.println("2 - Remover download");
            System.out.println("3 - Listar downloads");
            System.out.println("0 - Voltar");
            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> {
                    exibirCatalogo();
                    System.out.print("Escolha a música: ");
                    int indice = lerInteiro() - 1;
                    if (indice < 0 || indice >= MUSICAS.size()) {
                        System.out.println("Música inválida.");
                    } else {
                        premium.baixar(MUSICAS.get(indice));
                    }
                }
                case 2 -> {
                    if (premium.getBaixadas().isEmpty()) {
                        System.out.println("Nenhum download disponível.");
                        break;
                    }
                    for (int i = 0; i < premium.getBaixadas().size(); i++) {
                        System.out.println((i + 1) + " - " + premium.getBaixadas().get(i).getNome());
                    }
                    System.out.print("Escolha o download para remover: ");
                    int indice = lerInteiro() - 1;
                    if (indice < 0 || indice >= premium.getBaixadas().size()) {
                        System.out.println("Seleção inválida.");
                    } else {
                        premium.removerDownload(premium.getBaixadas().get(indice));
                    }
                }
                case 3 -> {
                    if (premium.getBaixadas().isEmpty()) {
                        System.out.println("Nenhum download disponível.");
                    } else {
                        System.out.println("Músicas baixadas:");
                        premium.getBaixadas().forEach(musica -> System.out.println("- " + musica.getNome()));
                    }
                }
                case 0 -> System.out.println("Voltando ao menu do usuário.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void listarUsuarios() {
        if (USUARIOS.isEmpty()) {
            System.out.println("Nenhum usuário registrado.");
            return;
        }
        for (int i = 0; i < USUARIOS.size(); i++) {
            Usuario usuario = USUARIOS.get(i);
            String tipo = usuario instanceof UsuarioPremium ? "Premium" : "Free";
            System.out.println((i + 1) + " - " + usuario.getNome() + " (" + tipo + ")");
        }
    }

    private static void relatorioMusicas() {
        System.out.println("\n--- Relatório de músicas ---");
        exibirCatalogo();
        System.out.println("Top 3 músicas mais reproduzidas:");
        RECOMENDADOR.topMaisReproduzidas(MUSICAS, 3)
                .forEach(musica -> System.out.println("- " + musica.getNome() + " (" + musica.getTotalReproducoes() + " reproduções)"));
    }

    private static void recomendarPorGenero() {
        System.out.print("Digite o gênero para recomendação: ");
        String genero = SCANNER.nextLine();
        List<Musica> recomendadas = RECOMENDADOR.recomendarPorGenero(MUSICAS, genero);
        if (recomendadas.isEmpty()) {
            System.out.println("Nenhuma recomendação encontrada para: " + genero);
            return;
        }
        System.out.println("Recomendações para gênero '" + genero + "':");
        recomendadas.forEach(Musica::exibir);
    }

    private static void exibirCatalogo() {
        System.out.println("\n--- Catálogo de músicas ---");
        for (int i = 0; i < MUSICAS.size(); i++) {
            System.out.print((i + 1) + " - ");
            MUSICAS.get(i).exibir();
        }
    }

    private static int lerInteiro() {
        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
