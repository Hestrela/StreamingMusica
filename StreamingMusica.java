import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    static ArrayList<String> titulos = new ArrayList<>();
    static ArrayList<String> artistas = new ArrayList<>();
    static ArrayList<Integer> duracoes = new ArrayList<>();
    static ArrayList<String> generos = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);
    static String[] generosValidos = {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"};

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);

        System.out.println("\n🎵 Até logo! 🎵");
        scanner.close();
    }

    public static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE STREAMING DE MÚSICA ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar todas as músicas");
        System.out.println("3. Buscar música por título");
        System.out.println("4. Buscar músicas por artista");
        System.out.println("5. Buscar músicas por gênero");
        System.out.println("6. Exibir estatísticas");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarMusica();
                break;
            case 2:
                listarMusicas();
                break;
            case 3:
                buscarPorTitulo();
                break;
            case 4:
                buscarPorArtista();
                break;
            case 5:
                buscarPorGenero();
                break;
            case 6:
                exibirEstatisticas();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção Inválida");
        }
    }

    public static void cadastrarMusica() {
        System.out.println("\n--- CADASTRAR MÚSICA ---");

        System.out.print("Título: ");
        String titulo = scanner.nextLine().trim();
        if (titulo.isEmpty()) {
            System.out.println("Erro: O título não pode ser vazio!");
            return;
        }

        System.out.print("Artista: ");
        String artista = scanner.nextLine().trim();
        if (artista.isEmpty()) {
            System.out.println("Erro: O artista não pode ser vazio!");
            return;
        }

        System.out.print("Duração (segundos): ");
        int duracao = lerOpcao();
        if (duracao <= 0) {
            System.out.println("Erro: A duração deve ser maior que 0!");
            return;
        }

        System.out.print("Gênero (Pop, Rock, Jazz, Eletrônica, Hip-Hop, Clássica): ");
        String genero = scanner.nextLine().trim();
        String generoFormatado = validarGenero(genero);
        if (generoFormatado == null) {
            System.out.println("Erro: Gênero inválido!");
            return;
        }

        titulos.add(titulo);
        artistas.add(artista);
        duracoes.add(duracao);
        generos.add(generoFormatado);

        System.out.println("Música cadastrada com sucesso!");
    }

    public static String validarGenero(String g) {
        for (int i = 0; i < generosValidos.length; i++) {
            if (generosValidos[i].equalsIgnoreCase(g)) {
                return generosValidos[i];
            }
        }
        return null;
    }

    public static void listarMusicas() {
        System.out.println("\n=== MÚSICAS CADASTRADAS ===");

        if (titulos.isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }

        for (int i = 0; i < titulos.size(); i++) {
            imprimirLinhaMusica(i);
        }
        System.out.println("Total: " + titulos.size() + " música(s)");
    }

    public static void buscarPorTitulo() {
        System.out.println("\n--- BUSCAR POR TÍTULO ---");
        System.out.print("Digite o título: ");
        String busca = scanner.nextLine().toLowerCase();

        boolean encontrou = false;
        for (int i = 0; i < titulos.size(); i++) {
            if (titulos.get(i).toLowerCase().contains(busca)) {
                imprimirLinhaMusica(i);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma música encontrada com esse título.");
        }
    }

    public static void buscarPorArtista() {
        System.out.println("\n--- BUSCAR POR ARTISTA ---");
        System.out.print("Digite o artista: ");
        String busca = scanner.nextLine().toLowerCase();

        boolean encontrou = false;
        for (int i = 0; i < artistas.size(); i++) {
            if (artistas.get(i).toLowerCase().contains(busca)) {
                imprimirLinhaMusica(i);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma música encontrada para esse artista.");
        }
    }

    public static void buscarPorGenero() {
        System.out.println("\n--- BUSCAR POR GÊNERO ---");
        System.out.print("Gêneros disponíveis: ");
        for (int i = 0; i < generosValidos.length; i++) {
            System.out.print(generosValidos[i] + (i < generosValidos.length - 1 ? ", " : "\n"));
        }
        System.out.print("Digite o gênero: ");
        String busca = scanner.nextLine().trim();

        boolean encontrou = false;
        for (int i = 0; i < generos.size(); i++) {
            if (generos.get(i).equalsIgnoreCase(busca)) {
                imprimirLinhaMusica(i);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma música encontrada para esse gênero.");
        }
    }

    public static void exibirEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS DO SISTEMA ===");
        int total = titulos.size();
        System.out.println("Total de músicas: " + total);

        if (total == 0) {
            return;
        }

        int somaDuracao = 0;
        for (int i = 0; i < duracoes.size(); i++) {
            somaDuracao += duracoes.get(i);
        }
        System.out.println("Duração total: " + formatarDuracao(somaDuracao));
        System.out.println("Duração média: " + formatarDuracao(somaDuracao / total));

        int[] contadores = new int[generosValidos.length];

        for (int i = 0; i < generos.size(); i++) {
            String generoDaMusica = generos.get(i);
            
            for (int j = 0; j < generosValidos.length; j++) {
                if (generoDaMusica.equalsIgnoreCase(generosValidos[j])) {
                    contadores[j]++;
                    break;
                }
            }
        }

        int maximo = 0;
        String generoMaisCadastrado = "";
        
        for (int i = 0; i < contadores.length; i++) {
            if (contadores[i] > maximo) {
                maximo = contadores[i];
                generoMaisCadastrado = generosValidos[i];
            }
        }

        if (maximo > 0) {
            System.out.println("Gênero mais cadastrado: " + generoMaisCadastrado + " (" + maximo + " músicas)");
        }
    }

    public static void imprimirLinhaMusica(int index) {
        System.out.println((index + 1) + ". Título: " + titulos.get(index) +
                " | Artista: " + artistas.get(index) +
                " | Duração: " + formatarDuracao(duracoes.get(index)) +
                " | Gênero: " + generos.get(index));
    }

    public static String formatarDuracao(int segundos) {
        int min = segundos / 60;
        int seg = segundos % 60;
        return String.format("%d:%02d", min, seg);
    }
}