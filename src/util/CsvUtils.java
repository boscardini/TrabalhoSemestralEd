package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import br.edu.fateczl.Lista;

public class CsvUtils {

    public static Lista<String[]> lerArquivo(String caminhoArquivo) throws IOException {
        File arquivo = new File(caminhoArquivo);
        File dir = arquivo.getParentFile();

        if (dir != null && !dir.exists()) {
            dir.mkdirs();
        }

        if (!arquivo.exists()) {
            arquivo.createNewFile();
        }

        Lista<String[]> linhas = new Lista<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    linhas.addLast(linha.split(";"));
                }
            }
        } catch (Exception e) {
            throw new IOException("Erro ao ler arquivo CSV: " + e.getMessage());
        }

        return linhas;
    }

    public static void escreverCSV(String caminhoArquivo, Lista<String> linhas) throws IOException {
        File arquivo = new File(caminhoArquivo);
        File dir = arquivo.getParentFile();

        if (dir != null && !dir.exists()) {
            dir.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (int i = 0, size = linhas.size(); i < size; i++) {
                bw.write(linhas.get(i));
                bw.newLine();
            }
        } catch (Exception e) {
            throw new IOException("Erro ao escrever no arquivo CSV: " + e.getMessage());
        }
    }

    public static void adicionarLinhaCSV(String caminhoArquivo, String novaLinha) throws IOException {
        File arquivo = new File(caminhoArquivo);
        File dir = arquivo.getParentFile();

        if (dir != null && !dir.exists()) {
            dir.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {
            bw.write(novaLinha);
            bw.newLine();
        } catch (Exception e) {
            throw new IOException("Erro ao adicionar linha ao CSV: " + e.getMessage());
        }
    }
}
