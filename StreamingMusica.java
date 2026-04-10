import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Musica> musicas = new ArrayList<>();
    static Usuario usuario = new Usuario("Matheus");

    public static void main(String[] args) {

        int op;

        do {
            System.out.println("\n1. Cadastrar música");
            System.out.println("2. Listar todas as músicas");
            System.out.println("3. Buscar música");
            System.out.println("4. Criar playlist");
            System.out.println("5. Gerenciar playlists");
            System.out.println("6. Exibir estatísticas");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1: cadastrar(); break;
                case 2: listar(); break;
                case 3: buscar(); break;
                case 4: criarPlaylist(); break;
                case 5: gerenciar(); break;
                case 6: estatisticas(); break;
            }

        } while (op != 0);
    }

    // cadastrar musica
    static void cadastrar() {
        try {
            System.out.print("Titulo: ");
            String titulo = sc.nextLine();

            System.out.print("Artista: ");
            String artista = sc.nextLine();

            System.out.print("Duracao: ");
            int duracao = Integer.parseInt(sc.nextLine());

            System.out.print("Genero: ");
            String genero = sc.nextLine();

            Musica m = new Musica(titulo, artista, duracao, genero);
            musicas.add(m);

            System.out.println("Música cadastrada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // listar musicas
    static void listar() {
        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }

        for (int i = 0; i < musicas.size(); i++) {
            System.out.print((i + 1) + " - ");
            musicas.get(i).exibir();
        }
    }

    // buscar musica
    static void buscar() {
        System.out.print("Digite algo: ");
        String busca = sc.nextLine().toLowerCase();

        boolean achou = false;

        for (Musica m : musicas) {
            if (m.getTitulo().toLowerCase().contains(busca) ||
                m.getArtista().toLowerCase().contains(busca) ||
                m.getGenero().toLowerCase().contains(busca)) {

                m.exibir();
                achou = true;
            }
        }

        if (!achou) {
            System.out.println("Nada encontrado.");
        }
    }

    // criar playlist
    static void criarPlaylist() {
        try {
            System.out.print("Nome da playlist: ");
            String nome = sc.nextLine();

            Playlist p = new Playlist(nome);
            usuario.adicionarPlaylist(p);

            System.out.println("Playlist criada!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // gerenciar playlists
    static void gerenciar() {
        int op;

        do {
            System.out.println("\n=== GERENCIAR PLAYLISTS ===");
            System.out.println("1. Listar minhas playlists");
            System.out.println("2. Adicionar música a uma playlist");
            System.out.println("3. Remover música de uma playlist");
            System.out.println("4. Exibir detalhes de uma playlist");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1: listarPlaylists(); break;
                case 2: addMusicaPlaylist(); break;
                case 3: removerMusicaPlaylist(); break;
                case 4: verPlaylist(); break;
            }

        } while (op != 0);
    }

    static void listarPlaylists() {
        if (usuario.getPlaylists().isEmpty()) {
            System.out.println("Nenhuma playlist.");
            return;
        }

        for (int i = 0; i < usuario.getPlaylists().size(); i++) {
            System.out.println((i + 1) + " - " + usuario.getPlaylists().get(i).getNome());
        }
    }

    static void addMusicaPlaylist() {
        if (usuario.getPlaylists().isEmpty()) {
            System.out.println("Crie uma playlist primeiro.");
            return;
        }

        if (musicas.isEmpty()) {
            System.out.println("Cadastre músicas primeiro.");
            return;
        }

        listar();

        System.out.print("Escolha musica: ");
        int m = Integer.parseInt(sc.nextLine()) - 1;

        listarPlaylists();

        System.out.print("Escolha playlist: ");
        int p = Integer.parseInt(sc.nextLine()) - 1;

        try {
            Playlist pl = usuario.getPlaylists().get(p);
            pl.adicionarMusica(musicas.get(m));
            System.out.println("Música adicionada!");

        } catch (Exception e) {
            System.out.println("Erro: índice inválido.");
        }
    }

    static void removerMusicaPlaylist() {
        listarPlaylists();

        System.out.print("Escolha playlist: ");
        int p = Integer.parseInt(sc.nextLine()) - 1;

        try {
            Playlist pl = usuario.getPlaylists().get(p);

            if (pl.getMusicas().isEmpty()) {
                System.out.println("Playlist vazia.");
                return;
            }

            for (int i = 0; i < pl.getMusicas().size(); i++) {
                System.out.println((i + 1) + " - " + pl.getMusicas().get(i).getTitulo());
            }

            System.out.print("Escolha musica: ");
            int m = Integer.parseInt(sc.nextLine()) - 1;

            pl.removerMusica(m);
            System.out.println("Música removida!");

        } catch (Exception e) {
            System.out.println("Erro: índice inválido.");
        }
    }

    static void verPlaylist() {
        listarPlaylists();

        System.out.print("Escolha playlist: ");
        int p = Integer.parseInt(sc.nextLine()) - 1;

        try {
            Playlist pl = usuario.getPlaylists().get(p);

            if (pl.getMusicas().isEmpty()) {
                System.out.println("Playlist vazia.");
                return;
            }

            for (Musica m : pl.getMusicas()) {
                m.exibir();
            }

        } catch (Exception e) {
            System.out.println("Erro: índice inválido.");
        }
    }

    // estatisticas
    static void estatisticas() {
        if (musicas.isEmpty()) {
            System.out.println("Sem músicas.");
            return;
        }

        int total = musicas.size();
        int soma = 0;

        for (Musica m : musicas) {
            soma += m.getDuracao();
        }

        int media = soma / total;

        System.out.println("Total de músicas: " + total);
        System.out.println("Duração média: " + media + " segundos");
    }
}