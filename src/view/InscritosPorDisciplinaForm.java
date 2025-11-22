package view;

import javax.swing.*;
import java.awt.*;

import controller.InscricaoController;
import controller.ProfessorController;
import controller.DisciplinaController;

import model.dto.Inscricao;
import model.dto.Professor;
import model.dto.Disciplina;
import model.dto.InscritoExibicao;

import br.edu.fateczl.Lista;
import util.SortUtils;

public class InscritosPorDisciplinaForm extends JDialog {

    private JComboBox<Disciplina> cbDisciplinas;
    private JButton btnConsultar;

    private InscricaoController inscricaoController;
    private ProfessorController professorController;
    private DisciplinaController disciplinaController;

    public InscritosPorDisciplinaForm(Window owner) throws Exception {
        super(owner, "Inscritos por Disciplina", ModalityType.APPLICATION_MODAL);

       
        inscricaoController = new InscricaoController();
        professorController = new ProfessorController();
        disciplinaController = new DisciplinaController();

        initComponents();
        pack();
        setLocationRelativeTo(owner);
    }

    private void initComponents() throws Exception {
        setLayout(new BorderLayout());
        cbDisciplinas = new JComboBox<>();

        Lista<Disciplina> disciplinas = disciplinaController.listarTodas();
        for (int i = 0; i < disciplinas.size(); i++)
            cbDisciplinas.addItem(disciplinas.get(i));

        add(cbDisciplinas, BorderLayout.NORTH);

        btnConsultar = new JButton("Consultar Inscritos");
        btnConsultar.addActionListener(e -> onConsultar());
        add(btnConsultar, BorderLayout.SOUTH);
    }

    private void onConsultar() {
        try {
            Disciplina disciplinaSelecionada = (Disciplina) cbDisciplinas.getSelectedItem();
            if (disciplinaSelecionada == null) {
                JOptionPane.showMessageDialog(this, "Selecione uma disciplina.");
                return;
            }

            Lista<Inscricao> inscricoes = inscricaoController.listarTodos();
            Lista<InscritoExibicao> exibicao = new Lista<>();

            for (int i = 0; i < inscricoes.size(); i++) {

                Inscricao ins = inscricoes.get(i);

                
                if (!ins.getIdProcesso().equals(disciplinaSelecionada.getId())) {
                    continue;
                }

                
                Professor prof = professorController.buscarPorId(ins.getIdProfessor());

                InscritoExibicao ie = new InscritoExibicao(
                        ins.getId(),
                        prof.getCpf(),
                        prof.getNome(),
                        prof.getQtdPontos(),
                        prof.getAreaConhecimento(),
                        disciplinaSelecionada.getNome(),
                        ins.getIdProcesso()
                );

                exibicao.addLast(ie);
            }

            
            Lista<InscritoExibicao> ordenada = SortUtils.mergeSort(exibicao);
            mostrarTabela(ordenada);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void mostrarTabela(Lista<InscritoExibicao> lista) throws Exception {
        String[] cols = {
                "ID", "CPF", "Nome", "Área", "Pontos", "Disciplina", "ID Processo"
        };

        Object[][] data = new Object[lista.size()][cols.length];

        for (int i = 0; i < lista.size(); i++) {
            InscritoExibicao ie = lista.get(i);

            data[i][0] = ie.getId();
            data[i][1] = ie.getCpf();
            data[i][2] = ie.getNome();
            data[i][3] = ie.getArea();
            data[i][4] = ie.getQtdPontos();
            data[i][5] = ie.getDisciplina();
            data[i][6] = ie.getIdProcesso();
        }

        JTable table = new JTable(data, cols);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(850, 350));

        JDialog dlg = new JDialog(this, "Inscritos ", true);
        dlg.add(scroll);
        dlg.pack();
        dlg.setLocationRelativeTo(this);
        dlg.setVisible(true);
    }
}
