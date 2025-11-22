package view;

import controller.ProfessorController;
import model.dto.Professor;

import javax.swing.*;
import java.awt.*;
import br.edu.fateczl.Lista;

public class ProfessorForm extends JDialog {

    private JTextField  txtId,txtCpf, txtNome, txtPontos, txtIdArea;
    private JButton btnSalvar, btnAtualizar, btnRemover, btnConsultar;
    private ProfessorController controller;

    public ProfessorForm(Window owner) throws Exception {
        super(owner, "CRUD Professores");
        controller = new ProfessorController();
        initComponents();
        pack();
        setLocationRelativeTo(owner);
    }

    private void initComponents() {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        txtId = new JTextField(15);
        panel.add(txtId, gbc);

        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("CPF:"), gbc);
        gbc.gridx = 1;
        txtCpf = new JTextField(15);
        panel.add(txtCpf, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        txtNome = new JTextField(15);
        panel.add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Pontos:"), gbc);
        gbc.gridx = 1;
        txtPontos = new JTextField(15);
        panel.add(txtPontos, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Area de Conhecimento:"), gbc);
        gbc.gridx = 1;
        txtIdArea = new JTextField(15);
        panel.add(txtIdArea, gbc);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> onSalvar());
        botoes.add(btnSalvar);

        btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> onAtualizar());
        botoes.add(btnAtualizar);

        btnRemover = new JButton("Remover");
        btnRemover.addActionListener(e -> onRemover());
        botoes.add(btnRemover);

        btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(e -> onConsultar());
        botoes.add(btnConsultar);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(e -> limparCampos());
        botoes.add(btnLimpar);

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(botoes, gbc);

        add(panel);
    }


    private void onSalvar() {
        try {
            Professor p = new Professor(
                    Integer.parseInt(txtId.getText()),
                    txtCpf.getText(),
                    txtNome.getText(),
                    Float.parseFloat(txtPontos.getText()),
                    txtIdArea.getText()
            );
            controller.inserir(p);
            JOptionPane.showMessageDialog(this, "Professor salvo");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void onAtualizar() {
        try {
            Professor p = new Professor(
            		 Integer.parseInt(txtId.getText()),
                    txtCpf.getText(),
                    txtNome.getText(),
                    Float.parseFloat(txtPontos.getText()),
                    txtIdArea.getText()
            );
            controller.atualizar(p);
            JOptionPane.showMessageDialog(this, "Professor atualizado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void onRemover() {
        try {
            int cpf = Integer.parseInt(txtCpf.getText());
            controller.excluir(cpf);
            JOptionPane.showMessageDialog(this, "Professor removido");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void onConsultar() {
        try {
            Lista<Professor> lista = controller.listarTodos();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < lista.size(); i++) {
                Professor p = lista.get(i);
                sb.append(String.format(" %s (%s) - Pontos: %.2f\n",
                         p.getId(),p.getNome(), p.getCpf(), p.getQtdPontos()));
            }

            JTextArea ta = new JTextArea(sb.toString());
            ta.setEditable(false);

            JScrollPane sp = new JScrollPane(ta);
            sp.setPreferredSize(new Dimension(450, 300));

            JOptionPane.showMessageDialog(this, sp, "Lista de Professores", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void limparCampos() {
    	txtId.setText("");
        txtCpf.setText("");
        txtNome.setText("");
        txtPontos.setText("");
        txtIdArea.setText("");

    }
}
