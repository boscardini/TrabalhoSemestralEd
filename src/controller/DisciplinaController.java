package controller;

import model.dao.DisciplinaArq;
import model.dao.InscricaoArq;
import model.dto.Disciplina;
import model.dto.Inscricao;
import br.edu.fateczl.Lista;

public class DisciplinaController {
    private DisciplinaArq disciplinaArq;
    private InscricaoArq inscricaoArq;

    public DisciplinaController() throws Exception {
        disciplinaArq = new DisciplinaArq();
        inscricaoArq = new InscricaoArq();
    }

    public void inserir(Disciplina d) throws Exception {
        disciplinaArq.inserir(d);
    }

    public void atualizar(Disciplina d) throws Exception {
        disciplinaArq.atualizar(d.getId(), d);
    }

    public void excluir(Integer idDisciplina) throws Exception {
        disciplinaArq.excluir(idDisciplina);

        Lista<Inscricao> inscricoes = inscricaoArq.getAll();

        for (int i = 0; i < inscricoes.size(); i++) {
            Inscricao ins = inscricoes.get(i);

            if (ins.getId().equals(idDisciplina)) {
                inscricaoArq.excluir(ins.getId());
            }
        }
    }

    public Lista<Disciplina> listarTodas() throws Exception {
        return disciplinaArq.getAll();
    }

    public Disciplina buscarPorId(Integer id) throws Exception {
        return disciplinaArq.buscarPorID(id);
    }
}
