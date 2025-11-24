package controller;

import model.dao.InscricaoArq;
import model.dto.Inscricao;
import br.edu.fateczl.Lista;

public class InscricaoController {
    private InscricaoArq inscricaoArq;

    public InscricaoController() throws Exception{
        inscricaoArq = new InscricaoArq();
    }

    public void inserir(Inscricao i) throws Exception{
        inscricaoArq.inserir(i);
    }

    public void atualizar(Inscricao i) throws Exception{
        inscricaoArq.atualizar(i.getId(), i);
    }

    public void excluir(Integer id) throws Exception{
        inscricaoArq.excluir(id);
    }

    public Lista<Inscricao> listarTodos() throws Exception{
        return inscricaoArq.getAll();
    }

    public Inscricao buscarPorId(Integer id) throws Exception{
        return inscricaoArq.buscarPorID(id);
    }
}
