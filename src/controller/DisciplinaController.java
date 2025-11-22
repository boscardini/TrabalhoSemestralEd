package controller;

import model.dao.DisciplinaArq;
import model.dao.ProcessoArq;
import model.dao.InscricaoArq;
import model.dto.Disciplina;
import model.dto.Processo;
import model.dto.Inscricao;
import br.edu.fateczl.Lista;

public class DisciplinaController {
    private DisciplinaArq disciplinaArq;
    private ProcessoArq processoArq;
    private InscricaoArq inscricaoArq;

    public DisciplinaController() throws Exception {
        disciplinaArq = new DisciplinaArq();
        processoArq = new ProcessoArq();
        inscricaoArq = new InscricaoArq();
    }

    public void inserir(Disciplina d) throws Exception{
        disciplinaArq.inserir(d);
    }

    public void atualizar(Disciplina d) throws Exception{
        disciplinaArq.atualizar(d.getId(), d);
    }

    public void excluir(Integer idDisciplina) throws Exception{
        // 1) remover disciplina
        disciplinaArq.excluir(idDisciplina);

        // 2) buscar processos vinculados a essa disciplina e remover suas inscrições
        Lista<Processo> processos = processoArq.getAll();
        for (int i = 0; i < processos.size(); i++){
            Processo p = processos.get(i);
            if (p.getIdDisciplina().equals(idDisciplina)){
                // remover inscrições deste processo
                Lista<Inscricao> inscricoes = inscricaoArq.getAll();
                for (int j = 0; j < inscricoes.size(); j++){
                    Inscricao ins = inscricoes.get(j);
                    if (ins.getIdProcesso().equals(p.getId())){
                        inscricaoArq.excluir(ins.getId());
                        // após excluir, re-carregamos na próxima iteração naturalmente
                    }
                }
                // remover processo
                processoArq.excluir(p.getId());
            }
        }
    }

    public Lista<Disciplina> listarTodas() throws Exception{
        return disciplinaArq.getAll();
    }

    public Disciplina buscarPorId(Integer id) throws Exception{
        return disciplinaArq.buscarPorID(id);
    }
}
