import java.util.ArrayList;
import java.util.Scanner;

// ===================== CLASSE MUSICA =====================
class Musica {
    String titulo;
    String artista;
    int duracao;
    String genero;

    void exibir() {
        System.out.println(this.titulo + " | " + this.artista + " | " 
            + formatarDuracao(this.duracao) + " | " + this.genero);
    }

    String formatarDuracao(int segundos) {
        int minutos = segundos / 60;
        int segs = segundos % 60;
        return minutos + ":" + String.format("%02d", segs);
    }
}

// ===================== CLASSE PLAYLIST =====================
class Playlist {
    String nome;
    ArrayList<Musica> musicas = new ArrayList<>();

    void adicionarMusica(Musica m) {
        musicas.add(m);
    }

    void listarMusicas() {
        if (musicas.isEmpty()) {
            System.out.println("Playlist vazia.");
            return;
        }

        for (Musica m : musicas) {
            m.exibir();
        }
    }
}

// ===================== CLASSE USUARIO =====================
class Usuario {
    String nome;
    ArrayList<Playlist> playlists = new ArrayList<>();

    void adicionarPlaylist(Playlist p) {
        playlists.add(p);
    }

    void listarPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist criada.");
            return;
        }

        for (Playlist p : playlists) {
            System.out.println("\nPlaylist: " + p.nome);
            p.listarMusicas();
        }
    }
}

// ===================== CLASSE PRINCIPAL =====================
public class StreamingMusicaOO {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Musica> musicas = new ArrayList<>();
    static Usuario usuario = new Usuario();

    static final String[] GENEROS = {
        "Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"
    };

    public static void main(String[] args) {

        usuario.nome = "Usuário";

        int opcao;

        do {
            menu();
            opcao = lerOpcao();

            switch (opcao) {
                case 1: cadastrarMusica(); break;
                case 2: listarMusicas(); break;
                case 3: buscarPorTitulo(); break;
                case 4: buscarPorArtista(); break;
                case 5: buscarPorGenero(); break;
                case 6: criarPlaylist(); break;
                case 7: adicionarMusicaPlaylist(); break;
                case 8: usuario.listarPlaylists(); break;
                case 9: exibirEstatisticas(); break;
                case 0: System.out.println("Saindo..."); break;
                default: System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }

    // ===================== MENU =====================
    static void menu() {
        System.out.println("\n===== STREAMING DE MÚSICA =====");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar músicas");
        System.out.println("3. Buscar por título");
        System.out.println("4. Buscar por artista");
        System.out.println("5. Buscar por gênero");
        System.out.println("6. Criar playlist");
        System.out.println("7. Adicionar música à playlist");
        System.out.println("8. Listar playlists");
        System.out.println("9. Estatísticas");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    // ===================== MÚSICAS =====================
    static void cadastrarMusica() {
        Musica m = new Musica();

        System.out.print("Título: ");
        m.titulo = scanner.nextLine();

        System.out.print("Artista: ");
        m.artista = scanner.nextLine();

        System.out.print("Duração (segundos): ");
        m.duracao = Integer.parseInt(scanner.nextLine());

        System.out.println("Escolha o gênero:");
        for (int i = 0; i < GENEROS.length; i++) {
            System.out.println((i + 1) + ". " + GENEROS[i]);
        }

        int g = Integer.parseInt(scanner.nextLine());
        m.genero = GENEROS[g - 1];

        musicas.add(m);

        System.out.println("✅ Música cadastrada!");
    }

    static void listarMusicas() {
        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }

        for (Musica m : musicas) {
            m.exibir();
        }
    }

    static void buscarPorTitulo() {
        System.out.print("Buscar título: ");
        String busca = scanner.nextLine().toLowerCase();

        boolean achou = false;

        for (Musica m : musicas) {
            if (m.titulo.toLowerCase().contains(busca)) {
                m.exibir();
                achou = true;
            }
        }

        if (!achou) System.out.println("Nenhuma encontrada.");
    }

    static void buscarPorArtista() {
        System.out.print("Buscar artista: ");
        String busca = scanner.nextLine().toLowerCase();

        boolean achou = false;

        for (Musica m : musicas) {
            if (m.artista.toLowerCase().contains(busca)) {
                m.exibir();
                achou = true;
            }
        }

        if (!achou) System.out.println("Nenhuma encontrada.");
    }

    static void buscarPorGenero() {
        for (int i = 0; i < GENEROS.length; i++) {
            System.out.println((i + 1) + ". " + GENEROS[i]);
        }

        int g = Integer.parseInt(scanner.nextLine());
        String genero = GENEROS[g - 1];

        boolean achou = false;

        for (Musica m : musicas) {
            if (m.genero.equals(genero)) {
                m.exibir();
                achou = true;
            }
        }

        if (!achou) System.out.println("Nenhuma encontrada.");
    }

    // ===================== PLAYLIST =====================
    static void criarPlaylist() {
        Playlist p = new Playlist();

        System.out.print("Nome da playlist: ");
        p.nome = scanner.nextLine();

        usuario.adicionarPlaylist(p);

        System.out.println("✅ Playlist criada!");
    }

    static void adicionarMusicaPlaylist() {
        if (usuario.playlists.isEmpty()) {
            System.out.println("Crie uma playlist primeiro.");
            return;
        }

        listarMusicas();

        System.out.print("Escolha a música: ");
        int mIndex = Integer.parseInt(scanner.nextLine()) - 1;

        System.out.print("Escolha a playlist: ");
        for (int i = 0; i < usuario.playlists.size(); i++) {
            System.out.println((i + 1) + ". " + usuario.playlists.get(i).nome);
        }

        int pIndex = Integer.parseInt(scanner.nextLine()) - 1;

        usuario.playlists.get(pIndex).adicionarMusica(musicas.get(mIndex));

        System.out.println("✅ Música adicionada à playlist!");
    }

    // ===================== ESTATÍSTICAS =====================
    static void exibirEstatisticas() {
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

        System.out.println("Total: " + total);
        System.out.println("Duração média: " + media + "s");
    }
}