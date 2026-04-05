import java.util.ArrayList;

public class Playlist {

    private String nome;
    private ArrayList<Musica> musicas;

    public String getNome() { return nome; }

    public void setNome(String nome) {
        if (nome == null) {
            System.out.println("O nome da playlist não deve ser nulo!");
        } else {
            String nomeCorreto = nome.trim();
            if (nomeCorreto.isEmpty()) {
                System.out.println("O nome não deve ser vazio!");
            } else {
                this.nome = nomeCorreto;
            }
        }
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void adicionarMusicas(Musica musica) {
        if (musica == null) {
            System.out.println("A música não deve ser nula!");
        } else {
            musicas.add(musica);
        }
    }

    public void removerMusica(int indice) {
        if (indice >= musicas.size() || indice < 0) {
            System.out.println("O índice não deve ser negativo ou maior que o tamanho da lista de músicas");
        } else {       
                musicas.remove(indice);         
        }
    }

    public Playlist() {
        this.nome = "Desconhecido";
    }

    public Playlist(String nome) {
        setNome(nome);
        musicas = new ArrayList<>();
    }

}