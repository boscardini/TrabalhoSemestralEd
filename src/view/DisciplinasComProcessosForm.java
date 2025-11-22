package view;

import javax.swing.*;
import java.awt.*;
import controller.DisciplinaController;
import controller.InscricaoController;
import model.dto.Disciplina;
import model.dto.Inscricao;
import br.edu.fateczl.Lista;
import util.HashTable;

public class DisciplinasComProcessosForm extends JDialog {

    private JTable table;
    private DisciplinaController disciplinaController;
    private InscricaoController inscricaoController;
    
    public DisciplinasComProcessosForm(JFrame parent) throws Exception {
        super(parent, "Disciplinas com Processos Abertos", true);

        disciplinaController = new DisciplinaController();
        inscricaoController = new InscricaoController();

        initComponents();
        
        pack();                    
        setLocationRelativeTo(parent);
        setVisible(true);           
    }

    private void initComponents() throws Exception {

        Lista<Disciplina> disciplinas = disciplinaController.listarTodas();
        Lista<Inscricao> inscricoes = inscricaoController.listarTodos();

        HashTable ht = new HashTable(11);

        for (int i = 0; i < inscricoes.size(); i++) {
            Inscricao ins = inscricoes.get(i);

            Integer idDisciplina = ins.getId();

            Disciplina d = disciplinaController.buscarPorId(idDisciplina);

            ht.put(idDisciplina, d);
        }

        Lista<Disciplina>[] buckets = ht.getAllBuckets();
        java.util.List<Disciplina> flattened = new java.util.ArrayList<>();

        for (int i = 0; i < buckets.length; i++) {
            Lista<Disciplina> bucket = buckets[i];
            for (int j = 0; j < bucket.size(); j++)
                flattened.add(bucket.get(j));
        }

        String[] cols = {"ID", "Nome", "Curso"};
        Object[][] data = new Object[flattened.size()][cols.length];

        for (int i = 0; i < flattened.size(); i++) {
            Disciplina d = flattened.get(i);
            data[i][0] = d.getId();
            data[i][1] = d.getNome();
            data[i][2] = d.getIdCurso();
        }

        table = new JTable(data, cols);
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(700, 300));
        
        add(sp, BorderLayout.CENTER);   
    }
}
