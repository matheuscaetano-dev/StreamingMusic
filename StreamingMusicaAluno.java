import java.util.ArrayList;
import java.util.Scanner;

/**
 * Sistema de Streaming de Música - CP1
 * VERSÃO ALUNO 
 * 
 * Complete as partes marcadas com TODO.
 */
public class StreamingMusicaAluno {
    
    // ArrayLists para armazenar os dados das músicas
    static ArrayList<String> titulos = new ArrayList<>();
    static ArrayList<String> artistas = new ArrayList<>();
    static ArrayList<Integer> duracoes = new ArrayList<>();
    static ArrayList<String> generos = new ArrayList<>();
    
    // Gêneros válidos
    static final String[] GENEROS_VALIDOS = {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"};
    
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        adicionarMusicasTeste();
        
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
        
        System.out.println("\n🎵 Obrigado por usar o Sistema de Streaming! Até logo! 🎵");
        scanner.close();
    }
    
    /**
     * FORNECIDO: Exibe o menu principal
     */
    public static void exibirMenu() {
        System.out.println("\n==================================================");
        System.out.println("🎵 SISTEMA DE STREAMING DE MÚSICA 🎵");
        System.out.println("==================================================");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar todas as músicas");
        System.out.println("3. Buscar música por título");
        System.out.println("4. Buscar músicas por artista");
        System.out.println("5. Buscar músicas por gênero");
        System.out.println("6. Exibir estatísticas");
        System.out.println("0. Sair");
        System.out.println("==================================================");
        System.out.print("Escolha uma opção: ");
    }
    
