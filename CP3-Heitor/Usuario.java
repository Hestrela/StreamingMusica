import java.util.ArrayList;

public class Usuario {

    private String nome;
    private ArrayList<Playlist> playlists;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("O nome do usuário não deve ser nulo!");
        } else {
            String usuarioCorreto = nome.trim();
            if (usuarioCorreto.isEmpty()) {
                throw new IllegalArgumentException("O nome do usuário não deve ser vazio!");
            } else {
                this.nome = usuarioCorreto;
            }
        }
    }

    public Playlist getPlaylist(int indice) {
        if (indice < 0 ||indice >= playlists.size()) {
            throw new IllegalArgumentException("O índice não deve ser menor que zero ou maior que a quantidade de playlists!");
        } else {
            return playlists.get(indice);
        }
    }

    public void criarPlaylist(String nome) {
        Playlist novaPlaylist = new Playlist(nome);
        playlists.add(novaPlaylist);
        }

    public void listarPlaylists() {
        for (int indice = 0; indice < playlists.size(); indice++) {
            Playlist p = playlists.get(indice);
            System.out.printf("%d. Nome: %s | Quantidade de músicas: %d | Total de tempo: %d%n", (indice + 1), p.getNome(), p.getQuantidadeMusicas(), p.getDuracaoTotal());
        }
    }

    public Usuario() {
        this("Desconhecido");

    }

    public Usuario(String nome) {
        setNome(nome);
        this.playlists = new ArrayList<>();
    }

    




}