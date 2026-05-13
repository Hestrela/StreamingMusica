package br.com.streaming.modelo
// Classe que representa uma música e sua regras de validação
public final class Musica {

    private String titulo;
    private String artista;
    private int duracaoSegundos;
    private String genero;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        // Valida se o titulo é nulo
        if (titulo == null) {
            throw new IllegalArgumentException("Título não deve ser nulo");      
        } else {
            // Valida se o título é vazio
            String tituloCorreto = titulo.trim();
            if (tituloCorreto.isEmpty()) {
                throw new IllegalArgumentException("Título não deve ser vazio");
            } else {
                this.titulo = tituloCorreto;
            }
        }
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        // Valida se o nome do artista é nulo
        if (artista == null) {
            throw new IllegalArgumentException("Artista não deve ser nulo");      
        } else {
            // Valida se o nome do artista é vazio 
            String artistaCorreto = artista.trim();
            if (artistaCorreto.isEmpty()) {
                throw new IllegalArgumentException("Artista não deve ser vazio");
            } else {
                this.artista = artistaCorreto;
            }
        }
    }

    public int getDuracao() {
        return duracaoSegundos;
    }

    public void setDuracao(int duracao) {
        // Valida se a duração da música é menor que zero ou tem 1 hora ou mais
        if (duracao <= 0 || duracao >= 3600 ) {
            throw new IllegalArgumentException("Musica com duração inválida, deve ser maior que 0 e menor que 3600 segundos");
        } else {
            this.duracaoSegundos = duracao;
        }
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        String[] generosMusicais = {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"};
        // flag de controle
        boolean generoEncontrado = false;

        // Valida se o genero da música tem valor nulo
        if (genero == null) {
            throw new IllegalArgumentException("Genero não pode ser nulo");
        } else {
            // Percorre a lista para achar o genero dentro da lista generosMusicais
            for(int contador = 0; contador < generosMusicais.length; contador++) {
                String generoCorreto = genero.toLowerCase();
                // Ignora a capitalização do texto para facilitar a busca
                if (generosMusicais[contador].equalsIgnoreCase(genero)) {
                    this.genero = generoCorreto;
                    generoEncontrado = true;
                    break;
                }
            }
            // Caso o genero não exista na lista, a flag continua falsa e uma excessão é criada
            if (generoEncontrado == false) {
                throw new IllegalArgumentException("Genero inválido");
            }
        }
        
    }

    public void exibir() {
        System.out.printf("Título: %s | Artista: %s | Duração: %d | Gênero: %s%n", getTitulo(), getArtista(), getDuracao(), getGenero());
    }

    public Musica() {
        // Construtor padrão
        this.titulo = "Desconhecido";
        this.artista = "Desconhecido";
        this.duracaoSegundos = 1;
        this.genero = "Pop";
    }

    public Musica(String titulo, String artista) {
        // Construtor completo com valores padrão
        this(titulo, artista, 1, "Pop");
    }


    public Musica(String titulo, String artista, int duracao, String genero) {
        // Construtor parametrizado
        setTitulo(titulo);
        setArtista(artista);
        setDuracao(duracao);
        setGenero(genero);
    }

    

    

}