    /**
     * FORNECIDO: Lê a opção do usuário com tratamento de erro
     */
    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * FORNECIDO: Processa a opção escolhida
     */
    public static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1: cadastrarMusica(); break;
            case 2: listarMusicas(); break;
            case 3: buscarPorTitulo(); break;
            case 4: buscarPorArtista(); break;
            case 5: buscarPorGenero(); break;
            case 6: exibirEstatisticas(); break;
            case 0: break;
            default: System.out.println("❌ Opção inválida! Tente novamente.");
        }
    }
    
    /**
     * FORNECIDO: Cadastra uma nova música
     */
    public static void cadastrarMusica() {
        System.out.println("\n--- CADASTRAR MÚSICA ---");
        
        System.out.print("Título: ");
        String titulo = scanner.nextLine().trim();
        
        if (titulo.isEmpty()) {
            System.out.println("❌ Título não pode ser vazio!");
            return;
        }
        
        System.out.print("Artista: ");
        String artista = scanner.nextLine().trim();
        
        if (artista.isEmpty()) {
            System.out.println("❌ Artista não pode ser vazio!");
            return;
        }
        
        System.out.print("Duração (em segundos): ");
        int duracao;
        try {
            duracao = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Duração inválida!");
            return;
        }
        
        if (duracao <= 0) {
            System.out.println("❌ Duração deve ser maior que 0!");
            return;
        }
        
        System.out.println("\nGêneros disponíveis:");
        for (int i = 0; i < GENEROS_VALIDOS.length; i++) {
            System.out.println((i + 1) + ". " + GENEROS_VALIDOS[i]);
        }
        
        System.out.print("Escolha o gênero (1-" + GENEROS_VALIDOS.length + "): ");
        int opcaoGenero;
        try {
            opcaoGenero = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Opção inválida!");
            return;
        }
        
        if (opcaoGenero < 1 || opcaoGenero > GENEROS_VALIDOS.length) {
            System.out.println("❌ Gênero inválido!");
            return;
        }
        
        String genero = GENEROS_VALIDOS[opcaoGenero - 1];
        
        titulos.add(titulo);
        artistas.add(artista);
        duracoes.add(duracao);
        generos.add(genero);
        
        System.out.println("✅ Música cadastrada com sucesso!");
    }
    
    /**
     * FORNECIDO: Lista todas as músicas
     */
    public static void listarMusicas() {
        System.out.println("\n--- MÚSICAS CADASTRADAS ---");
        
        if (titulos.isEmpty()) {
            System.out.println("Nenhuma música cadastrada ainda.");
            return;
        }
        
        for (int i = 0; i < titulos.size(); i++) {
            System.out.printf("%d. Título: %s | Artista: %s | Duração: %s | Gênero: %s%n",
                (i + 1),
                titulos.get(i),
                artistas.get(i),
                formatarDuracao(duracoes.get(i)),
                generos.get(i)
            );
        }
        
        System.out.println("\nTotal: " + titulos.size() + " música(s)");
    }
    
    /**
     * FORNECIDO: Busca músicas por título
     */
    public static void buscarPorTitulo() {
        System.out.println("\n--- BUSCAR POR TÍTULO ---");
        
        System.out.print("Digite o título (ou parte dele): ");
        String busca = scanner.nextLine().trim().toLowerCase();
        
        boolean encontrou = false;
        for (int i = 0; i < titulos.size(); i++) {
            if (titulos.get(i).toLowerCase().contains(busca)) {
                if (!encontrou) {
                    System.out.println("\nMúsicas encontradas:");
                    encontrou = true;
                }
                System.out.printf("- %s | %s | %s | %s%n",
                    titulos.get(i),
                    artistas.get(i),
                    formatarDuracao(duracoes.get(i)),
                    generos.get(i)
                );
            }
        }
        
        if (!encontrou) {
            System.out.println("❌ Nenhuma música encontrada com esse título.");
        }
    }
    
    /**
     * TODO: ALUNO IMPLEMENTA - Busca músicas por artista
     * Dica: Similar à busca por título
     */
    public static void buscarPorArtista() {
        System.out.println("\n--- BUSCAR POR ARTISTA ---");
        
        // TODO: Solicitar nome do artista
        System.out.print("Digite o nome do artista (ou parte dele): ");
        String busca = scanner.nextLine().trim().toLowerCase();
        
        boolean encontrou = false;
        
        // TODO: Percorrer ArrayList de artistas
        // TODO: Usar .toLowerCase() e .contains() para buscar
        for (int i = 0; i < artistas.size(); i++) {
            if (artistas.get(i).toLowerCase().contains(busca)) {
                if (!encontrou) {
                    // TODO: Exibir músicas encontradas
                    System.out.println("\nMúsicas encontradas:");
                    encontrou = true;
                }
                System.out.printf("- %s | %s | %s | %s%n",
                    titulos.get(i),
                    artistas.get(i),
                    formatarDuracao(duracoes.get(i)),
                    generos.get(i)
                );
            }
        }
        
        // TODO: Se não encontrou, exibir mensagem
        if (!encontrou) {
            System.out.println("❌ Nenhum artista encontrado com esse nome.");
        }
    }
    
        /**
     * TODO: ALUNO IMPLEMENTA - Busca músicas por gênero
     */
    public static void buscarPorGenero() {
        System.out.println("\n--- BUSCAR POR GÊNERO ---");
        
        // TODO: Exibir gêneros disponíveis
        System.out.println("Gêneros disponíveis:");
        for (int i = 0; i < GENEROS_VALIDOS.length; i++) {
            System.out.println((i + 1) + ". " + GENEROS_VALIDOS[i]);
        }
        
        // TODO: Solicitar escolha do gênero
        System.out.print("Escolha o gênero (1-" + GENEROS_VALIDOS.length + "): ");
        
        // TODO: Ler opção e validar
        int opcao;
        try {
            opcao = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Opção inválida!");
            return;
        }

        if (opcao < 1 || opcao > GENEROS_VALIDOS.length) {
            System.out.println("❌ Opção fora do intervalo!");
            return;
        }

        String generoEscolhido = GENEROS_VALIDOS[opcao - 1];
        boolean encontrou = false;

        // TODO: Buscar músicas do gênero escolhido
        // TODO: Exibir resultados
        for (int i = 0; i < generos.size(); i++) {
            if (generos.get(i).equals(generoEscolhido)) {
                if (!encontrou) {
                    System.out.println("\nMúsicas do gênero " + generoEscolhido + ":");
                    encontrou = true;
                }
                System.out.printf("- %s | %s | %s%n",
                    titulos.get(i),
                    artistas.get(i),
                    formatarDuracao(duracoes.get(i))
                );
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma música cadastrada no gênero " + generoEscolhido + ".");
        }
    }
    
    /**
     * TODO: ALUNO IMPLEMENTA - Exibe estatísticas do sistema
     */
    public static void exibirEstatisticas() {
        System.out.println("\n--- ESTATÍSTICAS DO SISTEMA ---");
        
        if (titulos.isEmpty()) {
            System.out.println("Nenhuma música cadastrada ainda.");
            return;
        }
        
        // TODO: Calcular total de músicas
        int totalMusicas = titulos.size();
        
        // TODO: Calcular duração total (somar todas as durações)
        int duracaoTotal = 0;
        // Dica: usar for-each para percorrer duracoes
        for (int d : duracoes) {
            duracaoTotal += d;
        }
        
        // TODO: Calcular duração média
        int duracaoMedia = duracaoTotal / totalMusicas;
        
        // TODO: Encontrar gênero mais comum (usar método auxiliar ou lógica direta)
        String generoMaisComum = "N/A";
        int maiorFrequencia = -1;

        for (String gValido : GENEROS_VALIDOS) {
            int contador = 0;
            for (String gMusica : generos) {
                if (gMusica.equals(gValido)) {
                    contador++;
                }
            }
            if (contador > maiorFrequencia) {
                maiorFrequencia = contador;
                generoMaisComum = gValido;
            }
        }
        
        // TODO: Exibir estatísticas
        System.out.println("Total de músicas: " + totalMusicas);
        System.out.println("Duração total do catálogo: " + formatarDuracao(duracaoTotal));
        System.out.println("Duração média das músicas: " + formatarDuracao(duracaoMedia));
        System.out.println("Gênero mais frequente: " + generoMaisComum + " (" + maiorFrequencia + " músicas)");
    }
    
    /**
     * FORNECIDO: Formata duração de segundos para MM:SS
     */
    public static String formatarDuracao(int segundos) {
        int minutos = segundos / 60;
        int segs = segundos % 60;
        return String.format("%d:%02d", minutos, segs);
    }
    
    /**
     * FORNECIDO: Adiciona músicas de teste
     */
    public static void adicionarMusicasTeste() {
        titulos.add("Bohemian Rhapsody");
        artistas.add("Queen");
        duracoes.add(354);
        generos.add("Rock");
        
        titulos.add("Billie Jean");
        artistas.add("Michael Jackson");
        duracoes.add(293);
        generos.add("Pop");
        
        titulos.add("Smells Like Teen Spirit");
        artistas.add("Nirvana");
        duracoes.add(301);
        generos.add("Rock");
    }
}