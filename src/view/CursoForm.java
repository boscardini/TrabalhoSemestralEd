package view;

import controller.CursoController;
import model.dto.Curso;

import javax.swing.*;
import java.awt.*;
import br.edu.fateczl.Lista;

public class CursoForm extends JDialog {

    private JTextField txtId, txtNome, txtArea;
    private JButton btnSalvar, btnAtualizar, btnRemover, btnConsultar, btnLimpar;
    private CursoController controller;

    public CursoForm(Window owner) throws Exception {
        super(owner, "Cursos");
        controller = new CursoController();
        initComponents();
        pack();
        setLocationRelativeTo(owner);
    }

    private void initComponents() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("ID:"), gbc);

        gbc.gridx = 1;
        txtId = new JTextField(15);
        add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        txtNome = new JTextField(15);
        add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Área de Conhecimento:"), gbc);

        gbc.gridx = 1;
        txtArea = new JTextField(15);
        add(txtArea, gbc);

        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> { onSalvar(); limparCampos(); });
        panelBotoes.add(btnSalvar);

        btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> { onAtualizar(); limparCampos(); });
        panelBotoes.add(btnAtualizar);

        btnRemover = new JButton("Remover");
        btnRemover.addActionListener(e -> { onRemover(); limparCampos(); });
        panelBotoes.add(btnRemover);

        btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(e -> onConsultar());
        panelBotoes.add(btnConsultar);

        btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(e -> limparCampos());
        panelBotoes.add(btnLimpar);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(panelBotoes, gbc);
    }


    private void onSalvar() {
        try {
            Curso c = new Curso(
                    Integer.parseInt(txtId.getText()),
                    txtNome.getText(),
                    txtArea.getText()
            );

            controller.inserir(c);
            JOptionPane.showMessageDialog(this, "Curso salvo");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void onAtualizar() {
        try {
            Curso c = new Curso(
                    Integer.parseInt(txtId.getText()),
                    txtNome.getText(),
                    txtArea.getText()
            );

            controller.atualizar(c);
            JOptionPane.showMessageDialog(this, "Curso atualizado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void onRemover() {
        try {
            int id = Integer.parseInt(txtId.getText());
            controller.excluir(id);
            JOptionPane.showMessageDialog(this, "Curso removido");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void onConsultar() {
        try {
            Lista<Curso> lista = controller.listarTodos();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < lista.size(); i++) {
                Curso c = lista.get(i);
                sb.append(String.format(
                        "ID: %d - %s (Área: %s)\n",
                        c.getId(),
                        c.getNome(),
                        c.getAreaConhecimento()
                ));
            }

            JTextArea ta = new JTextArea(sb.toString());
            ta.setEditable(false);

            JScrollPane sp = new JScrollPane(ta);
            sp.setPreferredSize(new Dimension(500, 300));

            JOptionPane.showMessageDialog(this, sp,
                    "Lista de Cursos", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }


    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtArea.setText("");
        txtId.requestFocus();
    }
}
