import java.util.ArrayList;
import java.util.Random;

public class PlaylistAutomatica extends Playlist{
    private String criterio;

    public PlaylistAutomatica(String nome,String criterio) {
        super(nome);
        setCriterio(criterio);
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    @Override
    public void reproduzir() {
        System.out.println("🤖 Playlist Automática: " + nome);
        System.out.println("📊 Critério: " + criterio);
        super.reproduzir();
    }

    public void atualizar(ArrayList<Musica> todasMusicas, ArrayList<Musica> historicoUsuario) {
        musicas.clear();
        
        if (criterio.equals("top")) {
            // Arrays auxiliares para funcionar como nossa "tabela" de contagem
            ArrayList<Musica> musicasUnicas = new ArrayList<>();
            ArrayList<Integer> contagens = new ArrayList<>();

            // 1. Percorre o histórico e conta as ocorrências
            for (Musica m : historicoUsuario) {
                int indice = musicasUnicas.indexOf(m);
                
                if (indice == -1) {
                    // Música apareceu pela primeira vez
                    musicasUnicas.add(m);
                    contagens.add(1);
                } else {
                    // Música já existe, apenas incrementamos o contador
                    int contagemAtual = contagens.get(indice);
                    contagens.set(indice, contagemAtual + 1);
                }
            }

            // 2. Encontra a música com o maior número na lista de contagens
            int maxReproducoes = 0;
            Musica maisTocada = null;
            
            for (int i = 0; i < musicasUnicas.size(); i++) {
                if (contagens.get(i) > maxReproducoes) {
                    maxReproducoes = contagens.get(i);
                    maisTocada = musicasUnicas.get(i);
                }
            }
            
            // 3. Adiciona a música campeã na playlist automática
            if (maisTocada != null) {
                musicas.add(maisTocada);
            }
            
        } else if (criterio.equals("recomendadas")) {
            // 1. Verificamos se tem músicas no acervo para evitar erros
            if (!todasMusicas.isEmpty()) {
                
               
                Random sorteador = new java.util.Random();
                
                // 3. Vamos sortear 5 músicas para a playlist recomendada, por exemplo
                for (int i = 0; i < 5; i++) {
                    
                    // Sorteia um número entre 0 e o limite máximo do acervo
                    int indiceSorteado = sorteador.nextInt(todasMusicas.size());
                    
                    // Pesca a música correspondente a esse número
                    Musica recomendada = todasMusicas.get(indiceSorteado);
                    
                    // Adiciona a música pescada na nossa playlist
                    musicas.add(recomendada);
                }
            }
        }
    }
}
