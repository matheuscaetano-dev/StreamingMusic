import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static Usuario usuarioLogado;

    static ArrayList<Musica> musicas = new ArrayList<>();

    public static void main(String[] args) {

        musicas.add(new Musica("Shape of You", "Ed Sheeran", 200, "pop"));
        musicas.add(new Musica("Bohemian Rhapsody", "Queen", 300, "rock"));
        musicas.add(new Musica("Billie Jean", "MJ", 250, "pop"));

        int op;

        do {
            System.out.println("\n1 Criar usuário");
            System.out.println("2 Login");
            System.out.println("3 Listar usuários");
            System.out.println("4 Estatísticas");
            System.out.println("0 Sair");

            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> criarUsuario();
                case 2 -> login();
                case 3 -> listarUsuarios();
                case 4 -> estatisticas();
            }

        } while (op != 0);
    }

    static void criarUsuario() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.println("1 Free | 2 Premium");
        int tipo = Integer.parseInt(sc.nextLine());

        if (tipo == 1) {
            usuarios.add(new UsuarioFree(nome, email));
        } else {
            System.out.print("Plano: ");
            String plano = sc.nextLine();
            usuarios.add(new UsuarioPremium(nome, email, plano));
        }
    }

    static void login() {
        listarUsuarios();
        int i = Integer.parseInt(sc.nextLine());
        usuarioLogado = usuarios.get(i - 1);

        menuUsuario();
    }

    static void menuUsuario() {
        int op;

        do {
            System.out.println("\n1 Reproduzir música");
            System.out.println("2 Criar playlist");
            System.out.println("3 Playlist automática");
            System.out.println("4 Ver detalhes");
            System.out.println("0 Sair");

            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> {
                    Musica m = musicas.get(0);
                    usuarioLogado.reproduzirMusica(m);
                }
                case 2 -> {
                    usuarioLogado.criarPlaylist("Minha Playlist");
                }
                case 3 -> {
                    PlaylistAutomatica p = new PlaylistAutomatica("Pop", "pop");
                    p.atualizar(musicas);
                    p.reproduzir();
                }
                case 4 -> detalhes(usuarioLogado);
            }

        } while (op != 0);
    }

    static void listarUsuarios() {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);

            if (u instanceof UsuarioPremium) {
                System.out.println((i + 1) + " " + u.getNome() + " Premium");
            } else {
                System.out.println((i + 1) + " " + u.getNome() + " Free");
            }
        }
    }

    static void detalhes(Usuario u) {
        System.out.println("Nome: " + u.getNome());

        if (u instanceof UsuarioPremium p) {
            System.out.println("Plano: " + p.getPlano());
            System.out.println("Downloads: " + p.getBaixadas().size());
        } else {
            System.out.println("Conta Free");
        }
    }

    static void estatisticas() {
        int free = 0, premium = 0;

        for (Usuario u : usuarios) {
            if (u instanceof UsuarioPremium) premium++;
            else free++;
        }

        System.out.println("Total: " + usuarios.size());
        System.out.println("Free: " + free);
        System.out.println("Premium: " + premium);
    }
}