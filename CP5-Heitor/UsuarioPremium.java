import java.util.ArrayList;

// Classe que representa o Usuário tipo premium, com os métodos padrões da classe Usuario + extras
public final class UsuarioPremium extends Usuario {
    private String plano; // Mensal, Anual, Familiar
    private ArrayList<Musica> musicasBaixadas;

    // Construtor parametrizado
    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        this.plano = plano;
        this.musicasBaixadas = new ArrayList<>();
    }
    
    @Override
    public void reproduzirMusica(Musica musica) {
        System.out.println("🎵 Reproduzindo em ALTA QUALIDADE: " + musica.getTitulo());
        historicoReproducao.add(musica);
    }
    
    public void baixarMusica(Musica musica) {
        // Se o array de músicas baixadas não tiver a música desejada, adiciona ela no array
        if (!musicasBaixadas.contains(musica)) {
            musicasBaixadas.add(musica);
            System.out.println("⬇️ Música baixada: " + musica.getTitulo());
        } else {
            System.out.println("ℹ️ Música já está baixada!");
        }
    }
    
    public void listarMusicasBaixadas() {
        System.out.println("\n--- MÚSICAS BAIXADAS ---");
        if (musicasBaixadas.isEmpty()) {
            System.out.println("Nenhuma música baixada.");
            return;
        }
        
        for (Musica m : musicasBaixadas) {
            m.exibir();
        }
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public ArrayList<Musica> getMusicasBaixadas() {
        return musicasBaixadas;
    }

    public void setMusicasBaixadas(ArrayList<Musica> musicasBaixadas) {
        this.musicasBaixadas = musicasBaixadas;
    }
}