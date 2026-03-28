public class Musica {

    private String titulo;
    private String artista;
    private int duracaoSegundos;
    private String genero;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null) {
            System.out.println("Título não deve ser nulo");      
        } else {
            String tituloCorreto = titulo.trim();
            if (tituloCorreto.isEmpty()) {
                System.out.println("Título não deve ser vazio");
            } else {
                this.titulo = tituloCorreto;
            }
        }
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        if (artista == null) {
            System.out.println("Artista não deve ser nulo");      
        } else {
            String artistaCorreto = artista.trim();
            if (artistaCorreto.isEmpty()) {
                System.out.println("Artista não deve ser vazio");
            } else {
                this.artista = artistaCorreto;
            }
        }
    }

    public int getDuracao() {
        return duracaoSegundos;
    }

    public void setDuracao(int duracao) {
        this.duracaoSegundos = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Musica() {
        this.titulo = "Desconhecido";
        this.artista = "Desconhecido";
        this.duracaoSegundos = 1;
        this.genero = "Pop";
    }

    public Musica(String titulo, String artista) {
        this(titulo, artista, 1, "Pop");
    }


    public Musica(String titulo, String artista, int duracao, String genero) {
        setTitulo(titulo);
        setArtista(artista);
        setDuracao(duracao);
        setGenero(genero);
    }

    

    

}