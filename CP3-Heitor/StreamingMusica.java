import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    static ArrayList<Musica> acervo = new ArrayList<>(); 
    static Usuario usuarioAtual = new Usuario("Aluno"); 
    static Scanner scanner = new Scanner(System.in);
    static final String[] GENEROS_VALIDOS = {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"};

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

    static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE STREAMING DE MÚSICA ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar todas as músicas");
        System.out.println("3. Buscar música");
        System.out.println("4. Editar música (NOVO)"); // Requisito do CP3
        System.out.println("5. Criar playlist");
        System.out.println("6. Gerenciar playlists");
        System.out.println("7. Exibir estatísticas");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1: cadastrarMusica(); break;
            case 2: listarMusicas(acervo, "MÚSICAS CADASTRADAS NO ACERVO"); break;
            case 3: menuBuscarMusica(); break;
            case 4: editarMusica(); break;
            case 5: criarPlaylist(); break;
            case 6: gerenciarPlaylists(); break;
            case 7: exibirEstatisticas(); break;
            case 0: break;
            default: System.out.println("Opção Inválida");
        }
    }

    static void cadastrarMusica() {
        try {
            System.out.println("\n--- CADASTRAR MÚSICA ---");

            System.out.print("Título: ");
            String titulo = scanner.nextLine().trim();

            System.out.print("Artista: ");
            String artista = scanner.nextLine().trim();
            

            System.out.print("Duração (segundos): ");
            int duracao = lerOpcao();
            

            System.out.print("Gênero (Pop, Rock, Jazz, Eletrônica, Hip-Hop, Clássica): ");
            String genero = scanner.nextLine().trim();

            Musica novaMusica = new Musica(titulo, artista, duracao, genero);
            acervo.add(novaMusica);

            System.out.println("Música cadastrada com sucesso!");

        } catch (IllegalArgumentException erro) {
            System.out.println("\nErro ao cadastrar: " + erro.getMessage());
            System.out.println("Por favor, tente cadastrar novamente.");
        }
    }

    static void editarMusica() {
        System.out.println("\n--- EDITAR MÚSICA ---");
        if (acervo.isEmpty()) {
            System.out.println("O acervo está vazio.");
            return;
        }

        listarMusicas(acervo, "ESCOLHA A MÚSICA PARA EDITAR");
        System.out.print("Digite o número da música: ");
        int indice = lerOpcao() - 1;

        if (indice < 0 || indice >= acervo.size()) {
            System.out.println("Música não encontrada!");
            return;
        }

        Musica m = acervo.get(indice);
        
        System.out.println("Editando: " + m.getTitulo() + " - " + m.getArtista());
        System.out.println("1. Editar Título");
        System.out.println("2. Editar Artista");
        System.out.println("3. Editar Duração");
        System.out.println("4. Editar Gênero");
        System.out.print("O que deseja editar? ");
        int op = lerOpcao();

        try {
            switch(op) {
                case 1:
                    System.out.print("Novo título: ");
                    m.setTitulo(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Novo artista: ");
                    m.setArtista(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Nova duração (segundos): ");
                    m.setDuracao(lerOpcao());
                    break;
                case 4:
                    System.out.print("Novo gênero: ");
                    m.setGenero(scanner.nextLine());
                    break;
                default:
                    System.out.println("Opção inválida.");
                    return;
            }
            System.out.println("Música atualizada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao editar: " + e.getMessage());
        }
    }

    static void listarMusicas(ArrayList<Musica> lista, String tituloCabecalho) {
        System.out.println("\n=== " + tituloCabecalho + " ===");

        if (lista.isEmpty()) {
            System.out.println("Nenhuma música encontrada.");
            return;
        }

        for (int indice = 0; indice < lista.size(); indice++) {
             System.out.printf("%d. Nome: %s | Artista: %s | Duração: %d segundos | Gênero: %s%n"
                , (indice + 1), lista.get(indice).getTitulo(), lista.get(indice).getArtista(), 
                lista.get(indice).getDuracao(), lista.get(indice).getGenero()
            );
        }
        System.out.println("Total: " + lista.size() + " música(s)");
    }

    static void menuBuscarMusica() {
        System.out.println("\n--- BUSCAR MÚSICA ---");
        System.out.println("1. Por Título");
        System.out.println("2. Por Artista");
        System.out.println("3. Por Gênero");
        System.out.print("Escolha: ");
        
        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1:
                buscar("titulo");
                break;
            case 2:
                buscar("artista");
                break;
            case 3:
                buscar("genero");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    static void buscar(String tipo) {
        System.out.print("Digite o termo de busca: ");
        String termo = scanner.nextLine().toLowerCase();
        
        ArrayList<Musica> resultados = new ArrayList<>();
        
        for (int i = 0; i < acervo.size(); i++) {
            Musica m = acervo.get(i);
            boolean achou = false;
            
            if (tipo.equals("titulo") && m.getTitulo().toLowerCase().contains(termo)) {
                achou = true;
            } else if (tipo.equals("artista") && m.getArtista().toLowerCase().contains(termo)) {
                achou = true;
            } else if (tipo.equals("genero") && m.getGenero().toLowerCase().contains(termo)) {
                achou = true;
            }
            
            if (achou) {
                resultados.add(m);
            }
        }
        
        listarMusicas(resultados, "RESULTADOS DA BUSCA");
    }

    static void criarPlaylist() {
        System.out.println("\n--- CRIAR PLAYLIST ---");
        System.out.print("Nome da playlist: ");
        String nome = scanner.nextLine();
        
        try {
            usuarioAtual.criarPlaylist(nome);
            System.out.println("Playlist '" + nome + "' criada com sucesso!");
        } catch (IllegalArgumentException erro) {
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    static void gerenciarPlaylists() {
        int opcao;
        do {
            System.out.println("\n=== GERENCIAR PLAYLISTS ===");
            System.out.println("1. Listar minhas playlists");
            System.out.println("2. Adicionar música a uma playlist");
            System.out.println("3. Remover música de uma playlist");
            System.out.println("4. Exibir detalhes de uma playlist");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            
            opcao = lerOpcao();
            
            switch (opcao) {
                case 1: 
                    System.out.println("\n--- MINHAS PLAYLISTS ---");
                    usuarioAtual.listarPlaylists(); 
                    break;
                case 2: adicionarMusicaPlaylist(); break;
                case 3: removerMusicaPlaylist(); break;
                case 4: exibirDetalhesPlaylist(); break;
                case 0: break;
                default: System.out.println("Opção Inválida");
            }
        } while (opcao != 0);
    }

    

    static Playlist selecionarPlaylist() {
        System.out.println("\n--- MINHAS PLAYLISTS ---");
        usuarioAtual.listarPlaylists();
        System.out.print("Escolha o número da playlist: ");
        int indice = lerOpcao() - 1;
        
        try {
            return usuarioAtual.getPlaylist(indice);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    static void adicionarMusicaPlaylist() {
        Playlist p = selecionarPlaylist();
        if (p == null) return;
        
        listarMusicas(acervo, "ACERVO DISPONÍVEL");
        if (acervo.isEmpty()) return;
        
        System.out.print("Escolha o número da música para adicionar: ");
        int indiceMusica = lerOpcao() - 1;
        
        if (indiceMusica >= 0 && indiceMusica < acervo.size()) {
            try {
                p.adicionarMusica(acervo.get(indiceMusica));
                System.out.println("Música adicionada à playlist '" + p.getNome() + "'!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Música não encontrada.");
        }
    }

    static void removerMusicaPlaylist() {
        Playlist p = selecionarPlaylist();
        if (p == null) return;
        
        System.out.println("\n=== MÚSICAS DA PLAYLIST: " + p.getNome() + " ===");
        p.listarMusicas();
        
        System.out.print("Escolha o número da música para remover: ");
        int indiceMusica = lerOpcao() - 1;
        
        try {
            p.removerMusica(indiceMusica);
            System.out.println("Música removida com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    static void exibirDetalhesPlaylist() {
        Playlist p = selecionarPlaylist();
        if (p == null) return;
        
        System.out.println("\n=== DETALHES DA PLAYLIST: " + p.getNome().toUpperCase() + " ===");
        p.listarMusicas();
        
        int totalSegundos = p.getDuracaoTotal();
        int min = totalSegundos / 60;
        int seg = totalSegundos % 60;
        System.out.println("\nDuração total: " + String.format("%d:%02d", min, seg));
        System.out.println("Quantidade de músicas: " + p.getQuantidadeMusicas());
    }

    static void exibirEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS DO SISTEMA ===");
        int total = acervo.size();
        System.out.println("Total de músicas no acervo: " + total);

        if (total == 0) return;

        int somaDuracao = 0;
        for (int i = 0; i < acervo.size(); i++) {
            somaDuracao += acervo.get(i).getDuracao();
        }
        
        int minTotal = somaDuracao / 60;
        int segTotal = somaDuracao % 60;
        System.out.println("Duração total do acervo: " + String.format("%d:%02d", minTotal, segTotal));

        int[] contadores = new int[GENEROS_VALIDOS.length];

        for (int i = 0; i < acervo.size(); i++) {
            String generoDaMusica = acervo.get(i).getGenero();
            for (int j = 0; j < GENEROS_VALIDOS.length; j++) {
                if (generoDaMusica.equalsIgnoreCase(GENEROS_VALIDOS[j])) {
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
                generoMaisCadastrado = GENEROS_VALIDOS[i];
            }
        }

        if (maximo > 0) {
            System.out.println("Gênero mais cadastrado: " + generoMaisCadastrado + " (" + maximo + " músicas)");
        }
    }
}