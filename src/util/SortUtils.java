package util;

import br.edu.fateczl.Lista;
import model.dto.InscritoExibicao;

public class SortUtils {

    public static Lista<InscritoExibicao> mergeSort(Lista<InscritoExibicao> lista) throws Exception {
        if (lista.size() <= 1) return lista;
        int mid = lista.size() / 2;
        Lista<InscritoExibicao> left = new Lista<>();
        Lista<InscritoExibicao> right = new Lista<>();
        for (int i = 0; i < mid; i++) left.addLast(lista.get(i));
        for (int i = mid; i < lista.size(); i++) right.addLast(lista.get(i));
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    }

    private static Lista<InscritoExibicao> merge(Lista<InscritoExibicao> a, Lista<InscritoExibicao> b) throws Exception{
        Lista<InscritoExibicao> result = new Lista<>();
        int i = 0, j = 0;
        while (i < a.size() && j < b.size()){
            InscritoExibicao ai = a.get(i);
            InscritoExibicao bj = b.get(j);
            
            if (ai.getQtdPontos() >= bj.getQtdPontos()){
                result.addLast(ai);
                i++;
            } else {
                result.addLast(bj);
                j++;
            }
        }
        while (i < a.size()) { result.addLast(a.get(i)); i++; }
        while (j < b.size()) { result.addLast(b.get(j)); j++; }
        return result;
    }
}
