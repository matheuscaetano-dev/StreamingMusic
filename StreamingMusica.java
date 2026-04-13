import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Musica> musicas = new ArrayList<>();
    static Usuario usuario;

    public static void main(String[] args) {

        System.out.println("=== BEM-VINDO AO STREAMING ===");

        System.out.print("Digite seu nome: ");
        String nome = sc.nextLine();

        System.out.print("Digite seu email: ");
        String email = sc.nextLine();

        System.out.println("\nEscolha o tipo de conta:");
        System.out.println("1. Free (Gratuito)");
        System.out.println("2. Premium (Pago)");
        System.out.print("Escolha: ");
        int tipo = Integer.parseInt(sc.nextLine());

        if (tipo == 1) {
            usuario = new UsuarioFree(nome, email);
            System.out.println("Conta Free criada!");
        } else {
            usuario = criarPremium(nome, email);
        }

        musicas.add(new Musica("Shape of You", "Ed Sheeran", 240, "pop"));
        musicas.add(new Musica("Bohemian Rhapsody", "Queen", 354, "rock"));
        musicas.add(new Musica("Billie Jean", "Michael Jackson", 294, "pop"));

        int op;

        do {
            if (usuario instanceof UsuarioFree) {

                System.out.println("\n1. Reproduzir música");
                System.out.println("2. Ver histórico");
                System.out.println("3. Criar playlist (máx. 3)");
                System.out.println("4. Fazer upgrade para Premium");
                System.out.println("0. Sair");

            } else {

                System.out.println("\n1. Reproduzir música (Alta Qualidade)");
                System.out.println("2. Ver histórico");
                System.out.println("3. Criar playlist (ilimitado)");
                System.out.println("4. Baixar música");
                System.out.println("5. Ver músicas baixadas");
                System.out.println("0. Sair");
            }

            System.out.print("Escolha: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1:
                    reproduzir();
                    break;

                case 2:
                    usuario.exibirHistorico();
                    break;

                case 3:
                    criarPlaylist();
                    break;

                case 4:
                    if (usuario instanceof UsuarioFree) {
                        usuario = upgradeParaPremium();
                    } else if (usuario instanceof UsuarioPremium up) {
                        baixar(up);
                    }
                    break;

                case 5:
                    if (usuario instanceof UsuarioPremium up) {
                        up.listarDownloads();
                    }
                    break;
            }

        } while (op != 0);
    }

    // 🔥 CRIAR PREMIUM
    static UsuarioPremium criarPremium(String nome, String email) {

        System.out.println("\nEscolha o plano Premium:");
        System.out.println("1. Mensal (R$ 19,90)");
        System.out.println("2. Anual (R$ 199,00)");
        System.out.println("3. Familiar (R$ 29,90)");
        System.out.print("Escolha: ");

        int p = Integer.parseInt(sc.nextLine());

        String plano;
        String valor;

        switch (p) {
            case 1:
                plano = "Mensal";
                valor = "R$ 19,90";
                break;
            case 2:
                plano = "Anual";
                valor = "R$ 199,00";
                break;
            case 3:
                plano = "Familiar";
                valor = "R$ 29,90";
                break;
            default:
                plano = "Mensal";
                valor = "R$ 19,90";
        }

        System.out.println("Conta Premium (" + plano + " - " + valor + ") criada!");
        return new UsuarioPremium(nome, email, plano);
    }

    static Usuario upgradeParaPremium() {
        System.out.println("\nFazendo upgrade para Premium...");

        UsuarioPremium novo = criarPremium(usuario.nome, usuario.email);

        // mantém playlists antigas
        novo.playlists = usuario.playlists;

        System.out.println("Upgrade concluído!");
        return novo;
    }

    static void reproduzir() {
        listar();

        System.out.print("Escolha a música: ");
        int i = Integer.parseInt(sc.nextLine()) - 1;

        try {
            usuario.reproduzirMusica(musicas.get(i));
        } catch (Exception e) {
            System.out.println("Erro!");
        }
    }

    static void listar() {
        for (int i = 0; i < musicas.size(); i++) {
            System.out.print((i + 1) + " - ");
            musicas.get(i).exibir();
        }
    }

    static void criarPlaylist() {
        System.out.print("Nome da playlist: ");
        String nome = sc.nextLine();
        usuario.criarPlaylist(nome);
    }

    static void baixar(UsuarioPremium up) {
        listar();

        System.out.print("Escolha a música: ");
        int i = Integer.parseInt(sc.nextLine()) - 1;

        try {
            up.baixarMusica(musicas.get(i));
        } catch (Exception e) {
            System.out.println("Erro!");
        }
    }
}