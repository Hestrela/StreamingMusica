import java.util.ArrayList;

public class Usuario {

    private String nome;
    private ArrayList<Playlist> playlists;

    public Usuario(String nome) {
        this.nome = nome;
        this.playlists = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void adicionarPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }
}