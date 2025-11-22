package view;

import javax.swing.*;
import java.awt.*;
import controller.DisciplinaController;
import model.dto.Disciplina;
import br.edu.fateczl.Lista;

public class DisciplinaForm extends JDialog {

    private JTextField txtId, txtNome, txtDia, txtHora, txtQtdHoras, txtIdCurso;
    private JButton btnSalvar, btnAtualizar, btnRemover, btnConsultar, btnLimpar;
    private DisciplinaController controller;

    public DisciplinaForm(Window owner) throws Exception {
        super(owner, "CRUD Disciplinas");
        controller = new DisciplinaController();
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
        add(new JLabel("Dia:"), gbc);

        gbc.gridx = 1;
        txtDia = new JTextField(15);
        add(txtDia, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Hora:"), gbc);

        gbc.gridx = 1;
        txtHora = new JTextField(15);
        add(txtHora, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Qtd Horas:"), gbc);

        gbc.gridx = 1;
        txtQtdHoras = new JTextField(15);
        add(txtQtdHoras, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        add(new JLabel("ID do Curso:"), gbc);

        gbc.gridx = 1;
        txtIdCurso = new JTextField(15);
        add(txtIdCurso, gbc);


        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

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
        gbc.gridy = 6; 
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(panelBotoes, gbc);
    }

   
    private void onSalvar() {
        try {
            Disciplina d = new Disciplina(
                    Integer.parseInt(txtId.getText()),
                    txtNome.getText(),
                    txtDia.getText(),
                    txtHora.getText(),
                    Double.parseDouble(txtQtdHoras.getText()),
                    Integer.parseInt(txtIdCurso.getText())
            );

            controller.inserir(d);
            JOptionPane.showMessageDialog(this, "Salvo com sucesso");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void onAtualizar() {
        try {
            Disciplina d = new Disciplina(
                    Integer.parseInt(txtId.getText()),
                    txtNome.getText(),
                    txtDia.getText(),
                    txtHora.getText(),
                    Double.parseDouble(txtQtdHoras.getText()),
                    Integer.parseInt(txtIdCurso.getText())
            );

            controller.atualizar(d);
            JOptionPane.showMessageDialog(this, "Atualizado com sucesso");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void onRemover() {
        try {
            Integer id = Integer.parseInt(txtId.getText());

            int confirma = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente excluir a disciplina e todos os dados relacionados?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirma == JOptionPane.YES_OPTION) {
                controller.excluir(id);
                JOptionPane.showMessageDialog(this, "Removido com sucesso");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void onConsultar() {
        try {
            Lista<Disciplina> lista = controller.listarTodas();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < lista.size(); i++) {
                Disciplina d = lista.get(i);

                sb.append(String.format(
                        "ID: %d - %s (Curso: %d)\n",
                        d.getId(),
                        d.getNome(),
                        d.getIdCurso()
                ));
            }

            JTextArea ta = new JTextArea(sb.toString());
            ta.setEditable(false);

            JScrollPane sp = new JScrollPane(ta);
            sp.setPreferredSize(new Dimension(500, 300));

            JOptionPane.showMessageDialog(
                    this, sp, "Lista de Disciplinas", JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }


    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtDia.setText("");
        txtHora.setText("");
        txtQtdHoras.setText("");
        txtIdCurso.setText("");

        txtId.requestFocus();
    }
}
