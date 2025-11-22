package controller;

import model.dao.CursoArq;
import model.dto.Curso;
import br.edu.fateczl.Lista;

public class CursoController {
    private CursoArq cursoArq;

    public CursoController() throws Exception{
        cursoArq = new CursoArq();
    }

    public void inserir(Curso c) throws Exception{
        cursoArq.inserir(c);
    }

    public void atualizar(Curso c) throws Exception{
        cursoArq.atualizar(c.getId(), c);
    }

    public void excluir(Integer id) throws Exception{
        cursoArq.excluir(id);
    }

    public Lista<Curso> listarTodos() throws Exception{
        return cursoArq.getAll();
    }

    public Curso buscarPorId(Integer id) throws Exception{
        return cursoArq.buscarPorID(id);
    }
}
