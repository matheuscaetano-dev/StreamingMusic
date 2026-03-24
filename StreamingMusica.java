import java.util.ArrayList;
import java.util.Scanner;

// MAIN
public class StreamingMusica {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Musica> musicas = new ArrayList<>();
    static Usuario usuario = new Usuario();

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
        Musica m = new Musica();

        System.out.print("Titulo: ");
        m.titulo = sc.nextLine();

        System.out.print("Artista: ");
        m.artista = sc.nextLine();

        System.out.print("Duracao: ");
        m.duracao = Integer.parseInt(sc.nextLine());

        System.out.print("Genero: ");
        m.genero = sc.nextLine();

        musicas.add(m);
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

    // buscar musica (simples)
    static void buscar() {
        System.out.print("Digite algo: ");
        String busca = sc.nextLine().toLowerCase();

        boolean achou = false;

        for (Musica m : musicas) {
            if (m.titulo.toLowerCase().contains(busca) ||
                m.artista.toLowerCase().contains(busca) ||
                m.genero.toLowerCase().contains(busca)) {

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
        Playlist p = new Playlist();

        System.out.print("Nome da playlist: ");
        p.nome = sc.nextLine();

        usuario.playlists.add(p);
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
        if (usuario.playlists.isEmpty()) {
            System.out.println("Nenhuma playlist.");
            return;
        }

        for (int i = 0; i < usuario.playlists.size(); i++) {
            System.out.println((i + 1) + " - " + usuario.playlists.get(i).nome);
        }
    }

    static void addMusicaPlaylist() {
        if (usuario.playlists.isEmpty()) {
            System.out.println("Crie uma playlist primeiro.");
            return;
        }

        listar();

        System.out.print("Escolha musica: ");
        int m = Integer.parseInt(sc.nextLine()) - 1;

        listarPlaylists();

        System.out.print("Escolha playlist: ");
        int p = Integer.parseInt(sc.nextLine()) - 1;

        usuario.playlists.get(p).adicionarMusica(musicas.get(m));
    }

    static void removerMusicaPlaylist() {
        listarPlaylists();

        System.out.print("Escolha playlist: ");
        int p = Integer.parseInt(sc.nextLine()) - 1;

        Playlist pl = usuario.playlists.get(p);

        for (int i = 0; i < pl.musicas.size(); i++) {
            System.out.println((i + 1) + " - " + pl.musicas.get(i).titulo);
        }

        System.out.print("Escolha musica: ");
        int m = Integer.parseInt(sc.nextLine()) - 1;

        pl.removerMusica(m);
    }

    static void verPlaylist() {
        listarPlaylists();

        System.out.print("Escolha playlist: ");
        int p = Integer.parseInt(sc.nextLine()) - 1;

        Playlist pl = usuario.playlists.get(p);

        if (pl.musicas.isEmpty()) {
            System.out.println("Playlist vazia.");
            return;
        }

        for (Musica m : pl.musicas) {
            m.exibir();
        }
    }

    // estatisticas simples
    static void estatisticas() {
        if (musicas.isEmpty()) {
            System.out.println("Sem músicas.");
            return;
        }

        int total = musicas.size();
        int soma = 0;

        for (Musica m : musicas) {
            soma += m.duracao;
        }

        int media = soma / total;

        System.out.println("Total de músicas: " + total);
        System.out.println("Duração média: " + media + " segundos");
    }
}