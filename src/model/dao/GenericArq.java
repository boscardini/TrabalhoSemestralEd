package model.dao;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import br.edu.fateczl.Fila;
import br.edu.fateczl.Lista;
import model.dto.IEntity;
import util.CsvUtils;

public abstract class GenericArq<T extends IEntity, ID>{

    private String caminhoArquivo;
    String fileName = null;


    public GenericArq(String nomeArquivoCSV) throws IOException{
        this.caminhoArquivo = obterCaminhoArquivo(nomeArquivoCSV);
    }

    private static String obterCaminhoArquivo(String nomeArquivoCSV) throws IOException  {
        String os = System.getProperty("os.name").toLowerCase();
        String baseDir = os.contains("win") ? "C:\\TEMP\\" : "/tmp/";

        Path diretorio = Paths.get(baseDir);
        try {
            Files.createDirectories(diretorio);
        } catch (IOException e) {
			throw new IOException(e.getMessage());

        }
        
        return diretorio.resolve(nomeArquivoCSV).toString();
    }

    
    
    public void atualizar(Integer id, T entidadeAtualizada) throws Exception {
        Fila<T> entidades = buscarTodos();
        Lista<String> entidadesCSV = new Lista<>();

        while (!entidades.isEmpty()) {
            T entidade = entidades.remove();
            if (entidade.getId().equals(entidadeAtualizada.getId())) {
                entidade = entidadeAtualizada;
            }
            entidadesCSV.addLast(entityToCSV(entidade));
        }

        CsvUtils.escreverCSV(caminhoArquivo, entidadesCSV);
    }

    
    public void inserir(T entidade) throws IOException {
        try {
          
            Lista<String[]> linhas = CsvUtils.lerArquivo(caminhoArquivo);
            Lista<T> entidades = new Lista<>();

           
            for (int i = 0; i < linhas.size(); i++) {
                T e = csvToEntity(linhas.get(i));
                entidades.addLast(e);
            }

           
            int novoId = getEntityId(entidade);
            boolean inserido = false;

            Lista<T> novaLista = new Lista<>();

            for (int i = 0; i < entidades.size(); i++) {
                T atual = entidades.get(i);
                int idAtual = getEntityId(atual);

                
                if (!inserido && novoId < idAtual) {
                    novaLista.addLast(entidade);
                    inserido = true;
                }
                novaLista.addLast(atual);
            }

            if (!inserido) {
                novaLista.addLast(entidade);
            }

            
            Lista<String> linhasCSV = new Lista<>();
            for (int i = 0; i < novaLista.size(); i++) {
                linhasCSV.addLast(entityToCSV(novaLista.get(i)));
            }

            CsvUtils.escreverCSV(caminhoArquivo, linhasCSV);

        } catch (Exception e) {
            throw new IOException("Erro ao inserir: " + e.getMessage());
        }
    }

    
    private int getEntityId(T entidade) throws Exception {
        try {
            var metodo = entidade.getClass().getMethod("getId");
            Object valor = metodo.invoke(entidade);
            return Integer.parseInt(valor.toString());
        } catch (Exception e) {
            throw new Exception("Erro ao acessar o ID da entidade: " + e.getMessage());
        }
    }


    public Lista<T> getAll() throws IOException {
        Lista<T> lista = new Lista<>();

       
        Lista<String[]> linhas = CsvUtils.lerArquivo(caminhoArquivo);

        for (int i = 0; i < linhas.size(); i++) {
            String[] dados = null;
			try {
				dados = linhas.get(i);
			} catch (Exception e) {
				e.printStackTrace();
			}    
            if (dados != null && dados.length > 0) {
                try {
                    T entidade = csvToEntity(dados);
                    if (entidade != null) {
                        lista.addLast(entidade);
                    }
                } catch (Exception e) {
                    
                    e.printStackTrace();
                }
            }
        }

        return lista;
    }


    public void salvar(Lista<T> lista) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo))) {
            for (int i = 0; i < lista.size(); i++) {
                writer.println(entityToCSV(lista.get(i)));
            }
        } catch (Exception e) {
        	throw new IOException("Falha ao salvar/criar arquivo CSV: " + e.getMessage(), e);      
        	}
    }

    
    public Fila<T> buscarTodos() throws Exception {
        Lista<T> lista = getAll();
        Fila<T> fila = new Fila<>();
        for (int i = 0; i < lista.size(); i++) {
            fila.insert(lista.get(i));
        }
        return fila;
    }

    public T buscarPorID(Integer id) throws Exception {
        Fila<T> entidades = buscarTodos();
        while (!entidades.isEmpty()) {
            T entidade = entidades.remove();
            if (entidade.getId().equals(id)) {
                return entidade;
            }
        }
        throw new Exception("Entidade não encontrada");
    }

    public void excluir(Integer id) throws Exception {
        Lista<T> lista = getAll();
        for (int i = 0; i < lista.size(); i++) {
            T entidade = lista.get(i);
            if (entidade.getId().equals(id)) {
                lista.remove(i);
                break;
            }
        }
        salvar(lista);
    }

    
    protected abstract String entityToCSV(T entidade);
    protected abstract T csvToEntity(String[] dados);
    
}