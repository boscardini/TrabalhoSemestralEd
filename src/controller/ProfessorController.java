package controller;

import model.dao.ProfessorArq;
import model.dto.Professor;
import br.edu.fateczl.Lista;

public class ProfessorController {
    private ProfessorArq professorArq;

    public ProfessorController() throws Exception{
        professorArq = new ProfessorArq();
    }

    public void inserir(Professor p) throws Exception{
        professorArq.inserir(p);
    }

    public void atualizar(Professor p) throws Exception{
        professorArq.atualizar(p.getId(), p);
    }

    public void excluir(Integer id) throws Exception{
        professorArq.excluir(id);
    }

    public Lista<Professor> listarTodos() throws Exception{
        return professorArq.getAll();
    }

    public Professor buscarPorId(Integer id) throws Exception{
        return professorArq.buscarPorID(id);
    }

    public Professor buscarPorCPF(String cpf) throws Exception{
        return professorArq.buscarPorCPF(cpf);
    }
    public int getNextId() throws Exception {
        Lista<Professor> lista = professorArq.getAll();
        int max = 0;
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getId();
            if (id > max) max = id;
        }
        return max + 1;
    }

}
